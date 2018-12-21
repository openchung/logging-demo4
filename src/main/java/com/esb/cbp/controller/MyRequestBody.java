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
public class MyRequestBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5076439573502922547L;
	
	@NotBlank(message = "body field is empty or null")
	private String body;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	
}
