package com.knighten.ai.search.npuzzle;

import com.knighten.ai.search.AbstractAStarNode;
import com.knighten.ai.search.AStarSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * ThreePuzzle is the state space(search node) representation of the three-puzzle problem.
 */
public class ThreePuzzle extends AbstractNPuzzle {


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
                this.setEmptySpaceLocation(currentSpace);

        if(this.getEmptySpaceLocation() == -1)
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
     * Returns the successor states for the current search node.
     *
     * @return array list of successor states.
     */
    @Override
    public ArrayList<AbstractAStarNode> getSuccessors() throws Exception {
        ArrayList<Integer> possibleNextMoves = new ArrayList<>();

        // Check If Empty Space Is In Top Left Or Bottom Right
        if(this.getEmptySpaceLocation() == 0 || this.getEmptySpaceLocation() == 3) {
            possibleNextMoves.add(1);
            possibleNextMoves.add(2);
        }

        // Check If Empty Space Is In Top Right Or Bottom Left
        if(this.getEmptySpaceLocation() == 1 || this.getEmptySpaceLocation() == 2) {
            possibleNextMoves.add(0);
            possibleNextMoves.add(3);
        }

        ArrayList<AbstractAStarNode> successors = new ArrayList<>();
        int[] successorBoard;
        for(Integer nextSpace: possibleNextMoves){
            successorBoard = Arrays.copyOf(this.getState(), this.getState().length);
            successorBoard[this.getEmptySpaceLocation()] = this.getState()[nextSpace];
            successorBoard[nextSpace] = 0;
            successors.add(new ThreePuzzle(successorBoard, this));
        }

        return successors;
    }

    // Usage Example
    public static void main(String[] args) throws Exception {

        int[] initStateArray = {0,3,2,1};
        int[] goalStateArray = {1,2,3,0};

        ThreePuzzle initialState = new ThreePuzzle(initStateArray);
        ThreePuzzle goalState = new ThreePuzzle(goalStateArray);

        NPuzzleManhattanDist heuristicFunction = new NPuzzleManhattanDist();

        AStarSearch searcher = new AStarSearch(initialState, goalState, heuristicFunction);
        AbstractAStarNode finalSearchNode = searcher.search();

        System.out.println("Initial State");
        System.out.println(initialState);

        System.out.println("Goal State");
        System.out.println(goalState);

        List<AbstractAStarNode> path = searcher.getPath(finalSearchNode);
        int step = 1;
        for(AbstractAStarNode node: path) {
            System.out.println("Step " + step);
            System.out.println(node);
            step++;
        }

    }

}
