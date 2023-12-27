package stepdefinitions;


import io.cucumber.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;
import pages.CustomerPage;
import pages.SignIn;
import utilities.ConfigReader;



public class US_16 {

    CustomerPage customerPage = new CustomerPage();
    SignIn signIn =new SignIn();
  //  ReusableMethods reusableMethods = new ReusableMethods();




    @And("user go to transfer money page")
    public void userGoToTransferMoneyPage() {
        customerPage.My_Operation.click();
        customerPage.Transfer_Money.click();

    }

    @And("user click account box")
    public void userClickAccountBox() {
        customerPage.fromAccount.click();

    }

    @And("user should see at least two account")
    public void userShouldSeeAtLeastTwoAccount( ) {

        customerPage.fromAccount.click();
        Select options = new Select(customerPage.fromAccount);
        Assert.assertTrue(options.getOptions().size() > 1);


    }


    @And("user can select first account")
    public void userCanSelectFirstAccount()  {

        customerPage.fromAccount.click();
        Select options = new Select(customerPage.fromAccount);
        options.selectByIndex(1);
        Assert.assertTrue(customerPage.fromAccount.isDisplayed());

//        List<WebElement> list = new ArrayList<>();
//        List<WebElement> allOption =options.getOptions();
//        for (WebElement el:allOption
//            list.add()
//        ) {
//            System.out.println(list.get(1));
//           // option.getText();
        //   }

    }

    @And("user can select balance")
    public void userCanSelectBalance() {

        Assert.assertTrue(customerPage.Balance.isEnabled());

    }


    @And("user select second box")
    public void userSelectSecondBox() {

        customerPage.toAccount.click();
        Select options = new Select(customerPage.toAccount);
        options.selectByIndex(1);
    }

    @And("user type balance")
    public void userTypeBalance() {
        customerPage.Balance.sendKeys("12");
    }

    @And("user make transfer without description")
    public void userMakeTransferWithoutDescription() {
        customerPage.MakeTransfer.click();
        Assert.assertTrue(customerPage.description_required.isDisplayed());
    }

    @And("user type description")
    public void userTypeDescription() {
        customerPage.Description.sendKeys("test");
    }

    @And("user make transfer")
    public void userMakeTransfer() throws InterruptedException {

        customerPage.MakeTransfer.click();
        Thread.sleep(4000);
       Assert.assertTrue(customerPage.successfull.isDisplayed());

    }

    @And("user provide valid costumername")
    public void userProvideValidCostumername() {
        signIn.usernameBox.sendKeys(ConfigReader.getProperty("Customer_username"));
    }

    @And("user provide valid costomerpassword")
    public void userProvideValidCostomerpassword() {
        signIn.passwordBox.sendKeys(ConfigReader.getProperty("Customer_password"));
    }
}
