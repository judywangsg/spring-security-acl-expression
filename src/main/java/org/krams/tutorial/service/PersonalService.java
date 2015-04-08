package org.krams.tutorial.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.krams.tutorial.domain.PersonalPost;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service("personalService")
public class PersonalService {

	private Map<String, PersonalPost> personalPosts = new HashMap<String, PersonalPost>();
	
	public PersonalService() {
		// Initiliaze our in-memory HashMap list
		init();
	}

	// filterObject refers to the current object in the collection
	@PostFilter("hasPermission(filterObject, 'READ')")
	public List<PersonalPost> getAll() {
		// Iterate our HashMap list and convert it to an ArrayList
		List<PersonalPost> personalList = new ArrayList<PersonalPost>();
		for (String key: personalPosts.keySet()) {
			personalList.add(personalPosts.get(key));
		}
		// Return our new list
		return personalList;
	}
	
	@PreAuthorize("hasPermission(#post, 'WRITE')")
	public Boolean add(PersonalPost post)  {
		// This will return true if it's accessible
		return true;
	}
	
	@PreAuthorize("hasPermission(#post, 'WRITE')")
	public Boolean edit(PersonalPost post)  {
		// This will return true if it's accessible
		return true;
	}

	@PreAuthorize("hasPermission(#post, 'WRITE')")
	public Boolean delete(PersonalPost post)  {
		// This will return true if it's accessible
		return true;
	}
	
	// Initiliazes an in-memory HashMap list
	private void init() {
		// Create new post
		PersonalPost post1 = new PersonalPost();
		post1.setId(UUID.randomUUID().toString());
		post1.setDate(new Date());
		post1.setMessage("This is personal's post #1");
		
		// Create new post
		PersonalPost post2 = new PersonalPost();
		post2.setId(UUID.randomUUID().toString());
		post2.setDate(new Date());
		post2.setMessage("This is personal's post #2");
		
		// Create new post
		PersonalPost post3 = new PersonalPost();
		post3.setId(UUID.randomUUID().toString());
		post3.setDate(new Date());
		post3.setMessage("This is personal's post #3");
		
		// Add to personalPosts
		personalPosts.put(post1.getId(), post1);
		personalPosts.put(post2.getId(), post2);
		personalPosts.put(post3.getId(), post3);
	}
}
