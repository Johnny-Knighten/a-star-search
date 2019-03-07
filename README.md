# A* Search

This is a java based implementation of two different versions of the A* search algorithm; the classic A* search and the
Iterative Deepening A* search(IDA*). The code is written as a framework so the supplied state space searchers can be 
used to solve a variety of problems. Users simply need to implement a class that represents the search problem.
 


A brief overview of A* and IDA* and be found [here](https://github.com/JKnighten/a-star-search/wiki/A*-and-IDA*-Search-Overview).


## How To Use

This framework uses gradle to build javadocs and a JAR file containing the framework.

### Building A JAR of The Framework

Execute the following to generate a JAR file containing the framework:

```
./gradlew jar
```

The JAR file can be found in /build/libs/.

### Building The javadocs For The Framework

Execute the following to generate the javadocs for the framework:

```
./gradlew javadoc
```

The javadocs can be found in /build/docs/javadoc/.

## High Level Overview Of Using The Framework

The AStarSearch and IDAStarSearch classes are responsible for performing state space searches using the algorithm they
are respectively named after. Both classes must be provided an initial state, a goal state, and a heuristic function. 
The initial and goal states are instances of a class that extends  AbstractAStarNode. The AbstractAStarNode class uses a 
generic that represents the type of the state that is stored in each search node. AbstractAStarNode contains methods for
getting/setting a nodes graph cost(g), heuristic score(h), total score(f), state representation, and parent node. 
Classes that extend AbstractAStarNode must implement methods that generate successor nodes, calculates the search graph
distance from the nodes parent, generate a hash code based on state, and an equals method that is based on the node's
state. Essentially a state space search problem must be represented by a class that extends AbstractAStarNode.

Besides the creating the classes that represent the search nodes, you must also provide a heuristic function that can
score how close a node is to the goal node. Heuristic functions in this framework are represented by IHeuristicFunction.

After creating a class that represents a state space search problem and a heuristic function, you then create search
space nodes that represents the initial and goal state and an instance of your desired heuristic function. Next you 
supply these two nodes and heuristic function to either AStarSearch or IDAStarSearch's constructor. To begin the search,
the search() method is called; both AStarSearch and IDAStarSearch provide the search() method. The search method will 
return the final node on the search path, or null if the goal state cannot be found. To generate the full path as a 
list, call the getPath() method supplying it the node returned from the search method.


To summarize, in order to perform a state space search:
1. Create a class that extends AbstractAStarNode which represents the state space search problem
2. Create a class that IHeuristicFunction  which represents a heuristic function
3. Create two search node that represent the initial and goal state of the search problem
4. Create an instance of AStarSearch or IDAStarSearch and supply the two nodes created above and the heuristic function
5. Call the search method provided by AStarSearch or IDAStarSearch
6. To get the resulting path, provide the node returned by the search method to the getPath method

A quick abstract example on how to use the searcher:
```java
AStarNode initialState = new SearchProblem(initialState);
AStarNode goalState = new SearchProblem(goalState);

IHeuristicFunction heuristic = new SomeHeuristic(goalState)

AStarSearch searcher = new AStarSearch(initialState, goalState, heuristic);
AStarNode finalSearchNode = searcher.search();

List<AStarNode> path = searcher.getPath(finalSearchNode);
```

Below are some implemented state space search problems to follow as examples.


## Implemented Search Problem

Each of these state space search problems' have a demo implemented as a main method in their associated classes.

Here is an example of constructing the necessary classes to perform a state space search - [link](https://github.com/JKnighten/a-star-search/wiki/Example:-Creating-An-State-Space-Search-Problem-For-A*).


### Three Puzzle

Let there be a 2x2 grid filled with 3 tiles(one grid space is an empty space). Tiles adjacent to the empty space can be
slid into the empty space. Given and initial tile configuration and a goal configuration, find the necessary tile moves
to reach the goal configuration.


### Eight Puzzle

Let there be a 3x3 grid filled with 8 tiles(one grid space is an empty space). Tiles adjacent to the empty space can be
slid into the empty space. Given and initial tile configuration and a goal configuration, find the necessary tile moves
to reach the goal configuration.

### Navigate A Maze

Given an maze and its start and end point, find the shortest path between the start and end.

### Navigate Some Terrain

Given a terrain and a start and end point, find the path that is easiest to traverse between the start and end 
point. This is similar to the maze problem, but now instead of just having simple paths there are now paths that
are harder to traverse than others. For instance instead of walking along a flat path, it is harder to walk up hill or
swim across water.






