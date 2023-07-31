package com.SpringMongo.jobListing.Repository;

import java.util.List;

import com.SpringMongo.jobListing.Model.Post;

public interface SearchRepo {
	
	List<Post> ListfindByText(String text);
	
}
