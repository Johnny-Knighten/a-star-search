package com.knighten.ai.search.navigation;

import com.knighten.ai.search.AbstractAStarNode;
import com.knighten.ai.search.interfaces.IHeuristicFunction;

/**
 * Represents the manhattan distance heuristic function for use with classes that extend AbstractNavigate.
 */
public class NavigationManhattanDist implements IHeuristicFunction {

    /**
     * The goal node that is used in every heuristic calculation.
     */
    private AbstractNavigate goalNode;

    /**
     * Creates the manhattan distance heuristic for AbstractNavigates
     *
     * @param goalNode the node the heuristic function always uses
     */
    public NavigationManhattanDist(AbstractNavigate goalNode) {
        this.goalNode = goalNode;
    }

    /**
     * Computes the manhattan distance between the search node and the goal node.
     *
     * @param searchNode the node being compared to the goal
     * @return the manhattan distance between the search node and goal node
     */
    @Override
    public double calculateHeuristic(AbstractAStarNode searchNode) {
        int diffOfCols = Math.abs(this.goalNode.getState()[0] - ((AbstractNavigate) searchNode).getState()[0]);
        int diffOfRows = Math.abs(this.goalNode.getState()[1] - ((AbstractNavigate) searchNode).getState()[1]);
        return diffOfCols + diffOfRows;
    }

}
