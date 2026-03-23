package com.example.gestioneordinipizza.service.ordine;

import com.example.gestioneordinipizza.model.Ordine;
import com.example.gestioneordinipizza.model.Pizza;
import com.example.gestioneordinipizza.repository.ordine.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrdineServiceImpl implements OrdineService{

    @Autowired
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
        return ordineRepository.findByIdEager(id);
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

    @Override
    public List<Ordine> listAllElementsWithCliente() {
        return ordineRepository.findAllOrdiniEager();
    }

    @Override
    public List<Ordine> findByExample(Ordine ordineExample) {
        return ordineRepository.findByExample(ordineExample);
    }

    @Override
    public List<Ordine> ordiniTraDate(LocalDateTime dataInizio, LocalDateTime dataFine) {
        if(dataInizio.isAfter(dataFine)){
            throw new RuntimeException("La data di inizio non può essere superiore alla data di fine!");
        }
        return ordineRepository.ordiniTraDueDate(dataInizio, dataFine);
    }

    @Override
    public double calcolaPrezzoOrdine(Long idOrdine) {
        Ordine ordine = ordineRepository.findByIdEager(idOrdine);
        double result = 0.0;
        if(ordine == null || ordine.getPizze() == null || ordine.getPizze().isEmpty()){
            throw new RuntimeException("Questo ordine non esiste o non ci sono pizze ordinate.");
        }
        for(Pizza pizza: ordine.getPizze()){
            result += pizza.getPrezzo();
        }
        result *= 1.15;
        ordine.setCostoTotale(result);
        ordineRepository.save(ordine);
        return result;
    }
}
