package com.knighten.ai.search.navigation;

import com.knighten.ai.search.AbstractAStarNode;

import java.util.Arrays;

/**
 * Represents the generic navigation state space search problem. Given an environment to be navigated, find the optimal
 * path from the start point to the end point.
 */
public abstract class AbstractNavigate extends AbstractAStarNode<int[]> {

    /**
     * The row of the current position. This is 0 indexed.
     */
    private int currentRow;

    /**
     * The column of the current position. This is 0 indexed.
     */
    private int currentCol;

    /**
     * The environment being navigated. It is assumed the environment is not a jagged array.
     */
    private int[][] environment;

    /**
     * Number of rows in the environment.
     */
    private int numberOfRows;

    /**
     * Number of columns in the environment.
     */
    private int numberOfCols;

    /**
     * Sets the state of the node and is responsible for setting the environment, currentRow, and currentCol. Also uses
     * environment to set numberOfRows and numberOfCols.
     *
     * @param environment the area being navigated
     * @param currentRow  the current row position
     * @param currentCol  the current column position
     */
    public AbstractNavigate(int[][] environment, int currentRow, int currentCol) {
        this.environment = environment;
        this.currentRow = currentRow;
        this.currentCol = currentCol;

        this.setState(new int[]{currentRow, currentCol});

        this.numberOfRows = environment.length;
        this.numberOfCols = environment[0].length;
    }

    /**
     * Gets the environment thats being navigated by the search algorithm.
     *
     * @return the environment being navigated
     */
    public int[][] getEnvironment() {
        return this.environment;
    }

    /**
     * Gets the number of rows contained in the environment.
     *
     * @return number of rows in the environment
     */
    public int getNumberOfRows() {
        return this.numberOfRows;
    }

    /**
     * Gets the number of columns contained in the environment.
     *
     * @return number of columns in the environment
     */
    public int getNumberOfCols() {
        return this.numberOfCols;
    }

    /**
     * Generates a hash code based on the current position in the environment.
     *
     * @return hash code for the maze
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(getState());
    }

    /**
     * Checks if two AbstractNavigate objects are at the same current position in the environment.
     *
     * @param otherMaze AbstractNavigate being compared to the current AbstractNavigate
     * @return true if two AbstractNavigate are at the same current position, otherwise false
     */
    @Override
    public boolean equals(Object otherMaze) {
        if (otherMaze instanceof AbstractNavigate) {
            return Arrays.equals(((AbstractNavigate) otherMaze).getState(), this.getState());
        }

        return false;
    }

    /**
     * Creates a string representation of the AbstractNavigate. The current position is represented as *, all other
     * values are just printed in their correct AbstractNavigate
     *
     * @return string representation of the maze
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int row = 0; row < this.numberOfRows; row++) {
            for (int column = 0; column < this.numberOfCols; column++) {

                if (row == this.currentRow && column == this.currentCol)
                    builder.append("* ");
                else
                    builder.append(Integer.toString(this.environment[row][column]) + ' ');

            }

            if (row != (this.numberOfRows - 1))
                builder.append('\n');
        }

        return builder.toString();
    }

}
