package com.jlab.JLabWebToApi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.jlab.JLabWebToApi.model.Customer;
import com.jlab.JLabWebToApi.service.CustomerService;

import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // 顯示客戶列表
    @GetMapping("/")
    public String index(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "index";  // Thymeleaf 模板名為 index.html
    }

    // 顯示新增客戶表單
    @GetMapping("/add")
    public String showAddCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "addCustomer";  // Thymeleaf 模板名為 addCustomer.html
    }

    // 處理新增客戶請求
    @PostMapping("/add")
    public String addCustomer(@ModelAttribute Customer customer, Model model) {
    	try {
            customerService.addCustomer(customer);
            model.addAttribute("successMessage", "客戶新增成功！");
//            return "redirect:/customers";
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", "新增客戶失敗：" + e.getMessage());
            return "addCustomer";
        }  // 新增後重新導向至客戶列表頁面
    }

    // 處理刪除客戶請求
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/";  // 刪除後重新導向至客戶列表頁面
    }
}
