# Rate Calculation

There is a need for a rate calculation system allowing prospective borrowers 
to obtain a quote from our pool of lenders for 36 month loans. This system 
will take the form of a command-line application. You will be provided with 
a file containing a list of all the offers being made by the lenders within 
the system in CSV format, see the example market.csv file provided alongside 
this specification. You should strive to provide as low a rate to the borrower 
as is possible to ensure that  as competitive as they can be 
against our competitors'. You should also provide the borrower with the details 
of the monthly repayment amount and the total repayment amount. Repayment 
amounts should be displayed to 2 decimal places and the rate of the loan should 
be displayed to one decimal place. Borrowers should be able to request a loan 
of any £100 increment between £1000 and £15000 inclusive. If the market does 
not have sufficient offers from lenders to satisfy the loan then the system 
should inform the borrower that it is not possible to provide a quote at that 
time.

The application should take arguments in the form:
```
 	cmd> [application] [market_file] [loan_amount]
```

Example:
```
	cmd> quote.exe market.csv 1000
	Requested amount: £1000
	Rate: 7.0%
	Monthly repayment: £30.78
	Total repayment: £1108.10
```

Remarks:
- Please write your solution in Java
- Your code should be tested to the appropriate degree.
- The solution should exhibit readability, modularity and good use of 
abstraction.
- The monthly and total repayment should use monthly compounding

## Getting Started

* Clone this repo to your local machine.
```
C:\RateCalculator>
```
* Ensure you have Maven installed 
(https://maven.apache.org/download.cgi#Installation)
* From the main directory, run `mvn clean package` 
```
C:\RateCalculator>mvn clean package
```
* To run the program, run use the following command:
`java -jar target/RateCalculator-1.0-SNAPSHOT.jar src/main/resources/marketData.csv 1100`

```
C:\RateCalculator>java -jar target/RateCalculator-1.0-SNAPSHOT.jar src/main
/resources/marketData.csv 1100
```
### Prerequisites

Ensure you have Maven installed (https://maven.apache.org/download.cgi#Installation)

### Installing

* Clone this repo to your local machine.
```
C:\RateCalculator>
```
* Ensure you have Maven installed 
(https://maven.apache.org/download.cgi#Installation)
* From the main directory, run `mvn clean package` 
```
C:\RateCalculator>mvn clean package
```
* To run the program, run use the following command:
`java -jar target/RateCalculator-1.0-SNAPSHOT.jar src/main/resources/marketData.csv 1100`

```
C:\RateCalculator>java -jar target/RateCalculator-1.0-SNAPSHOT.jar src/main
/resources/marketData.csv 1100
```

## Notes

* Scales for precision of calculation can be modified through properties file

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [IntelliJ Idea](https://www.jetbrains.com/idea/) - IDE

## Authors

* **Atila Ekimci** - *aek* - [github](https://github.com/Atilaek)

## Acknowledgments

* https://www.mkyong.com/
* https://dzone.com/
* https://stackoverflow.com/
