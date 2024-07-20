# Price Formatter

A personal practice project using Angular's Material UI components and a Spring Boot 2 Backend

## Tech Stack:
- Angular 8
- Spring Boot 2

## Assumptions
1. Implement a solution to format a given price on trading application UI.
2. The price of an order or a quote has three parts which are Big Figure (BF), Dealing Price (DP), Fractional PIPS (FP)

## Solution
A Price Formatter App which consist of a <b>UI</b> and an <b>API</b>. This App will accept input from users and generate the Price Format for BF, DP, and FP.

## Instructions

Below are the steps to run the App:

Inside the Project Directory `PriceFormatter/`, run the following command in the terminal.

```
$ mvn clean spring-boot:run
```


Alternatively, using Java, go inside the project directory and execute the following command to run the packaged JAR file.

```
$ java -jar target/priceformatter-0.0.1-SNAPSHOT.jar
```

Once the App is running, access the UI by entering the following URL in your browser:

```
http://localhost:8080/
```

#### Modules

##### I. UI 


The UI is the main interface for entering the prices that will be formatted by the system. The text fields in the UI should only contain numerical values. Please be aware that input validation is not yet included in this release - entering values other than numbers in the text field will be considered as invalid request to the API.

Note: The compiled Javascript files are already included in the package and there is no need to recompile and run the UI code. For reference, the UI source code  is located in `src/main/js/priceformatter-ui` directory.

To build and run the UI, go to the UI source code directoy and run the following commands:

```
$ npm run build

$ ng serve
```


##### II. API

The API contains the logic that will process the input coming from the UI and will send back the formatted values for BF, DP and FP.

Note: The compiled JAR file is already included in the package. To recompile and re-run the test scripts. Run the following command in the project directory:

```
mvn clean package
```

Thank you.

