package com.automation.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;


public class LoginSteps {

    WebDriver driver;

    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        // Add a unique user data directory to avoid conflicts
        String userDataDir = "/tmp/chrome-profile-" + System.currentTimeMillis();
        options.addArguments("--user-data-dir=" + userDataDir);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @When("User enters valid credentials")
    public void user_enters_valid_credentials() {
        // Enter valid credentials
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword!");

        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();
    }

    @Then("User is redirected to the home page")
    public void user_is_redirected_to_the_home_page() {

        // Assert that the login is successful (check for logout link)
        WebElement logoutLink = driver.findElement(By.linkText("Logout"));
        Assert.assertTrue(logoutLink.isDisplayed(), "Login failed! Logout link not found.");
        driver.quit();
    }

    @When("User enters invalid credentials")
    public void user_enters_invalid_credentials() {
        // Enter invalid credentials
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("wronguser");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("wrongpassword");

        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();
    }

    @Then("Error message is displayed")
    public void error_message_is_displayed() {        // Assert that an error message is displayed
        WebElement errorMessage = driver.findElement(By.id("flash"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for invalid login.");
        Assert.assertTrue(errorMessage.getText().contains("Your username is invalid!"), "Error message content mismatch.");
        driver.quit();
    }
}
