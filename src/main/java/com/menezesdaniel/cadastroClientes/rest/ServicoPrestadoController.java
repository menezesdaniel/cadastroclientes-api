package com.menezesdaniel.cadastroClientes.rest;

import com.menezesdaniel.cadastroClientes.model.entity.Cliente;
import com.menezesdaniel.cadastroClientes.model.entity.ServicoPrestado;
import com.menezesdaniel.cadastroClientes.model.repository.ClienteRepository;
import com.menezesdaniel.cadastroClientes.model.repository.ServicoPrestadoRepository;
import com.menezesdaniel.cadastroClientes.rest.dto.ServicoPrestadoDto;
import com.menezesdaniel.cadastroClientes.util.BigDecimalConverter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/servicos-prestados")
public class ServicoPrestadoController {

    private final ClienteRepository clienteRepository;
    private final ServicoPrestadoRepository repository;
    private final BigDecimalConverter bigDecimalConverter;

    public ServicoPrestadoController(
            ClienteRepository clienteRepository,
            ServicoPrestadoRepository repository,
            BigDecimalConverter bigDecimalConverter) {
        this.clienteRepository = clienteRepository;
        this.repository = repository;
        this.bigDecimalConverter = bigDecimalConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar( @RequestBody @Valid ServicoPrestadoDto dto ){
        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Integer idCliente = dto.getIdCliente();

        Cliente cliente =
                clienteRepository
                    .findById(idCliente)
                    .orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente!"));

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData(data);
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor( bigDecimalConverter.converter(dto.getPreco()) );

        return repository.save(servicoPrestado);
    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes
    ) {
        return repository.findByNomeClienteAndMes("%" + nome + "%", mes);
    }
}
