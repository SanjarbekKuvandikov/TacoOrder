package com.example.taco.controller;

import com.example.taco.repository.IngredientRepository;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.example.taco.data.Ingredient;
import com.example.taco.data.Taco;
import com.example.taco.data.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    private final IngredientRepository ingredientRepository;

    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
            filterByType(ingredients,type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order(){
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    private List<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
        List<Ingredient> filteredIngredients = new ArrayList<>();
        for(Ingredient ingredient : ingredients) {
            if(ingredient.getType().equals(type)) {
                filteredIngredients.add(ingredient);
            }
        }
        return filteredIngredients;
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {

        if (errors.hasErrors()) {
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco.toString());
        return "redirect:/orders/current";
    }
}
