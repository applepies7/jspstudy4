package com.test.di.step2;

import org.springframework.stereotype.Component;

@Component
public class SamsungPhone implements Phone {

	@Override
	public void calling() {
		System.out.println("삼성폰 전화옴.");
		
	}
	@Override
	public String info() {
		return "삼성폰";
		
	}
}
