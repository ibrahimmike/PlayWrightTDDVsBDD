<h1>PlayWright TDD Vs BDD for the automation of a test plan. </h1>


<h3>Hello and welcome to My project. :wave: </h3>


Why PlayWright, playWright offers lots of features like the device emulation which makes the execution of responsive tests easier and closer to to real devices and browsers, with the webkit we can run safari tests and emulate Mc OS devices and run the tests on EC2 linux machines which makes it the most cost-efficient way to the auto tests with no need for more expensive solutions like browserstack.
Pros of PlayWright over selenium:

1. Emulation which allows the execution of the tests on the combination of device and browsers, provides the most cost-efficient way for executing automated frameworks. 
2. The events wait feature allows checking the execution of certain API requests via the front-end when a certain event is triggered, provides a good assurance for the integration tests.
3. The builtin waits for locators offers flexibility on the execution scenarios.

The libs used to create this framework are:
- PlayWright 
- TestNg.
- Extent report.
- Cucumber.
- Java 21 and maven 3.8.6.

In this project we will discuss 2 different approaches TDD and BDD to test a simple application. 
The main challenge for creating this framework is to have the ability to run the same code for a BDD cucumber and a TDD testNg, the framework and the same tests are being executed on different screen sizes and devices, the device name should be added in the execution command.

Challenges concerning the React frontend application.

1. Web elements are usually built and destroyed each time the pages are loading, or a new component is showing so relocating them returns errors.
2. With each update the frontend developers do, the already created xpaths for the framework are obsolete and the elements can’t be located whether the elements are located via absolute or relative xpaths or other locators as many components of the react app are reusing the same css classes and the same name of the component over and over.

Those challenges make the creation of automation tests for the React applications hectic even with no coding tools like Katalon studio, whether the web elements on the same page got destroyed and rebuilt,  or the locators have changed completely.

How to fix:
1. Using the PlayWright inbuilt xpaths option using the inbuilt wait.
2. Applying OOP principals updates the locators for  the page as the return of a new instance of the page returns fresh elements every time it is called.
3. When assigning a locator to a web element on the page create a relative xpath, it should be relative  to the component, scroll to view to the component when the Object of the component is called can also make it easier to locate the elements.
4. Remember that React is all about reusing components, so it is totally normal that the same class and data in the html appear multiple times in different components, so use Parent child and ancestor xpaths to locate the desired component. 



<h3>Test Plan</h3>


The objective of this test plan is to define the criteria and the execution steps and procedures for the test cases as well as tools and reporting standards and methods for the team to follow.

The following users will be considered as different categories of users, as some services offer basic user, gold user and so on.

We will define 6 categories of users:
1. standard_user
2. locked_out_user
3. problem_user
4. performance_glitch_user
5. error_user
6. visual_user

Project description:
The project is an e-commerce website, that has no APIs just a frontend page, built using react there is no staging environment.

The Test process and the system under test:
The test process is applying the Agile testing methodology, the requirements section will be frequently updated. 
The absence of an API makes the tests based only on the frontend application, the testing will be focused on the integration and functionality of the application on different browsers and devices testing responsiveness and touch functionality using PlayWright emulators.

Entry criteria: Each category of users is considered as an entry point for a test scenario that covers the requirements in the requirements section, as there are no specific requirements for each category of users.

Exit criteria the following should be applied for each category of users:
1. Functionality is verified across defined devices and browsers.
2. All test cases related to requirements has been tested.
3. Design is verified across devices and browsers for each page.
4. All risk based test cases have been executed and verified. 

In order to achieve the entry and exit criteria mentioned above, the following tests should be executed through the automation framework as well as manually, depending on the specific test cases mentioned in the test cases section.
1. Sanity tests.
2. Smoke tests.
3. Regression tests.

The Sanity tests: Should cover the basic functionality of the application under test for all categories of users across different devices and browsers (we are under the assumption that each category of users has a difference in the UI). The sanity tests should be executed before running the tests of a new feature or a new change in code, if the sanity tests passes then proceed to the ticket under test.

Smoke tests: Should be executed after the verification and/or the reopen of the ticket or feature under test, smoke tests covers the main functionality plus the user experience tests, the User Acceptance Tests as well as the main features of the application for each user category.

Regression tests: Should be executed after the verification of the ticket under test, and the pass of the smoke and sanity tests. The main purpose of the regression tests is to validate that the build did not introduce any new bugs to the system. 

Tools and dependencies:
The tester should have access to the following:
1. Device laptop with chrome, firefox, edge.
2. Device with Safari installed. 
3. Jira.
4. Browser stack (if possible, if not use dev tools for responsiveness).
5. Access to the staging and production environments.
6. Username and password for each category of users.

Requirements:

Requirements under test the following is an exhaustive list of requirements for testing the application, each webpage of the application
will have its features listed and according to the requirements test cases should be created.


