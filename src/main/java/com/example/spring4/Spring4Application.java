package com.example.spring4;

import com.example.spring4.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Spring4Application {

    public static void main(String[] args) {
//        UserController userController = new UserController();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("XmlApplicationContext.xml");
        UserController userController = applicationContext.getBean("userController", UserController.class);
        String input = "{\"firstName\":\"firstName\"}";
//        userController.create(input);
        System.out.println(userController.get("4b39c6b1-716a-4201-b1d9-4e26773e7cc4"));
    }
}
