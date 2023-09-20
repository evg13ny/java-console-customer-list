import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Checking for throwing exceptions")
public class ExceptionTests {

    private CustomerStorage storage;

    @BeforeEach
    void beforeEach() {
        storage = new CustomerStorage();
    }

    @Test
    @DisplayName("Passed more than 4 words in the line")
    void moreThenFourElementsInputString() {
        final String input = "Vasily Petrov vasily.petrov@gmail.com +79215637722 5word";

        assertThrows(Throwable.class, () -> storage.addCustomer(input),
                "An exception was not thrown when the number of elements in the line were greater than 4");
    }

    @Test
    @DisplayName("Passed less than 4 words in the line")
    void lessThanFourElementsInputString() {
        final String input = "Vasily Petrov vasily.petrov@gmail.com";
        assertThrows(Throwable.class, () -> storage.addCustomer(input),
                "An exception was not thrown when the number of elements in the line were less than 4");
    }

    @Test
    @DisplayName("Wrong email format")
    void wrongEmailFormatWithoutAt() {
        final String wrongEmail = "thisIsNotAnEmail";
        final String input = "Vasily Petrov " + wrongEmail + " +79215637722";

        assertThrows(Throwable.class, () -> storage.addCustomer(input),
                "An exception was not thrown when the email format was wrong -> " + wrongEmail);
    }

    @Test
    @DisplayName("Wrong phone format")
    void wrongPhoneFormatWithoutDigits() {
        final String wrongPhoneNumber = "+thisIsNotANumber";
        final String input = "Vasily Petrov hello@skillbox.ru " + wrongPhoneNumber;

        assertThrows(Throwable.class, () -> storage.addCustomer(input),
                "An exception was not thrown when the phone format was wrong -> " + wrongPhoneNumber);
    }

    @Test
    @DisplayName("Test for adding correct customer's data")
    void insertCorrectData() {
        final String name = "Vasily Petrov";
        final String email = "hello@skillbox.ru";
        final String phone = "+79991234567";
        final String input = String.join(" ", name, email, phone);

        storage.addCustomer(input);
        assertEquals(1, storage.getCount());

        Customer customer = storage.getCustomer(name);
        assertEquals(name, customer.getName());
        assertEquals(email, customer.getEmail());
        assertEquals(phone, customer.getPhone());
    }
}
