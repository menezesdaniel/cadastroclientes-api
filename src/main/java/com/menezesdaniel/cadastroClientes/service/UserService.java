package com.menezesdaniel.cadastroClientes.service;

import com.menezesdaniel.cadastroClientes.model.entity.UserSystem;
import com.menezesdaniel.cadastroClientes.model.repository.UserRepository;
import com.menezesdaniel.cadastroClientes.rest.exceptions.UsuarioCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    public UserSystem salvar(UserSystem userSystem){
        boolean userExists = repository.existsByUsername(userSystem.getUsername());
        if(userExists){
            throw new UsuarioCadastradoException(userSystem.getUsername());
        }
        return repository.save(userSystem);
    }

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        UserSystem userSystem = repository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado.") );

        return User
                .builder()
                .username(userSystem.getUsername())
                .password(userSystem.getPassword())
                .roles("USER")
                .build();
    }
}
