# Script testing how changing tabu list travelling salesman problem solver
# parameters affects its performance by generating graphs and launching solver.
# Author:       Maciej 'mc' Suchecki

import os
import string
import random
import itertools

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

############################## SCRIPT ##############################

# parameters combinations to test
plusParameter = range(0:100)
minParameter = range(0:100)
maxParameter = range(0:100)
tabuListSize = range(0:100)

# generate random complete graph and save it to file
saveRandomGraphToFile("./graph.txt", 20)

# for every minParameter:
  # run solver
  # collect run time and result

# for every maxParameter:
  # run solver
  # collect run time and result

# for every plusParameter:
  # run solver
  # collect run time and result

# for every tabuListSize:
  # run solver
  # collect run time and result

# draw relationship between solver performance and minParameter
# draw relationship between solver performance and maxParameter
# draw relationship between solver performance and plusParameter
# draw relationship between solver performance and tabuListSize
