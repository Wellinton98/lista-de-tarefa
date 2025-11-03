package com.listatarefa.tarefa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.listatarefa.tarefa.model.Tarefa;
import com.listatarefa.tarefa.repository.TarefaRepository;



@Controller
public class TarefaController {
    

    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping("/")
    public String listarTarefas(Model model, @RequestParam(defaultValue = "0") int page){
        int tamanhoPagina = 5;
        Pageable configuracaoPagina = PageRequest.of(page, tamanhoPagina);
        Page<Tarefa> paginaTarefa = tarefaRepository.findAll(configuracaoPagina);

        model.addAttribute("paginaTarefas", paginaTarefa);
        model.addAttribute("paginaAtual", page);
        model.addAttribute("novaTarefa", new Tarefa());
        return "index";

    }
    @PostMapping("/adicionar")
    public String AdicionarTarefa(@ModelAttribute Tarefa tarefa){
        tarefaRepository.save(tarefa);
        return "redirect:/";
    }
    @GetMapping("/excluir")
    public String excluirTarefa(@RequestParam Long id){
        tarefaRepository.deleteById(id);
        return "redirect:/";
    }
  @GetMapping("/concluir")
public String concluirTarefa(@RequestParam Long id) {
    Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
    if (tarefa != null) {
        tarefa.setConcluida(true);
        tarefaRepository.save(tarefa);
    }
    return "redirect:/";
}

 @GetMapping("/editar")
public String editarTarefa(@RequestParam Long id, Model model) {
    Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
    if (tarefa != null) {
        model.addAttribute("tarefa", tarefa);
        return "editar"; 
    }
    return "redirect:/";
}   
@PostMapping("/atualizar")
public String atualizarTarefa(@ModelAttribute Tarefa tarefa) {
    tarefaRepository.save(tarefa);
    return "redirect:/";
}
    @PostMapping("/editar")
public String salvarEdicao(@ModelAttribute Tarefa tarefa) {
    tarefaRepository.save(tarefa);
    return "redirect:/";
}
    @GetMapping("/exibirFormularioEdicao")
public String exibirFormularioEdicao(@RequestParam Long id, Model model) {
    Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
    if (tarefa != null) {
        model.addAttribute("tarefa", tarefa);
        return "editar"; 
    
}
    return "redirect:/";
}

    
}
