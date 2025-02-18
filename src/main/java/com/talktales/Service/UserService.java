package com.talktales.Service;

import java.util.List;

import com.talktales.DTO.UserDTO;
import com.talktales.Entities.User;

public interface UserService {
	
	UserDTO createUser(UserDTO user);
	UserDTO updateUser(UserDTO user, Integer userid);
	UserDTO getUserById( Integer userid);
	UserDTO  getUserByEmail(String username);
	List<UserDTO>getAllUser();
	void deleteUser(Integer userid);

	

}
