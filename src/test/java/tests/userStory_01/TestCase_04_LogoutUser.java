package tests.userStory_01;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AutomationExercise;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBaseRapor;

public class TestCase_04_LogoutUser extends TestBaseRapor {


    AutomationExercise automationExercise = new AutomationExercise();
    Actions actions = new Actions(Driver.getDriver());
    Faker faker = new Faker();
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void LogoutUser (){

        extentTest = extentReports.createTest("Test von AutomationExercise");

        Driver.getDriver();
        extentTest.info("Browser wurde gestartet.");


        Driver.getDriver().get(ConfigReader.getProperty("AutomationExerciseUrl"));
        extentTest.info("Es wurde zur URL „http://automationexercise.com“ navigiert.");


        automationExercise.logoAutomationExercise.isDisplayed();
        extentTest.info("Es wurde überprüft, dass die Startseite erfolgreich sichtbar ist.");


        automationExercise.signupLoginLink.click();
        extentTest.info("Es wurde auf die Schaltfläche „Signup / Login“ geklickt.");

        automationExercise.loginToYourAccountText.isDisplayed();

        automationExercise.logInEMailAdresseBox
                .sendKeys(ConfigReader.getProperty("AECorrectEmailAdresse"),
                        Keys.TAB + ConfigReader.getProperty("AECorrectPassword"), Keys.TAB, Keys.ENTER);


       softAssert.assertTrue(automationExercise.loggedInAsText.isDisplayed());

        Driver.wait(3);


        automationExercise.logOutLink.click();


        softAssert.assertTrue(automationExercise.signupLoginLink.isDisplayed());

        softAssert.assertAll();







    }

}
