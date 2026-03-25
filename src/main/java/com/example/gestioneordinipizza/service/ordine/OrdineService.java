package com.example.gestioneordinipizza.service.ordine;

import com.example.gestioneordinipizza.model.Ordine;

import java.time.LocalDateTime;
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

    public List<Ordine> ordiniTraDate(LocalDateTime dataInizio, LocalDateTime dataFine);

    public double calcolaPrezzoOrdine(Long idOrdine, int ordiniEffettuati);



}
