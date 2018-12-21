/**

* 程式資訊摘要：
* 類別名稱：ExampleController.java
* 程式內容說明：
* 版本資訊：
* 程式設計人員姓名：Sam,Chen
* 程式修改記錄：2018年4月23日
* 版權宣告：
*/
package com.esb.cbp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.esb.cbp.biz.bean.RestServiceRequest;
import com.esb.cbp.biz.bean.RestServiceResponse;
import com.esb.cbp.dao.CustContactDao;
import com.esb.cbp.dao.CustContactDaoImpl;
import com.esb.cbp.entity.Customer;
import com.esb.cbp.service.CustomerService;
import com.esb.cbp.service.ExampleService;
import com.esb.common.auditlog.annotation.AuditResponseBody;
import com.esb.common.auditlog.annotation.AuditTrail;
import com.esb.common.logger.EsunLogger;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author sam.xps
 *
 */
@Api(value = "ExampleController", description = "CBP Controller 範例")
@RestController
@RequestMapping("/demo")
public class ExampleController {

    private Logger log = EsunLogger.create(ExampleController.class);

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private ApplicationContext context;
    
    @Autowired
    private ExampleService exampleService;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private CustContactDao custContactDao;
    
    @Autowired
    CustomerService cusService;

    @ApiOperation(value = "測試範例 Audit Trail功能", notes = "包含audit、outbound、tx等功能")
    @RequestMapping(value = "/audit", method = { RequestMethod.POST })
    @AuditResponseBody
    public RestServiceResponse<MyResponseBody> post(@Valid @RequestBody RestServiceRequest<MyRequestBody> restServiceRequest) throws JsonParseException, JsonMappingException, IOException {
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
        };
        HashMap<String, Object> jsonMap = OBJECT_MAPPER.readValue(ThreadContext.get(RestServiceRequest.CONTENT_CACHE), typeRef);
        RestServiceResponse<MyResponseBody> restServiceResponse = new RestServiceResponse<MyResponseBody>();
        restServiceResponse.setRequestModel(jsonMap);
        restServiceResponse.setResultDescription("測試 Demo");
        restServiceResponse.setResultCode("0000");
        MyResponseBody responseBody = new MyResponseBody();
        responseBody.setBody1("ResponseBody1");
        responseBody.setBody2("ResponseBody2");
        MyResponseBody.SubMyResponseBody subMyResponseBody = new MyResponseBody().new SubMyResponseBody();
        subMyResponseBody.setBody1("SubResponseBody1");
        subMyResponseBody.setBody2("SubResponseBody2");
        responseBody.setSubMyResponseBody(subMyResponseBody);
        restServiceResponse.setResultBody(responseBody);
//        =========================================================
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<RestServiceRequest<MyRequestBody>> request = new HttpEntity<RestServiceRequest<MyRequestBody>>(restServiceRequest, headers);

