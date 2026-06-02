package com.gustavo.apptarefas.service;

import java.util.List;
import java.util.Optional;

import com.gustavo.apptarefas.model.Projeto;

public interface ProjetoService {

    List<Projeto> getAllProjetos();
    void saveProjeto(Projeto projeto);
    Optional<Projeto> getProjetoById(long id);
    void deleteProjetoById(long id);
}
