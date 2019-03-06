package com.knighten.ai.search.navigation;

import com.knighten.ai.search.AbstractAStarNode;
import com.knighten.ai.search.IDAStarSearch;
import com.knighten.ai.search.interfaces.IHeuristicFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Maze is the state space(search node) representation of attempting to navigate a maze. The state is an integer array
 * containing the current row and current column.
 */
public class Maze extends AbstractAStarNode<int[]> {

    /**
     * The row of the current position. This is 0 indexed.
     */
    private int currentRow;

    /**
     * The column of the current position. This is 0 indexed.
     */
    private int currentCol;

    /**
     * The maze being traversed. A maze object can only contain 0s and 1s. 0s are walls and 1s are valid paths. We
     * assume the maze is not jagged.
     */
    private int[][] maze;

    /**
     * Number of rows in the maze.
     */
    private int numberOfRows;

    /**
     * Number of columns in the maze.
     */
    private int numberOfCols;

    /**
     * Creates a Maze object with a null parent.
     *
     * @param maze the maze being navigated
     * @param currentRow the row of the current position
     * @param currentCol the column of the current position
     */
    public Maze(int[][] maze, int currentRow, int currentCol) {
        this.maze = maze;
        this.currentRow = currentRow;
        this.currentCol = currentCol;

        this.setState(new int[]{currentRow, currentCol});

        this.numberOfRows = maze.length;
        this.numberOfCols = maze[0].length; // Assuming No Jagged Arrays
    }

    /**
     * Creates a Maze object with parent.
     *
     * @param maze the maze being navigated
     * @param currentRow the row of the current position
     * @param currentCol the column of the current position
     * @param parentMaze the parent of the new maze
     */
    public Maze(int[][] maze, int currentRow, int currentCol, Maze parentMaze) {
        this(maze, currentRow, currentCol);
        this.setParent(parentMaze);
    }

    /**
     * Creates a list of maze objects that represent the next possible positions when navigating the maze. Attempts to
     * move up, down, left, and right from the current position.
     *
     * @return a list of the next possible positions
     */
    @Override
    public List<AbstractAStarNode> getSuccessors() {
        List<AbstractAStarNode> possibleMoves = new ArrayList<>();

        // Move Up
        if(this.currentRow - 1 >= 0 && this.maze[this.currentRow - 1][this.currentCol] != 0)
            possibleMoves.add(new Maze(this.maze, this.currentRow - 1, this.currentCol, this));

        // Move Down
        if(this.currentRow + 1 < numberOfRows && this.maze[this.currentRow + 1][this.currentCol] != 0)
            possibleMoves.add(new Maze(this.maze, this.currentRow + 1, this.currentCol, this));

        // Move Left
        if(this.currentCol - 1 >= 0 && this.maze[this.currentRow][this.currentCol - 1] != 0)
            possibleMoves.add(new Maze(this.maze, this.currentRow, this.currentCol -1, this));

        //Move Right
        if(this.currentCol + 1 < numberOfCols && this.maze[this.currentRow][this.currentCol + 1] != 0)
            possibleMoves.add(new Maze(this.maze, this.currentRow, this.currentCol + 1, this));


        return possibleMoves;
    }

    /**
     * Since we are treating each maze state as having changed a single space, each maze is only one step from its
     * parent.
     *
     * @return the distance from the previous position/maze
     */
    @Override
    public double distFromParent() {
        return 1;
    }

    /**
     * Generates a hash code based on the current position in the maze.
     *
     * @return hash code for the maze
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new int[]{currentRow, currentCol});
    }


    /**
     * Checks if two Mazes are at the same current position in the maze.
     *
     * @param otherMaze Maze being compared to the current Maze
     * @return true if two mazes are at the same current position, otherwise false
     */
    @Override
    public boolean equals(Object otherMaze) {
        if(otherMaze instanceof Maze) {
            return Arrays.equals(((Maze) otherMaze).getState(), this.getState());
        }

        return false;
    }

    /**
     * Creates a string representation of the maze. The current position is represented as *, valid paths are 1s, and
     * walls are 0s.
     *
     * @return string representation of the maze
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for(int row = 0; row < this.numberOfRows; row++) {
            for(int column = 0; column < this.numberOfCols; column++) {

                if(row == this.currentRow && column == this.currentCol)
                    builder.append("* ");
                else
                    builder.append(Integer.toString(this.maze[row][column]) + ' ');

            }

            if(row != (numberOfRows - 1))
                builder.append('\n');
        }

        return builder.toString();
    }

    public static void main(String[] args) {

        int[][] maze = {
                {1, 0, 0, 0, 1},
                {1 ,0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 1, 1, 1, 1}};

        int startRow = 0;
        int startCol = 0;
        int goalRow = 0;
        int goalCol = 4;

        Maze initial = new Maze(maze, startRow, startCol);
        Maze goal = new Maze(maze, goalRow, goalCol);

        IHeuristicFunction heuristicFunction = new NavigationManhattanDist(goal);

        IDAStarSearch searcher = new IDAStarSearch(initial, goal, heuristicFunction);

        AbstractAStarNode finalSearchNode = searcher.search();

        System.out.println("Initial State");
        System.out.println(initial);

        System.out.println("Goal State");
        System.out.println(goal);

        List<AbstractAStarNode> path = searcher.getPath(finalSearchNode);
        int step = 1;
        for (AbstractAStarNode node : path) {
            System.out.println("Step " + step);
            System.out.println(node + "\n");
            step++;
        }

    }

}
