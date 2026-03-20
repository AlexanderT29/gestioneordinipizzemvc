package com.example.gestioneordinipizza.service.cliente;

import com.example.gestioneordinipizza.model.Cliente;

import java.util.List;

public interface ClienteService {

    public List<Cliente> listAllElements();

    public Cliente caricaSingoloCliente(Long id);

    public Cliente caricaSingoloElementoEager(Long id);

    public void aggiorna(Cliente cliente);

    public void inserisciNuovo(Cliente cliente);

    public void rimuovi(Long idCliente);

    public void disattivaCliente(Long id);

    public List<Cliente> findByExample(Cliente clienteExample);

}
