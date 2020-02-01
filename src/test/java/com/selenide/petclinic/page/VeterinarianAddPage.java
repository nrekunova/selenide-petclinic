package com.selenide.petclinic.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
//import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class VeterinarianAddPage {

    protected void setInvalidValueAndCheckError(SelenideElement field, String errorText, CharSequence ... c) {
        field.sendKeys(c);
        SelenideElement error = field.parent().$(By.className("help-block"));
        Assert.assertEquals(error.getText(), errorText);
    }

    public VeterinarianAddPage openPage(){
        open("/vets/add");
        Selenide.Wait().withTimeout(Duration.ofSeconds(2));
        return this;
    }

    private SelenideElement getFirstNameField(){
        return $("#firstName");
    }
//    @Step("Set one letter to first name field")
    public VeterinarianAddPage setOneLetterToFirstNameAndCheckError (){
        SelenideElement field = getFirstNameField();
        field.clear();
        setInvalidValueAndCheckError(field, "First name must be at least 2 characters long", "t");
        return this;
    }
//    @Step("Clear first name field")
    public VeterinarianAddPage clearFirstNameAndCheckRequired() {
        SelenideElement field = getFirstNameField();
        setInvalidValueAndCheckError(field, "First name is required", Keys.BACK_SPACE );
        return this;
    }
//    @Step("Fill first name")
    public VeterinarianAddPage fillFirstName(String name) {
        SelenideElement field = getFirstNameField();
        field.clear();
        field.sendKeys(name);
        return this;
    }

    private SelenideElement getLastNameField(){
        return $("#lastName");
    }

//    @Step("Set one letter to last name field")
    public VeterinarianAddPage setOneLetterToLastNameAndCheckError (){
        SelenideElement field = getLastNameField();
        field.clear();
        setInvalidValueAndCheckError(field, "Last name must be at least 2 characters long", "t");
        return this;
    }

//    @Step("Clear last name from the field and check")
    public VeterinarianAddPage clearLastNameAndCheckRequired() {
        SelenideElement field = getLastNameField();
        setInvalidValueAndCheckError(field, "Last name is required", Keys.BACK_SPACE );
        return this;
    }

//    @Step("Fill last name")
    public VeterinarianAddPage fillLastName(String name) {
        SelenideElement field = getLastNameField();
        field.clear();
        field.sendKeys(name);
        return this;
    }

    public VeterinarianAddPage selectSpecialty(String specialty){
        Select selection = new Select($("#specialties"));
        selection.selectByVisibleText(specialty);
        return this;
    }

    public VeterinarianAddPage clickSave () {
        $(By.xpath("//button[contains(text(), 'Save Vet')]")).click();
//        Selenide.Wait().withTimeout(Duration.ofSeconds(4));
        Selenide.sleep(3000);
        return this;
    }
}
