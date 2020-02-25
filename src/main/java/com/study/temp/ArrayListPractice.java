package com.study.temp;

import java.util.ArrayList;
import java.util.List;

public class ArrayListPractice {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();

		list.add("12345");
		list.add("qwer");
		list.add("123");
		list.add(1, "1번 인덱스");
		System.out.println(list);
		list.size();
		System.out.println(list.size());
		
		for (int i = 0; i < list.size(); i++) {
			if (list.iterator().hasNext()) {
System.out.println(list.iterator().toString()
		);				
			}
			
		}
		
	}

}
