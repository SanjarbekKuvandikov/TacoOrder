package com.example.taco.controller;

import com.example.taco.data.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    @GetMapping("/current")
    public String orderForm(){
        return "orderFrom";
    }

    @PostMapping
    public String processOrder(TacoOrder tacoOrder, SessionStatus status){
        log.info("Order submitted = {}", tacoOrder);
        status.setComplete();

        return "redirect:/";
    }
}
