package com.tssgqa.zaleniumdemo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.testng.annotations.*;


public class NotMuchAsTestsGo extends TestBase {

    /**
     * First of three bitty tests to produce a guaranteed success: 1==1.
     * Selenium is not what we're here about, exactly.
     *
     * Most statements are fluff, output to the console to
     * a) confirm which method is being run,
     * b) note that we are indeed multi-threaded, and
     * c) state what the status of the global variable TESTSTRING is before and
     *    after modificaation.
     *
     * Note: class extends TestBase, which runs all the TestNG @Before
     * and @After methods before and after these test cases (starting the
     * browser(s), mainly, because there are more there to demo TestNG
     * than to achieve anything noteworthy, for now).
     */
    @Test
    public void firstTestWillPass() {
        System.out.println("Browser: " + (driver.toString()));

        long id = Thread.currentThread().getId();
        System.out.println("Test firstTestWillPass(). Thread id is: " + id);

        // quasi-logging
        System.out.println(
                "Test firstTestWillPass(). this.TESTSTRING during @Test = " +
                        this.TESTSTRING);
        this.TESTSTRING = "TEST ONE";
        System.out.println(
                "Test firstTestWillPass(), this.TESTSTRING after changing = " +
                        this.TESTSTRING +"\n");

        int x=1;
        assertTrue(x==1);
    }

    /**
     * Second bitty test, but this one guaranteed to fail (5==1).
     */
    @Test
    public void secondTestWillFail() {
        System.out.println("Browser: " + (driver.toString()));

        long id = Thread.currentThread().getId();
        System.out.println("Test secondTestWillFail(). Thread id is: " + id);

        // quasi-logging
        System.out.println(
                "Test secondTestWillFail(. this.TESTSTRING during @Test before changing = " +
                this.TESTSTRING);
        this.TESTSTRING = "TEST TWO";
        System.out.println("Test secondTestWillFail(, this.TESTSTRING after changing = " +
                this.TESTSTRING +"\n");

        int x=5;
        assertTrue(x==1);
    }

    /**
     * Second bitty test, but this one guaranteed to fail (5==1).
     */
    @Test
    public void thirdTestWillPass() {
        System.out.println("Browser: " + (driver.toString()));

        long id = Thread.currentThread().getId();
        System.out.println("Test thirdTestWillPass(). Thread id is: " + id);

        // quasi-logging
        System.out.println("Test thirdTestWillPass(). this.TESTSTRING during @Test before changing = " + this.TESTSTRING);
        this.TESTSTRING = "TEST THREE";
        System.out.println("Test thirdTestWillPass(), this.TESTSTRING after changing = " + this.TESTSTRING +"\n");

        int x=1;
        assertTrue(x==1);
    }


}
