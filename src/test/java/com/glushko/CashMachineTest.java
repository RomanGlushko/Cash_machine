package com.glushko;

import com.glushko.servcie.abstracts.CashMachine;
import com.glushko.servcie.impl.ATM;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Roman Glushko on 27.01.2024
 */
public class CashMachineTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }


    @Test
    public void testCashMachineWorkflow() {
        List<String> commands = new ArrayList<>();
        commands.add("?");
        commands.add("+ USD 100 30");
        commands.add("+ RUR 250 10");
        commands.add("+ CHF 100 5");
        commands.add("+ USD 10 50");
        commands.add("?");
        commands.add("- USD 120");
        commands.add("- RUR 500");
        commands.add("- CHF 250");
        commands.add("?");
        commands.add("+ eur 105");
        commands.add("- CHF 400");
        commands.add("+ CHF 10 50");
        commands.add("?");

        CashMachine atm = new ATM();
        for (String command : commands) {
            System.out.println(atm.processCommand(command));
        }

        assertEquals(
                "OK\n" +
                        "OK\n" +
                        "ERROR\n" +
                        "OK\n" +
                        "OK\n" +
                        "CHF 100 5\n" +
                        "USD 100 30\n" +
                        "USD 10 50\n" +
                        "OK\n" +
                        "100 1\n" +
                        "10 2\n" +
                        "OK\n" +
                        "ERROR\n" +
                        "ERROR\n" +
                        "CHF 100 5\n" +
                        "USD 100 29\n" +
                        "USD 10 48\n" +
                        "OK\n" +
                        "ERROR\n" +
                        "100 4\n" +
                        "OK\n" +
                        "OK\n" +
                        "CHF 100 1\n" +
                        "CHF 10 50\n" +
                        "USD 100 29\n" +
                        "USD 10 48\n" +
                        "OK", outputStreamCaptor.toString()
                        .trim());
    }
}