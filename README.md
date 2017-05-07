# Knapsack Vanilla and Its Various Flavors

<img src="./images/knapsack.png" alt="Drawing" style="margin-left: 0px; width: 140px;"/>

## Introduction ##
This project explores different approach to the familiar Maximum 0-1 Knapsack problem:
* Maximum Value Dynamic Programming 
* Minimum Cost Dynamic Programming
* Greedy 2-Approximation 
* Fully Polynomial-time Approximation Scheme

As a _side_ experiment, we attempt to reduce 3SAT to Decision 0-1 Knapsack problem.

## Usage
To run the experiment, use the build script. You can modify various run settings by modifying [config.properties](config.properties). This script also helps building the report written in R. To modify the rendering of the report, refer to [configs.R](src/report/configs.R). **Make sure that you run the script directly, i.e. launch it from the directory it belongs to.**
```
./build.sh
```
The report comes in 2 different formats: [PDF](document/report.pdf), HTML (gitbook, bookdown). To view the HTML version, you can view this [file](document/index.html) or for better functionality, we recommend using the start script which serves the HTML files at a local server, i.e. [localhost:2302](localhost:2302), on your machine (with this, you can use the search function within the document).

```
./start.sh
```

The default port is 2302, which can be changed by modifying [configs.R](src/report/configs.R).

## Structure
* **src**: source codes for the project (Java and R).
* **bin**: Java generated classes.
* **data**: result of the experiments (in CSV and TXT).
* **document**: the [report](document/report.pdf) on result of the experiments.
