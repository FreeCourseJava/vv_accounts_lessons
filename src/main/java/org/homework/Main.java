package org.homework;

import org.homework.di.Injector;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Injector.createServices();
        Injector.start();

    }

}
