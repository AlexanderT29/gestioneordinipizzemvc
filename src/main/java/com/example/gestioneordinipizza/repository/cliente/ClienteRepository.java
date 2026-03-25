package com.example.gestioneordinipizza.repository.cliente;

import com.example.gestioneordinipizza.dto.ClienteDTOConteggio;
import com.example.gestioneordinipizza.model.Cliente;
import com.example.gestioneordinipizza.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, PagingAndSortingRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente>, CustomClienteRepository{
    @Query("select c from Cliente c left join fetch c.ordini where c.id = :id")
    Cliente findClienteEager(@Param("id") Long id);

    @Query("select c from Cliente c left join fetch c.ordini o where o.costoTotale > 100 ")
    List<Cliente> cercaClientiVirtuosi();

    @Query("SELECT new com.example.gestioneordinipizza.dto.ClienteDTOConteggio(c.id, c.nome, c.cognome, COUNT(o)) " +
            "FROM Cliente c " +
            "LEFT JOIN c.ordini o " +
            "GROUP BY c.id, c.nome, c.cognome")
    List<ClienteDTOConteggio> findClientiConNumeroOrdini();
}
