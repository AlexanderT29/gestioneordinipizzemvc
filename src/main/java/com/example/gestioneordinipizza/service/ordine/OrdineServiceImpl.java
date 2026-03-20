package com.example.gestioneordinipizza.service.ordine;

import com.example.gestioneordinipizza.model.Ordine;
import com.example.gestioneordinipizza.repository.ordine.OrdineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrdineServiceImpl implements OrdineService{

    private OrdineRepository ordineRepository;

    @Override
    public List<Ordine> listAllElements() {
        return (List<Ordine>) ordineRepository.findAll();
    }

    @Override
    public Ordine caricaSingoloOrdine(Long id) {
        return ordineRepository.findById(id).orElse(null);
    }

    @Override
    public Ordine caricaSingoloElementoEager(Long id) {
        return null;
    }

    @Override
    @Transactional
    public void aggiorna(Ordine ordine) {
        ordineRepository.save(ordine);
    }

    @Override
    @Transactional
    public void inserisciNuovo(Ordine ordine) {
        ordineRepository.save(ordine);
    }

    @Override
    @Transactional
    public void rimuovi(Long idOrdine) {
        ordineRepository.deleteById(idOrdine);
    }
}
