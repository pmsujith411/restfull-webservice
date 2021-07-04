package com.rest.webservice.restfullwebservice.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.rest.webservice.restfullwebservice.dto.User;

@Component
public class UserDao {
	private static List<User> userList = new ArrayList<>();

	static {
		userList.add(new User(1, "abc", new Date()));
		userList.add(new User(2, "bcd", new Date()));
		userList.add(new User(3, "cde", new Date()));
		userList.add(new User(4, "def", new Date()));
	}

	public List<User> findAll() {
		return userList;
	}

	public User save(final User user) {
		userList.add(user);

		return user;
	}

	public User findOne(int id) {
		Optional<User> optionalUser = userList.stream().filter(user -> user.getId() == id).findFirst();

		if (optionalUser.isEmpty()) {
			return null;
		}

		return optionalUser.get();
	}
	
	public User deleteById(int id)
	{
		Optional<User> optionalUser = userList.stream().filter(user -> user.getId() == id).findFirst();

		if (optionalUser.isPresent()) {
			userList.remove(id);
			
			return optionalUser.get();
		}

		return null;
	}
}
