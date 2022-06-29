package com.menezesdaniel.cadastroClientes.rest.exceptions;

public class UsuarioCadastradoException extends RuntimeException{
    public UsuarioCadastradoException( String login ){
        super("O usuário já está cadastrado para o login " + login);
    }
}
