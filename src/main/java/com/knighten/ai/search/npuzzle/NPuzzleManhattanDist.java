package com.knighten.ai.search.npuzzle;

import com.knighten.ai.search.AbstractAStarNode;
import com.knighten.ai.search.interfaces.IHeuristicFunction;

public class NPuzzleManhattanDist implements IHeuristicFunction {

    /**
     * Calculates the manhattan distance between two NPuzzles. For each position of search board we find how far
     * away that position is compared to the goal node. We calculate the distance of each position by finding the sum of
     * how far the positions differ in horizontal spaces and vertical spaces. For instance, in a 3x3 board the distance
     * between the top left position to the bottom right position would be 4: from the top left the bottom right is 2
     * rows down and 2 columns right. We ignore the empty space as part of this calculation.
     *
     * @param searchNode the search node whose heuristic value is being calculated
     * @param goalNode the goal node the search node is being compared to
     * @return returns the calculated heuristic(h()) score
     */
    @Override
    public int calculateHeuristic(AbstractAStarNode searchNode, AbstractAStarNode goalNode) {

        if(searchNode == null)
            throw new IllegalArgumentException("Search Node Cannot Be Null");

        if(goalNode == null)
            throw new IllegalArgumentException("Goal Node Cannot Be Null");

        int[] goalBoard = (int[]) goalNode.getState();
        int[] currentBoard = (int[]) searchNode.getState();
        int flatBoardLength = goalBoard.length;
        int rowLength = (int) Math.sqrt(goalBoard.length);

        int score = 0;
        for(int goalBoardPosition=0; goalBoardPosition<flatBoardLength; goalBoardPosition++) {

            // Empty Space Not Part Of Heuristic Calculation
            if(goalBoard[goalBoardPosition] == 0)
                continue;

            // Find Position In Search Board That Matches The Current Goal Board Position
            int matchingSpaceOnCurrentNode = 0;
            for(int currentNodePosition=0; currentNodePosition<flatBoardLength; currentNodePosition++)
                if(goalBoard[goalBoardPosition] == currentBoard[currentNodePosition])
                    matchingSpaceOnCurrentNode = currentNodePosition;

            // Convert One Dimensional Index To Two Dimensional Indices
            int[] currentIndices = oneDimIndToTwoInd(matchingSpaceOnCurrentNode, rowLength);
            int[] goalIndices = oneDimIndToTwoInd(goalBoardPosition, rowLength);

            // Sum All Differences Between X Positions And Y Positions
            score += Math.abs(currentIndices[1] - goalIndices[1]) + Math.abs(currentIndices[0] - goalIndices[0]);
        }

        return score;
    }

    /**
     * Converts a 1-dimensional index to a 2-dimensional index in a rowLength x rowLength grid.
     *
     * @param flatIndex the index in one dimension
     * @param rowLength the length of one row/column in the NPuzzle board.
     * @return array representing the two dimensional index
     */
    private int[] oneDimIndToTwoInd(int flatIndex, int rowLength) {
        int[] twoDimBoardPosition = new int[2];
        twoDimBoardPosition[0] = flatIndex % rowLength;
        twoDimBoardPosition[1] = flatIndex / rowLength;
        return twoDimBoardPosition;
    }

}
