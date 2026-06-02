package com.gustavo.apptarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustavo.apptarefas.model.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

}
