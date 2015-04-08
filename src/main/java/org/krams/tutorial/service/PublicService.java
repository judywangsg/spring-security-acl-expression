package org.krams.tutorial.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.krams.tutorial.domain.PublicPost;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service("publicService")
public class PublicService {

	private Map<String, PublicPost> publicPosts = new HashMap<String, PublicPost>();
	
	public PublicService() {
		// Initiliaze our in-memory HashMap list
		init();
	}

	// filterObject refers to the current object in the collection
	@PostFilter("hasPermission(filterObject, 'READ')")
	public List<PublicPost> getAll() {
		// Iterate our HashMap list and convert it to an ArrayList
		List<PublicPost> publicList = new ArrayList<PublicPost>();
		for (String key: publicPosts.keySet()) {
			publicList.add(publicPosts.get(key));
		}
		// Return our new list
		return publicList;
	}
	
	@PreAuthorize("hasPermission(#post, 'WRITE')")
	public Boolean add(PublicPost post)  {
		// This will return true if it's accessible
		return true;
	}
	
	@PreAuthorize("hasPermission(#post, 'WRITE')")
	public Boolean edit(PublicPost post)  {
		// This will return true if it's accessible
		return true;
	}

	@PreAuthorize("hasPermission(#post, 'WRITE')")
	public Boolean delete(PublicPost post)  {
		// This will return true if it's accessible
		return true;
	}
	
	// Initiliazes an in-memory HashMap list
	private void init() {
		// Create new post
		PublicPost post1 = new PublicPost();
		post1.setId(UUID.randomUUID().toString());
		post1.setDate(new Date());
		post1.setMessage("This is public's post #1");
		
		// Create new post
		PublicPost post2 = new PublicPost();
		post2.setId(UUID.randomUUID().toString());
		post2.setDate(new Date());
		post2.setMessage("This is public's post #2");
		
		// Create new post
		PublicPost post3 = new PublicPost();
		post3.setId(UUID.randomUUID().toString());
		post3.setDate(new Date());
		post3.setMessage("This is public's post #3");
		
		// Add to publicPosts
		publicPosts.put(post1.getId(), post1);
		publicPosts.put(post2.getId(), post2);
		publicPosts.put(post3.getId(), post3);
	}
}
