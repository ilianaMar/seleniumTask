package pages;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.github.javafaker.Faker;

import java.util.Locale;


public class RegisterPage extends BasePage{
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    By emailInputId = By.xpath("//input[@name='data[email]']");
    By firstPasswordInputId = By.xpath("//input[@name='data[password1]']");
    By secondPasswordInputId = By.xpath("//input[@name='data[password2]']");
    By phoneInputId = By.xpath("//input[@name='data[phone]']");
    By firstNameInputId = By.xpath("//input[@name='data[firstname]']");
    By lastNameInputId = By.xpath("//input[@name='data[family]']");
    By postcodeInputId = By.xpath("//input[@name='data[postcode]']");
    By cityInputId = By.xpath("//input[@name='data[city]']");
    By addressInputId = By.xpath("//input[@name='data[address]']");
    By gdprCheckboxId =  By.xpath("//input[@name='data[agree_gdpr]']");
    By confirmAgeCheckboxId =  By.xpath("//input[@name='data[confirm_age]']");
    By orderButtonId =  By.xpath("//a[@data-submit-name='register']");

    public User createUserAccount(){
        Faker bgFaker = new Faker(new Locale("bg-BG"));
        Faker faker = new Faker();
        User user = User.builder()
                .email(faker.internet().emailAddress())
                .firstName(bgFaker.name().firstName())
                .lastName(bgFaker.name().firstName())
                .phone(bgFaker.phoneNumber().cellPhone())
                .address(bgFaker.address().streetAddress())
                .city(bgFaker.address().cityName())
                .postcode(String.valueOf(bgFaker.number().numberBetween(1000, 9000)))
                .password(faker.internet().password())
                .build();

        System.out.println(user);
        writeText(emailInputId, user.getEmail());
        writeText(firstPasswordInputId, user.getPassword());
        writeText(secondPasswordInputId, user.getPassword());
        writeText(phoneInputId, user.getPhone());
        writeText(firstNameInputId, user.getFirstName());
        writeText(lastNameInputId, user.getLastName());
        writeText(postcodeInputId, user.getPostcode());
        writeText(cityInputId, user.getCity());
        writeText(addressInputId, user.getAddress());
        find(gdprCheckboxId).click();
        find(confirmAgeCheckboxId).click();
        find(orderButtonId).click();
        assertCurrentPageUrl("/register/?done");

        return user;
    }
}
