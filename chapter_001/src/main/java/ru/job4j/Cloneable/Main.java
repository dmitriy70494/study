package ru.job4j.Cloneable;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Main<T> {

    public Main() throws IllegalAccessException, InstantiationException {
    }

    public <E> void  generic(E object) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
    }

    public static void main(String[] args) throws CloneNotSupportedException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Clone one = new Clone("one", new Obj());
        Clone clone = (Clone) one.clone();
        one.getBla();
        System.out.println(one);
        System.out.println(clone);
        int a = 3;
        int b = 9;
        System.out.println(a&b);
        Main<Integer> main = new Main<>();
        main.generic(1);
    }
}
