package com.gustavo.apptarefas.service;

import java.util.List;

import com.gustavo.apptarefas.model.Tarefa;

public interface TarefaService {
    List<Tarefa> getAllTarefas();
    void saveTarefa(Tarefa tarefa);
    Tarefa getTarefaById(long id);
    void deleteTarefaById(long id);
}
