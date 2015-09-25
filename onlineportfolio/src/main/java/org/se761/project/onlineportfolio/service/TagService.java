package org.se761.project.onlineportfolio.service;

import java.util.List;

import org.se761.project.onlineportfolio.database.TagDatabase;
import org.se761.project.onlineportfolio.model.Tag;

public class TagService {
	
	private TagDatabase tagDatabase = new TagDatabase();
	
	/**
	 * Get all tags from the database
	 */
	public List<Tag> getAllTags(){
		return tagDatabase.getAllTags();
	}
	
	/**
	 * Add tag to the database
	 */
	public Tag addTag(Tag tag){
		return tagDatabase.addTag(tag);
	}
	
}
