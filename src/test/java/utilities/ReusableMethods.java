package utilities;


import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import pages.HomePage;
import pages.SignIn;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

    public class ReusableMethods {

        public static String getScreenshot(String name) throws IOException {
            // naming the screenshot with the current date to avoid duplication
            String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

            // TakesScreenshot is an interface of selenium that takes the screenshot
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);
            // full path to the screenshot location
            String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";
            File finalDestination = new File(target);
            // save the screenshot to the path given
            FileUtils.copyFile(source, finalDestination);
            return target;
        }

        //========Switching Window=====//
        public static void switchToWindow(String targetTitle) {
            String origin = Driver.getDriver().getWindowHandle();
            for (String handle : Driver.getDriver().getWindowHandles()) {
                Driver.getDriver().switchTo().window(handle);
                if (Driver.getDriver().getTitle().equals(targetTitle)) {
                    return;
                }
            }
            Driver.getDriver().switchTo().window(origin);
        }

        //========Hover Over=====//
        public static void hover(WebElement element) {
            Actions actions = new Actions(Driver.getDriver());
            actions.moveToElement(element).perform();
        }

        //========Use Keyboard and Mouse Actions=====//
        public static void keyAction(Keys key) {
            Actions actions = new Actions(Driver.getDriver());
            actions.sendKeys(key).build().perform();
        }

        //==========Return a list of string given a list of Web Element====////
        public static List<String> getElementsText(List<WebElement> list) {
            List<String> elemTexts = new ArrayList<>();
            for (WebElement el : list) {
                if (!el.getText().isEmpty()) {
                    elemTexts.add(el.getText());
                }
            }
            return elemTexts;
        }

        //========Returns the Text of the element given an element locator==//
        public static List<String> getElementsText(By locator) {
            List<WebElement> elems = Driver.getDriver().findElements(locator);
            List<String> elemTexts = new ArrayList<>();
            for (WebElement el : elems) {
                if (!el.getText().isEmpty()) {
                    elemTexts.add(el.getText());
                }
            }
            return elemTexts;
        }

        //===============Explicit Wait==============//

        public static void waitFor(int sec) {
            try {
                Thread.sleep(sec * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
            return wait.until(ExpectedConditions.visibilityOf(element));
        }

        public static WebElement waitForVisibility(By locator, int timeout) {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }

        public static WebElement waitForClickablility(WebElement element, int timeout) {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        }

        public static WebElement waitForClickablility(By locator, int timeout) {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        }

        public static void waitForPageToLoad(long timeOutInSeconds) {
            ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                }
            };
            try {
                System.out.println("Waiting for page to load...");
                WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
                wait.until(expectation);
            } catch (Throwable error) {
                System.out.println(
                        "Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
            }
        }

        //======Fluent Wait====//
        public static WebElement fluentWait(final WebElement webElement, int timeinsec) {
            //FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver()).withTimeout(timeinsec, TimeUnit.SECONDS).pollingEvery(timeinsec, TimeUnit.SECONDS);
            FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver())
                    .withTimeout(Duration.ofSeconds(3))
                    .pollingEvery(Duration.ofSeconds(1));

            WebElement element = wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return webElement;
                }
            });

            return element;
        }

        public static Boolean waitForText(By locator, String text, int timeout) {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
            return wait.until(ExpectedConditions.textToBe(locator, text));
        }

        /**
         * select element by index in dropdown
         *
         * @param el
         * @param index
         */
        public static void selectByIndex(WebElement el, int index) {
            Select options = new Select(el);
            options.selectByIndex(index);
        }

        /**
         * select element by value in dropdown
         *
         * @param el
         * @param value
         */
        public static void selectByValue(WebElement el, String value) {
            Select options = new Select(el);
            options.selectByValue(value);
        }

        /**
         * select element by visible text in dropdown
         *
         * @param el
         * @param text
         */
        public static void selectByText(WebElement el, String text) {
            Select options = new Select(el);
            options.selectByVisibleText(text);
        }

        public static void clickDropdownOption(
                List<WebElement> optionList,
                String optionText
        ) {
            ReusableMethods.waitFor(1);
            List<String> ls = getElementsText(optionList);
            if (ls.contains(optionText)) {
                int i = ls.indexOf(optionText);
                optionList.get(i).click();
            }
            keyAction(Keys.ESCAPE);
        }

        public static void clickListElementByText(
                List<WebElement> list,
                String elText
        ) {
            List<String> txtList = getElementsText(list);
            //make sure that the list has element
            Assert.assertTrue(elText + " does not exist", txtList.contains(elText));
            for (WebElement e : list) {
                if (!e.getText().isEmpty() && e.getText().equals(elText)) {
                    e.click();
                }
            }
        }

        public void printData(int row, int column){
            String xpath = "//tbody//tr["+row+"]//td["+column+"]";
            WebElement data = Driver.getDriver().findElement(By.xpath(xpath));
            System.out.println(data.getText());

        }


        public static void getDataTable(List<Map<String, Object>> dataTable, Object name) {
            name = dataTable.get(0).get("name");
        }


//        public static void clickWithTimeOut(WebElement element, int timeout) {
//            for (int i = 0; i < timeout; i++) {
//                try {
//                    element.click();
//                    return;
//                } catch (WebDriverException e) {
//                    wait(1);
//                }
//            }
//        }

//


        public static void loginWithUsernameAndPassword(String username,String password){
            WebDriver driver = new ChromeDriver();
            driver.get(ConfigReader.getProperty("gmi_url"));
            Driver.getDriver().findElement(By.id("account-menu")).click();
            Driver.getDriver().findElement(By.id("login-item")).click();
           // Driver.getDriver().findElement(By.id(""))
        }
        //you can sign in to GmiBank app using this method with different role
        public static void signInToApp(String username, String password) {
            SignIn signIn = new SignIn();
            HomePage homePage = new HomePage();
            Driver.getDriver().get(ConfigReader.getProperty("gmi_url"));
            ReusableMethods.waitFor(2);
            homePage.userIcon.click();
            ReusableMethods.waitFor(2);
            homePage.signIn.click();

            signIn.usernameBox.sendKeys(username);
            signIn.passwordBox.sendKeys(password);
            signIn.signInButton.click();
        }


    }
