package com.example.gestioneordinipizza.service.pizza;

import com.example.gestioneordinipizza.model.Pizza;

import java.util.List;

public interface PizzaService {

    public List<Pizza> listAllElements();

    public Pizza caricaSingoloPizza(Long id);

    public Pizza caricaSingoloElementoEager(Long id);

    public void aggiorna(Pizza pizza);

    public void inserisciNuovo(Pizza pizza);

    public void rimuovi(Long idPizza);

    public void disattivaPizza(Long id);

    public List<Pizza> findByExample(Pizza pizzaExample);
}
