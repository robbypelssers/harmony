package com.pelssers.verticle;

import com.pelssers.context.HarmonyProperties;
import com.pelssers.context.HarmonyTestConfiguration;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@RunWith(VertxUnitRunner.class)
public abstract class AbstractApiVerticleTest {

    protected Vertx vertx;
    protected Integer port;
    protected String host;

    @Before
    public void setUp(TestContext context) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(HarmonyTestConfiguration.class);
        port = applicationContext.getEnvironment().getProperty(HarmonyProperties.HTTP_PORT, Integer.class);
        host = applicationContext.getEnvironment().getProperty(HarmonyProperties.HOST);
        vertx = applicationContext.getBean(Vertx.class);
        vertx.deployVerticle(new ApiVerticle(applicationContext), context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

}
