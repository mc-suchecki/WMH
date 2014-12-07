import os
import sys
import graph
import subprocess
import matplotlib.pyplot as plot

############################ FUNCTIONS ############################

# runs the solver with default parameters and pass the input filename
def runSolver(filename):
  command = ["java", "-jar", "./solver.jar", "-graph", str(filename)]
  result = subprocess.check_output(command)
  return int(result)

# plots a simple graph
def plotGraph(data, xlabel, ylabel):
  plot.plot(list(data.keys()), list(data.values()))
  plot.xlabel(xlabel)
  plot.ylabel(ylabel)
  plot.show()

def displayProgress(currentNumber, lastNumber):
  sys.stdout.write("\r%d/%d" % (currentNumber, lastNumber))

############################## SCRIPT ##############################

# parameters
maxIterations = 25
graphSize =  10

# generate graphs
filenames = graph.saveRandomGraphsToFiles(maxIterations, graphSize)

# collect data
averageResults = {}
for iterations in range(1, maxIterations):
  displayProgress(iterations, maxIterations)
  averageResult = 0
  for i in range(iterations):
    averageResult += runSolver(filenames[i])
  averageResults[iterations] = averageResult / iterations

# remove graph files
for filename in filenames:
  os.remove(filename)

plotGraph(averageResults, "Liczba iteracji", "Średni wynik")
