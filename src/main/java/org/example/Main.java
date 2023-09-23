package org.example;

public class Main {
   public static void main(String[] args) {
        IntegerList integerList = new IntegerListImpl();
        integerList.add(4); // 0
        integerList.add(10); // 1
        integerList.add(5); // 2
        integerList.add(-8); // 3

        integerList.add(3, 5);

        System.out.println(integerList.contains(10));
        System.out.println(integerList.contains(11));
    }
    
}