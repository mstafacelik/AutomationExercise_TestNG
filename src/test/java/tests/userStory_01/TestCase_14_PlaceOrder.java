package tests.userStory_01;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AutomationExercise;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBaseRapor;

import java.util.Random;

public class TestCase_14_PlaceOrder extends TestBaseRapor {

    AutomationExercise automationExercise;
    SoftAssert softAssert;
    Random random;
    Faker faker;
    Actions actions;

    @Test
    public void placeOrder() {

        automationExercise = new AutomationExercise();
        softAssert = new SoftAssert();
        random = new Random();
        faker=new Faker();
        actions =new Actions(Driver.getDriver());


        extentTest = extentReports.createTest("Test von AutomationExercise");

        Driver.getDriver();
        extentTest.info("Browser wurde gestartet.");


        Driver.getDriver().get(ConfigReader.getProperty("AutomationExerciseUrl"));
        extentTest.info("Es wurde zur URL „http://automationexercise.com“ navigiert.");


        softAssert.assertTrue(automationExercise.logoAutomationExercise.isDisplayed());
        extentTest.info("Es wurde überprüft, dass die Startseite erfolgreich sichtbar ist.");



        for (int i = 0; i < 3; i++) {
            int productsSize = automationExercise.viewProductsList.size();
            int randomProduct = random.nextInt(productsSize);
            automationExercise.viewProductsList.get(randomProduct).click();
            Driver.waitAndClick(automationExercise.addToChartSchaltfläche);
            Driver.waitAndClick(automationExercise.continueShoppingSchaltfläche);
            Driver.waitAndClick(automationExercise.homeLinkHomePage);
            i++;

        }


        automationExercise.cartLink.click();

        softAssert.assertTrue(automationExercise.proceedToCheckOutSchaltfläche.isDisplayed());

        automationExercise.proceedToCheckOutSchaltfläche.click();
        automationExercise.registerLogInUnterCheckOut.click();

        automationExercise.newUserNameBox.sendKeys(faker.name().fullName(),
          Keys.TAB, faker.internet().emailAddress(), Keys.TAB, Keys.ENTER);

        automationExercise.radioTitleMr.click();
        automationExercise.paswordBoxZumKontoErstellen.sendKeys(faker.internet().password());
        Driver.selectByIndex(automationExercise.geburtsdatumTagSelect, 15);
        Driver.selectByIndex(automationExercise.geburtsdatumMonatSelect, 9);
        Driver.selectByValue(automationExercise.geburtsdatumJahrSelect, "1990");
        extentTest.info("zur Registration wurde der Titel angekruezt und Passwort und," +
                " Geburtsdatum wurden eingegeben.");

        String firstNameFaker= faker.name().firstName();
        String lastNameFaker= faker.name().lastName();
        String companyFaker=faker.company().name();
        String adresseFaker= faker.address().fullAddress();
        String country="Canada";
        String stateFaker=faker.address().state();
        String cityFaker=faker.address().city();
        String zipCode=faker.address().zipCode();
        String phoneFaker=faker.phoneNumber().phoneNumber();


        actions.click(automationExercise.vornameTextBox)
                .sendKeys(firstNameFaker, Keys.TAB, lastNameFaker, Keys.TAB, companyFaker, Keys.TAB, adresseFaker,
                        Keys.TAB, Keys.TAB, country, Keys.TAB, stateFaker, Keys.TAB, cityFaker, Keys.TAB,zipCode,
                        Keys.TAB,phoneFaker)
                .perform();

        Driver.wait(2);
        extentTest.info("zur Registration wurden die erforderlichen Daten eingegeben.");

        automationExercise.kontoErstellenSchaltfläche.click();
        extentTest.info("Es wurde auf die Schaltfläche „Create Account button“ geklickt.");


        Driver.wait(2);
        automationExercise.accountCreatedText.isDisplayed();
        extentTest.info("Es wurde überprüft, dass 'ACCOUNT CREATED!' ist sichtbar.");


        automationExercise.continueSchaltflächeUnterAccountCreatedText.click();
        extentTest.info("Es wurde auf die Schaltfläche „Continue“ geklickt.");

        Driver.wait(2);

        softAssert.assertTrue(automationExercise.loggedInAsText.isDisplayed());


        Driver.waitAndClick(automationExercise.cartLink);
        Driver.waitAndClick(automationExercise.proceedToCheckOutSchaltfläche);
        Driver.wait(5);


        String actulFullName="Mr." +" "+ firstNameFaker + " " + lastNameFaker;
        String expectedFullName=automationExercise.firstAndLastNameUnterAdresseDetails.getText();
        softAssert.assertEquals(actulFullName,expectedFullName);

        String actulCompany=companyFaker;
        String expectedCompany=automationExercise.companyUnterAdresseDetails.getText();
        softAssert.assertEquals(actulCompany,expectedCompany);

        String actualAdresse=adresseFaker;
        String  expectedAdresse=automationExercise.adresseUnterAdresseDetails.getText();
        softAssert.assertEquals(actualAdresse,expectedAdresse);


        String actualCountry=country;
        String expectedCountry=automationExercise.countryUnterAdresseDetails.getText();
        softAssert.assertEquals(actualCountry,expectedCountry);

        String actualCityStateZipCode=cityFaker + " " + stateFaker + " " + zipCode;
        String expectedCityStateZipCode=automationExercise.cityStateZipCodeUnterAdresseDetails.getText();
        softAssert.assertEquals(actualCityStateZipCode,expectedCityStateZipCode);

        String actualPhoneNummer=phoneFaker;
        String expectedPhoneNummer=automationExercise.phoneUnterAdresseDetails.getText();
        softAssert.assertEquals(actualPhoneNummer,expectedPhoneNummer);

        softAssert.assertAll();








    }

}