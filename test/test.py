# Script testing how changing tabu list travelling salesman problem solver
# parameters affects its performance by generating graphs and launching solver.
# Author:       Maciej 'mc' Suchecki

import os
import string
import random
import timeit
import itertools
import subprocess
import matplotlib.pyplot as plt

############################## FUNCTIONS ##############################

# generates random complete graph with desired amount of verticles
# and saves the edges to selected filename
# verticles count is max 26, because of conversion to letters
def saveRandomGraphToFile(filename, verticlesCount):
  graphFile = open(filename, "w")

  verticles = list(string.ascii_uppercase)
  verticles = verticles[:verticlesCount]

  # iterate over every possible pair containing two different verticles - which
  # means every edge in undirected complete graph - and write it to the file
  for edge in itertools.combinations(verticles, 2):
    weight = str(random.randint(1, 100)) 
    graphFile.write(edge[0] + " " + edge[1] + " " + weight + "\n")

  graphFile.close()

# runs the solver with desired parameters
def runSolver(parameters):
  command = ["java", "-jar", "./solver.jar", "-tabuSize", str(parameters[0]),\
          "-plus", str(parameters[1]), "-min", str(parameters[2]), "-max", str(parameters[3])]
  result = subprocess.check_output(command)
  return int(result)

# runs solver, gets result and run time
def runSolverAndMeasureTime(parameters):
  start = timeit.default_timer()
  result = runSolver(parameters)
  stop = timeit.default_timer()
  time = stop - start
  return time, result

# plots a simple graph
def plotGraph(data, xlabel, ylabel, figureNumber):
  plt.figure(figureNumber)
  plt.plot(list(data.keys()), list(data.values()))
  plt.ylabel(ylabel)
  plt.xlabel(xlabel)
  plt.show()

############################## SCRIPT ##############################

# parameters to test
defaultTabuSize = 10
defaultPlus = 5
defaultMin = 20
defaultMax = 40
tabuListSizes = range(0,50)
plusParameters = range(0,100)
minParameters = range(0,100)
maxParameters = range(0,100)

# create dictionaries for plotting
timesDictionary = {}
resultsDictionary = {}

# generate random complete graph and save it to file
saveRandomGraphToFile("./graph.txt", 20)

# test how changing tabu list size affects performance
for size in tabuListSizes:
  parameters = (size, defaultPlus, defaultMin, defaultMax)
  time, result = runSolverAndMeasureTime(parameters)
  timesDictionary[size] = time
  resultsDictionary[size] = result
plotGraph(timesDictionary, "Rozmiar listy Tabu", "Czas (ms)", 0)
plotGraph(resultsDictionary, "Rozmiar listy Tabu", "Wynik (długość ścieżki)", 1)
timesDictionary = {}
resultsDictionary = {}

# test how changing plus parameter affects performance
for parameter in plusParameters:
  parameters = (defaultTabuSize, parameter, defaultMin, defaultMax)
  time, result = runSolverAndMeasureTime(parameters)
  timesDictionary[parameter] = time
  resultsDictionary[parameter] = result
plotGraph(timesDictionary, "Wartość parametru Plus", "Czas (ms)", 2)
plotGraph(resultsDictionary, "Wartość parametru Plus", "Wynik (długość ścieżki)", 3)
timesDictionary = {}
resultsDictionary = {}

# test how changing min parameter affects performance
for parameter in minParameters:
  if parameter < defaultMax:
    parameters = (defaultTabuSize, defaultPlus, parameter, defaultMax)
  else:
    parameters = (defaultTabuSize, defaultPlus, parameter, parameter + 10)
  time, result = runSolverAndMeasureTime(parameters)
  timesDictionary[parameter] = time
  resultsDictionary[parameter] = result
plotGraph(timesDictionary, "Wartość parametru Min", "Czas (ms)", 4)
plotGraph(resultsDictionary, "Wartość parametru Min", "Wynik (długość ścieżki)", 5)
timesDictionary = {}
resultsDictionary = {}

# test how changing max parameter affects performance
for parameter in maxParameters:
  if parameter > defaultMin:
    parameters = (defaultTabuSize, defaultPlus, defaultMin, parameter)
  else:
    parameters = (defaultTabuSize, defaultPlus, parameter - 10, parameter)
  time, result = runSolverAndMeasureTime(parameters)
  timesDictionary[parameter] = time
  resultsDictionary[parameter] = result
plotGraph(timesDictionary, "Wartość parametru Max", "Czas (ms)", 6)
plotGraph(resultsDictionary, "Wartość parametru Max", "Wynik (długość ścieżki)", 7)
timesDictionary = {}
resultsDictionary = {}
