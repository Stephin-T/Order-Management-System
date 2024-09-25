package com.training.task.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.training.task.dto.UserDTO;
import com.training.task.entity.AppUsers;
import com.training.task.repo.UserRepository;
import com.training.task.service.IUserService;

@Service
public class UserService implements UserDetailsService, IUserService {
	
	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByUsername(username);
	}

	@Override
	public Boolean registerNewUser(UserDTO userDTO) {
		if (userRepo.findByUsername(userDTO.getUsername()) == null) {
			AppUsers user = new AppUsers();
			user.setUsername(userDTO.getUsername());
			user.setPassword(userDTO.getPassword());
			user.setAuthorities(userDTO.getRole().toString());
			userRepo.save(user);
			return true;
		}
		return false;
	}
}
