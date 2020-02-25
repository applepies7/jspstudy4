package com.study.free.vo;

public class FreeBoardVO {
	private int boNum;                /* 글번호 */
	private String boTitle;           /* 글제목 */
	private String boCategory;        /* 글분류 */
	private String boCatNm;        /* 글분류 */
	private String boWriter;          /*  */
	private String boPass;            /*  */
	private String boContent;         /*  */
	private String boIp;              /*  */
	private int boHit;                /*  */
	private String boRegDate;         /*  */
	private String boModDate;         /*  */
	private String boDelYn;           /*  */

	public FreeBoardVO() {
		super();
	}

	public FreeBoardVO(int boNum, String boTitle, String boCategory, String boCatNm, String boWriter, String boPass,
			String boContent, String boIp, int boHit, String boRegDate, String boModDate, String boDelYn) {
		super();
		this.boNum = boNum;
		this.boTitle = boTitle;
		this.boCategory = boCategory;
		this.boCatNm = boCatNm;
		this.boWriter = boWriter;
		this.boPass = boPass;
		this.boContent = boContent;
		this.boIp = boIp;
		this.boHit = boHit;
		this.boRegDate = boRegDate;
		this.boModDate = boModDate;
		this.boDelYn = boDelYn;
	}

	public int getBoNum() {
		return boNum;
	}

	public void setBoNum(int boNum) {
		this.boNum = boNum;
	}

	public String getBoTitle() {
		return boTitle;
	}

	public void setBoTitle(String boTitle) {
		this.boTitle = boTitle;
	}

	public String getBoCategory() {
		return boCategory;
	}

	public void setBoCategory(String boCategory) {
		this.boCategory = boCategory;
	}

	public String getBoCatNm() {
		return boCatNm;
	}

	public void setBoCatNm(String boCatNm) {
		this.boCatNm = boCatNm;
	}

	public String getBoWriter() {
		return boWriter;
	}

	public void setBoWriter(String boWriter) {
		this.boWriter = boWriter;
	}

	public String getBoPass() {
		return boPass;
	}

	public void setBoPass(String boPass) {
		this.boPass = boPass;
	}

	public String getBoContent() {
		return boContent;
	}

	public void setBoContent(String boContent) {
		this.boContent = boContent;
	}

	public String getBoIp() {
		return boIp;
	}

	public void setBoIp(String boIp) {
		this.boIp = boIp;
	}

	public int getBoHit() {
		return boHit;
	}

	public void setBoHit(int boHit) {
		this.boHit = boHit;
	}

	public String getBoRegDate() {
		return boRegDate;
	}

	public void setBoRegDate(String boRegDate) {
		this.boRegDate = boRegDate;
	}

	public String getBoModDate() {
		return boModDate;
	}

	public void setBoModDate(String boModDate) {
		this.boModDate = boModDate;
	}

	public String getBoDelYn() {
		return boDelYn;
	}

	public void setBoDelYn(String boDelYn) {
		this.boDelYn = boDelYn;
	}

	@Override
	public String toString() {
		return "FreeBoardVO [boNum=" + boNum + ", boTitle=" + boTitle + ", boCategory=" + boCategory + ", boCatNm="
				+ boCatNm + ", boWriter=" + boWriter + ", boPass=" + boPass + ", boContent=" + boContent + ", boIp="
				+ boIp + ", boHit=" + boHit + ", boRegDate=" + boRegDate + ", boModDate=" + boModDate + ", boDelYn="
				+ boDelYn + "]";
	}
	
	



	

	
	
	
	
	
}
