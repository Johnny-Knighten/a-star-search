# A* Search

This is a java based implementation of two different versions of the A* search algorithm; the classic A* search and the
Iterative Deepening A* search(IDA*).
 
It is written in such a way that users implement the details about the search nodes(state representation, heuristic 
calculations, and child node generation) used in a state space search problem and the implemented algorithms find the
optimal path from the initial state to the goal state.


## State Space Searches

State space search problems attempt to find the optimal path between an initial state and a goal state. This problem is
equivalent to finding the optimal path between two nodes on a graph. Each state can be represented as a node on a graph.
Each node has a set of reachable neighboring nodes which represent the states that can be reached from the current state.
By exploring paths on the graph we attempt to find the optimal path from a starting node to a goal node.
 
In state space search problems we have states which can me modified to make new states. For instance we might have a 
puzzle we are trying to solve. We start at some state that is not the solution to the puzzle. The puzzle may have some set
of rules that dictate what moves are currently possible(for instance we can only move an item a specific direction). So 
from that starting state there may only be a few possible moves and each of these moves will be a new state. Eventually
by searching the possible states we will find the goal state we are searching for.

A simple example would be navigating a maze. We start at some point in the maze and the goal is to reach the maze's 
endpoint. At any given point in the maze we have 4 basic directions that can be traveled: north, south, east, west. Of course
there may be a wall that limits the possible directions or one direction may be back tracking. Your starting point is a node
on a graph and each possible direction you can travel is a node connected to the current node. We can search through this
graphs of states(state being the current position you are in) to find the state which represents you reaching the endpoint.


## State Space Search Algorithms

There are a few algorithms that are popular for state space searches: depth-first search(DFS), breadth-first search(BFS),
iterative deepening depth-first search(IDS), greedy searching, and A* search.

All these algorithms have their own pros and cons. These pros and cons are often focused on: if the optimal solution is 
found, running time, and memory usage. We will skip discussion of other algorithms and solely focus on A* and IDA*. These
two algorithms will both find optimal solutions, but differ in running time and memory usage. A* can consume more more
memory than IDA*. IDA* can search the same paths over and over since it doesn’t use dynamic programming like A*, which
can result in longer runtimes.


## A* and IDA* Algorithms

Both these algorithms are similar in nature in that they try to intelligently select the paths they search. They both do
this by using a heuristic function that scores how close (in terms of steps to reach the goal node) a node is to the goal
node. The general idea is that we want to explore nodes that are getting closer to the goal node and have a short path
from and starting node. A node that has a good heuristic scores mean we are getting closer to the goal node. Having a
short path from the starting nodes means we are finding the shortest

An important characteristic of the heuristic function is that it must be admissible. A heuristic function is admissible
if it underestimates or calculates the exact number of steps to reach the goal node. If the heuristic function is not
admissible we cannot guarantee the optimal path will be found.

The main difference about these two algorithms is the process in which they explore the search space. In A* a priority
queue is used to keep track of all possible children nodes yet to be explored. This means there can be a large number of 
nodes sitting in the queue which can waste a large amount of memory. IDA* on the other hand searches the graph in an
iterative fashion exploring the search space at increasing depths per iteration. In each iteration it only stores the
current path being explored. This leads to the memory usage being linear to the length of the path being explored. The
downside to IDA* is that the same paths will be revisited on each iteration possibly resulting in a longer runtime.


## How To Use

The AStarSearch and IDAStarSearch classes are responsible for performing  state space searches using the algorithm they
are named after. Both classes must be provided an initial state and a goal state, which are instances of a class that
extends the abstract class AStarNode. The AStarNode abstract class uses a generic which will be the state representation
of the node. AStarNode contains implemented methods for getting/setting a nodes graph cost(g), heuristic score(h), total
score(f), state representation, and parent node. Classes that extend AStarNode must implement methods that generate
successor nodes, calculates the search graph distance from the nodes parent, calculates the nodes heuristic score, generate
a hash code, and an equals method that is based on the node's state representation. Essentially a state space search problem
must be represented by a class that extends AStarNode.

After creating a class that represents a state space search problem, you then create search space nodes that represents
the initial and goal state. Next you supply these two nodes to either AStarSearch or IDAStarSearch's constructor. To begin
the search method is called to being the search, both AStarSearch and IDAStarSearch provide the search method. The search method will return the final
node on the search path, or null if the goal state cannot be found. To generate the full path as a list, call the getPath method
supplying it the node returned from the search method.


To summarize, in order to perform a state space search:
1. Create a class that extends AStarNode which represents the state space search problem
2. Create two search node that represent the initial and goal state of the search problem
3. Create an instance of AStarSearch or IDAStarSearch and supply the two nodes created above
4. Call the search method provided by AStarSearch or IDAStarSearch
5. To get the search path, provide the node returned by the search method to the getPath method

Below are some implemented state space search problems to follow as examples.


## Implemented Search Problem

Each of these state space search problems' have a demo implemented as a main method in their associated AStateNode extensions.


### Three Puzzle

Let there be a 2x2 grid filled with 3 tiles(one grid space is an empty space). Tiles adjacent to the empty space can be
slid into the empty space. Given and initial tile configuration and a goal configuration, find the necessary tile moves
to reach the goal configuration.


### Eight Puzzle

Let there be a 3x3 grid filled with 8 tiles(one grid space is an empty space). Tiles adjacent to the empty space can be
slid into the empty space. Given and initial tile configuration and a goal configuration, find the necessary tile moves
to reach the goal configuration.






