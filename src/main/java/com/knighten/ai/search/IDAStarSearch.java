package com.knighten.ai.search;

import java.util.ArrayList;

public class IDAStarSearch {

    private AStarNode initialState;
    private AStarNode goalState;

    /**
     * Creates an AStarSearch with initial and goal states
     *
     * @param initialState the state where the search begins
     * @param goalState the state where the search ends
     */
    public IDAStarSearch(AStarNode initialState, AStarNode goalState) {
        this.initialState = initialState;
        this.goalState = goalState;
    }

    /**
     *  Begins the Iterative Deepening A* search. Will return null if the goal node can not be found.
     *
     * @return the goal node with its corresponding optimal path parent, or null if the goal state cannot be found
     */
    public AStarNode search() throws Exception {

        int currentFBound = initialState.calcH(goalState);
        ArrayList<AStarNode> path = new ArrayList<>();
        path.add(0, initialState);

        int smallestNewFBound;
        do {
            smallestNewFBound = recur_search(path, 0, currentFBound);

            if(smallestNewFBound == 0)
                return path.get(path.size()-1);

            currentFBound = smallestNewFBound;
        } while(currentFBound != Integer.MAX_VALUE);

        return null;
    }

    /**
     * Recursively searches down the children of nodes. Instantly stops when a node had a higher f value than the
     * current iterations fBoundary. Will return the smallest f value that has surpassed the currentFBoundary, this f
     * value will be used as the currentFBoundary in the next iteration.
     *
     * @param path list of nodes ordered by the order they were visited
     * @param graphCost current graph cost to get to the current node
     * @param currentFBound the max f boundary for current iteration
     * @return the smallest f value in the  iteration that was greater than the fBoundary for the iteration
     */
    private int recur_search(ArrayList<AStarNode> path, int graphCost, int currentFBound) throws Exception {

        AStarNode currentNode = path.get(path.size()-1);
        currentNode.setH(currentNode.calcH(goalState));
        currentNode.setG(graphCost);
        currentNode.setF(graphCost + currentNode.getH());

        // Current node has f larger than current bound
        if(currentNode.getF() > currentFBound)
            return currentNode.getF();

        // Found the goal node send signal to end recursion
        if(currentNode.equals(goalState))
            return 0;

        int minFFound = Integer.MAX_VALUE;
        ArrayList<AStarNode> children = currentNode.getSuccessors();

        // Expand on each child node
        for(AStarNode child: children) {
            // Verify child not already on path
            if(!path.contains(child)) {
                path.add(child);
                int minFOverBound = recur_search(path, currentNode.getG() + child.distFromParent(), currentFBound);

                // Signals to end recursion since goal was found
                if(minFOverBound == 0)
                    return 0;

                if(minFOverBound < minFFound)
                    minFFound = minFOverBound;

                path.remove(path.size()-1);
            }
        }

        return minFFound;
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
