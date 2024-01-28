package com.glushko.servcie.abstracts;

/**
 * Created by Roman Glushko on 27.01.2024
 */
public interface CashMachine {
    String processCommand(String command);

    boolean checkAction(String action);

    boolean checkCurrency(String currency);

    boolean checkValue(int value);
}
