package stepDefinitions.hooks;

import com.microsoft.playwright.Page;
import driverFactory.PageThreadLocal;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    public Page page;

    public Hooks(){

    }

    @Before
    public void beforeScenario(){
        System.out.println("I am the before scenario hook");
        page = PageThreadLocal.initPage();
    }

    @After
    public void afterScenario(){

        PageThreadLocal.removePage();
    }
}
