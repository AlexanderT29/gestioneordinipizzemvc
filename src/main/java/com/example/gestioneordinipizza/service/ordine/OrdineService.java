package com.example.gestioneordinipizza.service.ordine;

import com.example.gestioneordinipizza.model.Ordine;

import java.util.List;

public interface OrdineService {

    public List<Ordine> listAllElements();

    public Ordine caricaSingoloOrdine(Long id);

    public void aggiorna(Ordine ordine);

    public void inserisciNuovo(Ordine ordine);

    public void rimuovi(Long idOrdine);

    public List<Ordine> listAllElementsWithCliente();

    public Ordine caricaSingoloElementoEager(Long id);

    public List<Ordine> findByExample(Ordine ordineExample);

}
