// yeah, yeah.  Lyle Hodgson yadda yadda.

package com.tssgqa.zaleniumdemo;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class NotMuchAsTestsGo extends TestBase {

    /**
     * First of three bitty tests to produce a guaranteed success: 1==1.
     * Hey, the assertions are not what we're testing here.
     *
     * Most statements are fluff to output to the console to a) confirm
     * that the method is being run, b) note that we are indeed multi-threaded,
     * and c) what the status of the global variable TESTSTRING is before and
     * after modificaation.
     *
     * Note: class extends TestBase, which runs all the TestNG @Before
     * and @After methods in their expected sequence before and after these
     * test cases (starting the browser(s), mainly).
     */
    @Test(timeOut=120000)
    public void andThisTestShouldPass() {
        System.out.println("Browser: " + (driver.toString()));

        long id = Thread.currentThread().getId();
        System.out.println("Test ONE, andThisTestSHouldPass. Thread id is: " + id);

        // quasi-logging
        System.out.println(
                "Test ONE. this.TESTSTRING during @Test = " +
                        this.TESTSTRING);
        this.TESTSTRING = "TEST ONE";
        System.out.println(
                "Test ONE, this.TESTSTRING after changing = " +
                        this.TESTSTRING +"\n");

        int x=1;
        assertTrue(x==1);
    }

    /**
     * Second bitty test, but this one guaranteed to fail (5==1).
     */
    @Test(timeOut=120000)
    public void thisTestShouldFail() {
        System.out.println("Browser: " + (driver.toString()));

        long id = Thread.currentThread().getId();
        System.out.println("Test TWO, thisTestShouldFail. Thread id is: " + id);

        // quasi-logging
        System.out.println("Test TWO. this.TESTSTRING during @Test before changing = " + this.TESTSTRING);
        this.TESTSTRING = "TEST TWO";
        System.out.println("Test TWO, this.TESTSTRING after changing = " + this.TESTSTRING +"\n");

        int x=5;
        assertTrue(x==1);
    }


}
