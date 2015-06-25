package com.methodia.android.testautomation;

import java.util.Comparator;

/**
 * Created by Doychev on 24.6.2015 г..
 */
public class Model {

    private String name;
    // Comparator for sorting the list by Model name in ascending order.
    public static Comparator<Model> NameAscendingComparator = new Comparator<Model>() {
        @Override
        public int compare(Model lhs, Model rhs) {
            String lhsName = lhs.getName().toUpperCase();
            String rhsName = rhs.getName().toUpperCase();
            return lhsName.compareTo(rhsName);
        }
    };
    // Comparator for sorting the list by Model name in descending order.
    public static Comparator<Model> NameDescendingComparator = new Comparator<Model>() {
        @Override
        public int compare(Model lhs, Model rhs) {
            String lhsName = lhs.getName().toUpperCase();
            String rhsName = rhs.getName().toUpperCase();
            return rhsName.compareTo(lhsName);
        }
    };
    private int number;
    // Comparator for sorting the list by Model number in ascending order.
    public static Comparator<Model> NumberAscendingComparator = new Comparator<Model>() {
        @Override
        public int compare(Model lhs, Model rhs) {
            int lhsNumber = lhs.getNumber();
            int rhsNumber = rhs.getNumber();
            return lhsNumber - rhsNumber;
        }
    };
    // Comparator for sorting the list by Model number in descending order.
    public static Comparator<Model> NumberDescendingComparator = new Comparator<Model>() {
        @Override
        public int compare(Model lhs, Model rhs) {
            int lhsNumber = lhs.getNumber();
            int rhsNumber = rhs.getNumber();
            return rhsNumber - lhsNumber;
        }
    };

    public Model(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Model with name: " + name + " and number: " + number;
    }
}
