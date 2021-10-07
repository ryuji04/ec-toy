package com.example.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.ResisterUserRepository;

/**
 * ユーザー情報を登録するサービス.
 * 
 * @author adachiryuji
 *
 */
@Service
@Transactional
public class RegisterUserService {
	@Autowired
	ResisterUserRepository resisterUserRepository;
	
	/**
	 * ユーザー情報を登録する.
	 * 
	 * @param user ユーザー情報
	 */
	public void registerUser(User user) {
		resisterUserRepository.insert(user);
	}
	
	public List<User> findAll(){
		List<User>userList=resisterUserRepository.findAll();
		return userList;
	}
}
