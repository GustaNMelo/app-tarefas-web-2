package com.gustavo.apptarefas.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.apptarefas.model.Tarefa;
import com.gustavo.apptarefas.repository.TarefaRepository;
import com.gustavo.apptarefas.service.TarefaService;

@Service
public class TarefaServiceImpl implements TarefaService {
    
    @Autowired
    private TarefaRepository tarefaRepository;
    
    @Override
    public List<Tarefa> getAllTarefas() {
        return tarefaRepository.findAll();
    }
    
    @Override
    public void saveTarefa(Tarefa tarefa) {
        this.tarefaRepository.save(tarefa);
    }
    
    @Override
    public Tarefa getTarefaById(long id) {
        Optional<Tarefa> optional = tarefaRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Tarefa not found with id: " + id);
        }
    }
    
    @Override
    public void deleteTarefaById(long id) {
        this.tarefaRepository.deleteById(id);
    }
}
