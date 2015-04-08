package org.krams.tutorial.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.krams.tutorial.domain.AdminPost;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminService {

	private Map<String, AdminPost> adminPosts = new HashMap<String, AdminPost>();
	
	public AdminService() {
		// Initiliaze our in-memory HashMap list
		init();
	}

	// filterObject refers to the current object in the collection
	@PostFilter("hasPermission(filterObject, 'READ')")
	public List<AdminPost> getAll() {
		// Iterate our HashMap list and convert it to an ArrayList
		List<AdminPost> adminList = new ArrayList<AdminPost>();
		for (String key: adminPosts.keySet()) {
			adminList.add(adminPosts.get(key));
		}
		// Return our new list
		return adminList;
	}
	
	@PreAuthorize("hasPermission(#post, 'WRITE')")
	public Boolean add(AdminPost post)  {
		// This will return true if it's accessible
		return true;
	}
	
	@PreAuthorize("hasPermission(#post, 'WRITE')")
	public Boolean edit(AdminPost post)  {
		// This will return true if it's accessible
		return true;
	}

	@PreAuthorize("hasPermission(#post, 'WRITE')")
	public Boolean delete(AdminPost post)  {
		// This will return true if it's accessible
		return true;
	}
	
	// Initiliazes an in-memory HashMap list
	private void init() {
		// Create new post
		AdminPost post1 = new AdminPost();
		post1.setId(UUID.randomUUID().toString());
		post1.setDate(new Date());
		post1.setMessage("This is admin's post #1");
		
		// Create new post
		AdminPost post2 = new AdminPost();
		post2.setId(UUID.randomUUID().toString());
		post2.setDate(new Date());
		post2.setMessage("This is admin's post #2");
		
		// Create new post
		AdminPost post3 = new AdminPost();
		post3.setId(UUID.randomUUID().toString());
		post3.setDate(new Date());
		post3.setMessage("This is admin's post #3");
		
		// Add to adminPosts
		adminPosts.put(post1.getId(), post1);
		adminPosts.put(post2.getId(), post2);
		adminPosts.put(post3.getId(), post3);
	}
}
