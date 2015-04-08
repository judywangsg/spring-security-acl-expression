package org.krams.tutorial.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.annotation.Resource;

/**
 * A custom PermissionEvaluator implementation that uses a Map to
 * check whether a domain Object and access level exists for a particular user. 
 * This also uses RoleHiearchy to retrieve the highest role possible for the user.
 */
public class CustomPermissionEvaluator implements PermissionEvaluator {
        
	protected static Logger logger = Logger.getLogger("security");

	@Resource(name="permissionsMap")
	private Map permissionsMap;
	
	@Resource(name="roleHierarchy")
	private RoleHierarchy roleHierarchy;
	
	/**
	 * Evaluates whether the user has permission by delegating to 
	 * hasPermission(String role, Object permission, Object domain)
	 */
	public boolean hasPermission(Authentication authentication,
			Object targetDomainObject, Object permission) {
		logger.debug("Evaluating expression using hasPermission signature #1");
		
		logger.debug("Retrieving user's highest role");
		String role = getRole(authentication);

		logger.debug("****************");
		logger.debug("role: " + role);
		logger.debug("targetDomainObject: " + targetDomainObject);
		logger.debug("permission: " + permission);
		logger.debug("****************");
	
		// Check the type of object
		logger.debug("User is trying to access the object: " + targetDomainObject);

		logger.debug("Check if user has permission");
		// Delegate to another hasPermission signature
		return hasPermission(role, permission, targetDomainObject);
	}

	/**
	 * Another hasPermission signature. We will not implement this.
	 */
	public boolean hasPermission(Authentication authentication,
			Serializable targetId, String targetType, Object permission) {
		logger.debug("Evaluating expression using hasPermission signature #2");

		return false;
	}

	/**
	 * Retrieves the user's highest role
	 */
	private String getRole(Authentication authentication) {
		String highestRole = null;
		
		try {
			Collection<GrantedAuthority> auths = roleHierarchy.getReachableGrantedAuthorities(authentication.getAuthorities());
			for (GrantedAuthority auth: auths) {
				highestRole = auth.getAuthority();
				break;
			}
			logger.debug("Highest role hiearchy: " + roleHierarchy.getReachableGrantedAuthorities(authentication.getAuthorities()));
			
		} catch (Exception e) {
			logger.debug("No authorities assigned");
		}
		
		return highestRole;
	}
	
	/**
	 * Evaluates whether the user has permission
	 */
	private Boolean hasPermission(String role, Object permission, Object domain) {
		logger.debug("Check if role exists: " + role);
		if ( permissionsMap.containsKey(role) ) {
			logger.debug("Role exists: " + role);
			
			// Retrieve userPermission object
			Permission userPermission = (Permission) permissionsMap.get(role);
			
			// Check if domain exists in Map
			logger.debug("Check if domain exists: " + domain.getClass().getName());
			if ( userPermission.getObjects().containsKey(domain.getClass().getName())){
				logger.debug("Domain exists: " + domain.getClass().getName());

				// Loop the internal list and see if the class' full name matches
				logger.debug("Check if permission exists: " + permission);
				for (String action: userPermission.getObjects().get(domain.getClass().getName()) ) {
					if (action.equals(permission)) {
						logger.debug("Permission exists: " + action);
						logger.debug("Permission Granted!");
						return true;
					}
				}
			}
		}
		
		// By default, do not give permission
		logger.debug("Permission Denied!");
		return false;
	}
}
