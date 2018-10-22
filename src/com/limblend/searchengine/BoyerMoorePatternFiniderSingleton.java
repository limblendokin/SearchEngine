package com.limblend.searchengine;

public class BoyerMoorePatternFiniderSingleton {
    private static BoyerMoorePatternFiniderSingleton ourInstance = new BoyerMoorePatternFiniderSingleton();

    public static BoyerMoorePatternFiniderSingleton getInstance() {
        return ourInstance;
    }

    private BoyerMoorePatternFiniderSingleton() {
    }
}
