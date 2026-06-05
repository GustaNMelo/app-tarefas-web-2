package com.gustavo.apptarefas.tarefa;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.gustavo.apptarefas.model.Tarefa;
import com.gustavo.apptarefas.repository.TarefaRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class TarefaIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private TarefaRepository tarefaRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void testSaveTarefaIntegration() throws Exception {

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Tarefa de Teste");
        tarefa.setDescricao("Descricao de teste");
        tarefa.setConcluida(false);
        tarefa.setPrioridade("Alta");
        tarefa.setCategoria("Dev");

        mockMvc.perform(post("/task/save")
                .with(user("admin").authorities(new org.springframework.security.core.authority.SimpleGrantedAuthority("Admin")))
                .with(csrf())
                .flashAttr("tarefa", tarefa))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/task"));

        assertTrue(tarefaRepository.findAll()
                .stream()
                .anyMatch(t -> "Tarefa de Teste".equals(t.getTitulo())));
    }
}
