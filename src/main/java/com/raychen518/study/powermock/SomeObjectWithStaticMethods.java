package com.raychen518.study.powermock;

public class SomeObjectWithStaticMethods {

    public static String doSomething() {
        System.out.println("Executing the " + SomeObjectWithStaticMethods.class.getSimpleName()
                + ".doSomething() method...");
        return "Some Result";
    }

    public static String doSomething(int someParameter) {
        System.out.println("Executing the " + SomeObjectWithStaticMethods.class.getSimpleName()
                + ".doSomething(int someParameter) method... (" + someParameter + ")");
        return "Some Result";
    }

    public static void doSomethingWithoutReturn() {
        System.out.println("Executing the " + SomeObjectWithStaticMethods.class.getSimpleName()
                + ".doSomethingWithoutReturn() method...");
    }

}
