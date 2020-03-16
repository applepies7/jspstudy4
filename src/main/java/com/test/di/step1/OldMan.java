package com.test.di.step1;

public class OldMan {
	private String name = "밀키스";
	private Phone phone  = new IPhone();
	
	public void info() {
		System.out.println("내이름은 : " + name);
		System.out.println("["+phone.info()+"] 전화왔음...");
	}
	
	public void call() {
		phone.calling();
	}
}
