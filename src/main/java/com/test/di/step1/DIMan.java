package com.test.di.step1;

public class DIMan {

	private String name;
	private Phone phone;
	
//	//네임은 생성자로 DI
//	public DIMan(String name, Phone Phone) {
//		this.name = name;
//		this.phone = phone;
//	}

	//phone는 setter
	public void setPhone(Phone phone) {
		this.phone = phone;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void info() {
		System.out.println("내이름은 : " + name);
		System.out.println("핸드폰 정보 : " + phone);
	}
	
	public void call() {
		phone.calling();
	}
	public void myInit() {
		System.out.println("초기화 합니다.");
	}
	
	public void myDestory() {
		System.out.println("죽기 전에 종료 하세요...");
		
	}
}
