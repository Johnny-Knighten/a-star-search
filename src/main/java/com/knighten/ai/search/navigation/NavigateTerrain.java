package com.knighten.ai.search.navigation;

import com.knighten.ai.search.AStarSearch;
import com.knighten.ai.search.AbstractAStarNode;
import com.knighten.ai.search.interfaces.IHeuristicFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * NavigateTerrain is the state space(search node) representation of attempting to navigate a terrain were certain areas
 * are easy to move in that others. You can image that walking down a path is easier than swimming. The state is an
 * integer array containing the current row and current column. NavigateTerrains terrains are represented by a 0 being
 * an obstacle you cannot travel through and all other spaces are positive integers. The value of non-zero spaces
 * represent how hard it is to traverse that area. For instance walking down a path may be 1 because its simple while
 * swimming may be a 2.
 */
public class NavigateTerrain extends AbstractNavigate {

    /**
     * Creates a NavigateTerrain object with a null parent.
     *
     * @param terrain    the terrain being navigated
     * @param currentRow the row of the current position
     * @param currentCol the column of the current position
     */
    public NavigateTerrain(int[][] terrain, int currentRow, int currentCol) {
        super(terrain, currentRow, currentCol);
    }

    /**
     * Creates a NavigateTerrain object with parent.
     *
     * @param terrain    the maze being navigated
     * @param currentRow the row of the current position
     * @param currentCol the column of the current position
     * @param parentMaze the parent of the new maze
     */
    public NavigateTerrain(int[][] terrain, int currentRow, int currentCol, NavigateTerrain parentMaze) {
        super(terrain, currentRow, currentCol);
        this.setParent(parentMaze);
    }

    /**
     * Creates a list of NavigateTerrain objects that represent the next possible positions when navigating the maze.
     * Attempts to move up, down, left, and right from the current position.
     *
     * @return a list of the next possible positions
     */
    @Override
    public List<AbstractAStarNode> getSuccessors() {
        List<AbstractAStarNode> possibleMoves = new ArrayList<>();

        // Move Up
        if (this.getState()[0] - 1 >= 0 && this.getEnvironment()[this.getState()[0] - 1][this.getState()[1]] != 0)
            possibleMoves.add(new NavigateTerrain(this.getEnvironment(), this.getState()[0] - 1, this.getState()[1], this));

        // Move Down
        if (this.getState()[0] + 1 < super.getNumberOfRows() && this.getEnvironment()[this.getState()[0] + 1][this.getState()[1]] != 0)
            possibleMoves.add(new NavigateTerrain(this.getEnvironment(), this.getState()[0] + 1, this.getState()[1], this));

        // Move Left
        if (this.getState()[1] - 1 >= 0 && this.getEnvironment()[this.getState()[0]][this.getState()[1] - 1] != 0)
            possibleMoves.add(new NavigateTerrain(this.getEnvironment(), this.getState()[0], this.getState()[1] - 1, this));

        //Move Right
        if (this.getState()[1] + 1 < super.getNumberOfCols() && this.getEnvironment()[this.getState()[0]][this.getState()[1] + 1] != 0)
            possibleMoves.add(new NavigateTerrain(this.getEnvironment(), this.getState()[0], this.getState()[1] + 1, this));

        return possibleMoves;
    }

    /**
     * Since we are treating some parts of the environment as being harder to navigate than others the distance from
     * a nodes parent is the current position's value in the environment.
     *
     * @return the value of the environment at the current position
     */
    @Override
    public double distFromParent() {
        return this.getEnvironment()[this.getState()[0]][this.getState()[1]];
    }

    public static void main(String[] args) {

        int[][] terrain = {
                {1, 2, 2, 2, 2, 2, 2, 2, 2, 1},
                {1, 0, 2, 2, 2, 2, 2, 2, 0, 1},
                {1, 0, 2, 2, 2, 2, 2, 2, 0, 1},
                {1, 0, 2, 2, 2, 2, 2, 2, 0, 1},
                {1, 0, 2, 2, 2, 2, 2, 2, 0, 1},
                {1, 0, 2, 2, 2, 2, 2, 2, 0, 1},
                {1, 0, 2, 2, 2, 2, 2, 2, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        int startRow = 0;
        int startCol = 0;
        int goalRow = 0;
        int goalCol = 9;

        NavigateTerrain initial = new NavigateTerrain(terrain, startRow, startCol);
        NavigateTerrain goal = new NavigateTerrain(terrain, goalRow, goalCol);

        IHeuristicFunction heuristicFunction = new NavigationManhattanDist(goal);

        AStarSearch searcher = new AStarSearch(initial, goal, heuristicFunction);

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
