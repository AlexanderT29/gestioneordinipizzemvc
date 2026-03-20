package com.example.gestioneordinipizza.repository.pizza;

import com.example.gestioneordinipizza.model.Cliente;
import com.example.gestioneordinipizza.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long>, PagingAndSortingRepository<Pizza, Long>, JpaSpecificationExecutor<Pizza>, CustomPizzaRepository {

}
