package com.knighten.ai.search.interfaces;

import com.knighten.ai.search.AbstractAStarNode;

/**
 * Represents a heuristic function to be used with AStarSearch Or IDAStarSearch. Remember that this function MUST be
 * admissible.
 */
public interface IHeuristicFunction {

    /**
     * Calculates the heuristic score for the current search node when compared to the goal node. The goal node should
     * be contained in the implemented IHeuristicFunction class.
     *
     * @param searchNode the node being compared to the goal
     * @return the heuristic score for the search node
     */
    public int calculateHeuristic(AbstractAStarNode searchNode);
}
