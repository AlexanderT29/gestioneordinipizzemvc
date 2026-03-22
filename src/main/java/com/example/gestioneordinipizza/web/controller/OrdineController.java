package com.example.gestioneordinipizza.web.controller;

import com.example.gestioneordinipizza.dto.ClienteDTO;
import com.example.gestioneordinipizza.dto.OrdineDTO;
import com.example.gestioneordinipizza.dto.PizzaDTO;
import com.example.gestioneordinipizza.model.Cliente;
import com.example.gestioneordinipizza.model.Ordine;
import com.example.gestioneordinipizza.service.cliente.ClienteService;
import com.example.gestioneordinipizza.service.ordine.OrdineService;
import com.example.gestioneordinipizza.service.pizza.PizzaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(value = "/ordine")
public class OrdineController {

    @Autowired
    private OrdineService ordineService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public ModelAndView listAllOrdini(){
        ModelAndView mv = new ModelAndView();
        List<Ordine> ordini = ordineService.listAllElements();
        mv.addObject("ordine_list_attribute", OrdineDTO.createOrdineDTOListFromModelList(ordini, true));
        mv.setViewName("ordine/list");
        return mv;
    }

    @PostMapping("/list")
    public ModelAndView listOrdini(OrdineDTO example) {
        ModelAndView mv = new ModelAndView();
        List<Ordine> ordini = ordineService.findByExample(example.buildOrdineFromDTO());
        mv.addObject("ordine_list_attribute", OrdineDTO.createOrdineDTOListFromModelList(ordini, true));
        mv.setViewName("ordine/list");
        return mv;
    }

    @GetMapping("/search")
    public String searchOrdine(Model model) {
        model.addAttribute("search_ordine_attr", new OrdineDTO());
        model.addAttribute("clienti_list_attribute", ClienteDTO.createClienteDTOListFromModelList(clienteService.listAllElements()));
        return "ordine/search";
    }


    @GetMapping("/insert")
    public String createOrdine(Model model) {
        model.addAttribute("insert_ordine_attr", new OrdineDTO());
        List<ClienteDTO> listaClienti = ClienteDTO.createClienteDTOListFromModelList(clienteService.listAllElements());
        List<PizzaDTO> listaPizze = PizzaDTO.createPizzaDTOListFromModelList(pizzaService.listAllElements());

        model.addAttribute("clienti_list_attribute", listaClienti);
        model.addAttribute("pizze_list_attribute", listaPizze);

        return "ordine/insert";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("insert_ordine_attr") OrdineDTO ordineDTO, BindingResult result,
                             Model model, RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            model.addAttribute("clienti_list_attribute", ClienteDTO.createClienteDTOListFromModelList(clienteService.listAllElements()));
            model.addAttribute("pizze_list_attribute", PizzaDTO.createPizzaDTOListFromModelList(pizzaService.listAllElements()));

            return "ordine/insert";
        }
        ordineDTO.setClosed(false);
        ordineDTO.setCodice("GOP" + LocalDateTime.now());
        ordineService.inserisciNuovo(ordineDTO.buildOrdineFromDTO());
        redirectAttrs.addFlashAttribute("successMessage", "Ordine creato con successo");
        return "redirect:/ordine";
    }

    @GetMapping("/edit/{idOrdine}")
    public String editOrdine(@PathVariable(required = true) Long idOrdine, Model model) {
        model.addAttribute("edit_ordine_attr",
                OrdineDTO.buildOrdineDTOFromModel(ordineService.caricaSingoloElementoEager(idOrdine), true));
        List<ClienteDTO> listaClienti = ClienteDTO.createClienteDTOListFromModelList(clienteService.listAllElements());
        List<PizzaDTO> listaPizze = PizzaDTO.createPizzaDTOListFromModelList(pizzaService.listAllElements());

        model.addAttribute("clienti_list_attribute", listaClienti);
        model.addAttribute("pizze_list_attribute", listaPizze);

        return "ordine/edit";
    }

    @PostMapping("/update")
    public String updateOrdine(@Valid @ModelAttribute("edit_ordine_attr") OrdineDTO ordineDTO, BindingResult result,
                                RedirectAttributes redirectAttrs, HttpServletRequest request, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("clienti_list_attribute", ClienteDTO.createClienteDTOListFromModelList(clienteService.listAllElements()));
            model.addAttribute("pizze_list_attribute", PizzaDTO.createPizzaDTOListFromModelList(pizzaService.listAllElements()));
            return "ordine/edit";
        }
        ordineService.aggiorna(ordineDTO.buildOrdineFromDTO());

        redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
        return "redirect:/ordine";
    }


    @GetMapping("/delete/{id}")
    public String chiudiOrdine(@PathVariable(required = true) Long id, RedirectAttributes redirectAttributes){
        try {
            Ordine ordineDaChiudere = ordineService.caricaSingoloElementoEager(id);
            ordineDaChiudere.setClosed(true);
            ordineService.aggiorna(ordineDaChiudere);
            OrdineDTO ordineDTO = OrdineDTO.buildOrdineDTOFromModel(ordineDaChiudere, false);
            redirectAttributes.addFlashAttribute("successMessage", "Ordine chiuso con successo.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

        }
        return "redirect:/ordine";
    }

    @GetMapping("/deletedefinitivo/{id}")
    public String rimuovi(@PathVariable(required = true) Long id, RedirectAttributes redirectAttributes){
        try {
            ordineService.rimuovi(id);
            redirectAttributes.addFlashAttribute("successMessage", "Ordine eliminato con successo.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

        }
        return "redirect:/ordine";


    }

    @GetMapping("/show/{idOrdine}")
    public String showOrdine(@PathVariable(required = true) Long idOrdine, Model model) {
        model.addAttribute("show_ordine_attr",
                OrdineDTO.buildOrdineDTOFromModel(ordineService.caricaSingoloElementoEager(idOrdine), true));
        Ordine ordine = ordineService.caricaSingoloElementoEager(idOrdine);
        /*System.out.println("--- DETECTIVE CLIENTE ---");
        System.out.println("Sto cercando il Cliente con ID: " + idCliente);
        if (cliente.getOrdini() != null) {
            System.out.println("Numero di ordini che Hibernate ha trovato nel DB: " + cliente.getOrdini().size());
        } else {
            System.out.println("ALLARME: La scatola degli ordini è NULL!");
        }

        // Convertiamo il DTO
        ClienteDTO dtoConvertito = ClienteDTO.buildClienteDTOFromModel(cliente);

        // Guardiamo cosa c'è DENTRO il DTO appena convertito!
        System.out.println("Numero di ordini sopravvissuti nel DTO: " + dtoConvertito.getOrdini().size());
        System.out.println("-------------------------");
        // --- FINE SPIA DEBUG ---
         */
        return "ordine/show";
    }

}
