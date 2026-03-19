package com.example.gestioneordinipizza.service.pizza;

import com.example.gestioneordinipizza.model.Pizza;
import com.example.gestioneordinipizza.repository.pizza.PizzaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService{

    private PizzaRepository pizzaRepository;

    @Override
    public List<Pizza> listAllElements() {
        return (List<Pizza>) pizzaRepository.findAll();
    }

    @Override
    public Pizza caricaSingoloPizza(Long id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    @Override
    public Pizza caricaSingoloElementoEager(Long id) {
        return null;
    }

    @Override
    public void aggiorna(Pizza pizza) {
        pizzaRepository.save(pizza);
    }

    @Override
    public void inserisciNuovo(Pizza pizza) {
        pizzaRepository.save(pizza);
    }

    @Override
    public void rimuovi(Long idPizza) {
        pizzaRepository.deleteById(idPizza);
    }

    @Override
    public void disattivaPizza(Long id) {
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pizza non trovata"));
        pizza.setAttivo(false);
        pizzaRepository.save(pizza);
    }
}
