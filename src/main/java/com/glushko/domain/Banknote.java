package com.glushko.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Roman Glushko on 27.01.2024
 */
@AllArgsConstructor
@Getter
public class Banknote {
    private int value;
    private int quantity;
    private String currency;

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void subtractQuantity(int quantity) {
        if (this.quantity >= quantity) {
            this.quantity -= quantity;
        }
    }
}