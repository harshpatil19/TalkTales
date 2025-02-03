package com.talktales.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.talktales.Entities.User;

public interface UserRepo extends CrudRepository<User, Integer>  {

}
