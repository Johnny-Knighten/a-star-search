package com.knighten.ai.search.navigation;

import com.knighten.ai.search.AbstractAStarNode;
import com.knighten.ai.search.IDAStarSearch;
import com.knighten.ai.search.interfaces.IHeuristicFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Maze is the state space(search node) representation of attempting to navigate a maze. The state is an integer array
 * containing the current row and current column. Mazes are represented by two values: 1 is a path that can be followed
 * and 0 is a wall.
 */
public class NavigateMaze extends AbstractNavigate {

    /**
     * Creates a Maze object with a null parent.
     *
     * @param maze       the maze being navigated
     * @param currentRow the row of the current position
     * @param currentCol the column of the current position
     */
    public NavigateMaze(int[][] maze, int currentRow, int currentCol) {
        super(maze, currentRow, currentCol);

        boolean valueCheck = Arrays.stream(maze)
                .flatMapToInt(Arrays::stream)
                .anyMatch((i) -> i != 0 && i != 1);

        if(valueCheck)
            throw new IllegalArgumentException("Values Inside A Maze Can Only Be 0 or 1");
    }

    /**
     * Creates a Maze object with parent.
     *
     * @param maze       the maze being navigated
     * @param currentRow the row of the current position
     * @param currentCol the column of the current position
     * @param parentMaze the parent of the new maze
     */
    public NavigateMaze(int[][] maze, int currentRow, int currentCol, NavigateMaze parentMaze) {
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
        if (this.getState()[0] - 1 >= 0 && this.getEnvironment()[this.getState()[0] - 1][this.getState()[1]] != 0)
            possibleMoves.add(new NavigateMaze(this.getEnvironment(), this.getState()[0] - 1, this.getState()[1], this));

        // Move Down
        if (this.getState()[0] + 1 < super.getNumberOfRows() && this.getEnvironment()[this.getState()[0] + 1][this.getState()[1]] != 0)
            possibleMoves.add(new NavigateMaze(this.getEnvironment(), this.getState()[0] + 1, this.getState()[1], this));

        // Move Left
        if (this.getState()[1] - 1 >= 0 && this.getEnvironment()[this.getState()[0]][this.getState()[1] - 1] != 0)
            possibleMoves.add(new NavigateMaze(this.getEnvironment(), this.getState()[0], this.getState()[1] - 1, this));

        //Move Right
        if (this.getState()[1] + 1 < super.getNumberOfCols() && this.getEnvironment()[this.getState()[0]][this.getState()[1] + 1] != 0)
            possibleMoves.add(new NavigateMaze(this.getEnvironment(), this.getState()[0], this.getState()[1] + 1, this));

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

    public static void main(String[] args) {

        int[][] maze = {
                {1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 1, 1, 1, 1}};

        int startRow = 0;
        int startCol = 0;
        int goalRow = 0;
        int goalCol = 4;

        NavigateMaze initial = new NavigateMaze(maze, startRow, startCol);
        NavigateMaze goal = new NavigateMaze(maze, goalRow, goalCol);

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
