package com.example.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.ResisterUserRepository;

/**
 * ユーザー情報のサービスクラス.
 * 
 * @author adachiryuji
 *
 */
@Service
@Transactional
public class UserService {
	@Autowired
	private ResisterUserRepository resisterUserRepository;
	
	/**
	 * IDからユーザー情報を取得.
	 * 
	 * @param userId ユーザーID
	 * @return　ユーザー情報
	 */
	public User load(Integer userId) {
		User user=resisterUserRepository.load(userId);
		
		return user;
		
	}
	
}
