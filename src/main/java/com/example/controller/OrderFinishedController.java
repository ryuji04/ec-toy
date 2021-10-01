package com.example.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.server.OrderService;

/**
 * 注文情報を確認するコントローラークラス.
 * 
 * @author adachiryuji
 *
 */
@Controller
@RequestMapping("order-finished")
public class OrderFinishedController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	public OrderService orderService;
	
	@RequestMapping("")
	public String orderFinished(OrderForm form,String deliveryData,String responsibleCompany,String status)throws Exception  {
		
		//Timestampの使い方を確認しながら記述、リクエストパラメーターで受け取ったデータと現在の日時を比較する
		// System.out.println("deliveryData:"+deliveryData);
		 //  System.out.println("responsibleCompany"+responsibleCompany);
		   
		 String deliverytime=deliveryData+" "+responsibleCompany;
		   SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd HH");
			Date date =simpleDateFormat1.parse(deliverytime);
			Timestamp desiredDate=new Timestamp(date.getTime());
			LocalDateTime desiredDateTimeToLocal=desiredDate.toLocalDateTime();
			System.out.println("desiredDate"+desiredDate);
			
			//現在時刻のタイムスタンプと、現在から3時間後のタイムスタンプを作成する.
			
			//現在時刻のタイムスタンプ
			Timestamp nowTime=new Timestamp(System.currentTimeMillis());
			
			
			//現在から3時間後のタイムスタンプ
			LocalDateTime nowLocalDateTime=nowTime.toLocalDateTime();
			LocalDateTime plusTime=nowLocalDateTime.plusHours(3);
			
			//テスト用１０時～１８時の時間を作成
			//LocalDateTime freeTime=LocalDateTime.of(2021,10,01,15,20,30);
			
			System.out.println("plusTime"+plusTime);
			if(desiredDate.before(nowTime)) {
				System.out.println("希望時間が現在日時より前です");
				return "forward:order-confirm";
			}if(nowLocalDateTime.isBefore(desiredDateTimeToLocal)&&desiredDateTimeToLocal.isBefore(plusTime)) {
				System.out.println("３時間後の設定時刻を選択してください");
				return "forward:order-confirm";
			}
			
			LoginUser loginUser=new LoginUser();
			loginUser=(LoginUser)session.getAttribute("loginUser");
			
			Order order=orderService.findByUserId(loginUser.getId());
			
			BeanUtils.copyProperties(form,order);
			
			
		   
		   return "order_finished";
	}
}
