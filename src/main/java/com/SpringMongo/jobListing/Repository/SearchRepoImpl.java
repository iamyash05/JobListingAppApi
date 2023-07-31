package com.SpringMongo.jobListing.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import com.SpringMongo.jobListing.Model.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class SearchRepoImpl implements SearchRepo {
	
	@Autowired
	MongoClient client;
	
	@Autowired
	MongoConverter convert;
	
	@Override
	public List<Post> ListfindByText(String text) {
		final List<Post> posts = new ArrayList<>();
		
		MongoDatabase database = client.getDatabase("SpringBasic");
		MongoCollection<Document> collection = database.getCollection("JobPost");
		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
				new Document("text", 
						new Document("query", text)
						.append("path", Arrays.asList("techs", "desc", "profile")))), 
				new Document("$sort", 
						new Document("exp", -1L)), 
				new Document("$limit", 1L)));
		
		result.forEach(doc -> posts.add(convert.read(Post.class, doc)));
		
		return posts;
	}

}
