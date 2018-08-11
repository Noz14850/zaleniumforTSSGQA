// yeah, yeah.  Lyle Hodgson yadda yadda.

package com.tssgqa.zaleniumdemo;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {

    /*
     * Global variables.  My apologies to those offended by the use of them.
       *
       * First is just for the exercise of demonstrating in the
     * output the changes to one variable in each of the @Before (and @Test)
     * methods run (except @BeforeClass).
     */

    // not meant for real tests;
    // just here for monitoring TestNG execution in IntelliJ console
    protected String TESTSTRING = "NOT SET YET";

    protected String browserToUse = "";    // which browser type to start

    /*
     * Here's your Selenium driver - not WebDriver directly but
     * RemoteWebDriver for running on Zalenium Docker containers
     */    // Aaaaand the token website we're visiting.  waiting to test this for real

    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    protected static final String baseURL = "http://uitestingplayground.com";


     /* TestNG runs @BeforeTest first.  Here we are using it to define the
     * browser to be used in this round of test cases (remember, in TestNG
     * "test" refers to a *set* of test cases, not a single test case).
     *
     * @param tngTestName  String just to use to track progress in console
     * @param browserToUse
     */
    @BeforeTest
    @Parameters({"tngTestName", "browserToUse"})
    public void beforeTest(String tngTestName, String browserToUse) {

        // quasi-logging
        System.out.println("@BeforeTest:  Test name: " + tngTestName);

        this.browserToUse = browserToUse;  // will be *used* later

        long id = Thread.currentThread().getId();
        System.out.println("********* @BeforeTest. Thread id is: " + id);

        // quasi-logging
        System.out.println("@BeforeTest, this.TESTSTRING before changing = " + this.TESTSTRING);
        this.TESTSTRING = "BEFORETEST";
        System.out.println("@BeforeTest, this.TESTSTRING after changing = " + this.TESTSTRING +"\n");
    }

    @BeforeClass
    public static void navToTestPage() {

        long id = Thread.currentThread().getId();
        System.out.println("@BeforeClass. Thread id is: " + id +"\n");

        /*
           // quasi-logging below below doesn't work in @BeforeClass:
           System.out.println("@BeforeClass, this.TESTSTRING before changing = " + this.TESTSTRING);
           this.TESTSTRING = "BEFORECLASS";
           System.out.println("@BeforeClass, this.TESTSTRING after changing = " + this.TESTSTRING +"\n");
        */
    }

    @BeforeMethod
    public void nav() throws MalformedURLException {

        // INITIATING BROWSER FOR DOCKER:
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserToUse);
        //Set Browser to ThreadLocalMap
        driver.set(new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), capabilities));

        // need to call .get() differently than when using WebDriver
        driver.get().navigate().to(baseURL);

        long id = Thread.currentThread().getId();
        System.out.println("@BeforeMethod. Thread id is: " + id);

        // quasi-logging
        System.out.println("@BeforeMethod, this.TESTSTRING before changing = " + this.TESTSTRING);
        this.TESTSTRING = "BEFOREMETHOD";
        System.out.println("@BeforeMethod, this.TESTSTRING after changing = " + this.TESTSTRING +"\n");
    }

    @AfterMethod
    public void afterMethod() {
        long id = Thread.currentThread().getId();
        System.out.println("@AfterMethod. Thread id is: " + id +"\n");

        // using RemoteWebDriver, don't use driver.quit() -
        // although driver.remote() doesn't seem to be doing what I expected
        // either (?)
        driver.remove();
    }

    @AfterClass
    public static void quitDriver() {
        long id = Thread.currentThread().getId();
        System.out.println("AfterClass. Thread id is: " + id +"\n");
    }

    @AfterTest
    public void afterTest() {
        long id = Thread.currentThread().getId();
        System.out.println("@AfterTest. Thread id is: " + id +"\n");
    }


}
