package com.jlab.JLabWebToApi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import com.jlab.JLabWebToApi.model.Customer;
import com.jlab.JLabWebToApi.model.ApiResponse;

@Service
public class CustomerService {

    private final RestTemplate restTemplate;
    private final String apiUrl = "http://localhost:8080/JLabSamleForSpring/customers";  // 這裡是 JLabSamleForSpring6 API 的 URL
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class); // 日誌

    @Autowired
    public CustomerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 從 API 獲取所有客戶
    public List<Customer> getAllCustomers() {
        ResponseEntity<Customer[]> response = restTemplate.getForEntity(apiUrl, Customer[].class);
        
        Customer[] customersArray = response.getBody();
        
        // 記錄 API 回應資料
        if (customersArray != null) {
            logger.info("Received customer data from API: {}", Arrays.toString(customersArray));
        } else {
            logger.warn("No data received from API for customer list.");
        }

        return Arrays.asList(customersArray);
    }

    // 新增客戶
    public void addCustomer(Customer customer) {
        HttpEntity<Customer> request = new HttpEntity<>(customer);

        try {
            // 捕獲 API 回應，使用 String 接收
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

            // 檢查回應是否為 JSON 格式
            if (response.getHeaders().getContentType() != null && 
                response.getHeaders().getContentType().includes(MediaType.APPLICATION_JSON)) {

                // 解析回應的 JSON 字串為 ApiResponse<Customer> 物件
                ObjectMapper objectMapper = new ObjectMapper();
                ApiResponse<Customer> apiResponse = objectMapper.readValue(response.getBody(), new TypeReference<ApiResponse<Customer>>() {});

                // 根據 status 判斷結果
                if ("001".equals(apiResponse.getStatus())) {
                    // 成功回應
                    logger.info("Successfully added customer. Response: {}. Status code: {}", apiResponse.getData(), response.getStatusCode());
                } else if ("002".equals(apiResponse.getStatus())) {
                    // 失敗回應，記錄錯誤訊息
                    logger.error("Failed to add customer. API message: {}", apiResponse.getMessage());
                } else {
                    logger.warn("Unexpected API status: {}. Message: {}", apiResponse.getStatus(), apiResponse.getMessage());
                }

            } else {
                // 如果不是 JSON 格式，則記錄文字回應
                logger.info("Received non-JSON response: {}. Status code: {}", response.getBody(), response.getStatusCode());
            }

        } catch (Exception e) {
            logger.error("Error while adding customer: {}. Cause: {}", e.getMessage(), e.getCause());
            e.printStackTrace();
        }
    }


    // 刪除客戶
    public void deleteCustomer(Long id) {
        String deleteUrl = apiUrl + "/" + id;
        
        try {
            // 捕獲刪除回應
            ResponseEntity<String> response = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, null, String.class);

            // 檢查回應是否為 JSON 格式
            if (response.getHeaders().getContentType() != null && 
                response.getHeaders().getContentType().includes(MediaType.APPLICATION_JSON)) {

                // 解析回應的 JSON 字串為 ApiResponse<Void> 物件
                ObjectMapper objectMapper = new ObjectMapper();
                ApiResponse<Void> apiResponse = objectMapper.readValue(response.getBody(), new TypeReference<ApiResponse<Void>>() {});

                // 根據 status 判斷結果
                if ("001".equals(apiResponse.getStatus())) {
                    logger.info("Successfully deleted customer with ID: {}. API message: {}", id, apiResponse.getMessage());
                } else if ("002".equals(apiResponse.getStatus())) {
                    logger.error("Failed to delete customer with ID: {}. API message: {}", id, apiResponse.getMessage());
                } else {
                    logger.warn("Unexpected API status: {}. Message: {}", apiResponse.getStatus(), apiResponse.getMessage());
                }

            } else {
                // 如果不是 JSON 格式，則記錄文字回應
                logger.info("Received non-JSON response while deleting customer. Status code: {}", response.getStatusCode());
            }

        } catch (Exception e) {
            logger.error("Error while deleting customer with ID: {}. Cause: {}", id, e.getMessage());
            e.printStackTrace();
        }
    }

}
