package com.glushko;

import com.glushko.servcie.abstracts.CashMachine;
import com.glushko.servcie.impl.ATM;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        CashMachine atm = new ATM();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                break;
            }
            String result = atm.processCommand(command);
            System.out.println(result);
        }
    }
}
