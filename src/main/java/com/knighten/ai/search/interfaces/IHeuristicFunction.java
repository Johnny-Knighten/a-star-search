package com.knighten.ai.search.interfaces;

import com.knighten.ai.search.AbstractAStarNode;

/**
 * Represents a heuristic function to be used with AStarSearch Or IDAStarSearch. Remember that this function MUST be
 * admissible.
 */
public interface IHeuristicFunction {

    /**
     * Takes two AbstractAStarNodes and calculates the heuristic score for the current search node.
     *
     * @param searchNode the node being compared to the goal
     * @param goalNode the goal node/state of the search problem
     * @return the heuristic score for the search node
     */
    public int calculateHeuristic(AbstractAStarNode searchNode, AbstractAStarNode goalNode);
}
