package com.glushko.servcie.impl;

import com.glushko.servcie.abstracts.CashMachine;
import com.glushko.servcie.abstracts.CashStorage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Roman Glushko on 27.01.2024
 */
public class ATM implements CashMachine {
    private final Map<String, CashStorage> cashStorages;

    public ATM() {
        this.cashStorages = new HashMap<>();
    }

    @Override
    public String processCommand(String command) {
        String action = null;
        String currency = null;
        int value = 0;
        int number = 0;

        String[] parts = command.split(" ");

        for (int i = 0; i < parts.length; i++) {
            switch (i) {
                case 0:
                    action = parts[i];
                    break;
                case 1:
                    currency = parts[i];
                    break;
                case 2:
                    value = Integer.parseInt(parts[i]);
                    break;
                case 3:
                    number = Integer.parseInt(parts[i]);
                    break;
            }
        }

        if (!checkAction(action)) {
            return "ERROR";
        } else {
            if (action.equals("+") && checkAction(action) && checkCurrency(currency) && checkValue(value) && number > 0) {
                if (cashStorages.containsKey(currency)) {
                    cashStorages.get(currency).addCash(currency, value, number);
                    return "OK";
                } else {
                    CashStorage cashStorage = new Vault();
                    cashStorage.addCash(currency, value, number);
                    cashStorages.put(currency, cashStorage);
                    return "OK";
                }
            } else if (action.equals("-") && checkAction(action) && checkCurrency(currency)) {
                if (cashStorages.containsKey(currency)) {
                    Map<Integer, Integer> cash = cashStorages.get(currency).getCash(currency, value);
                    if (cash != null) {
                        StringBuilder result = new StringBuilder();
                        for (Map.Entry<Integer, Integer> entry : cash.entrySet()) {
                            result.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
                        }
                        result.append("OK");
                        return result.toString();
                    } else {
                        return "ERROR";
                    }
                } else {
                    return "ERROR";
                }
            } else if (command.equals("?")) {
                StringBuilder result = new StringBuilder();
                for (CashStorage cashStore : cashStorages.values()) {
                    result.append(cashStore.printCash());
                }
                result.append("OK");
                return result.toString();
            } else {
                return "ERROR";
            }
        }
    }

    @Override
    public boolean checkAction(String action) {
        return action != null && (action.equals("?") || action.equals("+") || action.equals("-"));
    }

    @Override
    public boolean checkCurrency(String currency) {
        return currency != null && (currency.length() == 3 && currency.toUpperCase().equals(currency));
    }

    @Override
    public boolean checkValue(int value) {
        boolean isMacthed = false;
        for (int n = 0; n <= 3; n++) {
            if (isMacthed) {
                return true;
            }
            int degreeNumber = (int) Math.pow(10, n);
            isMacthed = value == degreeNumber || (value == degreeNumber * 5);
        }
        return isMacthed;
    }
}
