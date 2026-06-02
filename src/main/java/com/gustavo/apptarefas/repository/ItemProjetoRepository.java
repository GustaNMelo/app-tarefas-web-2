package com.gustavo.apptarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustavo.apptarefas.model.ItemProjeto;

@Repository
public interface ItemProjetoRepository extends JpaRepository<ItemProjeto, Long> {

}
