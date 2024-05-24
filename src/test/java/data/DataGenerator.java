package data;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

import static java.time.LocalDate.now;



public class DataGenerator {

    private static Faker faker;
    @BeforeEach
    void setUpAll() {
        faker = new Faker(new Locale("ru"));
    }

    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        return now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity(String locale) {

        var faker = new Faker(new Locale(locale));
                // String city = String.valueOf(faker.address().city());
        return faker.address().city();
    }

    public static String generateName(String locale) {
        //String name = String.valueOf(faker.name().fullName());
        var faker = new Faker(new Locale(locale));
        return faker.name().fullName();
    }

    public static String generatePhone(String locale) {
        //String phoneNumber = String.valueOf(faker.phoneNumber().phoneNumber());
        var faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            // TODO: добавить логику для создания пользователя user с использованием методов generateCity(locale),
            // generateName(locale), generatePhone(locale)
            return new UserInfo(generateCity(locale), generateName(locale), generatePhone(locale));
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