System requirements:
This is an e-commerce application that should cover the following requirements;
1. The system should be work with the following browsers:
    - Firefox
    - Safari
    - Edge
    - Chrome
2. The application should display the products on the home page with each product Item should contain the product title,corresponding  picture, description, and button to add to cart.
3. User can add and remove products to his cart.
4. User can login with correct username and password, and the home page is loaded with the design required for the user category.
5. The application should be able to properly calculate the amount to pay for the complete order displaying each product quantity and price.
6. The amount before tax as well as the amount after tax and the tax amount should all be displayed separately.
7. The application should require from the user its data related to the delivery.

Features per page :

Login Page  User stories:
Authentication:
   - User enters correct username and correct password, user is redirected to home page.
   - User enters correct username and wrong password, the error for the wrong password is shown.
   - User enters wrong username and correct password, the error for the wrong email is shown.
   - User enters locked-out username and password, the error for the locked-out user is shown.
   - Repeat the test on the above mentioned browsers and devices, both on landscape and portrait.
   - Compare the design on different devices with the design required on Zoho.

Home page:
   - Home page component is loaded. 
   - The product items are shown. 
   - Each product item has its related picture title and description.
   - The header and footer are visible.
   - When the user clicks on the product title the single page product is loaded.
   - User clicks on the "Add product" button, the text on the button becomes "Remove" and the number on the cart logo on the header should increment by one.
   - User clicks on the cart logo, and the cart page is loaded.
   - Ordering filter is ordering the products according to name (A to Z) and (Z to A), price (Low to high) and (high to low).
 

Cart Page:

  - Cart page components are loaded the items chosen by the user are loaded.
  - The header is visible with the burger list the webpage title and the cart logo.
  - If the user has chosen products the cart page should have shown the list of products with quantity and product description and price per item.
  - User clicks on the "Continue Shopping" button the user is redirected to the home page.
  - User clicks on the "Check Out" button the user is redirected to the Checkout information page.
  - Header is visible with the burger list, the cart logo, the title for the page.

CheckOut information:

  - Header is visible with burger list cart logo and the title for the page.
  - The page should contain 3 input text boxes for the user first name, last name, zipcode. 
  - User clicks on the "Continue" button after filling the required text boxes the user is redirected to the Checkout:Overview page.
  - User doesn't enter the values for the text boxes and clicks "Continue" button, the error "Error: First Name is required" should appear.
  - User enters first name only for the text box and clicks "Continue" button, the error "Error: Last Name is required" should appear.
  - User enters first name and last name but not the ZipCode and clicks "Continue" button, the error "Error: Postal Code is required" should appear.
  - User clicks on the "Cancel" button to return to the cart page.

Checkout Overview page:
 
  - Header contains the burger list and the cart logo.
  - List of the items chosen by the user. 
  - Payment information, should be visible.
  - Shipping information, should be visible.
  - Price before tax, should be visible.
  - Tax amount, should be visible.
  - Total amount, should be visible.
  - User clicks on "Finish" button, then redirects to the checkout complete page.
  - User clicks on the "Cancel" button, gets redirected to the home page.

Functional and user experience requirements:

1. User should be able to logg in only with correct username and password.
2. Product items should be loaded and visible containing:
   - Related picture.
   - Title of the product containing its name.
   - Description of the product.
   - The amount of the items displayed on the home page should be at least 6.
3. Wen user clicks on the "Add to cart" button the item should be added to the cart.
4. When the user clicks on the cart loggo should be redirected to the cart page.
5. Each page that the user is redirected to should be tested as the requirements mentioned on the requirements part.



Test cases criteria and example:
Any feature whether it was already mentioned above or new to be developed, should be added to the requirements mentioned above,
requirements should be updated according to the development.

Test Case example:
Title: Adding items to cart button
Items add button is changing text and incrementing the amount on the cart logo.
Steps:
1. User is logged in.
2. User is on home page.
3. User clicks on the "Add To Cart" button.
Expected result: Button text is changed to "Remove" and  number of items on the cart is incremented by one.

Bug reporting standards:
A bug report should have the following information, user category device browser, and clear steps to reproduce.
Bug example:
Title: The home page does not refresh after the user clicks on the "Reset App State" the page does not refresh.
Device: laptop linux jammy 22.04
Browser: Chrome (add the browser version here).
Steps to reproduce:
1. Standard user is logged in.
2. User adds Items to the cart.
3. User clicks on the burger list while he is on the home page.
4. User clicks on the burger list.
5. User clicks on the "Reset App State" option on the burger list page.

Actual results: The number on the cart has been deleted is not visible anymore, but the chosen items are still having the remove test instead of "Add to cart".

Expected results:
1. The number on the cart should disappear and the cart gets emptied (achieved).
2. The items button should change from "Remove" to "Add to cart".(Not achieved).

Risks and their mitigation:

