package com.test.di.step2;

import org.springframework.stereotype.Component;

@Component
public class IPhone implements Phone {

	@Override
	public void calling() {
		System.out.println("아이폰 전화옴.");
		
	}
	
	@Override
	public String info() {
		return "아이폰";
		
	}
}
