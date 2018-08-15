package com.knighten.ai.search;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * ThreePuzzle is the state space(search node) representation of the three-puzzle problem.
 */
public class ThreePuzzle extends AStarNode<int[]> {

    /**
     * The one dimensional position of the empty puzzle space(the zero).
     */
    private int emptySpaceLocation = -1;

    /**
     * Creates a state representation for the three-puzzle problem. Zero is used to represent the empty space. Useful
     * for representing the initial and goal states. This will also find the location of the empty space on the board.
     *
     * @param puzzleBoard array of ints representing the puzzle board
     */
    public ThreePuzzle(int[] puzzleBoard) throws Exception {

        if(puzzleBoard.length != 4)
            throw new Exception("Puzzle board most have 4 spaces(Array length 4).");

        this.setState(puzzleBoard);

        for(int currentSpace=0; currentSpace<puzzleBoard.length; currentSpace++)
            if(puzzleBoard[currentSpace] == 0)
                this.emptySpaceLocation = currentSpace;

        if(this.emptySpaceLocation == -1)
            throw new Exception("Puzzle board most contain an empty space(0 must be in the array).");
    }

    /**
     * Creates a state representation for the three-puzzle problem. Zero is used to represent the empty space. This
     * constructor will assign the node's parent when initialized.
     *
     * @param puzzleBoard  array of ints representing the puzzle board
     * @param parentPuzzle the parent node of the current
     */
    public ThreePuzzle(int[] puzzleBoard, ThreePuzzle parentPuzzle) throws Exception {
        this(puzzleBoard);
        this.setParent(parentPuzzle);
    }

    /**
     * Determines if two puzzles are equal to one another. Puzzles are equal if their boards(state) are equal to each
     * other. f(),h(), or g() are not taken into consideration for equality. This must match the hash function.
     *
     * @return  true if puzzles(state) are equal and false if not
     */
    @Override
    public boolean equals(Object otherPuzzle) {

        if(otherPuzzle instanceof ThreePuzzle) {
            int[] currentBoard = this.getState();
            int[] otherBoard = ((ThreePuzzle) otherPuzzle).getState();

            for (int currentSpace = 0; currentSpace < 4; currentSpace++)
                if (currentBoard[currentSpace] != otherBoard[currentSpace])
                    return false;

            return true;
        } else
            return false;
    }

    /**
     * Returns the successor states for the current search node.
     *
     * @return array list of successor states.
     */
    @Override
    public ArrayList<AStarNode> getSuccessors() throws Exception {
        ArrayList<Integer> possibleNextMoves = new ArrayList<>();

        // Check If Empty Space Is In Top Left Or Bottom Right
        if(this.emptySpaceLocation == 0 || this.emptySpaceLocation == 3) {
            possibleNextMoves.add(1);
            possibleNextMoves.add(2);
        }

        // Check If Empty Space Is In Top Right Or Bottom Left
        if(this.emptySpaceLocation == 1 || this.emptySpaceLocation == 2) {
            possibleNextMoves.add(0);
            possibleNextMoves.add(3);
        }

        ArrayList<AStarNode> successors = new ArrayList<>();
        int[] successorBoard;
        for(Integer nextSpace: possibleNextMoves){
            successorBoard = Arrays.copyOf(this.getState(), this.getState().length);
            successorBoard[this.emptySpaceLocation] = this.getState()[nextSpace];
            successorBoard[nextSpace] = 0;
            successors.add(new ThreePuzzle(successorBoard, this));
        }

        return successors;
    }

    /**
     * Returns the hash code for the puzzle board. Only compares state, so ThreePuzzle's with the same board layout but
     * different f(),h(). and g() scores will still collide.
     *
     * @return hash code for puzzle
     */
    @Override
    public int hashCode() {return Arrays.hashCode(this.getState());}

    /**
     * Returns the path length between current node and its parent. Since there will always be a single move difference
     * between a puzzle and its parent, this will always return 1.
     *
     * @return distance from parent to node - always 1
     */
    @Override
    public int distFromParent() {return 1;}

    /**
     * Calculates the heuristic score for the puzzle given a goal puzzle. Calculates the manhattan distances for every
     * number on the puzzle board(the empty space is not part of the calculation).
     *
     *@return returns the calculated h() score
     */
    @Override
    public int calcH(AStarNode<int[]> goalPuzzle) {
        int score = 0;
        int[] goalBoard = goalPuzzle.getState();
        int[] currentBoard = this.getState();

        for(int goalPosition=0; goalPosition<4; goalPosition++) {
            if(goalBoard[goalPosition] == 0)
                continue;

            // Find Position In Current Puzzle That Matches Current Position In Goal Board
            int matchingSpaceOnCurrentNode = 0;
            for(int currentNodePosition=0; currentNodePosition<4; currentNodePosition++)
                if(goalBoard[goalPosition] == currentBoard[currentNodePosition])
                    matchingSpaceOnCurrentNode = currentNodePosition;

            int[] currentIndices = oneDimIndToTwoInd(matchingSpaceOnCurrentNode);
            int[] goalIndices = oneDimIndToTwoInd(goalPosition);

            score += Math.abs(currentIndices[1] - goalIndices[1]) + Math.abs(currentIndices[0] - goalIndices[0]);
        }

        return score;
    }

    /**
     * Converts the puzzle into its string representation as a 2x2 grid. An * is used instead of 0 to represent
     * the empty space.
     *
     * @return a string representation of the puzzle
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        int[] nodesBoard = this.getState();
        for(int currentPosition=0; currentPosition<4; currentPosition++) {
            String space = (nodesBoard[currentPosition] == 0) ? "*" : Integer.toString(nodesBoard[currentPosition]);
            String charAfterSpace = (currentPosition % 2 == 1) ? "\n" : " ";
            stringBuilder.append(space);
            stringBuilder.append(charAfterSpace);
        }

        return stringBuilder.toString();
    }

    /**
     * Converts a 1-dimensional index to a 2-dimensional index in a 2x2 grid.
     *
     * @param flatIndex the index in one dimension
     * @return array representing the two dimensional index
     */
    private int[] oneDimIndToTwoInd(int flatIndex) {return new int[]{flatIndex % 2, (int) Math.floor(flatIndex / 2)};}

}
