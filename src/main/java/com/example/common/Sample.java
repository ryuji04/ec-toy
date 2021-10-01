package com.example.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Sample {
	public static void main(String[] args)throws Exception {

		//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		//System.out.println(timestamp);
		
		String str1="2020-11-12 11";
		SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd HH");
		Date date2=simpleDateFormat1.parse(str1);
		Timestamp ts=new Timestamp(date2.getTime());
		
		//System.out.println(ts.before(timestamp));
		
		
		
		
	     Timestamp time=new Timestamp(System.currentTimeMillis());
	     LocalDateTime localDateTime=time.toLocalDateTime();
	     LocalDateTime plusTime=localDateTime.plusHours(3);
	     System.out.println("plusTime"+plusTime);
	     LocalDateTime nowTime=LocalDateTime.now();
	     System.out.println(nowTime.isBefore(plusTime));
	     
	     LocalDateTime freeTime=LocalDateTime.of(2021,10,01,15,20,30);
	     System.out.println("freeTime"+freeTime);
	}
}
