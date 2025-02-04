package com.talktales.ServiceImpl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talktales.DTO.UserDTO;
import com.talktales.Entities.User;
import com.talktales.Exception.ResourceNotFound;
import com.talktales.Repositories.UserRepo;
import com.talktales.Service.UserService;

@Service
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
	public UserDTO updateUser(UserDTO userDto, Integer userid) {
		User user = this.userRepo.findById(userid)  .orElseThrow(() -> new ResourceNotFound("User","Id",userid));

		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updateUser= this.userRepo.save(user);
		UserDTO userToDto1=this.userToDto(updateUser);
		
		return userToDto1 ;
		}

	@Override
	public UserDTO getUserById(Integer userid) {
		User user=this.userRepo.findById(userid).orElseThrow(() -> new ResourceNotFound("User","Id",userid));
		return this.userToDto(user);
	}

	@Override
	public List<UserDTO> getAllUser() {
List<User>users=this.userRepo.findAll();
List<UserDTO> userDtoList = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
		return userDtoList ;
	}

	@Override
	public void deleteUser(Integer userid) {
	User user=this.userRepo.findById(userid).orElseThrow(()-> new ResourceNotFound("User  ", "Id", userid));
	this.userRepo.delete(user);
		
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
