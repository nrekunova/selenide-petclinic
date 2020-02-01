package com.selenide.petclinic.page;

import com.codeborne.selenide.*;

import com.selenide.petclinic.pojo.OwnerUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class NewOwnerPage {


//    @Step
    public NewOwnerPage openPage() {
        open("/owners/add");
        Selenide.Wait().withTimeout(Duration.ofSeconds(2));
        return this;
    }

    public NewOwnerPage addOwnerButtonIsDisabled (){
        $(By.xpath("//*[contains(text(), 'Add Owner')]")).shouldBe(Condition.disabled);
        return this;
    }

    private SelenideElement getFirstNameField(){
        return $("#firstName");
    }

    protected void setInvalidValueAndCheckError(SelenideElement field, String errorText, CharSequence ... c) {
        field.sendKeys(c);
        SelenideElement error = field.parent().$(By.className("help-block"));
        Assert.assertEquals(error.getText(), errorText);
    }

//    @Step("Set one letter to first name and check error message")
    public NewOwnerPage setOneLetterToFirstNameAndCheckError (){
        SelenideElement field = getFirstNameField();
        field.clear();
        setInvalidValueAndCheckError(field, "First name must be at least 2 characters long", "t");
        return this;
    }
//    @Step("Clear first name and check")
    public NewOwnerPage clearFirstNameAndCheckRequired() {
        SelenideElement field = getFirstNameField();
        setInvalidValueAndCheckError(field, "First name is required", Keys.BACK_SPACE );
        return this;
    }
//    @Step("Fill first name")
    public NewOwnerPage fillFirstName(String name) {
        SelenideElement field = getFirstNameField();
        field.clear();
        field.setValue(name);
        return this;
    }


    private SelenideElement getLastNameField(){
        return $("#lastName");
    }
//    @Step("Set one letter to last name and check error message")
    public NewOwnerPage setOneLetterToLastNameAndCheckError (){
        SelenideElement field = getLastNameField();
        field.clear();
        setInvalidValueAndCheckError(field, "Last name must be at least 2 characters long", "t");
        return this;
    }
//    @Step("Clear last name and check")
    public NewOwnerPage clearLastNameAndCheckRequired() {
        SelenideElement field = getLastNameField();
        setInvalidValueAndCheckError(field, "Last name is required", Keys.BACK_SPACE );
        return this;
    }
//    @Step("Fill last name")
    public NewOwnerPage fillLastName(String name) {
        SelenideElement field = getLastNameField();
        field.clear();
        field.setValue(name);
        return this;
    }

    private SelenideElement getTelephoneField(){
        return $("#telephone");
    }
    public NewOwnerPage setLetterToTelephoneAndCheckError (){
        SelenideElement field = getTelephoneField();
        field.clear();
        setInvalidValueAndCheckError(field, "Phone number only accept digits", "t");
        return this;
    }
//    @Step("Clear telephone and check")
    public NewOwnerPage clearTelephoneAndCheckRequired() {
        SelenideElement field = getTelephoneField();
        setInvalidValueAndCheckError(field, "Phone number is required", Keys.BACK_SPACE );
        return this;
    }
//    @Step("Fill telephone")
    public NewOwnerPage fillTelephone(String text) {
        SelenideElement field = getTelephoneField();
        field.clear();
        field.setValue(text);
        return this;
    }

    private SelenideElement getCityField(){
        return $("#city");
    }

//    @Step("Clear city and check")
    public NewOwnerPage clearCityAndCheckRequired() {
        SelenideElement field = getCityField();
        setInvalidValueAndCheckError(field, "City is required", Keys.BACK_SPACE );
        return this;
    }
//    @Step("Fill City")
    public NewOwnerPage fillCity(String text) {
        SelenideElement field = getCityField();
        field.clear();
        field.setValue(text);
        return this;
    }

    private SelenideElement getAddressField(){
        return $("#address");
    }

//    @Step("Clear address and check")
    public NewOwnerPage clearAddressAndCheckRequired() {
        SelenideElement field = getAddressField();
        setInvalidValueAndCheckError(field, "Address is required", Keys.BACK_SPACE );
        return this;
    }
//    @Step("Fill address")
    public NewOwnerPage fillAddress(String text) {
        SelenideElement field = getAddressField();
        field.clear();
        field.setValue(text);
        return this;
    }

//    @Step("Check 'Add owner' button is anabled")
    public NewOwnerPage addOwnerButtonIsEnabled (){
        $(By.xpath("//*[contains(text(), 'Add Owner')]")).shouldBe(Condition.enabled);
        return this;
    }

//    @Step("Click 'Add owner' button")
    public NewOwnerPage clickAddOwnerAndWaitForOwnersPage(OwnerUI owner){
        $((By.xpath("//*[contains(text(), 'Add Owner')]"))).shouldBe(Condition.enabled).click();
//        Selenide.Wait().withTimeout(Duration.ofSeconds(5));
        Selenide.sleep(3000);
//        Assert.assertEquals( WebDriverRunner.url(), Configuration.baseUrl + "/owners");
        Assert.assertTrue(getOwnersList().contains(owner), "New test owner should be present in the table");
        return this;
    }


    private OwnerUI createOwner(SelenideElement userRow) {
        OwnerUI owner = new OwnerUI();
        String fullName = userRow.$(By.xpath("./td/a")).getText();
        String[] fullNameArray = fullName.split(" ");
        if (fullNameArray.length > 1) {
            owner.setFirstName(fullNameArray[0]);
            owner.setLastName(fullNameArray[1]);
        } else {
            owner.setFirstName(fullNameArray[0]);
        }
        owner.setAddress(userRow.$(By.xpath("./td[2]")).getText());
        owner.setCity(userRow.$(By.xpath("./td[3]")).getText());
        owner.setTelephone(userRow.$(By.xpath("./td[4]")).getText());

        String pets = userRow.$(By.xpath("./td[5]")).getText();
        if (!pets.isEmpty()) {
            owner.setPets(pets);
        }
        return owner;
    }

    public List<OwnerUI> getOwnersList() {
        List<OwnerUI> owners = new ArrayList<>();
        SelenideElement ownersTable = $(By.xpath("//*[@class='table-responsive']"));

        List<SelenideElement> ownersList = ownersTable.$$(By.xpath(".//tbody/tr"));
        for (SelenideElement userRow : ownersList) {
            owners.add(createOwner(userRow));
        }

        return owners;
    }
}
