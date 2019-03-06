package com.knighten.ai.search.npuzzle;

import com.knighten.ai.search.AbstractAStarNode;

import java.util.Arrays;


/**
 * Abstract class that represents the generic NPuzzle Problem. Contains methods that will work with any N value.
 */
public abstract class AbstractNPuzzle extends AbstractAStarNode<int[]> {

    /**
     * The one dimensional index of the empty puzzle space(the zero). Top left corner is 0 and bottom right is n-1.
     */
    private int emptySpaceLocation = -1;

    /**
     * Gets the board's empty space location using a one dimensional index. Top left corner is 0 and bottom right is
     * n-1.
     *
     * @return the puzzles 1dim empty board space location
     */
    public int getEmptySpaceLocation() {
        return this.emptySpaceLocation;
    }

    /**
     * Sets the boards empty space location using a one dimensional index. Top left corner is 0 and bottom right is n-1.
     *
     * @param emptySpaceLocation one dimensional index where empty space is located
     */
    public void setEmptySpaceLocation(int emptySpaceLocation) {

        if(emptySpaceLocation < 0 || emptySpaceLocation > (this.getState().length-1))
            throw new IllegalArgumentException("The Empty Space Location Must Be Between 0 And N");

        this.emptySpaceLocation = emptySpaceLocation;
    }

    /**
     * Returns the path length between current node and its parent. Since there will always be a single move difference
     * between a puzzle and its parent, this will always return 1.
     *
     * @return distance from parent to node - always 1
     */
    @Override
    public double distFromParent() {return 1;}

    /**
     * Returns the hash code for the puzzle board. Only compares state, so a AbstractNPuzzle's with the same board
     * layout but different f(),h(). and g() scores will still collide.
     *
     * @return hash code for puzzle
     */
    @Override
    public int hashCode() {return Arrays.hashCode(this.getState());}

    /**
     * Determines if two puzzles are equal to one another. Puzzles are equal if their boards(state) are equal to each
     * other. f(),h(), or g() are not taken into consideration for equality. This must match the hash function.
     *
     * @return  true if puzzles(state) are equal and false if not
     */
    @Override
    public boolean equals(Object otherPuzzle) {

        if(otherPuzzle instanceof AbstractNPuzzle) {
            return Arrays.equals(((AbstractNPuzzle) otherPuzzle).getState(), this.getState());
        }

        return false;
    }

    /**
     * Converts the puzzle into its string representation as a sqrt(N+1)xsqrt(N+1) grid. An * is used instead of 0 to
     * represent the empty space.
     *
     * @return a string representation of the puzzle
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        int[] nodesBoard = this.getState();
        int rowLength = (int) Math.sqrt(nodesBoard.length);
        for(int currentPosition=0; currentPosition<nodesBoard.length; currentPosition++) {
            String space = (nodesBoard[currentPosition] == 0) ? "*" : Integer.toString(nodesBoard[currentPosition]);
            String charAfterSpace = (currentPosition % rowLength == (rowLength-1)) ? "\n" : " ";
            stringBuilder.append(space);
            stringBuilder.append(charAfterSpace);
        }

        return stringBuilder.toString();
    }

}