This is an e-commerce website that has main objective of presenting products to the different categories of users as well assuring the data for the delivery.
Risks:
1. User is logged in but the products items are not displayed or not displayed correctly.
2. User adding items to cart but the cart is not updating correctly.
3. User has removed item from the cart, but it is showing on the checkout page, or on the cart page.
4. The amount to pay is not calculated correctly.
5. The order has been accepted without the correct information on the delivery.
6. The User can't add multiple items to the cart.
7. Application behavior fails on any of browser device combination. 
8. The new shipping order is not created after the user gave his address details, and displayed.
9. The payment information was not verified before verifying the order, and displayed.
10. A user category doesn't access the features offered to him (hypothetical).

Risks mitigation:
The risk mitigation technic is to create and automate tests that covers the risks, the smoke test phase, should cover:
Create test cases for each user Category that covers the following:
1. Items are properly displayed on the home page if the user is properly logged in.
2. User can add items to cart and the cart is updating accordingly by updating the amount on the cart logo as well as on the cart page.
3. If user removes an item from the cart, the cart should update and the removed item doesn't show.
4. The amount to be paid for the items in the cart is shown and the  sum of the chosen items price is accurately. 
5. The tax amount is according to the legislation and calculated accordingly.
6. The total amount to pay should be displayed independently and should equal to (sum of the products items prices*(1+tax percentage)).
7. The tests should be run on different device browsers combination covered on the tools section.
8. The shipping order should be verified and that it is displayed, each order should have a different order ID.
9. The payment information should be displayed and verified for each user category.
10. Each user category should have tests for its additional feature, and difference in design.
11. The tests covering the risk mitigation should be automated and the test procedures and strategy should apply.

TDD VS BDD 

Test Driven Development or Behavior Driven development which way to go?

Test driven development in a nutshell is accepting the code from the development team if it covers some predefined tests, unit tests functional test and User Stories, User Acceptance Criteria.
In a test driven development approach the requirements are divided into smaller requirements Unit tests, functional tests  and integration tests between different components, in order to achieve the testing of a complete feature.
Example related to this application (hypothetical): 
Feature: Order by name A to Z and Z to A.
1. Create a unit test for the API to be sent with the user request to get the order of the alphabetical order and analyse the response.
2. Compare the API response in the API with the data in the Data base if there was related products that were not added into the API response.
3. Test the integration between the API and the webb application.
Each step from the above needs a set of test cases in order to fulfill the requirements. 

Behavior driven development in a different nutshell is dividing the requirements into different features, and each feature will be tested by different scenarios written in plain english using Gherkin cucumber which makes the testing procedures more related to what the user experience will be like,
the cucumber gherkin approach allows the none technical team members to be able to participate in the testing process as the test results as well will show which step of the test is failing, those gherkin English steps will be detailed in the Feature files, and executed automatically.
Example of the BDD gherkin approach:
Feature: Filter order products items by name A to Z and Z to A.
Scenario: 
Given User is logged in.
And User is on home page.
And User clicks on the Filter button.
And User chooses from the select list order A to Z.
Then The items are alphabetically ordered from A to Z.

Each select option will  have its own scenario, and those scenarios will be running for each category of users.

So which way to go BDD or TDD, the best testing approach would be to combine both technics in order to achieve best testing practices,
TDD tests should be created and executed as the development process is going in parallel, the gherkin Cucumber BDD tests are being executed in order to mimic the user experience with each feature.

Test Driven Development approach is the approach that the development should cover certain predefined tests, the tests are not related to a certain behavior or interactions.
The test driven approach covers not only functional tests, also it covers design as well as user experience.

The BDD or behavior driven approach is the creation of test cases using simple steps that mimics the user behavior, here the focus is on the behavior of the application given certain conditions to be executed.


Automation tests reporting standards

In this framework 2 types of reports are being used the first is the Extent report the second is the Cucumber reports:

1. Both reporting utilities should have the detailed steps executed.
2. The User Category that has executed the test.
3. The browser name and the device that the tests where running on (Emulated device).
4. The metrics how many tests were running and the amount of passed and failed tests.
5. In case of a failed UI test a snapshot of the bug should be added to the report.
6. Which kind of test was executed (Smoke, Sanity, Regression) and when time stamp for the execution.

Notice, the same framework is running both TDD and BDD tests same code different execution styles, maven profiles is used
in order to have different builds for running the tests via jenkins, the tdd tests are running using docker images, and the bdd tests are running using maven.



How to run the BDD tests on the terminal 
``` mvn
mvn -e clean test -Pbdd  -Dbrowser=chrome -Dheadless=false -Ddevice=desktop  -Dcucumber.filter.tags="@Regression"
 ```
<h5> to run the TDD as an executable jar file </h5>
 first

``` Bash
mvn clean -Ptdd package -DskipTests
```
then cd to target/docker-resources

``` Bash
java -Ddevice=iphone14 -Dbrowser=safari -cp "libs/*" org.testng.TestNG testSuites/RegressionTest.xml
```
