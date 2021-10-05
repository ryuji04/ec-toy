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
	private OrderService orderService;
	
	@RequestMapping("")
	public String index() {
		return "order_finished";
	}
	
	
	@RequestMapping("orderFinished")
	public String orderFinished(OrderForm form,String deliveryData,String responsibleCompany,String status)throws Exception  {
		
		//Timestampの使い方を確認しながら記述、リクエストパラメーターで受け取ったデータと現在の日時を比較する
		// System.out.println("deliveryData:"+deliveryData);
		 //  System.out.println("responsibleCompany"+responsibleCompany);
		   
		 String deliverytime=deliveryData+" "+responsibleCompany;
		   SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd HH");
			Date date =simpleDateFormat1.parse(deliverytime);
			Timestamp desiredDate=new Timestamp(date.getTime());
			LocalDateTime desiredDateTimeToLocal=desiredDate.toLocalDateTime();
			
			//現在時刻のタイムスタンプと、現在から3時間後のタイムスタンプを作成する.
			
			//現在時刻のタイムスタンプ
			Timestamp nowTime=new Timestamp(System.currentTimeMillis());
			
			
			//現在から3時間後のタイムスタンプ
			LocalDateTime nowLocalDateTime=nowTime.toLocalDateTime();
			LocalDateTime plusTime=nowLocalDateTime.plusHours(3);
			
			//テスト用１０時～１８時の時間を作成
			//LocalDateTime freeTime=LocalDateTime.of(2021,10,01,15,20,30);
			
			LoginUser loginUser=new LoginUser();
			loginUser=(LoginUser)session.getAttribute("loginUser");
			
			
			if(desiredDate.before(nowTime)) {
				return "forward:order-confirm";
			}if(nowLocalDateTime.isBefore(desiredDateTimeToLocal)&&desiredDateTimeToLocal.isBefore(plusTime)) {
				return "forward:order-confirm";
			}
			
			
			Order order=orderService.findByUserIdAndStatus(loginUser.getId(),0);
			BeanUtils.copyProperties(form, order);
			order.setUserId(loginUser.getId());
			order.setOrderDate(nowTime);;
			order.setDeliveryTime(desiredDate);
			
			
			orderService.update(order,order.getId());
			
			
		   
		   return "redirect:";
	}
}
