# Reqres First Project
Rest API based on test API : https://reqres.in/

## Table of Contents
* [General info](#general-info)
* [Code Examples](#code-examples)
* [Technologies](#technologies)
* [Setup](#setup)
* [Launching](#launching)
* [Inspiration](#inspiration)

## General info
Test project for learning Rest API Test with Kotlin, GIT and RestAssured.
This is a project that preserves knowledge from the online course material.

## Code examples

```
@Test
    fun getDelayedResponse(){
        val getDelayedResponse =  given().spec(requestSpecification)
            .queryParam("delay","3")
            .`when`().get("users")
            .then().assertThat().statusCode(200).assertThat()
            .contentType(ContentType.JSON)
            .extract().response().asString()
            Payload().log(getDelayedResponse)
    }
```

## Technologies
Project is created with: 
* IntelliJ IDEA Community Edition 2021.2.2
* JAVA 17
* Kotlin 1.5.10
* MAVEN 2.22.2
* Rest Assured 4.4.0
* Hamcrest 2.2
* JUnit 4.13.2

## Setup
To open this project you have to:

### 1. Install IntelliJ IDEA Community Edition
- Open Google and search for IntelliJ IDEA   
- Open the page and click on Download
- As you can see, there are two versions of the software – a free Community version and a paid Ultimate version with a 30-day trial period. In most cases, a free version will be enough
- Click the Download under the Community version. The downloading will start automatically. The file will be saved in the specified folder
- Open the folder with the downloaded file and run it. 
- Select the folder for installation and click on the Next. 
- At the Installation Options step, tick the checkboxes next to the 64-bit launcher (if you have a 64-bit version of Windows), Add launchers dir to the PATH, and .java. All these parameters are not mandatory, but they will make your life easier in the future.
- Complete the installation

### 2. Download and install java
- Open Google and search for java
- Download Java from oracle page
- Install java on your computer
- Add java to your path:   
```
> In search, search for and then select: System (Control Panel)   
> Click the Advanced system settings link    
> Click Environment Variables   
> In the Edit System Variable window, specify the value of the PATH environment variable   
> Save changes
```

### 3. Install GIT
- To install GIT go to: https://git-scm.com/book/en/v2/Getting-Started-Installing-Git

### 4. Clone Repository from GitHub and open in IntelliJ
- Click on Green button "Code"
- Copy HTTPS address
- Open IntelliJ
- > If you already have open project then goo to File -> New -> Project From Version Control... and open it
- > else just click on Get from Version Control
- Paste HTTPS address into URL input
- You can change the directory - it is up to you
- Click Clone button

## Launching

### Configured RunAllTest Configuration
* Save all JUnits test
* Select test you want to be last
* Add all tests in Before launching setup
* REMEMBER NOT to add your last test in before launching setup otherwise it will launch twice
* Apply configuration

### How to launch test
* Click on Run
* Click on Run
* Choose RunAllTests

## Inspiration

* Kotlin fundamentals https://app.pluralsight.com/library/courses/kotlin-fundamentals/table-of-contents
* Rest API Automation Testing Rest Assured https://www.udemy.com/course/rest-api-automation-testing-rest-assured/

## Author
Katarzyna Radecka
