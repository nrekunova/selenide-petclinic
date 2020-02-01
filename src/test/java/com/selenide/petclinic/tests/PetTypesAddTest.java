package com.selenide.petclinic.tests;

import com.codeborne.selenide.Configuration;
import com.selenide.petclinic.page.PetTypesPage;
//import io.qameta.allure.Epic;
//import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

//@Epic("Petclinic")
//@Feature("Pet Types")
public class PetTypesAddTest {

    static {
        Configuration.baseUrl = com.selenide.petclinic.config.Configuration.getInstance().getMainUrl();
//        Configuration.timeout = 7000;
          Configuration.browser = com.selenide.petclinic.config.Configuration.getInstance().getBrowser();
    }



    @Test(description = "Add pet types test")
    private void addPetTypeTest() {
        PetTypesPage page = new PetTypesPage();

        String testType = "testType";
        page.openPage()
                .deleteIfExist(testType)
                .clickAddButton()
                .fillName(testType)
                .clickSaveButton()
                .openPage()
                .tableContainsPetType(testType);

    }
}
