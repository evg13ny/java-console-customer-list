import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String phoneRegex = "^(\\+7)[0-9]{10}$";
        String emailRegex = "^(.+)@(\\S+)$";

        String[] components = data.split("\\s+");

        if (components.length != 4) {
            throw new IllegalArgumentException("Wrong format. Correct format:\n" +
                    "add Vasily Petrov vasily.petrov@gmail.com +79215637722");
        }

        if (!components[INDEX_PHONE].matches(phoneRegex)) {
            throw new IllegalArgumentException("Wrong phone format. Correct format:\n" +
                    "+79215637722");
        }

        if (!components[INDEX_EMAIL].matches(emailRegex)) {
            throw new IllegalArgumentException("Wrong email format. Correct format:\n" +
                    "vasily.petrov@gmail.com");
        }

        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
        System.out.println("The client " + name + " has been successfully added to the list");
    }

    public void listCustomers() {
        if (storage.isEmpty()) {
            throw new IllegalArgumentException("The clients list is empty :(");
        }

        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        if (!storage.containsKey(name)) {
            throw new IllegalArgumentException("The client " + name + " is not in the list :(");
        }

        storage.remove(name);
        System.out.println("The client " + name + " has been successfully deleted from the list");
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}
