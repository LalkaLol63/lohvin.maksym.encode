package org.javarush.maksym;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Runner runner = new Runner();
        try{
            runner.run(args);
        } catch(IOException e) {
            System.out.println("Проблема з файлами!");
        }

    }
}