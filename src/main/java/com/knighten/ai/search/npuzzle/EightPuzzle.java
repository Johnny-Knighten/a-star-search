package com.knighten.ai.search.npuzzle;

import com.knighten.ai.search.AbstractAStarNode;
import com.knighten.ai.search.IDAStarSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * EightPuzzle is the state space(search node) representation of the eight-puzzle problem.
 */
public class EightPuzzle extends AbstractNPuzzle {


    /**
     * Creates a state representation for the eight-puzzle problem. Zero is used to represent the empty space. Useful
     * for representing the initial and goal states. This will also find the location of the empty space on the board.
     *
     * @param puzzleBoard array of ints representing the puzzle board
     */
    public EightPuzzle(int[] puzzleBoard) {

        if (puzzleBoard == null)
            throw new IllegalArgumentException("8Puzzle Board's Cannot Be Null");

        if (puzzleBoard.length != 9)
            throw new IllegalArgumentException("8Puzzle Board's Must Have 9 Spaces(Array Length of 9)");

        this.setState(puzzleBoard);

        // Find Location of The Empty Space aka 0
        for (int currentSpace = 0; currentSpace < puzzleBoard.length; currentSpace++)
            if (puzzleBoard[currentSpace] == 0)
                this.setEmptySpaceLocation(currentSpace);

        if (this.getEmptySpaceLocation() == -1)
            throw new IllegalArgumentException("All NPuzzle Boards Most Contain an Empty Space(0 Must Be In The Array)");
    }

    /**
     * Creates a state representation for the eight-puzzle problem. Zero is used to represent the empty space. This
     * constructor will assign the node's parent when initialized.
     *
     * @param puzzleBoard  array of ints representing the puzzle board
     * @param parentPuzzle the parent node of the current
     */
    public EightPuzzle(int[] puzzleBoard, EightPuzzle parentPuzzle) {
        this(puzzleBoard);
        this.setParent(parentPuzzle);
    }

    /**
     * Returns the successor states for the current search node. Attempts to move tiles into the empty space to create
     * new states.
     *
     * @return array list of successor states.
     */
    @Override
    public ArrayList<AbstractAStarNode> getSuccessors() {
        ArrayList<Integer> possibleNextMoves = new ArrayList<>();

        // Check Moving Empty Space Left
        if (((this.getEmptySpaceLocation() - 1) >= 0) && (this.getEmptySpaceLocation() % 3 != 0))
            possibleNextMoves.add(this.getEmptySpaceLocation() - 1);

        // Check Moving Empty Space Right
        if (((this.getEmptySpaceLocation() + 1) < 9) && (this.getEmptySpaceLocation() % 3 != 2))
            possibleNextMoves.add(this.getEmptySpaceLocation() + 1);

        // Check Moving Empty Space Up
        if (((this.getEmptySpaceLocation() - 3) >= 0) && (this.getEmptySpaceLocation() / 3 != 0))
            possibleNextMoves.add(this.getEmptySpaceLocation() - 3);

        // Check Moving Empty Space Down
        if (((this.getEmptySpaceLocation() + 3) < 9) && (this.getEmptySpaceLocation() / 3 != 2))
            possibleNextMoves.add(this.getEmptySpaceLocation() + 3);

        // Create List of All Successor States
        ArrayList<AbstractAStarNode> successors = new ArrayList<>();
        int[] successorBoard;
        for (Integer nextSpace : possibleNextMoves) {
            successorBoard = Arrays.copyOf(this.getState(), this.getState().length);
            successorBoard[this.getEmptySpaceLocation()] = this.getState()[nextSpace];
            successorBoard[nextSpace] = 0;
            successors.add(new EightPuzzle(successorBoard, this));
        }

        return successors;
    }

    // Usage Example
    public static void main(String[] args) {

        int[] initStateArray = {7, 2, 4, 5, 0, 6, 8, 3, 1};
        int[] goalStateArray = {0, 1, 2, 3, 4, 5, 6, 7, 8};

        EightPuzzle initialState = new EightPuzzle(initStateArray);
        EightPuzzle goalState = new EightPuzzle(goalStateArray);

        NPuzzleManhattanDist heuristicFunction = new NPuzzleManhattanDist(goalState);

        IDAStarSearch searcher = new IDAStarSearch(initialState, goalState, heuristicFunction);
        AbstractAStarNode finalSearchNode = searcher.search();

        System.out.println("Initial State");
        System.out.println(initialState);

        System.out.println("Goal State");
        System.out.println(goalState);

        List<AbstractAStarNode> path = searcher.getPath(finalSearchNode);
        int step = 1;
        for (AbstractAStarNode node : path) {
            System.out.println("Step " + step);
            System.out.println(node);
            step++;
        }

    }

}
