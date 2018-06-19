package com.knighten.ai.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStarSearch {

    private AStarNode initialState;
    private AStarNode goalState;

    /**
     * Creates an AStarSearch with initial and goal states
     *
     * @param initialState the state where the search begins
     * @param goalState the state where the search ends
     */
    public AStarSearch(AStarNode initialState, AStarNode goalState) {
        this.initialState = initialState;
        this.goalState = goalState;
    }

    /**
     *  Begins the A* search. Will return null if the goal node can not be found.
     *
     * @return the goal node with its corresponding optimal path parent, or null if the goal state cannot be found
     */
    public AStarNode search() throws Exception {

        HashSet<AStarNode> closedSet = new HashSet<>();

        PriorityQueue<AStarNode> openSet = new PriorityQueue<>(1, goalState);
        // Used to quickly determine if node with same state is in the open set and to retrieve that states best f
        HashMap<AStarNode, Integer> openSetHash = new HashMap<>();
        initialState.setF(initialState.calcH(goalState));
        openSet.add(initialState);
        openSetHash.put(initialState, initialState.getF());

        while(!openSet.isEmpty()) {
            AStarNode currentState = openSet.poll();

            if(currentState.equals(goalState))
                return currentState;

            closedSet.add(currentState);
            ArrayList<AStarNode> childrenStates = currentState.getSuccessors();

            for(AStarNode childState: childrenStates) {

                if(closedSet.contains(childState))
                    continue;

                childState.setG(childState.getParent().getG() + childState.distFromParent());
                childState.setH(childState.calcH(goalState));
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
    public ArrayList<AStarNode> getPath(AStarNode endPathNode) {
        ArrayList<AStarNode> path = new ArrayList<>();
        path.add(endPathNode);

        while(endPathNode.getParent() != null) {
            path.add(0, endPathNode.getParent());
            endPathNode = endPathNode.getParent();
        }

        return path;
    }
    
}
