package driverFactory;

import com.microsoft.playwright.*;
import tests.BaseTest;
import utils.ReadProperties;

import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class BrowserManager  {
    private static ThreadLocal<Playwright> playwrightThread = new ThreadLocal<>();
    private static ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> browserContextThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();

    private  Properties properties;

    public static  Playwright getPlayWright(){
        return   playwrightThread.get();
    }
    public static void setPlayWright(Playwright playWright){
        playwrightThread.set(playWright);
    }
    public   static   Browser getBrowser(){
        return browserThreadLocal.get();
    }
    public  static void setBrowser(Browser browser){
        browserThreadLocal.set(browser);
    }
    public   static   BrowserContext getBrowserContext(){
        return browserContextThreadLocal.get();
    }
    public static void setBrowserContext(BrowserContext context){
        browserContextThreadLocal.set(context);
    }
    public static   Page getPage(){
        return pageThreadLocal.get();
    }
    public  static void setPage(Page page){
        pageThreadLocal.set(page);
    }

    private BrowserManager(){


    }



    public static void  setBrowserManager(){


            try {
                    setPlayWright(Playwright.create());
                    String browser = ReadProperties.getPropertyValue("browser");
                System.out.println("Read property without boolean " + ReadProperties.getPropertyValue("headless"));
                String head = ReadProperties.getPropertyValue("headless");

                  boolean headless = Boolean.parseBoolean(head);
                System.out.println("headless boolean : "+headless);

                    switch (browser) {
                        case "chrome":
                            setBrowser(getPlayWright().chromium().launch(
                                    new BrowserType.LaunchOptions().setHeadless(headless).setChannel("chrome")
                                            //.setArgs(List.of("--start-maximized"))
                                            ));
                            break;
                        case "firefox":
                            setBrowser(getPlayWright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless).setChannel("firefox")));

                            break;
                        case "safari":
                            setBrowser(getPlayWright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless)));
                            break;
                        case "edge":
                            setBrowser(getPlayWright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless).setChannel("msedge")));

                            break;
                        default:
                            setBrowser(getPlayWright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless)));
                            break;
                    }
                    String device = ReadProperties.getPropertyValue("device");

                    switch(device){
                        case "ipadAir10.9":
                            setBrowserContext(getBrowser().newContext(new Browser.NewContextOptions()

                                    .setScreenSize(820 ,1180)
                                    .setDeviceScaleFactor(2)
                                    .setViewportSize(820 ,1180)

                            ));
                            break;
                        case "desktop":
                            setBrowserContext(getBrowser().newContext(new Browser.NewContextOptions()
                                    .setScreenSize(1920 , 1080)
                                    .setDeviceScaleFactor(2)
                                    .setViewportSize(1920 , 1080)

                            ));
                            break;
                        case "iphone16ProMax":
                            setBrowserContext(getBrowser().newContext(new Browser.NewContextOptions()
                                    .setScreenSize(440 , 956)
                                    .setDeviceScaleFactor(2)
                                    .setViewportSize(440 , 956)

                            ));
                            break;
                        case "iphone14pro":
                           setBrowserContext(getBrowser().newContext(new Browser.NewContextOptions().setDeviceScaleFactor(3)
                                .setHasTouch(true)
                                .setIsMobile(true)
                                .setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 16_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/18.2 Mobile/15E148 Safari/604.1")
                                .setViewportSize(393, 660)));
                        case "iphone13":
                            setBrowserContext(getBrowser().newContext( new Browser.NewContextOptions()
                                    .setDeviceScaleFactor(3)
                                    .setHasTouch(true)
                                    .setIsMobile(true)
                                    .setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 15_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/18.2 Mobile/15E148 Safari/604.1")
                                    .setViewportSize(390, 664)));
                            break;
                        default:
                            setBrowserContext(getBrowser().newContext(new Browser.NewContextOptions()
                                            .setScreenSize(440 , 956)
                                            .setDeviceScaleFactor(2)
                                            .setViewportSize(440 , 956)

                                    )
                                    //.newContext(new Browser.NewContextOptions().setViewportSize(null))
                            );
                            break;



                    }



//                BrowserContext context = browser.newContext(new Browser.NewContextOptions()
//                        .isMobile(false));
                    getBrowserContext().setDefaultTimeout(60000);
                    setPage(getBrowserContext().newPage());







            } catch (RuntimeException e) {
                e.getMessage();

            }
            getPage().navigate("https://www.saucedemo.com");
            //return getPage();

         //   return getPage();





    }

    public static void tearDown(){
        try {

            if (pageThreadLocal.get() != null) pageThreadLocal.get().close();
            if (browserThreadLocal.get() != null) browserThreadLocal.get().close();
            if (playwrightThread.get() != null) playwrightThread.get().close();
        }catch (RuntimeException e){
            e.getMessage();
        }

    }

}
