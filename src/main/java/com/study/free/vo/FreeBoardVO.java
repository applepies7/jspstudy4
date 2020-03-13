package com.study.free.vo;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.study.common.valid.ModifyValid;
import com.study.common.valid.RegistValid;

public class FreeBoardVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Min(groups = ModifyValid.class, message = "글번호가 존재 하지 않습니다.", value = 1)
	private int boNum; /* 글번호 */

	@NotBlank(message = "제목은 필수입니다.")
	@Size(min = 3, message = "제목은 3글자 이상입니다.")
	private String boTitle; /* 글제목 */

	@NotBlank(groups = RegistValid.class, message = "분류는 필수로 선택해야 합니다.")
	private String boCategory; /* 글분류 */
	private String boCatNm; /* 글분류 */

	@NotBlank(message = "작성자는 필수입니다.")
	@Pattern(regexp = ".*[가-힣].*", message = "한글이 없습니다.")
	private String boWriter; /*  */

	@NotBlank(message = "패스워드는 필수입니다.")
	@Size(min = 4, max = 20, message = "패스워드는 4자 이상 20자 이하입니다.")
	private String boPass; /*  */

	private String boContent; /*  */
	private String boIp; /*  */
	private int boHit; /*  */
	private String boRegDate; /*  */
	private String boModDate; /*  */
	private String boDelYn; /*  */

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
		return ToStringBuilder.reflectionToString(this);
	}

}
