package com.example;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RecipiApplication extends Application {
    ExecutorService srv = Executors.newCachedThreadPool();
}