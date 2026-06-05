package com.gustavo.apptarefas.tarefa;

import static org.mockito.Mockito.reset;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

import com.gustavo.apptarefas.config.TestConfig;
import com.gustavo.apptarefas.controller.TaskController;
import com.gustavo.apptarefas.service.TarefaService;

@WebMvcTest(TaskController.class)
@Import(TestConfig.class)
public class TarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TarefaService tarefaService;

    @AfterEach
    void resetMocks() {
        reset(tarefaService);
    }
}
