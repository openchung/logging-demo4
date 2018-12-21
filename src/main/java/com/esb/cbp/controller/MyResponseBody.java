 /**
 * 程式資訊摘要：
 * 類別名稱：RequestBodyI.java
 * 程式內容說明：
 * 版本資訊：
 * 程式設計人員姓名：Sam,Chen
 * 程式修改記錄：2017年11月28日
 * 版權宣告：
 */
package com.esb.cbp.controller;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author sam.xps
 *
 */
public class MyResponseBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5076439573502922547L;
	
	@NotBlank(message = "body field is empty or null")
	private String body1;
	
	private String body2;
	
	private SubMyResponseBody subMyResponseBody;

	public String getBody1() {
		return body1;
	}

	public void setBody1(String body1) {
		this.body1 = body1;
	}
	
	public String getBody2() {
		return body2;
	}

	public void setBody2(String body2) {
		this.body2 = body2;
	}
	
	public SubMyResponseBody getSubMyResponseBody() {
		return subMyResponseBody;
	}

	public void setSubMyResponseBody(SubMyResponseBody subMyResponseBody) {
		this.subMyResponseBody = subMyResponseBody;
	}

	class SubMyResponseBody {
		
		private String body1;
		
		private String body2;

		public String getBody1() {
			return body1;
		}

		public void setBody1(String body1) {
			this.body1 = body1;
		}
		
		public String getBody2() {
			return body2;
		}

		public void setBody2(String body2) {
			this.body2 = body2;
		}
	}
	
	
}
