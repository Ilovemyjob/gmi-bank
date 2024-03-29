package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.CreateOrEditCustomerPage;
import pages.ManageCustomerPage;

public class CreatedCustomerSearch {

    CreateOrEditCustomerPage createOrEditCustomerPage = new CreateOrEditCustomerPage();
    ManageCustomerPage manageCustomerPage = new ManageCustomerPage();

    @Given("user clicks on manage customer")
    public void user_clicks_on_manage_customer() {
        manageCustomerPage.myOperations.click();
        manageCustomerPage.manageCustomers.click();

    }

    @Given("user clics on create a new customer")
    public void user_clics_on_create_a_new_customer() {
        manageCustomerPage.createCustomerButton.click();

    }

    @And("user type ssn number into ssnBox {string}")
    public void userTypeSsnNumberIntoSsnBox(String ssn) {
        createOrEditCustomerPage.searchSSN.sendKeys(ssn);
    }

    @Given("user clicks on search button")
    public void user_clicks_on_search_button() {
        createOrEditCustomerPage.searchSSNbutton.click();

    }

    @Then("user verify that all user data provided")
    public void user_verify_that_all_user_data_provided() {
        Assert.assertTrue(createOrEditCustomerPage.createFirstName.getText().equals(""));

    }
}
