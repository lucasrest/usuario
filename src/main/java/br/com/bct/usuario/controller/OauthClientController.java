package br.com.bct.usuario.controller;

import br.com.bct.usuario.logger.LoggerStepEnum;
import br.com.bct.usuario.logger.LoggerUtil;
import br.com.bct.usuario.model.OauthClientDetails;
import br.com.bct.usuario.model.constants.Permissoes;
import br.com.bct.usuario.model.dto.OauthClientDetailsDTO;
import br.com.bct.usuario.service.OauthClientService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "Oauth Clients")
@RequestMapping(path = "/v1/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class OauthClientController {

    @Autowired
    private OauthClientService service;

    @PreAuthorize("hasAuthority('" + Permissoes.CLIENT_BUSCARTODOS + "')")
    @GetMapping
    public ResponseEntity buscarTodos(Pageable pageable) {
        LoggerUtil.logger(LoggerStepEnum.USU00001, pageable);

        Page<OauthClientDetails> lista = service.buscarTodos(pageable);

        LoggerUtil.logger(LoggerStepEnum.USU00001, pageable, lista);

        return ResponseEntity.ok(lista);

    }

    @PreAuthorize("hasAuthority('" + Permissoes.CLIENT_BUSCARPORID + "')")
    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id) {
        LoggerUtil.logger(LoggerStepEnum.USU00002, id);

        OauthClientDetails oauthClientDetails = service.buscarPorId(id);

        LoggerUtil.logger(LoggerStepEnum.USU00002, id, oauthClientDetails);

        return ResponseEntity.ok(oauthClientDetails);

    }

    @PreAuthorize("hasAuthority('" + Permissoes.CLIENT_INCLUIR + "')")
    @PostMapping
    public ResponseEntity incluir(@RequestBody @Valid OauthClientDetailsDTO oauthClientDetails) {
        LoggerUtil.logger(LoggerStepEnum.USU00003, oauthClientDetails);

        OauthClientDetails oauthClientDetailsDb = service.incluir(service.converterDTOParaEntidade(oauthClientDetails));

        LoggerUtil.logger(LoggerStepEnum.USU00003, oauthClientDetails, oauthClientDetailsDb);

        return new ResponseEntity<>(oauthClientDetailsDb, HttpStatus.CREATED);

    }

    @PreAuthorize("hasAuthority('" + Permissoes.CLIENT_ATUALIZAR + "')")
    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid OauthClientDetailsDTO oauthClientDetails) {
        LoggerUtil.logger(LoggerStepEnum.USU00004, oauthClientDetails);

        OauthClientDetails oauthClientDetailsDb = service.atualizar(service.converterDTOParaEntidade(oauthClientDetails));

        LoggerUtil.logger(LoggerStepEnum.USU00004, oauthClientDetails, oauthClientDetailsDb);

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('" + Permissoes.CLIENT_EXCLUIR + "')")
    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@RequestBody Long id) {
        LoggerUtil.logger(LoggerStepEnum.USU00005, id);

        service.excluir(id);

        LoggerUtil.logger(LoggerStepEnum.USU00005, id);

        return ResponseEntity.noContent().build();
    }

}
