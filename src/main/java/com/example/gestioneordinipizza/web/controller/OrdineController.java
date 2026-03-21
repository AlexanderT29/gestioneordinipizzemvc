package com.example.gestioneordinipizza.web.controller;

import com.example.gestioneordinipizza.dto.ClienteDTO;
import com.example.gestioneordinipizza.dto.OrdineDTO;
import com.example.gestioneordinipizza.dto.PizzaDTO;
import com.example.gestioneordinipizza.model.Cliente;
import com.example.gestioneordinipizza.model.Ordine;
import com.example.gestioneordinipizza.service.cliente.ClienteService;
import com.example.gestioneordinipizza.service.ordine.OrdineService;
import com.example.gestioneordinipizza.service.pizza.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public ModelAndView listAllClienti(){
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
        ordineService.inserisciNuovo(ordineDTO.buildOrdineFromDTO());
        redirectAttrs.addFlashAttribute("successMessage", "Ordine creato con successo");
        return "redirect:/ordine";
    }

}
