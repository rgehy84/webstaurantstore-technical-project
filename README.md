# Webstaurant Store Quality Engineering Technical Project
![image](https://user-images.githubusercontent.com/19873511/214816808-bd2d19e9-88e7-49f4-b426-91b193e2323c.png)

This is the Webstaurant Store Quality Engineering Technical Project by Ralph Gehy

This automates the functionality of adding a product to the shopping cart. The steps this automated test case goes through are as follows:
1. Go to https://www.webstaurantstore.com/
2. Search for "stainless work table"
3. Check the search result ensuring every product has the word "Table" in its title.
4. Add the last of found items to Cart.
5. Empty Cart.

The directory break down below displays the file structure that the framework and tests were built.
<img width="349" alt="image" src="https://user-images.githubusercontent.com/19873511/214818091-9deabe1e-41dd-4915-a8f3-bcfa5defdb27.png">


## Software Libraries Used
This project was created with Java (v19), Selenium WebDriver (v4.7.2), Maven (v3.8.7), TestNG v(7.7.1), Extent Reports (v5.0.9), WebDriverManager (v5.3.2) and compiled with the Maven Compiler source and target of 17.

## Framework

Generally, the framework package will be developed, maintained, and updated in a separate library and be imported into any automation project that will provide consistency across automated testing projects.
The framework contains setting up the Selenium WebDriver, the ability to execute the tests in Google Chrome, Mozilla Firefox, Safari, Internet Explorer and Microsoft Edge. 
The framework also uses WebDriverManager to simplify the use of manually maintaining browser driver binaries.

The framework also enhances the assertions to document what is being verified and takes a screenshot which is added to the report. In addition, through the WebDriverActions class, it simplifies common methods which makes it faster and easier to automate the actual test cases.

Lastly, the framework integrates ExtentReports with a custom listener that will write the results with screenshots on Assertions as well as screenshots on failures.

The current version of the framework is mainly focused on UI End to End testing but can add the capabilities of backend API testing as well as database testing. In addition, the framework can be enhanced to run headless, run on a VNC based Selenium Grid either locally or in a Cloud server.


## Test Cases
In the actual TestAddToCartFlow class, it creates an instance of the Page objects being used (MainNavigation, Search Results page, Product Details page, and Shopping Cart page) to navigate through the site. This reduces the amount of repitive code when automating which leads to greater efficiency.

## Executing the Test Cases
This automation project can be executed through a Java-based IDE or through the command-line with Maven. Maven as well as at least JDK 17 (preferably JDK 19) must be installed in order to successfully be executed.

The code to execute in the command line will be the following:

`mvn test -DsuiteXmlFile="testng-xml-runners/webstaurantstore-testng-runner.xml"`

## Reporting 
Below is a sample report generated from the test execution. You can see the HTML generated report here: extent-reports/extent-report.html
<img width="1437" alt="image" src="https://user-images.githubusercontent.com/19873511/214820077-ba1cc74e-7034-4c99-bb10-550ac99c1b0c.png">


## Conclusion
Thank you for taking the time to review my WebstaurantStore Technical Project. While this project was written in Java and TestNG, I am certain the concepts of automation and development remain the same with Groovy.
