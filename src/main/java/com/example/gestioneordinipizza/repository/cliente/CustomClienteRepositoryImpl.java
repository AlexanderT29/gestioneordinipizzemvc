package com.example.gestioneordinipizza.repository.cliente;

import com.example.gestioneordinipizza.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomClienteRepositoryImpl implements CustomClienteRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cliente> findByExample(Cliente clienteExample) {
        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        List<String> whereClauses = new ArrayList<String>();

        StringBuilder queryBuilder = new StringBuilder("select c from Cliente c where c.id = c.id ");

        if(StringUtils.isNotEmpty(clienteExample.getNome())){
            whereClauses.add(" c.nome like :nome");
            paramaterMap.put("nome", "%" + clienteExample.getNome() + "%");
        }
        if(StringUtils.isNotEmpty(clienteExample.getCognome())){
            whereClauses.add(" c.cognome like :cognome");
            paramaterMap.put("cognome", "%" + clienteExample.getCognome() +"%");
        }
        if(StringUtils.isNotEmpty(clienteExample.getIndirizzo())){
            whereClauses.add(" c.indirizzo like :indirizzo");
            paramaterMap.put("indirizzo", "%" + clienteExample.getIndirizzo() +"%");
        }
        if(clienteExample.getAttivo() != null) {
            whereClauses.add(" c.attivo = :attivo");
            paramaterMap.put("attivo", clienteExample.getAttivo());
        }

        queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
        queryBuilder.append(StringUtils.join(whereClauses, " and "));

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(queryBuilder.toString(), Cliente.class);

        for (String key : paramaterMap.keySet()) {
            typedQuery.setParameter(key, paramaterMap.get(key));
        }

        return typedQuery.getResultList();
    }
}
