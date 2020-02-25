package com.study.util;

public class Love {

	public void milkis(int x) {
		for (int i = 0; i < x; i++) {
			System.out.println("사랑해요 밀키스!!!<br>");

		}
	}

	public String love(int cnt) {
		long st = System.currentTimeMillis();
		String res = "";
		for (int i = 0; i < cnt; i++) {
			res += "사랑해요 밀키스!!!<br>";

		}
		System.out.println("소요시간: " + (System.currentTimeMillis() - st));
		return res;
	}

	public String love2(int cnt) {
		return love2("밀키스", cnt);
	}


	public String love2(String name, int cnt) {
		long st = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < cnt; i++) {
			sb.append("사랑해요 " + name + "♥♡♥♡♥♡♥♡<br>");

		}
		System.out.println("소요시간: " + (System.currentTimeMillis() - st));
		return sb.toString();
	}

}
