package com.selenide.petclinic.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;

public class PetTypesPage {


//    @Step("Open page")
    public PetTypesPage openPage(){
        open("/pettypes");
        Selenide.sleep(3000);
        return this;
    }


//    @Step("Delete pet type if it's existing")
    public PetTypesPage deleteIfExist(String petType){
        if(getPetTypes().contains(petType)){
            $(By.xpath("//input[@ng-reflect-model='" + petType + "']")).parent().parent()
                    .$(By.xpath("./td/button[contains(text(), 'Delete')]"))
                    .shouldBe(Condition.enabled).click();
            Selenide.sleep(2000);
            openPage();

            Assert.assertFalse(getPetTypes().contains(petType), "New pet type shouldn't be in the table");
        }
        return this;
    }

    private List<String> getPetTypes() {
        List<String> pettypes = new ArrayList<>();
        $$(By.xpath("//table[@id='pettypes']/tbody/tr/td/input[@type='text']")).forEach(se -> {
//        driver.findElements(By.xpath("//table[@id='pettypes']/tbody/tr/td/input[@type='text']")).forEach(we -> {
//            pettypes.add(we.getAttribute("ng-reflect-model"));
            pettypes.add(se.getAttribute("ng-reflect-model"));
        });
        return pettypes;
    }

//    @Step("Click 'Add' button to add pet type")
    public PetTypesPage clickAddButton() {
        $(By.xpath("//button[contains(text(), ' Add ')]")).shouldBe(Condition.enabled).click();
        return this;
//        WebElement addButton = driver.findElement(By.xpath("//button[contains(text(), ' Add ')]"));
//        addButton.click();
    }

//    @Step("Fill pet type's neme")
    public PetTypesPage fillName(String petType) {
        $("#name").shouldBe(Condition.enabled).setValue(petType);
        return this;
//        WebElement inputName = driver.findElement(By.id("name"));
//        inputName.sendKeys(petType);
    }
//    @Step("Click 'Save' button")
    public PetTypesPage clickSaveButton (){
        $(By.xpath("//*[contains(text(), 'Save')]")).shouldBe(Condition.enabled).click();
        Selenide.Wait().withTimeout(Duration.ofSeconds(3));
        return this;
//        WebElement saveButton = driver.findElement(By.xpath("//*[contains(text(), 'Save')]"));
//        saveButton.click();
//        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
    }

    public PetTypesPage tableContainsPetType(String petType) {
        Assert.assertTrue(getPetTypes().contains(petType), "New pet type should appear in the table");
        return this;
    }

}
