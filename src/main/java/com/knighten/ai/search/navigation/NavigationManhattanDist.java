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

        if (goalNode == null)
            throw new IllegalArgumentException("Goal Node Cannot Be Null");

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

        if (searchNode == null)
            throw new IllegalArgumentException("Search Node Cannot Be Null");

        int[] goalNodePosition = this.goalNode.getState();
        int[] searchNodePosition = ((AbstractNavigate) searchNode).getState();

        int diffOfCols = Math.abs(goalNodePosition[0] - searchNodePosition[0]);
        int diffOfRows = Math.abs(goalNodePosition[1] - searchNodePosition[1]);
        return diffOfCols + diffOfRows;
    }

}
