package com.example.gestioneordinipizza.dto;

import com.example.gestioneordinipizza.model.Ordine;
import com.example.gestioneordinipizza.model.Pizza;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrdineDTO {

    private Long id;

    @NotNull(message = "{ordine.dataordine.notnull}")
    private LocalDateTime dataOrdine;

    @NotNull(message = "{ordine.closed.notnull}")
    private Boolean closed = false;

    @NotNull(message = "{ordine.costoTotale.notnull}")
    private Double costoTotale;

    @NotNull(message = "{ordine.insiemePizze.notnull}")
    private Set<PizzaDTO> insiemePizze = new HashSet<>();

    @NotNull(message = "{ordine.clienteDTO.notnull}")
    private ClienteDTO clienteDTO;

    private Long[] pizzeIds;

    public OrdineDTO() {
    }

    public OrdineDTO(Long id, LocalDateTime dataOrdine, Boolean closed, Double costoTotale) {
        this.id = id;
        this.dataOrdine = dataOrdine;
        this.closed = closed;
        this.costoTotale = costoTotale;
    }

    public OrdineDTO(Long id, LocalDateTime dataOrdine, Boolean closed, Double costoTotale, Set<PizzaDTO> insiemePizze, ClienteDTO clienteDTO) {
        this.id = id;
        this.dataOrdine = dataOrdine;
        this.closed = closed;
        this.costoTotale = costoTotale;
        this.insiemePizze = insiemePizze;
        this.clienteDTO = clienteDTO;
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

    public Set<PizzaDTO> getInsiemePizze() {
        return insiemePizze;
    }

    public void setInsiemePizze(Set<PizzaDTO> insiemePizze) {
        this.insiemePizze = insiemePizze;
    }

    public ClienteDTO getClienteDTO() {
        return clienteDTO;
    }

    public void setClienteDTO(ClienteDTO clienteDTO) {
        this.clienteDTO = clienteDTO;
    }

    public Long[] getPizzeIds() {
        return pizzeIds;
    }

    public void setPizzeIds(Long[] pizzeIds) {
        this.pizzeIds = pizzeIds;
    }

    public static OrdineDTO buildOrdineDTOFromModel(Ordine ordineModel, boolean includeClientiEPizze) {
        OrdineDTO result = new OrdineDTO();
        result.setId(ordineModel.getId());
        result.setDataOrdine(ordineModel.getDataOrdine());
        result.setClosed(ordineModel.getClosed());
        result.setCostoTotale(ordineModel.getCostoTotale());
        if(includeClientiEPizze) {
            if(ordineModel.getCliente() != null) {
                result.setClienteDTO(ClienteDTO.buildClienteDTOFromModel(ordineModel.getCliente()));
            }
            if (ordineModel.getPizze() != null && !ordineModel.getPizze().isEmpty()) {
                result.setInsiemePizze(ordineModel.getPizze().stream()
                        .map(PizzaDTO::buildPizzaDTOFromModel)
                        .collect(Collectors.toSet()));
            }
        }
        return result;
    }

    public Ordine buildOrdineFromDTO(){
        Ordine result = new Ordine();
        result.setId(this.getId());
        result.setDataOrdine(this.dataOrdine);
        result.setClosed(this.closed);
        result.setCostoTotale(this.costoTotale);
        if(this.clienteDTO != null) {
            result.setCliente(this.clienteDTO.buildClienteModel());
        }
        if (this.pizzeIds != null && this.pizzeIds.length > 0) {
            for (Long idPizza : this.pizzeIds) {
                Pizza pizzaModel = new Pizza();
                pizzaModel.setId(idPizza);
                result.getPizze().add(pizzaModel);
            }
        }
        return result;
    }

    public static List<OrdineDTO> createOrdineDTOListFromModelList(List<Ordine> modelListInput, boolean includeClientiEPizze) {
        if (modelListInput == null || modelListInput.isEmpty()) {
            return new ArrayList<>();
        }
        return modelListInput.stream().map(ordineEntity -> {
            return OrdineDTO.buildOrdineDTOFromModel(ordineEntity, includeClientiEPizze);
        }).collect(Collectors.toList());
    }
}
