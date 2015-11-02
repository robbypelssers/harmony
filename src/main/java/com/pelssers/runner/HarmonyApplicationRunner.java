package com.pelssers.runner;


import com.pelssers.context.HarmonyConfiguration;
import com.pelssers.verticle.ApiVerticle;
import io.vertx.core.Vertx;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HarmonyApplicationRunner {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HarmonyConfiguration.class);
        Vertx vertx = context.getBean(Vertx.class);
        vertx.deployVerticle(new ApiVerticle(context));
    }
}
