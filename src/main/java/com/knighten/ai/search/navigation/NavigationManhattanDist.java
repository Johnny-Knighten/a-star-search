package com.knighten.ai.search.navigation;

import com.knighten.ai.search.AbstractAStarNode;
import com.knighten.ai.search.interfaces.IHeuristicFunction;

public class NavigationManhattanDist implements IHeuristicFunction {

    private AbstractNavigate goalNode;

    public NavigationManhattanDist(AbstractNavigate goalNode) {
        this.goalNode = goalNode;
    }

    @Override
    public double calculateHeuristic(AbstractAStarNode searchNode) {
        int diffOfCols =  Math.abs(this.goalNode.getState()[0] - ((AbstractNavigate) searchNode).getState()[0]);
        int diffOfRows = Math.abs(this.goalNode.getState()[1] - ((AbstractNavigate) searchNode).getState()[1]);
        return diffOfCols + diffOfRows;
    }

}
