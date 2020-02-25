package com.study.member.vo;

public class MemberVO {
	private String memId; /* 회원 아이디 */
	private String memPass; /* 회원 비밀번호 */
	private String memName; /**/
	private String memRegno1; /**/
	private String memRegno2; /**/
	private String memBir; /**/
	private String memZip; /**/
	private String memAdd1; /**/
	private String memAdd2; /**/
	private String memHp; /**/
	private String memMail; /**/
	private String memJob; /**/
	private String memLike; /**/
	private int memMileage; /**/
	private String memDelete; /**/
	private String memJobnm; /**/
	private String memLikenm; /**/
	
	
	
	
	public MemberVO() {
		super();
	}

	

	public MemberVO(String memId, String memPass, String memName, String memRegno1, String memRegno2, String memBir,
			String memZip, String memAdd1, String memAdd2, String memHp, String memMail, String memJob, String memLike,
			int memMileage, String memDelete, String memJobnm, String memLikenm) {
		super();
		this.memId = memId;
		this.memPass = memPass;
		this.memName = memName;
		this.memRegno1 = memRegno1;
		this.memRegno2 = memRegno2;
		this.memBir = memBir;
		this.memZip = memZip;
		this.memAdd1 = memAdd1;
		this.memAdd2 = memAdd2;
		this.memHp = memHp;
		this.memMail = memMail;
		this.memJob = memJob;
		this.memLike = memLike;
		this.memMileage = memMileage;
		this.memDelete = memDelete;
		this.memJobnm = memJobnm;
		this.memLikenm = memLikenm;
	}



	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPass() {
		return memPass;
	}

	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemRegno1() {
		return memRegno1;
	}

	public void setMemRegno1(String memRegno1) {
		this.memRegno1 = memRegno1;
	}

	public String getMemRegno2() {
		return memRegno2;
	}

	public void setMemRegno2(String memRegno2) {
		this.memRegno2 = memRegno2;
	}

	public String getMemBir() {
		return memBir;
	}

	public void setMemBir(String memBir) {
		this.memBir = memBir;
	}

	public String getMemZip() {
		return memZip;
	}

	public void setMemZip(String memZip) {
		this.memZip = memZip;
	}

	public String getMemAdd1() {
		return memAdd1;
	}

	public void setMemAdd1(String memAdd1) {
		this.memAdd1 = memAdd1;
	}

	public String getMemAdd2() {
		return memAdd2;
	}

	public void setMemAdd2(String memAdd2) {
		this.memAdd2 = memAdd2;
	}

	public String getMemHp() {
		return memHp;
	}

	public void setMemHp(String memHp) {
		this.memHp = memHp;
	}

	public String getMemMail() {
		return memMail;
	}

	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}

	public String getMemJob() {
		return memJob;
	}

	public void setMemJob(String memJob) {
		this.memJob = memJob;
	}

	public String getMemLike() {
		return memLike;
	}

	public void setMemLike(String memLike) {
		this.memLike = memLike;
	}

	public int getMemMileage() {
		return memMileage;
	}

	public void setMemMileage(int memMileage) {
		this.memMileage = memMileage;
	}

	public String getMemDelete() {
		return memDelete;
	}

	public void setMemDelete(String memDelete) {
		this.memDelete = memDelete;
	}

	public String getMemJobnm() {
		return memJobnm;
	}

	public void setMemJobnm(String memJobnm) {
		this.memJobnm = memJobnm;
	}

	public String getMemLikenm() {
		return memLikenm;
	}

	public void setMemLikenm(String memLikenm) {
		this.memLikenm = memLikenm;
	}

	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", memPass=" + memPass + ", memName=" + memName + ", memRegno1=" + memRegno1
				+ ", memRegno2=" + memRegno2 + ", memBir=" + memBir + ", memZip=" + memZip + ", memAdd1=" + memAdd1
				+ ", memAdd2=" + memAdd2 + ", memHp=" + memHp + ", memMail=" + memMail + ", memJob=" + memJob
				+ ", memLike=" + memLike + ", memMileage=" + memMileage + ", memDelete=" + memDelete + ", memJobnm="
				+ memJobnm + ", memLikenm=" + memLikenm + "]";
	}
	
	

	
	
	
	
}
