package tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

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


    @ValueSource(strings = {"asdw@nail.com"})
    @ParameterizedTest(name = "Авторизация под пользаком \"{0}\"")
    void fillFormTwitchValueSource(String login) {
        $("button[data-a-target = login-button]").click();
        $("#login-username").setValue(login);
        $("#password-input").setValue("password");
        $("button[data-a-target = passport-login-button]").click();
        $("strong").shouldHave(text("Мы отключили возможность входа с помощью адреса электронной почты."));
    }

    static Stream<Arguments> checkButtonTwitch(){
        return Stream.of(
                Arguments.of("[data-a-target = user-card]", List.of("Войти", "Регистрация")),
                Arguments.of("[data-a-target = browse-link]", List.of("Просмотр"))
        );
    }
    @MethodSource("checkButtonTwitch")
    @ParameterizedTest
    void checkButtonTwitch(String place, List<String> nameButton) {
        $$(place).shouldHave(CollectionCondition.texts(nameButton));
    }
}
