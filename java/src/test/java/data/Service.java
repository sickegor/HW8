package data;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.selector.ByText;
import com.github.javafaker.Faker;
import pages.Locators;

import java.io.File;



public class Service {

    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String phoneNumber = faker.phoneNumber().phoneNumber();
    String subjects = faker.pokemon().name();

    public void fillForm() {
        fillName();
        fillSurname();
        fillEmail();
        clickRadioButton();
        fillPhoneNumber();
        fillPictureForm();
        fillSubjects();
        clickHobbies();
        fillAddress();
        clickState();
        clickCity();
    }

    public void clickButton() {
        Locators.submitButton.click();
    }

    public void assertForm() {
        Locators.nameForm.shouldHave(Condition.text("Thanks for submitting the form"));
    }

    private void fillName() {
        Locators.name.setValue(firstName);
    }

    private void fillSurname() {
        Locators.surname.setValue(lastName);
    }

    private void fillEmail() {
        Locators.email.setValue(email);
    }

    private void clickRadioButton() {
        Locators.radioButton.click();
    }

    private void fillPhoneNumber() {
        Locators.phone.setValue(phoneNumber);
    }

    private void fillPictureForm() {
        Locators.picture.uploadFile(new File("src/test/resources/pngegg.png"));
    }

    private void fillSubjects() {
        Locators.subjects.setValue(subjects);
    }

    private void clickHobbies() {
        Locators.hobbies.find(new ByText("Code")).click();
    }

    private void fillAddress() {
        Locators.currentAddress.setValue("Moscow");
    }

    private void clickState() {
        Locators.state.click();
        Locators.state.find(new ByText("NCR")).click();
    }

    private void clickCity() {
        Locators.city.click();
        Locators.city.find(new ByText("Delhi")).click();
    }


}
