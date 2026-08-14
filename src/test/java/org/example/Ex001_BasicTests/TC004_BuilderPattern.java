package org.example.Ex001_BasicTests;

public class TC004_BuilderPattern {
    public TC004_BuilderPattern step1(){
        System.out.println("Open the browser");
        return this;
    }

    public TC004_BuilderPattern step2(){
        System.out.println("launch chrome and open google.com");
        return this;
    }

    public TC004_BuilderPattern step3(){
        System.out.println("Verify the title");
        return this;
    }

    public static void main(String[] args) {
        TC004_BuilderPattern nb = new TC004_BuilderPattern();
//        nb.step1();
//        nb.step2();
//        nb.step3();

        nb.step1().step2().step3();

    }
}
