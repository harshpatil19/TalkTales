package com.talktales.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.talktales.DTO.UserDTO;
import com.talktales.Entities.User;
import com.talktales.Repositories.UserRepo;
import com.talktales.Service.UserService;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public UserDTO createUser(UserDTO userDto) {
		User user= this.dtoToUser(userDto);
		User savedUser= this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO user, Integer userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getUserById(Integer userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Integer userid) {
		// TODO Auto-generated method stub
		
	}
	private User dtoToUser(UserDTO userDTO) {
		
		User user= new User();
		user.setUserid(userDTO.getUserid());
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setAbout(userDTO.getAbout());
		return user;
	}
	
	public UserDTO userToDto(User user ) {
		
		UserDTO userdto=new UserDTO();
		userdto.setUserid(user.getUserid());
		userdto.setName(user.getName());
		userdto.setEmail(user.getEmail());
		userdto.setPassword(user.getPassword());
		userdto.setAbout(user.getAbout());
		
		return userdto;
		
	}

}
