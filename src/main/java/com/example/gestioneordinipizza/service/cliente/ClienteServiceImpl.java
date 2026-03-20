package com.example.gestioneordinipizza.service.cliente;

import com.example.gestioneordinipizza.model.Cliente;
import com.example.gestioneordinipizza.repository.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listAllElements() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    @Override
    public Cliente caricaSingoloCliente(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente caricaSingoloElementoEager(Long id) {
        return null;
    }

    @Override
    @Transactional
    public void aggiorna(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public void inserisciNuovo(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public void rimuovi(Long idCliente) {
        clienteRepository.deleteById(idCliente);
    }

    @Override
    @Transactional
    public void disattivaCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente non trovato"));
        cliente.setAttivo(false);
        clienteRepository.save(cliente);
    }
}
