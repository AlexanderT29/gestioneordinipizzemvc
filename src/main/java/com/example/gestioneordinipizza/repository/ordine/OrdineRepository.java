package com.example.gestioneordinipizza.repository.ordine;

import com.example.gestioneordinipizza.model.Cliente;
import com.example.gestioneordinipizza.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdineRepository extends JpaRepository<Ordine, Long>, PagingAndSortingRepository<Ordine, Long>, JpaSpecificationExecutor<Ordine>, CustomOrdineRepository {

    @Query("select o from Ordine o left join fetch o.cliente")
    List<Ordine> findAllOrdiniEager();

    @Query("select o from Ordine o left join fetch o.cliente left join fetch o.pizze where o.id = :id")
    Ordine findByIdEager(@Param("id") Long id);


}
