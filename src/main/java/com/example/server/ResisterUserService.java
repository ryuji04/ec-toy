package com.example.server;

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
public class ResisterUserService {
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
}