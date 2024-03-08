# Epam-POC-Playwright

## Pre-requisites
This framework run on Java 11.

## Run your first Playwright test
1. Clone this repository
2. Install the dependencies on pom.xml
3. Choose the command line below to run test on Web or Mobile:

On Web:
`mvn test -Dsurefire.suiteXmlFiles="testNG/desktop.xml" -DisMobile=false`

On Mobile:
`mvn test -Dsurefire.suiteXmlFiles="testNG/desktop.xml" -DisMobile=true`

If you need to re-run the test failed, set the rerunFailingTestsCount property to be a value larger than 0:
`mvn test -Dsurefire.rerunFailingTestsCount=2 -Dsurefire.suiteXmlFiles="testNG/desktop.xml"`

## Use Test Generator
Open terminal of your project and run the command line below:

`mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen"`

## Use Trace Viewer(Screenshot)
Open terminal of your project and run the command line below:

`mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="show-trace traces/<fileNameTest>.zip"`

## Generate Allure report
When running the tests, `allure-results` folder is generated.
To view report, use command:

`allure serve allure-results`

### The list test cases supported run on Mobile:
1. HeaderLocationTest