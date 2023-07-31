package com.SpringMongo.jobListing.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.SpringMongo.jobListing.Model.Post;

public interface PostRepo extends MongoRepository<Post, String>{

}
