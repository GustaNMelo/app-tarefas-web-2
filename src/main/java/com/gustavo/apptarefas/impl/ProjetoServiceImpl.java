package com.gustavo.apptarefas.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.apptarefas.model.ItemProjeto;
import com.gustavo.apptarefas.model.Projeto;
import com.gustavo.apptarefas.model.Tarefa;
import com.gustavo.apptarefas.repository.ItemProjetoRepository;
import com.gustavo.apptarefas.repository.ProjetoRepository;
import com.gustavo.apptarefas.repository.TarefaRepository;
import com.gustavo.apptarefas.service.ProjetoService;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private final TarefaRepository tarefaRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ItemProjetoRepository itemProjetoRepository;

    ProjetoServiceImpl(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    @Override
    public List<Projeto> getAllProjetos() {
        return projetoRepository.findAll();
    }

    @Override
    public Optional<Projeto> getProjetoById(long id) {
        return projetoRepository.findById(id);
    }

    @Override
    public void saveProjeto(Projeto projeto) throws IllegalStateException {

        if (projeto.getItens() != null) {
            for (ItemProjeto item : projeto.getItens()) {
                Optional<Tarefa> tarefaOpt = Optional.of(item.getTarefa());
                if (tarefaOpt.isPresent()) {
                    tarefaOpt = tarefaRepository.findById(tarefaOpt.get().getId());
                    if (tarefaOpt.isEmpty()) {
                        throw new IllegalStateException("Tarefa não encontrada: " + item.getTarefa().getId());
                    }
                    if (item.getId() != null) {
                        Optional<ItemProjeto> optItem = itemProjetoRepository.findById(item.getId());
                        optItem.ifPresent(existing -> item.setHorasEstimadas(
                            item.getHorasEstimadas() != null ? item.getHorasEstimadas() : existing.getHorasEstimadas()
                        ));
                    }
                }
                item.setProjeto(projeto);
            }
        }
        projeto.calcularTotalHoras();
        projetoRepository.save(projeto);
    }

    @Override
    public void deleteProjetoById(long id) {
        projetoRepository.deleteById(id);
    }
}
