package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class DemoInjection {

        public DemoInjection() {
        }

        public String test() {
            return "Injection";
        }
}


