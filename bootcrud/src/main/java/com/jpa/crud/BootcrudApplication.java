package com.jpa.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.jpa.crud.dao.UserRepository;
import com.jpa.crud.entities.User;

@SpringBootApplication
public class BootcrudApplication {

	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(BootcrudApplication.class, args);
		User utilis= new User(2,"David","Lome","REFUSE");
		UserRepository ur= context.getBean(UserRepository.class);
		
		//single saving user
//		User u =ur.save(utilis);
//		System.out.println("User: "+u);
		
		// multi saving user
//		User u1 = new User(3,"DJASSAH","Agoe","ACCEPT");
//		User u2 = new User(4,"Jean","Agoe","ACCEPT");
//		List<User> users = List.of(u1,u2);
//		Iterable<User> users1 = ur.saveAll(users);
//		users1.forEach(user -> {
//			System.out.println("user: "+user);
//		});
		
		//update the user of id 1
//		Optional<User> optional	= ur.findById(1);
//		User user = optional.get();
//		user.setName("De'Indja");
//		User res = ur.save(user);		
//		System.out.println("user find: "+res);
		
		//how to get data
		//findById
//		Iterable<User> itr= ur.findAll();
//		itr.forEach(user -> {
//			System.out.println("user "+user);
//		});
		
		//delete data
//		ur.deleteById(4);
//		System.out.println("User was deleted");
		
		//find by name
//		Iterable<User> itr =ur.findByName("Jean");
//		itr.forEach(userit -> {
//			System.out.println("user: "+userit);
//		});
		
		//jql alluser
//		Iterable<User> itr =ur.getAllUser();
//		itr.forEach(userit -> {
//			System.out.println("user: "+userit);
//		});
		
		//jql name user
//		Iterable<User> itr =ur.getUserByName("Jean");
//		itr.forEach(userit -> {
//			System.out.println("user: "+userit);
//		});
		
		//native query
		Iterable<User> itr =ur.getUsers();
		itr.forEach(userit -> {
			System.out.println("user: "+userit);
		});
	}

}
