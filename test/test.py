# Script testing how changing tabu list travelling salesman problem solver
# parameters affects its performance by generating graphs and launching solver.
# Author:       Maciej 'mc' Suchecki

import os
import string
import random
import timeit
import itertools
import subprocess

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
  command = ["java", "-jar", "./solver.jar", "-tabuSize", parameters[0],\
          "-plus", parameters[1], "-min", parameters[2], "-max", parameters[3]]
  result = subprocess.check_output(command)
  return int(result)

# runs solver, gets result and run time
def runSolverAndMeasureTime(parameters):
  start = timeit.default_timer()
  result = runSolver(parameters)
  stop = timeit.default_timer()
  time = stop - start
  return time, result

############################## SCRIPT ##############################

# parameters to test
defaultTabuSize = 25
defaultPlus = 5
defaultMin = 20
defaultMax = 40
tabuListSizes = range(10,100)
plusParameters = range(0,100)
minParameters = range(0,100)
maxParameters = range(0,100)

# empty dictionaries keeping data
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
# TODO draw relationship between solver performance and tabuListSize
timesDictionary = {}
resultsDictionary = {}

# test how changing plus parameter affects performance
for parameter in plusParameters:
  parameters = (defaultTabuSize, parameter, defaultMin, defaultMax)
  time, result = runSolverAndMeasureTime(parameters)
  timesDictionary[parameter] = time
  resultsDictionary[parameter] = result
# TODO draw relationship between solver performance and plusParameter
timesDictionary = {}
resultsDictionary = {}

# test how changing min parameter affects performance
for parameter in minParameters:
  # run solver
  # collect run time and result
  pass
# TODO draw relationship between solver performance and minParameter
timesDictionary = {}
resultsDictionary = {}

# test how changing max parameter affects performance
for parameter in maxParameters:
  # run solver
  # collect run time and result
  pass
# TODO draw relationship between solver performance and maxParameter
timesDictionary = {}
resultsDictionary = {}
