package com.gustavo.apptarefas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "tarefas")
public class Tarefa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(min = 3, max = 100, message = "Título deve conter entre 3 e 100 caracteres")
    @NotBlank(message = "Título é um campo obrigatório")
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;
    
    @Column(name = "descricao", length = 500)
    private String descricao;
    
    @Column(name = "concluida", nullable = false)
    private Boolean concluida;
    
    @Column(name = "prioridade", length = 20)
    private String prioridade;
    
    @Column(name = "categoria", length = 50)
    private String categoria;

}
