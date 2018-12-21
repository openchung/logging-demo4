 /**
 * 程式資訊摘要：
 * 類別名稱：ExampleControllerAdvice.java
 * 程式內容說明：
 * 版本資訊：
 * 程式設計人員姓名：Sam,Chen
 * 程式修改記錄：2018年4月23日
 * 版權宣告：
 */
package com.esb.cbp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.esb.cbp.biz.bean.RestServiceRequest;
import com.esb.cbp.biz.bean.RestServiceResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author sam.xps
 *
 */
@ControllerAdvice
public class ExampleControllerAdvice {
	
	private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	private HashMap<String,Object> getRequestModel() throws JsonParseException, JsonMappingException, IOException {
		TypeReference<HashMap<String,Object>> typeRef  = new TypeReference<HashMap<String,Object>>() {};
		return OBJECT_MAPPER.readValue(ThreadContext.get(RestServiceRequest.CONTENT_CACHE), typeRef);
	}

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public RestServiceResponse<MyResponseBody> processException(Throwable e) throws JsonParseException, JsonMappingException, IOException {
		RestServiceResponse<MyResponseBody> restServiceResponse = new RestServiceResponse<MyResponseBody>();
		restServiceResponse.setRequestModel(getRequestModel());
		restServiceResponse.setResultDescription("測試ControllerAdvice(Throwable)");
		restServiceResponse.setResultCode("9999");
		restServiceResponse.setErrorMessages(Arrays.asList(e.getLocalizedMessage()));
		return restServiceResponse;
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public RestServiceResponse<MyResponseBody> processException(RuntimeException e) throws JsonParseException, JsonMappingException, IOException {
		RestServiceResponse<MyResponseBody> restServiceResponse = new RestServiceResponse<MyResponseBody>();
		restServiceResponse.setRequestModel(getRequestModel());
		restServiceResponse.setResultDescription("測試ControllerAdvice(RuntimeException)");
		restServiceResponse.setResultCode("9999");
		restServiceResponse.setErrorMessages(Arrays.asList(e.getLocalizedMessage()));
		return restServiceResponse;
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public RestServiceResponse<MyResponseBody> processException(HttpMessageNotReadableException e) throws JsonParseException, JsonMappingException, IOException {
		RestServiceResponse<MyResponseBody> restServiceResponse = new RestServiceResponse<MyResponseBody>();
		restServiceResponse.setResultDescription("測試ControllerAdvice(HttpMessageNotReadableException)");
		restServiceResponse.setResultCode("9999");
		restServiceResponse.setErrorMessages(Arrays.asList(e.getLocalizedMessage()));
		return restServiceResponse;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public RestServiceResponse<MyResponseBody> processException(MethodArgumentNotValidException e) throws JsonParseException, JsonMappingException, IOException {
		RestServiceResponse<MyResponseBody> restServiceResponse = new RestServiceResponse<MyResponseBody>();
		restServiceResponse.setRequestModel(getRequestModel());
		restServiceResponse.setResultDescription("測試ControllerAdvice(MethodArgumentNotValidException)");
		restServiceResponse.setResultCode("9999");
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		List<String> errorMessageList = new ArrayList<String>();
		for(FieldError fieldError : fieldErrors) {
			errorMessageList.add(fieldError.getDefaultMessage());
		}
		restServiceResponse.setErrorMessages(errorMessageList);
		return restServiceResponse;
	}
}
