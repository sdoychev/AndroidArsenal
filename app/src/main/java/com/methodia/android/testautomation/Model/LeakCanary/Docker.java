package com.methodia.android.testautomation.Model.LeakCanary;

/**
 * Created by Stefan.Doychev on 22.07.2015.
 */
public class Docker {
    static SchrodingerBox container;

    public static SchrodingerBox getContainer() {
        return container;
    }

    public static void setContainer(SchrodingerBox container) {
        Docker.container = container;
    }
}
