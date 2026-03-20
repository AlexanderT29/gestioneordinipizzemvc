package com.example.gestioneordinipizza.repository.pizza;

import com.example.gestioneordinipizza.model.Pizza;

import java.util.List;

public interface CustomPizzaRepository {

    public List<Pizza> findAllByExample(Pizza pizzaExample);
}
