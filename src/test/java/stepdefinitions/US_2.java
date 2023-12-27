package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.RegistrationPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.List;


public class US_2 {

    Faker faker = new Faker();
    RegistrationPage registrationPage = new RegistrationPage();


    @Given("User go to GMI BAnk homepage")
    public void userGoToGMIBAnkHomepage() {
        Driver.getDriver().get(ConfigReader.getProperty("gmi_url"));

    }

    @And("User go to Registration page")
    public void userGoToRegistrationPage() {
        registrationPage.accountmenu.click();
        registrationPage.register.click();
    }
    @And("User enters SSN number {string}")
    public void userEntersSSNNumber(String ssn) {
        registrationPage.ssn.sendKeys(ssn);
//        String a = "" +faker.number().numberBetween(1000,9999);
//        registrationPage.ssn.sendKeys(a);


    }

    @And("user enters firstname {string}")
    public void userEntersFirstname(String first) {
        registrationPage.firstname.sendKeys(first);
    }

    @And("user enters lastname {string}")
    public void userEntersLastname(String last) {
        registrationPage.lastname.sendKeys(last);
    }

    @And("user enters address {string}")
    public void userEntersAddress(String address) {
        registrationPage.address.sendKeys(address);

    }
    @And("user enters mobile phone {string}")
    public void userEntersMobilePhone(String number) {

        registrationPage.mobilephone.sendKeys(number);

    }

    @And("user enters username {string}")
    public void userEntersUsername(String username) {
        registrationPage.username.sendKeys(username);

    }

    @And("user enters email {string}")
    public void userEntersEmail(String email) throws InterruptedException {
        Thread.sleep(2000);
        registrationPage.email.sendKeys(email);

    }

    @And("user enters new password {string}")
    public void userEntersNewPassword(String password) {
        registrationPage.firstPassword.sendKeys(password);

    }

    @And("user enters new password confirmation {string}")
    public void userEntersNewPasswordConfirmation(String secondpassword) {
        registrationPage.secondPassword.sendKeys(secondpassword);

    }

    @And("user clicks register")
    public void userClicksRegister() {
        registrationPage.registerbutton.click();


    }

    @Then("User verifies the email invalid message")
    public void userVerifiesTheEmailInvalidMessage() {
        Assert.assertTrue(registrationPage.emailtextinvalid.getText().equals("This field is invalid"));


    }
    @Then("User verifies the Mobilephone number invalid message")
    public void userVerifiesTheMobilephoneNumberInvalidMessage() {
        Assert.assertTrue(registrationPage.mobilephonetextinvalid.getText().equals("Your mobile phone number is invalid"));

    }

    @Then("User verifies the SSN invalid message")
    public void userVerifiesTheSSNInvalidMessage() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println(registrationPage.ssntextinvalid.getText());
        String expected =registrationPage.ssntextinvalid.getText();
        Assert.assertTrue(registrationPage.ssntextinvalid.getText().equals("Your SSN is invalid"));

        //Assert.assertTrue(us_2.ssntextinvalid.getText().contains("Your SSN is invalid\n"));
    }
    @And("user should not registration")
    public void userShouldNotRegistration() {

        List<WebElement> elementExist = Driver.getDriver().findElements(By.xpath("//div[@class='Toastify_toast Toastify_toast--success toastify-toast']"));

        if(elementExist.size()>0) {
            Assert.assertFalse(registrationPage.Alert.isDisplayed());
            System.out.println("user registired with blank box");
        }else
            Assert.assertTrue(registrationPage.message.isDisplayed());


//        Assert.assertTrue(registrationPage.message.isDisplayed());
//        //  ReusableMethods.waitForVisibility(registrationPage.succesPopUP, 10);
//        ReusableMethods.waitFor(3);
//        if (registrationPage.succesPopUP.isDisplayed()) {
//            Assert.assertFalse(registrationPage.succesPopUP.isDisplayed());
//        }
    //    List<WebElement> Alert = Driver.getDriver().findElements(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]/div[1]"));
//        if(elementExist.size()>0) {
//            Assert.assertTrue(registrationPage.succesPopUP.isDisplayed());
//            System.out.println("user registired with blank box");
//        }else
//            Assert.assertFalse(registrationPage.message.isDisplayed());


        //  Assert.assertFalse(registrationPage.greenApproveMessage.isDisplayed());
        //   Assert.assertFalse(registrationPage.redApproveMessage.isDisplayed());


    }


}

