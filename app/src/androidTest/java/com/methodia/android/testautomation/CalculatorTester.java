package com.methodia.android.testautomation;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;
import android.test.InstrumentationTestCase;

/**
 * Created by Stefan.Doychev on 02.06.2015.
 */
public class CalculatorTester extends InstrumentationTestCase {

    private UiDevice device;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        device = UiDevice.getInstance(getInstrumentation());
        device.pressHome();

        // Wait for the Apps icon to show up on the screen
        device.wait(Until.hasObject(By.desc("Apps")), 3000);
        UiObject2 appsButton = device.findObject(By.desc("Apps"));
        appsButton.click();

        // Wait for the Calculator icon to show up on the screen
        device.wait(Until.hasObject(By.text("Calculator")), 3000);
        UiObject2 calculatorApp = device.findObject(By.text("Calculator"));
        calculatorApp.click();
    }

    public void testAdd() throws Exception {
        // Wait till the Calculator's buttons are on the screen
        device.wait(Until.hasObject(By.text("9")), 3000);

        // Select the button for 9
        UiObject2 buttonNine = device.findObject(By.text("9"));
        buttonNine.click();
        // Select the button for +
        UiObject2 buttonPlus = device.findObject(By.desc("plus"));
        buttonPlus.click();
        // Press 9 again as we are calculating 9+9
        buttonNine.click();
        // Select the button for =
        UiObject2 buttonEquals = device.findObject(By.desc("equals"));
        buttonEquals.click();

        device.waitForIdle(3000);
        UiObject2 resultText = device.findObject(By.clazz("android.widget.EditText"));
        String result = resultText.getText();
        assertTrue(result.equals("18"));
    }
}
