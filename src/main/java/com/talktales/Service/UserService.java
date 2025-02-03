package com.talktales.Service;

import java.util.List;

import com.talktales.DTO.UserDTO;

public interface UserService {
	
	UserDTO createUser(UserDTO user);
	UserDTO updateUser(UserDTO user, Integer userid);
	UserDTO getUserById( Integer userid);
	List<UserDTO>getAllUser();
	void deleteUser(Integer userid);
	

}
