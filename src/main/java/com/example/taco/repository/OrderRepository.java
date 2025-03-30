package com.example.taco.repository;

import com.example.taco.data.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder tacoOrder);
}
