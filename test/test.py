# Script testing how changing tabu list travelling salesman problem solver
# parameters affects its performance by generating graphs and launching solver.
# Author:       Maciej 'mc' Suchecki

import sys
import graph
import timeit
import subprocess
import matplotlib.pyplot as plot

############################## PARAMETERS ##############################

repetitions = 10    # how many times solver will be re-run to calculate avg
graphSize = 20      # size of the graphs to generate for tests purpose

############################## FUNCTIONS ##############################

# runs the solver with desired parameters
def runSolver(parameters, graphFilename):
  command = ["java", "-jar", "./solver.jar", "-graph", str(graphFilename),\
          "-tabuSize", str(parameters[0]), "-plus", str(parameters[1]),\
          "-min", str(parameters[2]), "-max", str(parameters[3])]
  result = subprocess.check_output(command)
  return int(result)

# runs solver, gets result and run time
def runSolverAndMeasureTime(parameters, graphFilename):
  start = timeit.default_timer()
  result = runSolver(parameters, graphFilename)
  stop = timeit.default_timer()
  time = stop - start
  return time, result

# runs solver multiple times with different graphs
# and collects average result and average time
def collectAverageTimeAndResult(parameters, graphFilenames):
  averageTime = 0
  averageResult = 0
  for graph in graphFilenames:
    time, result = runSolverAndMeasureTime(parameters, graph)
    averageTime += time
    averageResult += result
  averageTime /= repetitions
  averageResult /= repetitions
  return averageTime, averageResult

# plots a graph containing 2 relationships: time(parameter) and result(parameter)
def plotGraph(times, results, xlabel):
  figure, axis1 = plot.subplots()
  axis2 = axis1.twinx()
  time, = axis1.plot(list(times.keys()), list(times.values()), 'r-')
  result, = axis2.plot(list(results.keys()), list(results.values()), 'g-')
  axis1.legend([time, result], ['Czas', 'Wynik'], 'upper right')
  axis1.set_xlabel(xlabel)
  axis1.set_ylabel('Czas (ms)')
  axis2.set_ylabel('Wynik (długość ścieżki)')
  plot.show()

def displayProgress(currentNumber, lastNumber):
  sys.stdout.write("\r%d/%d" % (currentNumber, lastNumber))

############################## SCRIPT ##############################

# parameters to test
defaultTabuSize = 15
defaultMin = 10
defaultMax = 20
defaultPlus = 5
tabuListSizes = range(1, 51)
minParameters = range(1, 91)
maxParameters = range(11, 101)
plusParameters = range(1, 101)

# create dictionaries for plotting
timesDictionary = {}
resultsDictionary = {}

# generate random complete graphs
print("Generating test graphs...")
graphFilenames = graph.saveRandomGraphsToFiles(repetitions, graphSize)

# test how changing tabu list size affects performance
print("Testing Tabu list size...")
for size in tabuListSizes:
  displayProgress(size, tabuListSizes[-1])
  parameters = (size, defaultPlus, defaultMin, defaultMax)
  time, result = collectAverageTimeAndResult(parameters, graphFilenames)
  timesDictionary[size] = time
  resultsDictionary[size] = result
plotGraph(timesDictionary, resultsDictionary, "Rozmiar listy Tabu")
timesDictionary = {}
resultsDictionary = {}

# test how changing plus parameter affects performance
print("Testing Plus parameter...")
for parameter in plusParameters:
  displayProgress(parameter, plusParameters[-1])
  parameters = (defaultTabuSize, parameter, defaultMin, defaultMax)
  time, result = collectAverageTimeAndResult(parameters, graphFilenames)
  timesDictionary[parameter] = time
  resultsDictionary[parameter] = result
plotGraph(timesDictionary, resultsDictionary, "Wartość parametru Plus")
timesDictionary = {}
resultsDictionary = {}

# test how changing min parameter affects performance
print("Testing Min parameter...")
for parameter in minParameters:
  displayProgress(parameter, minParameters[-1])
  if parameter < defaultMax:
    parameters = (defaultTabuSize, defaultPlus, parameter, defaultMax)
  else:
    parameters = (defaultTabuSize, defaultPlus, parameter, parameter + 10)
  time, result = collectAverageTimeAndResult(parameters, graphFilenames)
  timesDictionary[parameter] = time
  resultsDictionary[parameter] = result
plotGraph(timesDictionary, resultsDictionary, "Wartość parametru Min")
timesDictionary = {}
resultsDictionary = {}

# test how changing max parameter affects performance
print("Testing Max parameter...")
for parameter in maxParameters:
  displayProgress(parameter, maxParameters[-1])
  if parameter > defaultMin:
    parameters = (defaultTabuSize, defaultPlus, defaultMin, parameter)
  else:
    parameters = (defaultTabuSize, defaultPlus, parameter - 10, parameter)
  time, result = collectAverageTimeAndResult(parameters, graphFilenames)
  timesDictionary[parameter] = time
  resultsDictionary[parameter] = result
plotGraph(timesDictionary, resultsDictionary, "Wartość parametru Max")
timesDictionary = {}
resultsDictionary = {}

# remove graph files
for filename in graphFilenames:
  os.remove(filename)
