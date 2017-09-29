# MRP algorithms
This Repository contains a Spring Boot application with different routes for calculating typical production planning algorithms.
Follow the steps to set up a development environment or to run the standalone application.

# Releases  
[Download Version 0_0_1](https://drive.google.com/open?id=0BwL4D4dBsZu_c1pDOEtuaFNZclU)

# Quick Start
Download the latest release candidate.
Open the command line and type.  
> cd \<download directory of the release canidate\>  
> java -jar mrplauf-0.0.1-SNAPSHOT.jar

Caution: make sure that you have installed java runtime environment (jre) 8+  

Open your web browser and navigate to http://localhost:8080
# 1 Development environment

## 1.1 Prerequesites
Download [Eclipse]:(http://www.eclipse.org/downloads/eclipse-packages/)
Download and install [Java JDK8]:(http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

Make sure to set the JAVA_HOME environment variable correct.

## 1.2 Repository
Clone the repository and open it in your favorit IDE as a gradle Project

> git clone https://github.com/Erlix322/MRPLauf

## 1.3 Start up
Start the Application and open a browser under http://localhost:8080

# Routes

The following routes a available: 
> http://localhost:8080/giffler  
Returns an optimized production plan as JSON String
ATTENTION: The optimization is calculated based on the "shortest processing time". This is a very simple yet not the best priority rule in this algorithm.


