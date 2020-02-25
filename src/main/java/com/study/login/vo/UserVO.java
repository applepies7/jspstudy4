package com.study.login.vo;

public class UserVO {
	
	private String userId;
	private String userPass;
	private String userName;
	private String userRole;
	
	public UserVO() {

	}

	public UserVO(String userId, String userPass, String userName, String userRole) {
		super();
		this.userId = userId;
		this.userPass = userPass;
		this.userName = userName;
		this.userRole = userRole;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		System.out.println("getUserId call " + userId + "return");
		this.userId = userId;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		System.out.println("getUserName call " + userName + "return");
		this.userName = userName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", userPass=" + userPass + ", userName=" + userName + ", userRole="
				+ userRole + "]";
	}
	
}
