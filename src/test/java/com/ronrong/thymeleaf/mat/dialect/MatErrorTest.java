package com.ronrong.thymeleaf.mat.dialect;


import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IEngineContext;
import org.thymeleaf.context.StandardEngineContextFactory;

public class MatErrorTest extends AbstractMatTest {

    private static final String PACKAGE_PATH = "com/ronrong/thymeleaf/mat/dialect/test";
    private static final String TEST_TEMPLATE_PATH = PACKAGE_PATH + "/mat_error_test.html";


    private static void setupMat() {
    }


    @BeforeClass
    public static void beforeClass() {
        setupMat();
        setupThymeleaf();
    }


    @After
    public void tearDownSubject() {

    }


    @Test
    public void testSlide() {
//
        //Context context = new Context();
        StandardEngineContextFactory standardEngineContextFactory = new StandardEngineContextFactory();
        IEngineContext engineContext = standardEngineContextFactory.createEngineContext(templateEngine.getConfiguration(), null, null, new Context());
        //EngineContext engineContext = new EngineContext(templateEngine.getConfiguration(), null, null , null , null);

        engineContext.setVariable("shop",   "web_main");
        engineContext.setVariable("page",   "index");
//        engineContext.setVariable("position",   "1");
//        engineContext.setVariable("description",   "description");
//        engineContext.setVariable("style",   "stander");
//        engineContext.setVariable("search", "file");

        for(int i = 0; i< 1; i++){
            String result;
            result = templateEngine.process(TEST_TEMPLATE_PATH, engineContext);
            System.out.println(result);

        }
    }













}