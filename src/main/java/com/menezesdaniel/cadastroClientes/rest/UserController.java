package com.menezesdaniel.cadastroClientes.rest;

import com.menezesdaniel.cadastroClientes.model.entity.UserSystem;
import com.menezesdaniel.cadastroClientes.rest.exceptions.UsuarioCadastradoException;
import com.menezesdaniel.cadastroClientes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid UserSystem userSystem){
        try{
            service.salvar(userSystem);
        }catch (UsuarioCadastradoException e){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, e.getMessage() );
        }
    }
}
