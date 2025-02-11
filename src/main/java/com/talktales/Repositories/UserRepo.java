package com.talktales.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.talktales.Entities.User;

public interface UserRepo extends JpaRepository<User, Integer>  {
Optional<User> findByEmail(String email);
	
}
