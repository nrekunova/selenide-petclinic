package com.selenide.petclinic.tests;

import com.codeborne.selenide.Configuration;
import com.selenide.petclinic.page.NewOwnerPage;
import com.selenide.petclinic.pojo.OwnerUI;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//@Epic("Petclinic")
//@Feature("Pets Owners")

public class AddOwnerTest {
    static {
        Configuration.baseUrl = com.selenide.petclinic.config.Configuration.getInstance().getMainUrl();
//        Configuration.timeout = 7000;
        Configuration.browser = com.selenide.petclinic.config.Configuration.getInstance().getBrowser();
    }

    @Test(description = "Add owner, save and validation test")
    public void saveButtonAndValidationTest() {
        new NewOwnerPage()
                .openPage()
                .addOwnerButtonIsDisabled()
                .setOneLetterToFirstNameAndCheckError()
                .clearFirstNameAndCheckRequired()
                .fillFirstName("Tom")
                .setOneLetterToLastNameAndCheckError()
                .clearLastNameAndCheckRequired()
                .fillLastName("Ford")
                .setLetterToTelephoneAndCheckError()
                .clearTelephoneAndCheckRequired()
                .fillTelephone("12345")
                .fillCity("t")
                .clearCityAndCheckRequired()
                .fillCity("New York")
                .fillAddress("t")
                .clearAddressAndCheckRequired()
                .fillAddress("5th Avenue, 4")
                .addOwnerButtonIsEnabled();
    }

    @Test(description = "Fill owner's data test")
    public void addOwner() {

        OwnerUI owner = new OwnerUI();
        owner.setFirstName("testFirstName");
        owner.setLastName("testLastName");
        owner.setAddress("testAddress");
        owner.setCity("testCity");
        owner.setTelephone("123456");

        new NewOwnerPage()
                .openPage()
                .fillFirstName(owner.getFirstName())
                .fillLastName(owner.getLastName())
                .fillAddress(owner.getAddress())
                .fillCity(owner.getCity())
                .fillTelephone(owner.getTelephone())
                .clickAddOwnerAndWaitForOwnersPage(owner);
    }

}
