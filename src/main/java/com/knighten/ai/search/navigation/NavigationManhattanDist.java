package com.knighten.ai.search.navigation;

import com.knighten.ai.search.AbstractAStarNode;
import com.knighten.ai.search.interfaces.IHeuristicFunction;

public class NavigationManhattanDist implements IHeuristicFunction {

    private Maze goalNode;

    public NavigationManhattanDist(Maze goalNode) {
        this.goalNode = goalNode;
    }

    @Override
    public double calculateHeuristic(AbstractAStarNode searchNode) {
        int diffOfCols =  Math.abs(this.goalNode.getState()[0] - ((Maze) searchNode).getState()[0]);
        int diffOfRows = Math.abs(this.goalNode.getState()[1] - ((Maze) searchNode).getState()[1]);
        return diffOfCols + diffOfRows;
    }

}
