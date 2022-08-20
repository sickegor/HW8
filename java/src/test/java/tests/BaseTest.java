package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

    @CsvSource(value = "chrome")
    @ParameterizedTest(name = "Открытие браузера \"{0}\"")
    @BeforeEach
    void openPage() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        open("https://www.twitch.tv");
    }

    @CsvSource(value = {
            "asdw@nail.com | 12wd23e3",
            "dwadwa@dwad.com | 123eadaww"
    },
            delimiter = '|')
    @ParameterizedTest(name = "Авторизация под пользаком \"{0}\"")
    void fillFormTwitch(String login, String password) {
        $("button[data-a-target = login-button]").click();
        $("#login-username").setValue(login);
        $("#password-input").setValue(password);
        $("button[data-a-target = passport-login-button]").click();
        $("strong").shouldHave(text("Мы отключили возможность входа с помощью адреса электронной почты."));
    }

    static Stream<Arguments> checkButtonTwitch(){
        return Stream.of(
                Arguments.of("Войти", "Регистрация")
        );
    }
    @MethodSource("checkButtonTwitch")
    @ParameterizedTest
    void checkButtonTwitch(String nameButton) {
        $("[data-a-target = user-card]").shouldHave(text(nameButton));
    }
}
