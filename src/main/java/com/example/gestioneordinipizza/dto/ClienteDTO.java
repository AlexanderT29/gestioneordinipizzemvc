package com.example.gestioneordinipizza.dto;

import com.example.gestioneordinipizza.model.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteDTO {

    private Long id;
    @NotBlank(message = "{cliente.nome.notblank}")
    private String nome;
    @NotBlank(message = "{cliente.cognome.notblank}")
    private String cognome;
    @NotBlank(message = "{cliente.inidirizzo.notblank}")
    private String indirizzo;

    private Boolean attivo;

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String nome, String cognome, String indirizzo, Boolean attivo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.attivo = attivo;
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

    public Cliente buildClienteModel() {
        return new Cliente(this.id, this.nome, this.cognome, this.indirizzo, this.attivo);
    }

    public static ClienteDTO buildClienteDTOFromModel(Cliente cliente){
        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getCognome(), cliente.getIndirizzo(), cliente.getAttivo());
    }

    public static List<ClienteDTO> createClienteDTOListFromModelList(List<Cliente> listInput){
        return listInput.stream().map(clienteEntity ->{
            return ClienteDTO.buildClienteDTOFromModel(clienteEntity);
        }).collect(Collectors.toList());
    }
}
