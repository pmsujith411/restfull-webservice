package com.rest.webservice.restfullwebservice.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.webservice.restfullwebservice.dto.User;
import com.rest.webservice.restfullwebservice.exception.UserNotFoundException;
import com.rest.webservice.restfullwebservice.repository.UseRepository;
import com.rest.webservice.restfullwebservice.repository.UserDao;

@RestController
public class UserController 
{
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UseRepository useRepository;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers()
	{
		return useRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id)
	{
		Optional<User> optionalUser = useRepository.findById(id);
		
		if(!optionalUser.isPresent())
		{
			throw new UserNotFoundException("Id - " + id);
		}
		
		EntityModel<User> entityModel = EntityModel.of(optionalUser.get());
		
		WebMvcLinkBuilder mvcLinkBuilder = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		entityModel.add(mvcLinkBuilder.withRel("all-users"));
		
		return entityModel;
	}
	
	@DeleteMapping("/users/{id}")
	public User deleteUser(@PathVariable int id)
	{
		User user = userDao.deleteById(id);
		
		if(user == null)
		{
			throw new UserNotFoundException("Id - " + id);
		}
			
		return userDao.findOne(id);
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user)
	{
		User savedUser = useRepository.save(user);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