        ResponseEntity<RestServiceResponse> response = restTemplate.postForEntity("http://localhost:8888/example/demo/outbound", request , RestServiceResponse.class );
        log.debug("Call /demo/getOutbound response: " + response);
        log.debug("Call /demo/audit!");
        return restServiceResponse;
    }
    
    @ApiOperation(value = "測試範例 Audit Trail功能", notes = "包含audit、outbound、tx等功能")
    @RequestMapping(value = "/outbound", method = { RequestMethod.POST })
    @AuditResponseBody
    public RestServiceResponse<MyResponseBody> getOutbound(@Valid @RequestBody RestServiceRequest<MyRequestBody> restServiceRequest) throws JsonParseException, JsonMappingException, IOException {
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
        };
        HashMap<String, Object> jsonMap = OBJECT_MAPPER.readValue(ThreadContext.get(RestServiceRequest.CONTENT_CACHE), typeRef);
        RestServiceResponse<MyResponseBody> restServiceResponse = new RestServiceResponse<MyResponseBody>();
        restServiceResponse.setRequestModel(jsonMap);
        restServiceResponse.setResultDescription("測試 Demo");
        restServiceResponse.setResultCode("0000");
        MyResponseBody responseBody = new MyResponseBody();
        responseBody.setBody1("ResponseBody1");
        responseBody.setBody2("ResponseBody2");
        MyResponseBody.SubMyResponseBody subMyResponseBody = new MyResponseBody().new SubMyResponseBody();
        subMyResponseBody.setBody1("SubResponseBody1");
        subMyResponseBody.setBody2("SubResponseBody2");
        responseBody.setSubMyResponseBody(subMyResponseBody);
        restServiceResponse.setResultBody(responseBody);
        log.debug("Call /demo/postOutbound!");
        return restServiceResponse;
    }

    @ApiOperation(value = "測試範例使用@ImportResource annotation init spring bean", notes = "")
    @RequestMapping(value = "/get", method = { RequestMethod.GET })
    public void get() {
        exampleService.process();
    }
    
    
    @ApiOperation(value = "測試範例 Audit Trail功能", notes = "包含audit、sql等功能")
    @RequestMapping(value = "/sql", method = { RequestMethod.POST })
    @AuditResponseBody
    public RestServiceResponse<MyResponseBody> getSql(@Valid @RequestBody RestServiceRequest<MyRequestBody> restServiceRequest) throws JsonParseException, JsonMappingException, IOException {
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
        };
        HashMap<String, Object> jsonMap = OBJECT_MAPPER.readValue(ThreadContext.get(RestServiceRequest.CONTENT_CACHE), typeRef);
        RestServiceResponse<MyResponseBody> restServiceResponse = new RestServiceResponse<MyResponseBody>();
        restServiceResponse.setRequestModel(jsonMap);
        restServiceResponse.setResultDescription("測試 Sql");
        restServiceResponse.setResultCode("0000");
        MyResponseBody responseBody = new MyResponseBody();
        responseBody.setBody1("ResponseBody1");
        responseBody.setBody2("ResponseBody2");
        custContactDao.create(Long.parseLong("0"), "danniel", "0988965588");
        MyResponseBody.SubMyResponseBody subMyResponseBody = new MyResponseBody().new SubMyResponseBody();
        subMyResponseBody.setBody1("SubResponseBody1");
        subMyResponseBody.setBody2("SubResponseBody2");
        responseBody.setSubMyResponseBody(subMyResponseBody);
        restServiceResponse.setResultBody(responseBody);
        log.debug("Call /demo/postOutbound!");
        return restServiceResponse;
    }
    
    @ApiOperation(value = "測試範例 Audit Trail功能", notes = "包含audit、sql等功能")
    @RequestMapping(value = "/postgresql", method = { RequestMethod.POST })
    @AuditResponseBody
    public RestServiceResponse<MyResponseBody> getPpostgresSql(@Valid @RequestBody RestServiceRequest<MyRequestBody> restServiceRequest) throws JsonParseException, JsonMappingException, IOException {
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
        };
        HashMap<String, Object> jsonMap = OBJECT_MAPPER.readValue(ThreadContext.get(RestServiceRequest.CONTENT_CACHE), typeRef);
        RestServiceResponse<MyResponseBody> restServiceResponse = new RestServiceResponse<MyResponseBody>();
        restServiceResponse.setRequestModel(jsonMap);
        restServiceResponse.setResultDescription("測試 Sql");
        restServiceResponse.setResultCode("0000");
        MyResponseBody responseBody = new MyResponseBody();
        responseBody.setBody1("ResponseBody1");
        responseBody.setBody2("ResponseBody2");
        CustomerService cusService = context.getBean(CustomerService.class);
        /*
         * Create Customer
         */
        Random r = new Random();

        // Customer 1
        Customer cus_1 = new Customer();
        Long cus_1_id = r.nextLong();
        cus_1.setCustId(cus_1_id);
        cus_1.setName("demoCustomer_1");
        cus_1.setAge(30);

        // Customer 2
        Customer cus_2 = new Customer();
        Long cus_2_id = r.nextLong();
        cus_2.setCustId(cus_2_id);
        cus_2.setName("demoCustomer_2");
        cus_2.setAge(30);

        // Customer 3
        Customer cus_3 = new Customer();
        Long cus_3_id = r.nextLong();
        cus_3.setCustId(cus_3_id);
        cus_3.setName("demoCustomer_2");
        cus_3.setAge(30);

        // Insert a customer to DB
        cusService.insert(cus_1);

        // Insert a List of Customer to DB
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(cus_2);
        customers.add(cus_3);
        cusService.insertBatch(customers);

        // Load All Customer and display
        cusService.loadAllCustomer();

        // Get Customer By Id
        System.out.println("=============Get Customer By Id");
        cusService.getCustomerById(Long.valueOf(cus_1_id));

        // Get Customer's name by Id
        System.out.println("=============Get Customer Name by Id");
        cusService.getCustomerNameById(cus_2_id);

        // Get Total Customers in DB
        System.out.println("=============Get Total Number Customer");
        cusService.getTotalNumerCustomer();

        System.out.println("#######################################");
        System.out.println("Done!!!");
        System.out.println("#######################################");
        MyResponseBody.SubMyResponseBody subMyResponseBody = new MyResponseBody().new SubMyResponseBody();
        subMyResponseBody.setBody1("SubResponseBody1");
        subMyResponseBody.setBody2("SubResponseBody2");
        responseBody.setSubMyResponseBody(subMyResponseBody);
        restServiceResponse.setResultBody(responseBody);
        log.debug("Call /demo/postOutbound!");
        return restServiceResponse;
    }
}
