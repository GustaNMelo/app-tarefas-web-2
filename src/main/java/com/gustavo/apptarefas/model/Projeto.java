package com.gustavo.apptarefas.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "projetos")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dataInicio = LocalDateTime.now();

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemProjeto> itens = new ArrayList<>();

    private Float totalHoras = 0.0f;

    public void addItem(ItemProjeto item) {
        itens.add(item);
        item.setProjeto(this);
    }

    public void calcularTotalHoras() {
        this.totalHoras = (float) itens.stream()
                .mapToDouble(i -> i.getHorasEstimadas() != null ? i.getHorasEstimadas() : 0)
                .sum();
    }
}
