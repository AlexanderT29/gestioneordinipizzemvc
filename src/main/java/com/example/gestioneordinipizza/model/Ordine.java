package com.example.gestioneordinipizza.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ordine")
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "dataordine")
    private LocalDateTime dataOrdine;

    @Column(name = "closed")
    private Boolean closed = false;
    @Column(name = "costototale")
    private Double costoTotale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToMany
    @JoinTable(name = "ordine_pizza", joinColumns = @JoinColumn(name = "ordine_id"), inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    private Set<Pizza> pizze = new HashSet<>();

    public Ordine() {
    }

    public Ordine(Long id, LocalDateTime dataOrdine, Boolean closed, Double costoTotale, Cliente cliente) {
        this.id = id;
        this.dataOrdine = dataOrdine;
        this.closed = closed;
        this.costoTotale = costoTotale;
        this.cliente = cliente;
    }

    public Ordine(LocalDateTime dataOrdine, Boolean closed, Double costoTotale, Cliente cliente) {
        this.dataOrdine = dataOrdine;
        this.closed = closed;
        this.costoTotale = costoTotale;
        this.cliente = cliente;
    }

    public Ordine(LocalDateTime dataOrdine, Boolean closed, Double costoTotale) {
        this.dataOrdine = dataOrdine;
        this.closed = closed;
        this.costoTotale = costoTotale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(LocalDateTime dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public Double getCostoTotale() {
        return costoTotale;
    }

    public void setCostoTotale(Double costoTotale) {
        this.costoTotale = costoTotale;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<Pizza> getPizze() {
        return pizze;
    }

    public void setPizze(Set<Pizza> pizze) {
        this.pizze = pizze;
    }

    public void addPizza(Pizza pizza){
        this.pizze.add(pizza);
        pizza.getOrdini().add(this);
    }

    public void removePizza(Pizza pizza){
        this.pizze.remove(pizza);
        pizza.getOrdini().remove(this);
    }
}
