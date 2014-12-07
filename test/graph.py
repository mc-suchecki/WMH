# Python module for generating random complete graphs.
# Author:       Maciej 'mc' Suchecki

import random
import string
import itertools

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

# generates multiple random graphs and saves them to files
def saveRandomGraphsToFiles(graphsCount, verticlesCount):
  filenamesList = []
  for number in range(0, graphsCount):
    filename = './graph' + str(number) + '.txt'
    saveRandomGraphToFile(filename, verticlesCount)
    filenamesList.append(filename)
  return filenamesList
