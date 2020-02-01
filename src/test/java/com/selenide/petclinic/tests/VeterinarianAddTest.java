package com.selenide.petclinic.tests;


import com.codeborne.selenide.Configuration;
import com.selenide.petclinic.page.VeterinarianAddPage;
import com.selenide.petclinic.page.VeterinariansPage;
import com.selenide.petclinic.pojo.Veterinarian;
//import io.qameta.allure.Epic;
//import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//@Epic("Petclinic")
//@Feature("Veterinarian")
public class VeterinarianAddTest {

    static {
        Configuration.baseUrl = com.selenide.petclinic.config.Configuration.getInstance().getMainUrl();
        Configuration.timeout = 7000;
        Configuration.browser = com.selenide.petclinic.config.Configuration.getInstance().getBrowser();
    }

    @Test(description = "Add new veterinarian test")
    private void addVeterinarianTest() {
        Veterinarian veterinarian = new Veterinarian();
        veterinarian.setFirstName("testFirstName");
        veterinarian.setLastName("testLastName");
        veterinarian.setType("surgery");

        new VeterinarianAddPage().openPage()
                .setOneLetterToFirstNameAndCheckError()
                .clearFirstNameAndCheckRequired()
                .fillFirstName(veterinarian.getFirstName())
                .setOneLetterToLastNameAndCheckError()
                .clearLastNameAndCheckRequired()
                .fillLastName(veterinarian.getLastName())
                .selectSpecialty(veterinarian.getType())
                .clickSave();

        new VeterinariansPage().openPage()
                .tableContainsVeterinarian(veterinarian);

    }
}
