package com.menezesdaniel.cadastroClientes.model.repository;

import com.menezesdaniel.cadastroClientes.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


}
