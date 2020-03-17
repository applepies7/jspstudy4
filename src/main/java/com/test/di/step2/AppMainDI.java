package com.test.di.step2;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMainDI {
	public static void main(String[] args) {
		//

		AbstractApplicationContext context  = new ClassPathXmlApplicationContext("test/step2_anno.xml");
		
		DIMan man = context.getBean("DIMan", DIMan.class);
		man.info();
		man.call();
		
		System.out.println("man : " + man.hashCode());
		
		
		DIMan man2 = context.getBean("DIMan", DIMan.class);
		man2.call();
		System.out.println("man2 : " + man2.hashCode());
		context.close();
	}
}
