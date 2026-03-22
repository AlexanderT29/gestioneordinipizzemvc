package com.example.gestioneordinipizza.repository.ordine;

import com.example.gestioneordinipizza.model.Cliente;
import com.example.gestioneordinipizza.model.Ordine;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomOrdineRepositoryImpl implements CustomOrdineRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Ordine> findByExample(Ordine ordineExample) {
        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        List<String> whereClauses = new ArrayList<String>();

        StringBuilder queryBuilder = new StringBuilder("select o from Ordine o where o.id = o.id ");

        if(ordineExample.getDataOrdine() != null){
            whereClauses.add(" o.dataOrdine >= :dataordine");
            paramaterMap.put("dataordine", ordineExample.getDataOrdine());
        }
        if(ordineExample.getClosed() != null) {
            whereClauses.add(" o.closed = :closed");
            paramaterMap.put("closed", ordineExample.getClosed());
        }

        if(ordineExample.getCostoTotale() != null){
            whereClauses.add(" o.costoTotale >= :costoTotale");
            paramaterMap.put("costoTotale", ordineExample.getCostoTotale());
        }

        if(StringUtils.isNoneBlank(ordineExample.getCodice())){
            whereClauses.add(" o.codice = :codice");
            paramaterMap.put("codice", ordineExample.getCodice());
        }

        queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
        queryBuilder.append(StringUtils.join(whereClauses, " and "));

        TypedQuery<Ordine> typedQuery = entityManager.createQuery(queryBuilder.toString(), Ordine.class);

        for (String key : paramaterMap.keySet()) {
            typedQuery.setParameter(key, paramaterMap.get(key));
        }

        return typedQuery.getResultList();
    }
}
