package com.bpcbt.lessons.spring.task1.springMVCController;

import com.bpcbt.lessons.spring.task1.ObjectMapping.Customer;
import com.bpcbt.lessons.spring.task1.ObjectMapping.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;


@Controller
public class MainController {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MainController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showAllAccount() {
        List<Customer> customer = jdbcTemplate.query(
                "select * from customers",
                new CustomerMapper());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("customerList", customer);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
