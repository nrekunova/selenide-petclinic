package com.selenide.petclinic.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.selenide.petclinic.pojo.Veterinarian;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class VeterinariansPage {

    public VeterinariansPage openPage() {
        open("/vets");
//        Selenide.Wait().withTimeout(Duration.ofSeconds(4));
        Selenide.sleep(3000);
        return this;
    }

    public VeterinariansPage tableContainsVeterinarian (Veterinarian veterinarian){
        Assert.assertTrue(getVeterinarianList().contains(veterinarian), "New test veterinarian should be present in the table");
        return this;
    }

    public List<Veterinarian> getVeterinarianList() {
        List<Veterinarian> veterinarians = new ArrayList<>();
        $(By.xpath("//*[@class='table table-striped']")).$$(By.xpath(".//tbody/tr")).forEach(selenideElement -> {
            veterinarians.add(createVeterinarian(selenideElement));
        });
        System.out.println("getVeterinarianList :");
        veterinarians.forEach(v -> System.out.println(v));
        return veterinarians;
    }

    private Veterinarian createVeterinarian(SelenideElement userRow) {
        Veterinarian veterinarian = new Veterinarian();
        String fullName = userRow.findElement(By.xpath("./td[1]")).getText();
        String[] fullNameArray = fullName.split(" ");
        if (fullNameArray.length > 1) {
            veterinarian.setFirstName(fullNameArray[0]);
            veterinarian.setLastName(fullNameArray[1]);
        } else {
            veterinarian.setFirstName(fullNameArray[0]);
        }
        String type = userRow.findElement(By.xpath("./td[2]")).getText();
        if(!type.isEmpty()) {
            veterinarian.setType(type);
        }
        return veterinarian;
    }

}
