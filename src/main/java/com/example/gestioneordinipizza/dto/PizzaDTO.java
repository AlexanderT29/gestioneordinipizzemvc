package com.example.gestioneordinipizza.dto;

import com.example.gestioneordinipizza.model.Cliente;
import com.example.gestioneordinipizza.model.Pizza;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class PizzaDTO {

    private Long id;
    @NotBlank(message = "{pizza.descrizione.notblank}")
    private String descrizione;
    @NotBlank(message = "{pizza.ingredienti.notblank}")
    private String ingredienti;
    @NotNull(message = "{pizza.prezzo.notnull}")
    @Min(1)
    private Double prezzo;

    private Boolean attiva;

    public PizzaDTO() {
    }

    public PizzaDTO(Long id, String descrizione, String ingredienti, Double prezzo, Boolean attiva) {
        this.id = id;
        this.descrizione = descrizione;
        this.ingredienti = ingredienti;
        this.prezzo = prezzo;
        this.attiva = attiva;
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

    public String getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public Boolean getAttiva() {
        return attiva;
    }

    public void setAttiva(Boolean attiva) {
        this.attiva = attiva;
    }

    public Pizza buildPizzaModel() {
        return new Pizza(this.id, this.descrizione, this.ingredienti, this.prezzo, this.attiva);
    }

    public static PizzaDTO buildPizzaDTOFromModel(Pizza pizza){
        return new PizzaDTO(pizza.getId(), pizza.getDescrizione(), pizza.getIngredienti(), pizza.getPrezzo(), pizza.getAttivo());
    }

    public static List<PizzaDTO> createPizzaDTOListFromModelList(List<Pizza> listInput){
        return listInput.stream().map(pizzaEntity ->{
            return PizzaDTO.buildPizzaDTOFromModel(pizzaEntity);
        }).collect(Collectors.toList());
    }
}
