package com.gustavo.apptarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gustavo.apptarefas.model.Tarefa;
import com.gustavo.apptarefas.service.TarefaService;
import jakarta.validation.Valid;

@Controller
public class TaskController {
    
    @Autowired
    private TarefaService tarefaService;
    
    @GetMapping("/task")
    public String index(Model model) {
        model.addAttribute("tarefasList", tarefaService.getAllTarefas());
        return "task/index";
    }
    
    @GetMapping("/task/create")
    public String create(Model model) {
        model.addAttribute("tarefa", new Tarefa());
        return "task/create";
    }
    
    @PostMapping("/task/save")
    public String save(@ModelAttribute @Valid Tarefa tarefa, BindingResult result, Model model) {
        
        System.out.println(tarefa);
        if (result.hasErrors()) {
            model.addAttribute("tarefa", tarefa);
            return "task/create";
        }
        
        tarefaService.saveTarefa(tarefa);
        return "redirect:/task";
    }
    
    @GetMapping("/task/delete/{id}")
    public String delete(@PathVariable Long id) {
        this.tarefaService.deleteTarefaById(id);
        return "redirect:/task";
    }
    
    @GetMapping("/task/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Tarefa tarefa = tarefaService.getTarefaById(id);
        model.addAttribute("tarefa", tarefa);
        return "task/edit";
    }
}
