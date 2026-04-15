package com.gustavo.apptarefas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {

    @GetMapping("/task")
    public String getMethodName() {
        return "/task/index";
    }
}
