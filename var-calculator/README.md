### VaR Calculator

A simple application to calculate Value at Risk (VaR) for either a Position or a Portfolio of Positions

##### Contents:
in src/main/java/app/
- stats/VaRCalculator.java - contains the methods to compute the VaR
- VaRApplication.java - a SpringBoot application to view the computation. Can be accessed at https://localhost/8080/index

in src/test/java/stats/
- VaRCalculatorTest.java - a test class to test the methods in VarCalculator


##### Assumptions made:
- only the historical method of VaR is used
- the Nearest-Rank method is used for computing percentiles
- all Positions are made up of 1 share, which implies that Portfolios are equal-weighted 
- historical prices for each Position are greater than zero
- each Position contains 250 historical prices, 250 being an approximate number of trading days in one calendar year
- when calculating VaR for a Portfolio, all Positions have an equal number of prices
- there is a max of 100 positions per portfolio


##### To run:
mvn spring-boot:run