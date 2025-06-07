package runner;

import io.cucumber.core.cli.Main;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.TestNG;
import org.testng.TestRunner;
import org.testng.annotations.DataProvider;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import utils.ReadProperties;

import java.util.Collections;


@CucumberOptions(
            features = "src/test/resources/features",
            //src/test/resources/
            glue = "stepDefinitions",
            plugin = {"pretty","html:target/HtmlReports1/report.html",

                    "json:target/JSONReports/report.json",

                    "junit:target/JUnitReports/report.xml"}

    )

    public  class RunCucumberTest extends AbstractTestNGCucumberTests {

    @DataProvider(parallel = true)

    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }





//            String[] arguments = {"features=/src/test/resources/features",
//                    //src/test/resources/
//                    "glue=stepDefinitions",
//                    "plugin={pretty,html:target/HtmlReports1/report.html,json:target/JSONReports/report.json,junit:target/JUnitReports/report.xml}"
//            };
//            cucumber.api.cli.Main.main(arguments);
//
//
//            public static void main(String[] args) throws Throwable {
//                try {
//    //cucumber.api.cli.Main.main
//
//
//
//                 Main.main (new String[] {
//
//
//                                    "-g","stepDefinitions",
////                                   "-g","com.sadakar.cucumber.runner",
//
//                                   "classpath:features",
//                                  //  "features","src/test/resources/features",
//
//                                    "-t","@Regression",
//
//
//                                    "-p", "pretty",
//                                    "-p", "json:target/cucumber-reports/cucumber.json",
//                                    "-p", "html:target/cucumber-reports/cucumberreport.html",
//
//                                    "-m"
//                            }
//                    );
////
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    System.out.println("Main method exception : " + e);
//                }
//            }


  //  public static void main(String[] args) {
//        TestNG testNG = new TestNG();
//        XmlSuite suite = new XmlSuite();
////        int threadCount = getThreadCount();
////        System.out.println("Configured Thread Count " + threadCount);
////
//        suite.setDataProviderThreadCount(3);
//        XmlTest test = new XmlTest(suite);
//        test.setName("Cucumber Tests");
//        test.setXmlClasses(Collections.singletonList(new XmlClass(TestRunner.class)));
//        testNG.setUseDefaultListeners(false);
//        testNG.setXmlSuites(Collections.singletonList(suite));
//        testNG.run();


 //   }
//    @Override
//    @DataProvider(parallel = true)
//    public Object[][] scenarios(){
//        return super.scenarios();
//    }
}

