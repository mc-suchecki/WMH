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

############################## SCRIPT ##############################
maxIterations = 50
graphSize =  10

averageResults = {}
filenames = graph.saveRandomGraphsToFiles(maxIterations, graphSize)
for iterations in range(1, maxIterations):
  print(iterations)
  sumOfResults = 0
  for j in range(iterations):
    sumOfResults += runSolver(filenames[j])
  averageResults[iterations] = sumOfResults / iterations

plotGraph(averageResults, "Liczba iteracji", "Średni wynik")
