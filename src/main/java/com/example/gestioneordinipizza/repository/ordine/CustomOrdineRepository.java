package com.example.gestioneordinipizza.repository.ordine;

import com.example.gestioneordinipizza.model.Ordine;

import java.util.List;

public interface CustomOrdineRepository {

    List<Ordine> findByExample(Ordine ordineExample);
}
