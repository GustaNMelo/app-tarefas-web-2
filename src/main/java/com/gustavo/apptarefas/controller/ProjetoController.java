package com.gustavo.apptarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.gustavo.apptarefas.model.Projeto;
import com.gustavo.apptarefas.service.ProjetoService;
import com.gustavo.apptarefas.service.TarefaService;

@Controller
@RequestMapping("/projeto")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("projetos", projetoService.getAllProjetos());
        return "projeto/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("projeto", new Projeto());
        model.addAttribute("tarefas", tarefaService.getAllTarefas());
        return "projeto/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Projeto projeto, BindingResult bindingResult, Model model) {
        try {
            projetoService.saveProjeto(projeto);
        } catch (IllegalStateException e) {
            model.addAttribute("projeto", projeto);
            model.addAttribute("tarefas", tarefaService.getAllTarefas());
            bindingResult.addError(new ObjectError("projeto", e.getMessage()));
            return "projeto/form";
        }
        return "redirect:/projeto";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Projeto projeto = projetoService.getProjetoById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto não encontrado"));
        model.addAttribute("projeto", projeto);
        model.addAttribute("tarefas", tarefaService.getAllTarefas());
        return "projeto/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        projetoService.deleteProjetoById(id);
        return "redirect:/projeto";
    }
}
