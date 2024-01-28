package com.glushko.servcie.impl;

import com.glushko.servcie.abstracts.CashStorage;
import com.glushko.domain.Banknote;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Roman Glushko on 27.01.2024
 */
public class Vault implements CashStorage {
    private final Map<Integer, Banknote> notes;

    public Vault() {
        this.notes = new HashMap<>();
    }

    @Override
    public void addCash(String currency, int value, int number) {
        if (notes.containsKey(value)) {
            notes.get(value).addQuantity(number);
        } else {
            notes.put(value, new Banknote(value, number, currency));
        }
    }

    @Override
    public Map<Integer, Integer> getCash(String currency, int amount) {
        Map<Integer, Integer> cash = new HashMap<>();
        int remainingAmount = amount;

        for (int value : notes.keySet()) {
            int noteCount = Math.min(remainingAmount / value, notes.get(value).getQuantity());
            if (noteCount > 0) {
                cash.put(value, noteCount);
                remainingAmount -= value * noteCount;
            }
        }

        if (remainingAmount == 0) {
            for (int value : cash.keySet()) {
                notes.get(value).subtractQuantity(cash.get(value));
            }
            return cash;
        } else {
            return null;
        }
    }

    @Override
    public String printCash() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, Banknote> entry : notes.entrySet()) {
            result.append(entry.getValue().getCurrency()).append(" ").append(entry.getKey()).append(" ").append(entry.getValue().getQuantity()).append("\n");
        }
        return result.toString();
    }
}
