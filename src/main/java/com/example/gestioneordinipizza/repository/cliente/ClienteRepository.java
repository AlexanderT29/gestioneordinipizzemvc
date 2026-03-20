package com.example.gestioneordinipizza.repository.cliente;

import com.example.gestioneordinipizza.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, PagingAndSortingRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente>, CustomClienteRepository{

}
