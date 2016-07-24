package edu.asu.ows.secure;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

public class UserManager {
	private List<User> users = new ArrayList<User>();
	
	public User getUser(String name) {
		for (User user : this.getUsers()) {
			if (user.getName().equals(name)){
				return user;
			}
		}
		return null;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
