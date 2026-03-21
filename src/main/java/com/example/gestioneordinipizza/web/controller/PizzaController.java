package com.example.gestioneordinipizza.web.controller;

import com.example.gestioneordinipizza.dto.ClienteDTO;
import com.example.gestioneordinipizza.dto.PizzaDTO;
import com.example.gestioneordinipizza.model.Cliente;
import com.example.gestioneordinipizza.model.Pizza;
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

import java.util.List;

@Controller
@RequestMapping(value = "/pizza")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping({"", "/", "/list"})
    public ModelAndView listAllPizze(){
        ModelAndView mv = new ModelAndView();
        List<Pizza> pizze = pizzaService.listAllElements();
        mv.addObject("pizza_list_attribute", PizzaDTO.createPizzaDTOListFromModelList(pizze));
        mv.setViewName("pizza/list");
        return mv;
    }

    @PostMapping("/list")
    public ModelAndView listPizze(PizzaDTO example) {
        ModelAndView mv = new ModelAndView();
        List<Pizza> pizze = pizzaService.findByExample(example.buildPizzaModel());
        mv.addObject("pizza_list_attribute", PizzaDTO.createPizzaDTOListFromModelList(pizze));
        mv.setViewName("pizza/list");
        return mv;
    }

    @GetMapping("/insert")
    public String pizzaCliente(Model model) {
        model.addAttribute("insert_pizza_attr", new PizzaDTO());
        return "pizza/insert";
    }

    @PostMapping("/save")
    public String savePizza(@Valid @ModelAttribute("insert_pizza_attr") PizzaDTO pizzaDTO, BindingResult result,
                              RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            return "pizza/insert";
        }
        pizzaDTO.setAttiva(true);
        pizzaService.inserisciNuovo(pizzaDTO.buildPizzaModel());
        redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
        return "redirect:/pizza";
    }

    @GetMapping("/search")
    public String searchPizza() {
        return "pizza/search";
    }

    @GetMapping("/delete/{id}")
    public String rimuovi(@PathVariable(required = true) Long id, RedirectAttributes redirectAttributes){
        try {
            pizzaService.disattivaPizza(id);
            redirectAttributes.addFlashAttribute("successMessage", "Pizza disattivata con successo.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

        }
        return "redirect:/pizza";
    }

    @GetMapping("/show/{idPizza}")
    public String showPizza(@PathVariable(required = true) Long idPizza, Model model) {
        model.addAttribute("show_pizza_attr",
                PizzaDTO.buildPizzaDTOFromModel(pizzaService.caricaSingoloPizza(idPizza)));
        return "pizza/show";
    }

    @GetMapping("/edit/{idPizza}")
    public String editPizza(@PathVariable(required = true) Long idPizza, Model model) {
        model.addAttribute("edit_pizza_attr",
                PizzaDTO.buildPizzaDTOFromModel(pizzaService.caricaSingoloPizza(idPizza)));
        return "pizza/edit";
    }

    @PostMapping("/update")
    public String updatePizza(@Valid @ModelAttribute("edit_pizza_attr") PizzaDTO pizzaDTO, BindingResult result,
                                RedirectAttributes redirectAttrs, HttpServletRequest request) {

        if (result.hasErrors()) {
            return "pizza/edit";
        }
        pizzaService.aggiorna(pizzaDTO.buildPizzaModel());

        redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
        return "redirect:/pizza";
    }
}
