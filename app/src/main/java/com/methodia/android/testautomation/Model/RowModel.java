package com.methodia.android.testautomation.Model;

import java.util.Comparator;

/**
 * Created by Doychev on 24.6.2015 г.
 */
public
@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor(suppressConstructorProperties = true)
@lombok.EqualsAndHashCode(of = {"name", "number"})
@lombok.ToString
/* @Data =
    @lombok.Getter
    @lombok.Setter
    @lombok.RequiredArgsConstructor
    @lombok.EqualsAndHashCode(of = {“equalsParam”, “hashCodeParam”})
    @lombok.ToString
    -
    @Getter and @Setter annotation at class level we ask Lombok to generate getter and setter for all non-static fields. Final fields will not have setters.
    -
    @RequiredArgsConstructor generates a constructor for all final fields, with parameter order same as field order.
    @NoArgsConstructor creates an empty constructor.
    @AllArgsConstructor(suppressConstructorProperties=true) creates a constructor for all fields.
    -
    Never use @lombok.EqualsAndHashCode without specifying the fields. Without parameters, @lombok.EqualsAndHashCode will use all fields, possibly creating heavy equals and hashCode methods.
    -
    @lombok.ToString(exclude = "strList") - While having a toString method is highly suggested, avoid collections and arrays fields in the toString method to avoid too long text lines.
    -
    More info - https://gualtierotesta.wordpress.com/2014/03/03/tutorial-using-lombok-to-reduce-boilerplate-code-in-java/
*/
class RowModel {

    // Comparator for sorting the list by Model name in ascending order.
    public static Comparator<RowModel> NameAscendingComparator = new Comparator<RowModel>() {
        @Override
        public int compare(RowModel lhs, RowModel rhs) {
            String lhsName = lhs.getName().toUpperCase();
            String rhsName = rhs.getName().toUpperCase();
            return lhsName.compareTo(rhsName);
        }
    };
    // Comparator for sorting the list by Model name in descending order.
    public static Comparator<RowModel> NameDescendingComparator = new Comparator<RowModel>() {
        @Override
        public int compare(RowModel lhs, RowModel rhs) {
            String lhsName = lhs.getName().toUpperCase();
            String rhsName = rhs.getName().toUpperCase();
            return rhsName.compareTo(lhsName);
        }
    };
    // Comparator for sorting the list by Model number in ascending order.
    public static Comparator<RowModel> NumberAscendingComparator = new Comparator<RowModel>() {
        @Override
        public int compare(RowModel lhs, RowModel rhs) {
            int lhsNumber = lhs.getNumber();
            int rhsNumber = rhs.getNumber();
            return lhsNumber - rhsNumber;
        }
    };
    // Comparator for sorting the list by Model number in descending order.
    public static Comparator<RowModel> NumberDescendingComparator = new Comparator<RowModel>() {
        @Override
        public int compare(RowModel lhs, RowModel rhs) {
            int lhsNumber = lhs.getNumber();
            int rhsNumber = rhs.getNumber();
            return rhsNumber - lhsNumber;
        }
    };
    private String name;
    private int number;

    /* No longer required - Lombok is here!
    *
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
    *
    */
}
