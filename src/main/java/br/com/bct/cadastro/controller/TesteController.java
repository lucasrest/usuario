package br.com.bct.cadastro.controller;

import br.com.bct.cadastro.logger.LoggerStepEnum;
import br.com.bct.cadastro.logger.LoggerUtil;
import br.com.bct.cadastro.model.Teste;
import br.com.bct.cadastro.model.constants.API;
import br.com.bct.cadastro.model.dto.TesteDTO;
import br.com.bct.cadastro.service.TesteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = API.VERSAO + "/teste", produces = MediaType.APPLICATION_JSON_VALUE)
public class TesteController {

    @Autowired
    private TesteService service;

    @GetMapping
    public ResponseEntity<Object> buscarTodos(Pageable pageable) {
        LoggerUtil.logger(LoggerStepEnum.TES00001, pageable);

        Page<Teste> testes = service.buscarTodos(pageable);

        LoggerUtil.logger(LoggerStepEnum.TES00001, pageable, testes);

        return ResponseEntity.ok(testes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id) {
        LoggerUtil.logger(LoggerStepEnum.TES00002, id);

        Teste teste = service.buscarPorId(id);

        LoggerUtil.logger(LoggerStepEnum.TES00002, id, teste);

        return ResponseEntity.ok(teste);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Object> incluir(@RequestBody @Valid TesteDTO testeDTO) {
        LoggerUtil.logger(LoggerStepEnum.TES00003, testeDTO);

        Teste testeIncluido = service.incluir(service.converterDTOParaEntidade(testeDTO));

        LoggerUtil.logger(LoggerStepEnum.TES00003, testeDTO, testeIncluido);

        return new ResponseEntity<>(service.converterEntidadeParaDTO(testeIncluido), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> atualizar(@RequestBody @Valid TesteDTO testeDTO) {
        LoggerUtil.logger(LoggerStepEnum.TES00004, testeDTO);

        Teste testeAtualizado = service.atualizar(service.converterDTOParaEntidade(testeDTO));

        LoggerUtil.logger(LoggerStepEnum.TES00004, testeAtualizado);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        LoggerUtil.logger(LoggerStepEnum.TES00005, id);

        service.excluir(id);

        LoggerUtil.logger(LoggerStepEnum.TES00005, id);

        return ResponseEntity.noContent().build();
    }
}