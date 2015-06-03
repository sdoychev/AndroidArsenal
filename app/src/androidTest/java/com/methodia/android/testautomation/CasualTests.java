package com.methodia.android.testautomation;

import android.test.InstrumentationTestCase;

import java.util.ArrayList;

/**
 * Created by Stefan.Doychev on 03.06.2015.
 */
public class CasualTests extends InstrumentationTestCase {

    ArrayList<String> stringList;
    String result = "";

    public void init() {
        stringList = new ArrayList<String>();
        stringList.add("asd");
        stringList.add("asd");
        stringList.add("asd");
    }

    public String myConcat(ArrayList<String> list) {
        for (String s : list) {
            result = result.concat(s);
        }
        return result;
    }

    public void testConcat() throws Exception {
        init();
        String result = myConcat(stringList);
        assertTrue(result.equals("asdasdasd"));
    }

}
