// load JAR-file: "java -jar artifacts/app-replan-delivery.jar" before starting test
package test;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

class DeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        // TODO: добавить логику теста в рамках которого будет выполнено планирование и перепланирование встречи.
        // Для заполнения полей формы можно использовать пользователя validUser и строки с датами в переменных
        // firstMeetingDate и secondMeetingDate. Можно также вызывать методы generateCity(locale),
        // generateName(locale), generatePhone(locale) для генерации и получения в тесте соответственно города,
        // имени и номера телефона без создания пользователя в методе generateUser(String locale) в датагенераторе
        SelenideElement form = $("form");
        ElementsCollection button = $$("button");

        // Первое заполнение анкеты
        form.find("[data-test-id=city] input").setValue(validUser.getCity());
        form.find("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        form.find("[data-test-id=date] input").setValue(firstMeetingDate);
        form.find("[data-test-id=name] input").setValue(validUser.getName());
        form.find("[data-test-id=phone] input").setValue(validUser.getPhone());
        form.find("[data-test-id='agreement']").click();
        button.get(1).click();
        $(".notification__content").shouldHave(exactText("Встреча успешно запланирована на " + firstMeetingDate));
        button.get(1).click();
        button.get(3).click();
        $(".notification__content").shouldHave(exactText("Встреча успешно запланирована на " + firstMeetingDate));
    }
}
