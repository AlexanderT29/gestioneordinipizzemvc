package com.example.gestioneordinipizza.repository.ordine;

import com.example.gestioneordinipizza.dto.ClienteDTO;
import com.example.gestioneordinipizza.dto.ClienteDTOConteggio;
import com.example.gestioneordinipizza.model.Cliente;
import com.example.gestioneordinipizza.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrdineRepository extends JpaRepository<Ordine, Long>, PagingAndSortingRepository<Ordine, Long>, JpaSpecificationExecutor<Ordine>, CustomOrdineRepository {

    @Query("select o from Ordine o left join fetch o.cliente")
    List<Ordine> findAllOrdiniEager();

    @Query("select o from Ordine o left join fetch o.cliente left join fetch o.pizze where o.id = :id")
    Ordine findByIdEager(@Param("id") Long id);

    @Query("select o from Ordine o left join fetch o.cliente where o.dataOrdine between :dataInizio and :dataFine")
    List<Ordine> ordiniTraDueDate(@Param("dataInizio")LocalDateTime dataInizio, @Param("dataFine") LocalDateTime dataFine);

    @NativeQuery("SELECT c.id, c.nome, c.cognome, COUNT(o.id) AS numeroordini " +
            "FROM cliente c " +
            "JOIN ordine o ON ordine.cliente_id = cliente.id " +
            "GROUP BY  c.id, c.nome, c.cognome")
    List<ClienteDTOConteggio> contaOrdini(@Param("id") Long id);
}
