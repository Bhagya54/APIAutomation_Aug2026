package org.example.Ex001_BasicTests;

public class TC003_NonBuilderPattern {

    public void step1(){
        System.out.println("Open the browser");
    }

    public void step2(){
        System.out.println("launch chrome and open google.com");
    }

    public void step3(){
        System.out.println("Verify the title");
    }

    public static void main(String[] args) {
        TC003_NonBuilderPattern nb = new TC003_NonBuilderPattern();
        nb.step1();
        nb.step2();
        nb.step3();
    }
}
