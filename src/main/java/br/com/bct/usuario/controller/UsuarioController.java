package br.com.bct.usuario.controller;

import br.com.bct.usuario.logger.LoggerStepEnum;
import br.com.bct.usuario.logger.LoggerUtil;
import br.com.bct.usuario.model.Usuario;
import br.com.bct.usuario.model.dto.RecuperaSenhaDTO;
import br.com.bct.usuario.model.dto.UsuarioDTO;
import br.com.bct.usuario.service.UsuarioService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin
@Api(value = "Usuario")
@RequestMapping(path = "/v1/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<Object> buscarTodos(Pageable pageable) {
        LoggerUtil.logger(LoggerStepEnum.USU00006, pageable);

        Page<Usuario> usuarios = service.buscarTodos(pageable);

        LoggerUtil.logger(LoggerStepEnum.USU00006, pageable, usuarios);

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id) {
        LoggerUtil.logger(LoggerStepEnum.USU00007, id);

        Usuario usuario = service.buscarPorId(id);

        LoggerUtil.logger(LoggerStepEnum.USU00007, id, usuario);

        return ResponseEntity.ok().body(usuario);
    }


    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Object> incluir(@RequestBody @Valid UsuarioDTO dto) {
        LoggerUtil.logger(LoggerStepEnum.USU00008, dto);

        Usuario usuarioIncluido = service.incluir(service.converterDTOParaEntidade(dto));

        LoggerUtil.logger(LoggerStepEnum.USU00008, dto, usuarioIncluido);

        return new ResponseEntity<>(service.converterEntidadeParaDTO(usuarioIncluido), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> atualizar(@RequestBody @Valid UsuarioDTO dto) {
        LoggerUtil.logger(LoggerStepEnum.USU00009, dto);

        Usuario usuarioAtualizado = service.atualizar(service.converterDTOParaEntidade(dto));

        LoggerUtil.logger(LoggerStepEnum.USU00009, dto, usuarioAtualizado);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        LoggerUtil.logger(LoggerStepEnum.USU00010, id);

        service.excluir(id);

        LoggerUtil.logger(LoggerStepEnum.USU00010, id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{email}/recuperar")
    public ResponseEntity<Object> recuperarSenha(@PathVariable String email) {
        LoggerUtil.logger(LoggerStepEnum.USU00011, email);

        Usuario usuarioAtualizado = service.enviarEmailCodigoRecuperacao(email);

        LoggerUtil.logger(LoggerStepEnum.USU00011, email, usuarioAtualizado);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{email}/resetar")
    public ResponseEntity<Object> resetarSenha(@PathVariable String email, @RequestBody @Valid RecuperaSenhaDTO dto) {
        LoggerUtil.logger(LoggerStepEnum.USU00012, email);

        Usuario usuarioAtualizado = service.resetarSenha(email, dto);

        LoggerUtil.logger(LoggerStepEnum.USU00012, usuarioAtualizado);

        return ResponseEntity.noContent().build();
    }

}
