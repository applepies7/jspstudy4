package com.test.di.step2;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DIMan {
	
	@Value("으라차차")
	private String name;
	
//	@Autowired
//	@Qualifier("IPhone")
	@Resource(name = "IPhone")
	private Phone phone;
	
//	@Autowired
//	@Qualifier("IPhone")
//	public void setPhone(Phone phone) {
//		this.phone = phone;
//	}
//
//	@Value("으라차차")
//	public void setName(String name) {
//		this.name = name;
//	}

	
	public void info() {
		System.out.println("내이름은 : " + name);
		System.out.println("핸드폰 정보 : " + phone);
	}
	
	
	public void call() {
		phone.calling();
	}
	@PostConstruct
	public void myInit() {
		System.out.println("초기화 합니다.");
	}
	@PreDestroy
	public void myDestory() {
		System.out.println("죽기 전에 종료 하세요...");
		
	}
}
