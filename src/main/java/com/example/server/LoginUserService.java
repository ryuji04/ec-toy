package com.example.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.LoginUser;
import com.example.repository.LoginUserRepository;

/**
 * ログイン情報のサービス.
 * @author adachiryuji
 *
 */
@Service
@Transactional
public class LoginUserService {
	@Autowired
	public LoginUserRepository loginUserRepository;
	
	/**
	 * ログインする為のメソッド.
	 * 
	 * @param email メールアドレス
	 * @param password　パスワード
	 * @return　ログインするユーザーの情報
	 */
	public LoginUser findByEmailAndPassword(String email,String password) {
		LoginUser loginUser=loginUserRepository.findByEmailAndPassword(email, password);
		
		return loginUser;
	}
	
}
