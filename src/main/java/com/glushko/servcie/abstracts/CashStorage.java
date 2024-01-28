package com.glushko.servcie.abstracts;

import java.util.Map;

/**
 * Created by Roman Glushko on 27.01.2024
 */
public interface CashStorage {
    void addCash(String currency, int value, int number);

    Map<Integer, Integer> getCash(String currency, int amount);

    String printCash();
}
