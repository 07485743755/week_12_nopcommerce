package computer;

import browserTesting.BaseTest;
import homepage.TopMenuTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

    /*
   ●Create the package name computer
1. Create class “TestSuite”

    Write the following Test:
    1.Test name verifyProductArrangeInAlphaBaticalOrder()
	1.1 Click on Computer Menu.
	1.2 Click on Desktop
	1.3 Select Sort By position "Name: Z to A"
	1.4 Verify the Product will arrange in Descending order.

2. Test name verifyProductAddedToShoppingCartSuccessFully()
	2.1 Click on Computer Menu.
	2.2 Click on Desktop
	2.3 Select Sort By position "Name: A to Z"
	2.4 Click on "Add To Cart"
	2.5 Verify the Text "Build your own computer"
	2.6 Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
	2.7.Select "8GB [+$60.00]" using Select class.
	2.8 Select HDD radio "400 GB [+$100.00]"
	2.9 Select OS radio "Vista Premium [+$60.00]"
    2.10 Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander [+$5.00]"
	2.11 Verify the price "$1,475.00"
	2.12 Click on "ADD TO CARD" Button.
	2.13 Verify the Message "The product has been added to your shopping cart" on Top green Bar
    After that close the bar clicking on the cross button.
	2.14 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
	2.15 Verify the message "Shopping cart"
	2.16 Change the Qty to "2" and Click on "Update shopping cart"
	2.17 Verify the Total"$2,950.00"
	2.18 click on checkbox “I agree with the terms of service”
	2.19 Click on “CHECKOUT”
	2.20 Verify the Text “Welcome, Please Sign In!”
	2.21Click on “CHECKOUT AS GUEST” Tab
	2.22 Fill the all mandatory field
	2.23 Click on “CONTINUE”
    2.24 Click on Radio Button “Next Day Air($0.00)”
    2.25 Click on “CONTINUE”
    2.26 Select Radio Button “Credit Card”
    2.27 Select “Master card” From Select credit card dropdown
    2.28 Fill all the details
    2.29 Click on “CONTINUE”
    2.30 Verify “Payment Method” is “Credit Card”
    2.32 Verify “Shipping Method” is “Next Day Air”
	2.33 Verify Total is “$2,950.00”
	2.34 Click on “CONFIRM”
	2.35 Verify the Text “Thank You”
	2.36 Verify the message “Your order has been successfully processed!”
	2.37 Click on “CONTINUE”
    2.37 Verify the text “Welcome to our store”
*/

public class TestSuite extends TopMenuTest {


    String baseUrl = "https://demo.nopcommerce.com/";

    @Before

    public void setUp() {

        openBrowser(baseUrl);

    }


    @Test

    public void verifyProductArrangeInAlphaBaticalOrder() throws InterruptedException {


        verifyPageNavigation();
        Thread.sleep(3000);
        clickOnElement(By.xpath("//a[text()=' Desktops ']"));
        verifyPageNavigation(By.xpath("//h1[text()='Desktops']"), "Desktops");

        Select select = new Select(driver.findElement(By.id("products-orderby")));
        select.selectByVisibleText("Name: Z to A");

        List<WebElement> productList = driver.findElements(By.xpath("//div[@class='products-container']/descendant::h2"));


        String[] linkText = new String[productList.size()];
        int i = 0;
        for (WebElement a : productList) {
            linkText[i] = a.getText();
            i++;
        }


        if (!checkDescendingOrder(linkText)) {

            System.out.println("not sorted");
        }

    }

