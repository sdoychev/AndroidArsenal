package com.methodia.android.testautomation;

import android.test.InstrumentationTestCase;

import java.util.ArrayList;

/**
 * Created by Stefan.Doychev on 03.06.2015.
 */
public class CasualTests extends InstrumentationTestCase {

    ArrayList<String> stringList;
    ArrayList<Integer> integerList;
    ArrayList<Integer> resultList;
    String result = "";

    public void initStringList() {
        stringList = new ArrayList<>();
        stringList.add("asd");
        stringList.add("asd");
        stringList.add("asd");
    }

    private void initIntegerList() {
        integerList = new ArrayList<>();
        integerList.add(2);
        integerList.add(5);
        integerList.add(6);
    }

    public String myConcat(ArrayList<String> list) {
        for (String s : list) {
            result = result.concat(s);
        }
        return result;
    }

    public ArrayList<Integer> myFilter(ArrayList<Integer> list) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Integer i : list) {
            if (i % 2 == 0) {
                result.add(i);
            }
        }
        return result;
    }

    public void testConcat() throws Exception {

        //initStringList();
        //String result = myConcat(stringList);
        //assertTrue(result.equals("asdasdasd"));

        initIntegerList();
        resultList = myFilter(integerList);
        assertTrue(resultList.size() == 2);
    }

}
