package com.example.gestioneordinipizza.repository.ordine;

import com.example.gestioneordinipizza.model.Cliente;
import com.example.gestioneordinipizza.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrdineRepository extends JpaRepository<Ordine, Long>, PagingAndSortingRepository<Ordine, Long>, JpaSpecificationExecutor<Ordine> {
}
