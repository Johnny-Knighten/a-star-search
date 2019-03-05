package com.knighten.ai.search.interfaces;

import com.knighten.ai.search.AbstractAStarNode;

public interface IHeuristicFunction {
    public int calculateHeuristic(AbstractAStarNode searchNode, AbstractAStarNode goalNode);
}
