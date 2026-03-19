package com.example.gestioneordinipizza.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "descrizione")
    private String descrizione;
    @Column(name = "ingredienti")
    private List<String> ingredienti;
    @Column(name = "prezzo")
    private double prezzo;
    @Column(name = "attivo")
    private boolean attivo = true;

    @ManyToMany(mappedBy = "pizze")
    private Set<Ordine> ordini = new HashSet<>(0);

    public Pizza() {
    }

    public Pizza(Long id, String descrizione, List<String> ingredienti, double prezzo, boolean attivo) {
        this.id = id;
        this.descrizione = descrizione;
        this.ingredienti = ingredienti;
        this.prezzo = prezzo;
        this.attivo = attivo;
    }

    public Pizza(String descrizione, List<String> ingredienti, double prezzo, boolean attivo) {
        this.descrizione = descrizione;
        this.ingredienti = ingredienti;
        this.prezzo = prezzo;
        this.attivo = attivo;
    }

    public Pizza(String descrizione, List<String> ingredienti, double prezzo) {
        this.descrizione = descrizione;
        this.ingredienti = ingredienti;
        this.prezzo = prezzo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<String> getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(List<String> ingredienti) {
        this.ingredienti = ingredienti;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }

    public Set<Ordine> getOrdini() {
        return ordini;
    }

    public void setOrdini(Set<Ordine> ordini) {
        this.ordini = ordini;
    }
}