    @Test

    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {


        verifyPageNavigation();
        Thread.sleep(3000);
        clickOnElement(By.xpath("//a[text()=' Desktops ']"));
        verifyPageNavigation(By.xpath("//h1[text()='Desktops']"), "Desktops");

        Select select = new Select(driver.findElement(By.id("products-orderby")));
        select.selectByVisibleText("Name: A to Z");

        List<WebElement> list = driver.findElements(By.xpath("//div[@class='products-container']/descendant::h2"));

        String[] linkText = new String[list.size()];
        int i = 0;
        for (WebElement a : list) {
            linkText[i] = a.getText();
            i++;
        }

        if (!checkAscendingOrder(linkText)) {

            System.out.println("Not sorted");
        }

        Thread.sleep(2000);
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[3]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/button[1]"));
        verifyPageNavigation(By.xpath("//h1[text()='Build your own computer']"), "Build your own computer");

        selectByValueDropDown(By.id("product_attribute_1"), "1");
        selectByValueDropDown(By.id("product_attribute_2"), "5");
        Thread.sleep(1000);
        clickOnElement(By.xpath("//label[text()='400 GB [+$100.00]']"));
        clickOnElement(By.xpath("//label[text()='Vista Premium [+$60.00]']"));
        Thread.sleep(1000);
        WebElement checkBox = driver.findElement(By.xpath("//input[@id='product_attribute_5_10']"));
        if (!checkBox.isSelected()) {

            checkBox.click();
        }
        clickOnElement(By.xpath("//label[text()='Total Commander [+$5.00]']"));
        Thread.sleep(1000);
        verifyPageNavigation(By.xpath("//span[@id='price-value-1']"), "$1,475.00");

        Thread.sleep(3000);

        clickOnElement(By.id("add-to-cart-button-1"));

        try {
            Alert alert = driver.switchTo().alert();
            String actualAlertMessage = alert.getText();
            String expectedAlertMessage = "The product has been added to your shopping cart";
            System.out.println(actualAlertMessage);
            Thread.sleep(3000);
            Assert.assertEquals("Alert doesn't match", expectedAlertMessage, actualAlertMessage);
            driver.switchTo().alert().dismiss();

        } catch (Exception e) {

            verifyPageNavigation(By.xpath("//body/div[@id='bar-notification']/div[1]/p[1]"),"The product has been added to your shopping cart");
            Thread.sleep(2000);
            clickOnElement(By.xpath("//span[@class='close']"));
        }

        Thread.sleep(4000);
        mouseHoverToElement(By.xpath("//span[text()='Shopping cart']"));
        mouseHoverAndClickElement(By.xpath("//button[contains(text(),'Go to cart')]"));

        verifyPageNavigation(By.xpath("//h1[contains(text(),'Shopping cart')]"), "Shopping cart");
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.xpath("//input[contains(@id,'itemquantity')]"));
        element.clear();
        element.sendKeys("2");

        clickOnElement(By.xpath("//button[contains(text(),'Update shopping cart')]"));
        verifyPageNavigation(By.xpath("//td[@class='subtotal']/child::span"), "$2,950.00");

        Thread.sleep(3000);
        clickOnElement(By.id("termsofservice"));
        clickOnElement(By.id("checkout"));

        Thread.sleep(3000);
        verifyPageNavigation(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"), "Welcome, Please Sign In!");
        clickOnElement(By.xpath("//button[@class='button-1 checkout-as-guest-button']"));

        Thread.sleep(2000);
        sendTextToElement(By.id("BillingNewAddress_FirstName"), "Jems");
        sendTextToElement(By.id("BillingNewAddress_LastName"), "Piter");
        sendTextToElement(By.id("BillingNewAddress_Email"), "jemspiter@yahoo.com");
        Thread.sleep(3000);
        selectByValueDropDown(By.id("BillingNewAddress_CountryId"), "233");
        sendTextToElement(By.id("BillingNewAddress_City"), "Leicester");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "76,Corporation road");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "LE45NJ");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "07896543215");
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));

        Thread.sleep(2000);
        clickOnElement(By.id("shippingoption_1"));
        clickOnElement(By.xpath("//button[@onclick='ShippingMethod.save()']"));
        clickOnElement(By.id("paymentmethod_1"));
        clickOnElement(By.xpath("//button[@onclick='PaymentMethod.save()']"));
        Thread.sleep(3000);
        selectByValueDropDown(By.id("CreditCardType"), "MasterCard");
        sendTextToElement(By.id("CardholderName"), "Jems Piter");
        sendTextToElement(By.id("CardNumber"), "5465222828379708");
        Thread.sleep(2000);
        selectByValueDropDown(By.id("ExpireMonth"), "1");
        selectByValueDropDown(By.id("ExpireYear"), "2022");
        sendTextToElement(By.id("CardCode"), "283");
        clickOnElement(By.xpath("//button[@onclick='PaymentInfo.save()']"));

        Thread.sleep(500);
        verifyPageNavigation(By.xpath("//li[@class='payment-method']"), "Payment Method: Credit Card");
        verifyPageNavigation(By.xpath("//li[@class='shipping-method']"), "Shipping Method: Next Day Air");
        verifyPageNavigation(By.xpath("//span[@class='value-summary']/child::strong"), "$2,950.00");

        Thread.sleep(500);
        clickOnElement(By.xpath("//button[@onclick='ConfirmOrder.save()']"));
        verifyPageNavigation(By.xpath("//h1[text()='Thank you']"), "Thank you");
        verifyPageNavigation(By.xpath("//strong[text()='Your order has been successfully processed!']"), "Your order has been successfully processed!");
        Thread.sleep(1000);
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));

        Thread.sleep(1000);

        verifyPageNavigation(By.xpath("//h2[text()='Welcome to our store']"), "Welcome to our store");

    }

    @After

    public void tearDown() {

        closeBrowser();
    }
}
