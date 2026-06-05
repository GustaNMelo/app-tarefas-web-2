package com.gustavo.apptarefas.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.gustavo.apptarefas.service.TarefaService;

@TestConfiguration
public class TestConfig {

    @Bean
    public TarefaService tarefaService() {
        return Mockito.mock(TarefaService.class);
    }
}
