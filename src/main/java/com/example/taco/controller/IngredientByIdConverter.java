package com.example.taco.controller;

import com.example.taco.data.Ingredient;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private final Map<String, Ingredient> ing_map = new HashMap<>();

    public IngredientByIdConverter() {
        ing_map.put("FLTO",
                new Ingredient("FLTO","Flour Tortilla", Ingredient.Type.WRAP));
        ing_map.put("COCO",
                new Ingredient("COTO","Corn Tortilla", Ingredient.Type.WRAP));
        ing_map.put("GRBF",
                new Ingredient("GRBF","Ground Beef", Ingredient.Type.PROTEIN));
        ing_map.put("CARN",
                new Ingredient("CARN","Carnitas", Ingredient.Type.PROTEIN));
        ing_map.put("TMTO",
                new Ingredient("TMTO","Diced Tomatoes", Ingredient.Type.VEGGIES));
        ing_map.put("LETC",
                new Ingredient("LETC","Lettuce", Ingredient.Type.VEGGIES));
        ing_map.put("CHED",
                new Ingredient("CHED","Cheddar", Ingredient.Type.CHEESE));
        ing_map.put("JACK",
                new Ingredient("JACK","Monterrey Jack", Ingredient.Type.CHEESE));
        ing_map.put("SLSA",
                new Ingredient("SLSA","Salsa", Ingredient.Type.SAUCE));
        ing_map.put("SRCR",
                new Ingredient("SRCR","Sour Cream", Ingredient.Type.SAUCE));

    }
    @Override
    public Ingredient convert(String id) {
        return ing_map.get(id);
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }

}
