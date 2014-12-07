import subprocess
import matplotlib.pyplot as plot

############################ FUNCTIONS ############################

# runs the solver with default parameters
def runSolver():
  command = ["java", "-jar", "./solver.jar"]
  result = subprocess.check_output(command)
  return int(result)

# plots a simple graph
def plotGraph(data, xlabel, ylabel):
  plot.plot(list(data.keys()), list(data.values()))
  plot.xlabel(xlabel)
  plot.ylabel(ylabel)
  plot.show()

############################## SCRIPT ##############################
averageResults = {}
for iterations in range(1,20):
  print(iterations)
  sumOfResults = 0
  for j in range(iterations):
    sumOfResults += runSolver()
  averageResults[iterations] = sumOfResults / iterations

plotGraph(averageResults, "Iterations", "Average result")
