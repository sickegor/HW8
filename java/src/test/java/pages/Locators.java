package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Locators {

    static public SelenideElement
            name = $("#firstName"),
            surname = $("#lastName"),
            email = $("#userEmail"),
            radioButton = $("[for=gender-radio-1]"),
            phone = $("#userNumber"),
            picture = $("#uploadPicture"),
            dateOfBorn = $("#dateOfBirthInput"),
            subjects = $("#subjectsInput"),
            hobbies = $("#hobbiesWrapper"),
            currentAddress = $("#currentAddress"),
            state = $("#state"),
            city = $("#city"),
            submitButton = $("#submit"),
            nameForm = $("#modal-title h4");
}
