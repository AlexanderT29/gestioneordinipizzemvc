package com.example.gestioneordinipizza.dto;

import com.example.gestioneordinipizza.model.Cliente;
import com.example.gestioneordinipizza.model.Ordine;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClienteDTOConteggio {
    private Long id;
    @NotBlank(message = "{cliente.nome.notblank}")
    private String nome;
    @NotBlank(message = "{cliente.cognome.notblank}")
    private String cognome;
    @NotBlank(message = "{cliente.inidirizzo.notblank}")
    private String indirizzo;
    @NotBlank(message = "{cliente.stato.notblank}")
    private String stato;


    private Boolean attivo;

    private int ordini;

    public ClienteDTOConteggio() {
    }


    public ClienteDTOConteggio(Long id, String nome, String cognome, String indirizzo, Boolean attivo, int ordini, String stato) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.attivo = attivo;
        this.ordini = ordini;
        this.stato = stato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Boolean getAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }

    public int getOrdini() {
        return ordini;
    }

    public void setOrdini(int ordini) {
        this.ordini = ordini;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }


    public ClienteDTOConteggio(Long id, String nome, String cognome, Long ordini) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.ordini = (ordini != null) ? ordini.intValue() : 0;
        if(this.ordini > 9 && this.ordini < 20){
            this.stato = "SILVER";
        } else if (this.ordini >= 20){
            this.stato = "GOLD";
        } else {
            this.stato = "NORMALE";
        }
    }

    public Cliente buildClienteModel() {
        Cliente result = new Cliente();
        result.setId(this.id);
        result.setNome(this.nome);
        result.setCognome(this.cognome);
        result.setIndirizzo(this.indirizzo);
        result.setAttivo(this.attivo);
        return result;
    }

    public static ClienteDTOConteggio buildClienteDTOConteggioFromModel(Cliente cliente){
        ClienteDTOConteggio result = new ClienteDTOConteggio();
        result.setId(cliente.getId());
        result.setNome(cliente.getNome());
        result.setCognome(cliente.getCognome());
        result.setIndirizzo(cliente.getIndirizzo());
        result.setAttivo(cliente.getAttivo());
        if (cliente.getOrdini() != null) {
            result.setOrdini(cliente.getOrdini().size());
        }
        if(result.ordini > 9 && result.ordini < 20){
            result.setStato("SILVER");
        } else if (result.ordini>= 20){
            result.setStato("GOLD");
        } else {
            result.setStato("NORMALE");
        }
        return result;
    }

    public static List<ClienteDTOConteggio> createClienteDTOConteggioListFromModelList(List<Cliente> listInput){
        return listInput.stream().map(clienteEntity ->{
            return ClienteDTOConteggio.buildClienteDTOConteggioFromModel(clienteEntity);
        }).collect(Collectors.toList());
    }
}
