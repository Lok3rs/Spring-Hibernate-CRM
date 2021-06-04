package com.codecool.controller;


import com.codecool.entity.Customer;
import com.codecool.service.CustomerService;
import com.codecool.util.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    private CustomerService customerService;


    @GetMapping("/list")
    public String listCustomers(Model model, @RequestParam(required = false) String sort) {
        List<Customer> customers = customerService.getCustomers(SortUtils.LAST_NAME);
        if (sort != null) {
            int sortField = Integer.parseInt(sort);
            customers = customerService.getCustomers(sortField);
        }
        model.addAttribute("customers", customers);
        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:list";
    }

    @GetMapping("/showFormUpdate/{id}")
    public String showFormForUpdate(@PathVariable int id, Model model) {
        Customer customer = customerService.getCustomer(id);
        model.addAttribute("customer", customer);
        return "customer-form";
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable int id, HttpServletRequest request) {
        customerService.deleteCustomer(id);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/search")
    public String searchCustomer(@RequestParam("searchName") String searchName, Model model) {
        List<Customer> customers = customerService.searchCustomers(searchName);
        model.addAttribute("customers", customers);
        return "list-customers";
    }
}
