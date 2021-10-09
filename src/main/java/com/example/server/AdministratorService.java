package com.example.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.RegisterAdministratorRepository;

/**
 * 管理者情報のサービスクラス.
 * 
 * @author adachiryuji
 *
 */
@Service
@Transactional
public class AdministratorService {
	@Autowired
	private RegisterAdministratorRepository administratorRepository;
	
	/**
	 * 管理者情報をインザートしますす.
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator) {
		administratorRepository.insert(administrator);
	}
	
	/**
	 * メールアドレスとパスワードから管理者情報を検索する.
	 * 
	 * @param email メールアドレス
	 * @param password　パスワード
	 * @return　管理者情報ｚ
	 */
	public Administrator findByEmailAndPassword(String email,String password){
		 Administrator administrator=administratorRepository.findByEmailAndPassword(email, password);
	return administrator;
	}
	
}
