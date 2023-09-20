import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    private static final String ADD_COMMAND = "add Vasily Petrov " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Vasily Petrov";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();

        while (true) {
            System.out.println("Enter help if you need help or press 0 for exit");

            try {
                String command = scanner.nextLine();
                String[] tokens = command.split("\\s+", 2);

                logger.log(Level.INFO, command);

                switch (tokens[0]) {
                    case "0" -> {
                        scanner.close();
                        System.out.println("Bye");
                        System.exit(0);
                    }
                    case "add" -> {
                        if (tokens.length != 2) {
                            throw new IllegalArgumentException("Wrong format. Correct format:\n" +
                                    "add Vasily Petrov vasily.petrov@gmail.com +79215637722");
                        }

                        executor.addCustomer(tokens[1]);
                    }
                    case "list" -> executor.listCustomers();
                    case "remove" -> {
                        if (tokens.length != 2) {
                            throw new IllegalArgumentException("Wrong format. Correct format:\n" +
                                    "remove Vasily Petrov");
                        }

                        executor.removeCustomer(tokens[1]);
                    }
                    case "count" -> System.out.println("There are " + executor.getCount() + " customers");
                    case "help" -> System.out.println(helpText);
                    default -> System.out.println(COMMAND_ERROR);
                }
            } catch (IllegalArgumentException e) {
                logger.log(Level.WARN, e.getMessage());

                System.out.println(e.getMessage());
            }
        }
    }
}
