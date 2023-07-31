package com.SpringMongo.jobListing.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringMongo.jobListing.Model.Post;
import com.SpringMongo.jobListing.Repository.PostRepo;
import com.SpringMongo.jobListing.Repository.SearchRepo;

import springfox.documentation.annotations.ApiIgnore;

@RestController
public class PostController {
	
	@Autowired
	PostRepo repo;
	
	@Autowired
	SearchRepo srepo;
	
	@ApiIgnore
	@RequestMapping(value="/")
	public void redirect(HttpServletResponse response) throws IOException {
		response.sendRedirect("/swagger-ui.html");
	}
	
	@GetMapping("/posts")
	public List<Post> getAllPosts(){
		return repo.findAll(); 
	}
	
	@PostMapping("/post")
	public Post addPost(@RequestBody Post post) {
		return repo.save(post);
	}
	
	@GetMapping("/posts/{text}")
	public List<Post> search(@PathVariable String text){
		return srepo.ListfindByText(text);
	}
}
