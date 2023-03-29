package com.jpa.crud.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jpa.crud.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	public List<User> findByName(String name);
	
	@Query("select u from User u" )
	public List<User> getAllUser();
	
	//JQL
	@Query("select u From User u where u.name=:n")
	public List<User> getUserByName(@Param("n")String name);
	
	//native query
	@Query(value = "select * from User",nativeQuery =true)
	public List<User> getUsers();
}
