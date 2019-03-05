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
are respectively named after. Both classes must be provided an initial state and a goal state, which are instances of a
class that extends the abstract class AStarNode. The AStarNode abstract class uses a generic which will be the state 
representation of the node. AStarNode contains methods for getting/setting a nodes graph cost(g), heuristic score(h), 
total score(f), state representation, and parent node. Classes that extend AStarNode must implement methods that 
generate successor nodes, calculates the search graph distance from the nodes parent, calculates the nodes heuristic 
score, generate a hash code, and an equals method that is based on the node's state representation. Essentially a state
space search problem must be represented by a class that extends AStarNode.

After creating a class that represents a state space search problem, you then create search space nodes that represents
the initial and goal state. Next you supply these two nodes to either AStarSearch or IDAStarSearch's constructor. To 
begin the search method is called to begin the search, both AStarSearch and IDAStarSearch provide a search method. The 
search method will return the final node on the search path, or null if the goal state cannot be found. To generate the
full path as a list, call the getPath method supplying it the node returned from the search method.


To summarize, in order to perform a state space search:
1. Create a class that extends AStarNode which represents the state space search problem
2. Create two search node that represent the initial and goal state of the search problem
3. Create an instance of AStarSearch or IDAStarSearch and supply the two nodes created above
4. Call the search method provided by AStarSearch or IDAStarSearch
5. To get the search path, provide the node returned by the search method to the getPath method

A quick abstract example on how to use the searcher:
```java
AStarNode initialState = new SearchProblem(initialState);
AStarNode goalState = new SearchProblem(goalState);

AStarSearch searcher = new AStarSearch(initialState, goalState);
AStarNode finalSearchNode = searcher.search();

List<AStarNode> path = searcher.getPath(finalSearchNode);

```

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






