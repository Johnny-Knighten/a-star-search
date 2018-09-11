package com.knighten.ai.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * EightPuzzle is the state space(search node) representation of the eight-puzzle problem.
 */
public class EightPuzzle extends AStarNode<int[]> {

    /**
     * The one dimensional position of the empty puzzle space(the zero).
     */
    private int emptySpaceLocation = -1;

    /**
     * Creates a state representation for the eight-puzzle problem. Zero is used to represent the empty space. Useful
     * for representing the initial and goal states. This will also find the location of the empty space on the board.
     *
     * @param puzzleBoard array of ints representing the puzzle board
     */
    public EightPuzzle(int[] puzzleBoard) throws Exception {

        if(puzzleBoard.length != 9)
            throw new Exception("Puzzle board most have 9 spaces(Array length 9).");

        this.setState(puzzleBoard);

        for(int currentSpace=0; currentSpace<puzzleBoard.length; currentSpace++)
            if(puzzleBoard[currentSpace] == 0)
                this.emptySpaceLocation = currentSpace;

        if(this.emptySpaceLocation == -1)
            throw new Exception("Puzzle board most contain an empty space(0 must be in the array).");
    }

    /**
     * Creates a state representation for the eight-puzzle problem. Zero is used to represent the empty space. This
     * constructor will assign the node's parent when initialized.
     *
     * @param puzzleBoard  array of ints representing the puzzle board
     * @param parentPuzzle the parent node of the current
     */
    public EightPuzzle(int[] puzzleBoard, EightPuzzle parentPuzzle) throws Exception {
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

        if(otherPuzzle instanceof  EightPuzzle) {
            int[] currentBoard = this.getState();
            int[] otherBoard = ((EightPuzzle) otherPuzzle).getState();

            for (int currentSpace = 0; currentSpace < 9; currentSpace++)
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

        // Check Moving Empty Space Left
        if(((this.emptySpaceLocation - 1) >= 0) && (this.emptySpaceLocation % 3 != 0))
            possibleNextMoves.add(this.emptySpaceLocation - 1);

        // Check Moving Empty Space Right
        if(((this.emptySpaceLocation + 1) < 9) && (this.emptySpaceLocation % 3 != 2))
            possibleNextMoves.add(this.emptySpaceLocation + 1);

        // Check Moving Empty Space Up
        if(((this.emptySpaceLocation - 3) >= 0) && (Math.floor(this.emptySpaceLocation / 3) != 0))
            possibleNextMoves.add(this.emptySpaceLocation - 3);

        // Check Moving Empty Space Down
        if(((this.emptySpaceLocation + 3) < 9) && (Math.floor(this.emptySpaceLocation / 3) != 2))
            possibleNextMoves.add(this.emptySpaceLocation + 3);

        ArrayList<AStarNode> successors = new ArrayList<>();
        int[] successorBoard;
        for(Integer nextSpace: possibleNextMoves){
            successorBoard = Arrays.copyOf(this.getState(), this.getState().length);
            successorBoard[this.emptySpaceLocation] = this.getState()[nextSpace];
            successorBoard[nextSpace] = 0;
            successors.add(new EightPuzzle(successorBoard, this));
        }

        return successors;
    }

    /**
     * Returns the hash code for the puzzle board. Only compares state, so EightPuzzle's with the same board layout but
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

        for(int goalPosition=0; goalPosition<9; goalPosition++) {
            if(goalBoard[goalPosition] == 0)
                continue;

            // Find Position In Current Puzzle That Matches Current Position In Goal Board
            int matchingSpaceOnCurrentNode = 0;
            for(int currentNodePosition=0; currentNodePosition<9; currentNodePosition++)
                if(goalBoard[goalPosition] == currentBoard[currentNodePosition])
                    matchingSpaceOnCurrentNode = currentNodePosition;

            int[] currentIndices = oneDimIndToTwoInd(matchingSpaceOnCurrentNode);
            int[] goalIndices = oneDimIndToTwoInd(goalPosition);

            score += Math.abs(currentIndices[1] - goalIndices[1]) + Math.abs(currentIndices[0] - goalIndices[0]);
        }

        return score;
    }

    /**
     * Converts the puzzle into its string representation as a 3x3 grid. An * is used instead of 0 to represent
     * the empty space.
     *
     * @return a string representation of the puzzle
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        int[] nodesBoard = this.getState();
        for(int currentPosition=0; currentPosition<9; currentPosition++) {
            String space = (nodesBoard[currentPosition] == 0) ? "*" : Integer.toString(nodesBoard[currentPosition]);
            String charAfterSpace = (currentPosition % 3 == 2) ? "\n" : " ";
            stringBuilder.append(space);
            stringBuilder.append(charAfterSpace);
        }

        return stringBuilder.toString();
    }

    /**
     * Converts a 1-dimensional index to a 2-dimensional index in a 3x3 grid.
     *
     * @param flatIndex the index in one dimension
     * @return array representing the two dimensional index
     */
    private int[] oneDimIndToTwoInd(int flatIndex) {return new int[]{flatIndex % 3, (int) Math.floor(flatIndex / 3)};}


    // Usage Example
    public static void main(String[] args) throws Exception {

        int[] initStateArray = {0, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] goalStateArray = {1, 2, 3, 4, 5, 6, 7, 8, 0};

        EightPuzzle initialState = new EightPuzzle(initStateArray);
        EightPuzzle goalState = new EightPuzzle(goalStateArray);

        IDAStarSearch searcher = new IDAStarSearch(initialState, goalState);
        AStarNode finalSearchNode = searcher.search();

        System.out.println("Initial State");
        System.out.println(initialState);

        System.out.println("Goal State");
        System.out.println(goalState);

        List<AStarNode> path = searcher.getPath(finalSearchNode);
        int step = 1;
        for (AStarNode node : path) {
            System.out.println("Step " + step);
            System.out.println(node);
            step++;
        }

    }

}
