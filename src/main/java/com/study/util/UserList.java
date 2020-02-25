package com.study.util;

import java.util.HashMap;
import java.util.Map;

import com.study.login.vo.UserVO;

public class UserList {
	
	private static Map<String,UserVO>  userMap = new HashMap<String,UserVO>();
	static {
		userMap.put("malja", new UserVO("malja", "1004", "착한말자","USER"));
		userMap.put("milkis", new UserVO("milkis", "1004", "밀키스","MANAGER"));
		userMap.put("admin", new UserVO("admin", "1004", "관리자","ADMIN"));
	}

	public static UserVO getUser(String userId) {
		return userMap.get(userId);
		
	}
}
