package com.example.gestioneordinipizza.repository.cliente;

import com.example.gestioneordinipizza.model.Cliente;

import java.util.List;

public interface CustomClienteRepository {

    public List<Cliente> findByExample(Cliente clienteExample);
}
