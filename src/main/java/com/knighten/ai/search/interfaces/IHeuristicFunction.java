package com.knighten.ai.search.interfaces;

import com.knighten.ai.search.AStarNode;

public interface IHeuristicFunction {
    public int calculateHeuristic(AStarNode searchNode, AStarNode goalNode);
}
