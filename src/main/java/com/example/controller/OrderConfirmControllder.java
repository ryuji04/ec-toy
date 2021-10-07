package com.example.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.User;
import com.example.form.OrderForm;
import com.example.server.OrderService;
import com.example.server.UserService;

/**
 * 注文確認情報のコントローラークラス.
 * 
 * @author adachiryuji
 *
 */
@Controller
@RequestMapping("order-confirm")
public class OrderConfirmControllder {
	
	
	@Autowired
	public UserService userService;

	@ModelAttribute
	public OrderForm setUpForm() {
		
		OrderForm orderForm=new OrderForm();
		
		LoginUser loginUser=(LoginUser)(session.getAttribute("loginUser"));
		
		User user=userService.load(loginUser.getId());
		
		
				orderForm.setDestinationName(user.getName());
				orderForm.setDestinationEmail(user.getEmail());
				orderForm.setDestinationZipcode(user.getZipCode());
				orderForm.setDestinationAddress(user.getAddress());
				orderForm.setDestinationTel(user.getTelephone());
		return orderForm;
	}

	@Autowired
	private HttpSession session;

	@Autowired
	private OrderService orderService;

	@RequestMapping("")
	public String showConfirm() {

		LoginUser loginUser = new LoginUser();

		loginUser = (LoginUser) session.getAttribute("loginUser");

		Order order = orderService.findByUserIdAndStatus(loginUser.getId(), 0);
		session.setAttribute("order", order);

		return "order_confirm";
	}

	@RequestMapping("orderFinished")
	public String orderFinished(@Validated OrderForm form, BindingResult result, String responsibleCompany,
			String status) throws Exception {

		// 配達日時が設定されていない時にエラー文が表示されるように記述
		// 記述の関係でここと下記ににエラー文を書いている。2つかかないと配達日時が空であった場合と配達日時が現在より前or3時間以内のエラー文が出ない

		if (form.getOrderDate().isEmpty()) {
			if (result.hasErrors()) {

				LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");

				Order order = orderService.findByUserIdAndStatus(loginUser.getId(), 0);
				session.setAttribute("order", order);
				return "order_confirm";
			}
		}

		// Timestampの使い方を確認しながら記述、リクエストパラメーターで受け取ったデータと現在の日時を比較する
		// System.out.println("deliveryData:"+deliveryData);
		// System.out.println("responsibleCompany"+responsibleCompany);

		String deliveryTime = form.getOrderDate() + " " + responsibleCompany;
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH");
		Date date = simpleDateFormat1.parse(deliveryTime);

		Timestamp desiredDate = new Timestamp(date.getTime());

		LocalDateTime desiredDateTimeToLocal = desiredDate.toLocalDateTime();
		// 現在時刻のタイムスタンプと、現在から3時間後のタイムスタンプを作成する.

		// 現在時刻のタイムスタンプ
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());

		// 現在から3時間後のタイムスタンプ
		LocalDateTime nowLocalDateTime = nowTime.toLocalDateTime();
		LocalDateTime plusTime = nowLocalDateTime.plusHours(3);

		// テスト用１０時～１８時の時間を作成
		// LocalDateTime freeTime=LocalDateTime.of(2021,10,01,15,20,30);

		if (desiredDate.before(nowTime)) {
			result.rejectValue("orderDate", null, "希望の日時が本日よりも前に指定されています");
		}
		if (nowLocalDateTime.isBefore(desiredDateTimeToLocal) && desiredDateTimeToLocal.isBefore(plusTime)) {
			result.rejectValue("orderDate", null, "現在時刻よりも3時間後に指定を願います");
		}

		LoginUser loginUser = new LoginUser();
		loginUser = (LoginUser) session.getAttribute("loginUser");

		if (result.hasErrors()) {

			// loginUser=(LoginUser)session.getAttribute("loginUser");

			Order order = orderService.findByUserIdAndStatus(loginUser.getId(), 0);
			session.setAttribute("order", order);
			return "order_confirm";
		}

		Order order = orderService.findByUserIdAndStatus(loginUser.getId(), 0);
		BeanUtils.copyProperties(form, order);
		order.setUserId(loginUser.getId());
		order.setOrderDate(nowTime);
		;
		order.setDeliveryTime(desiredDate);
		if (form.getStatus() == 1) {
			order.setPaymentMethod(1);
		} else if (form.getStatus() == 2) {
			order.setPaymentMethod(2);
		}

		orderService.update(order, order.getId());
		

		return "redirect:/order-finished";
	}

}
