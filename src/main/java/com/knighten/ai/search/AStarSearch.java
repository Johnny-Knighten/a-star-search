package com.knighten.ai.search;

import com.knighten.ai.search.interfaces.IHeuristicFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStarSearch {

    private AbstractAStarNode initialState;
    private AbstractAStarNode goalState;
    private IHeuristicFunction heuristicFunction;

    /**
     * Creates an AStarSearch with initial and goal states
     *
     * @param initialState the state where the search begins
     * @param goalState the state where the search ends
     */
    public AStarSearch(AbstractAStarNode initialState, AbstractAStarNode goalState, IHeuristicFunction heuristicFunction) {
        this.initialState = initialState;
        this.goalState = goalState;
        this.heuristicFunction = heuristicFunction;
    }

    /**
     *  Begins the A* search. Will return null if the goal node can not be found.
     *
     * @return the goal node with its corresponding optimal path parent, or null if the goal state cannot be found
     */
    public AbstractAStarNode search() throws Exception {

        HashSet<AbstractAStarNode> closedSet = new HashSet<>();

        PriorityQueue<AbstractAStarNode> openSet = new PriorityQueue<>(1, goalState);
        // Used to quickly determine if node with same state is in the open set and to retrieve that states best f
        HashMap<AbstractAStarNode, Integer> openSetHash = new HashMap<>();
        initialState.setF(this.heuristicFunction.calculateHeuristic(this.initialState, this.goalState));
        openSet.add(initialState);
        openSetHash.put(initialState, initialState.getF());

        while(!openSet.isEmpty()) {
            AbstractAStarNode currentState = openSet.poll();

            if(currentState.equals(goalState))
                return currentState;

            closedSet.add(currentState);
            ArrayList<AbstractAStarNode> childrenStates = currentState.getSuccessors();

            for(AbstractAStarNode childState: childrenStates) {

                if(closedSet.contains(childState))
                    continue;

                childState.setG(childState.getParent().getG() + childState.distFromParent());
                childState.setH(this.heuristicFunction.calculateHeuristic(childState, this.goalState));
                childState.setF(childState.getG() + childState.getH());

                // Note the short circuit logic
                if(openSetHash.containsKey(childState) && openSetHash.get(childState) < childState.getF())
                    continue;

                openSet.add(childState);
                openSetHash.put(childState, childState.getF());
            }
        }

        return null;
    }

    /**
     * Takes the output from search() and makes it into a list of states that represent the optimal path from the
     * initial state to the state.
     *
     * @param endPathNode the output from search()
     * @return an list of nodes ordered to represent the optimal path
     */
    public ArrayList<AbstractAStarNode> getPath(AbstractAStarNode endPathNode) {
        ArrayList<AbstractAStarNode> path = new ArrayList<>();
        path.add(endPathNode);

        while(endPathNode.getParent() != null) {
            path.add(0, endPathNode.getParent());
            endPathNode = endPathNode.getParent();
        }

        return path;
    }

}
