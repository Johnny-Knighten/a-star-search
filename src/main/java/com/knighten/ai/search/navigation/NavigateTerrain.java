package com.knighten.ai.search.navigation;

import com.knighten.ai.search.AbstractAStarNode;
import com.knighten.ai.search.IDAStarSearch;
import com.knighten.ai.search.interfaces.IHeuristicFunction;

import java.util.ArrayList;
import java.util.List;

public class NavigateTerrain extends Maze {

    public NavigateTerrain(int[][] terrain, int currentRow, int currentCol) {
        super(terrain, currentRow, currentCol);
    }

    public NavigateTerrain(int[][] maze, int currentRow, int currentCol, NavigateTerrain parentMaze) {
        super(maze, currentRow, currentCol, parentMaze);
    }

    @Override
    public double distFromParent() {
        return super.getMaze()[this.getState()[0]][this.getState()[1]];
    }

    @Override
    public List<AbstractAStarNode> getSuccessors() {
        List<AbstractAStarNode> possibleMoves = new ArrayList<>();

        // Move Up
        if(this.getState()[0] - 1 >= 0 && super.getMaze()[this.getState()[0] - 1][this.getState()[1]] != 0)
            possibleMoves.add(new NavigateTerrain(super.getMaze(), this.getState()[0] - 1, this.getState()[1], this));

        // Move Down
        if(this.getState()[0] + 1 < super.getNumberOfRows() && super.getMaze()[this.getState()[0] + 1][this.getState()[1]] != 0)
            possibleMoves.add(new NavigateTerrain(super.getMaze(), this.getState()[0] + 1, this.getState()[1], this));

        // Move Left
        if(this.getState()[1] - 1 >= 0 && super.getMaze()[this.getState()[0]][this.getState()[1] - 1] != 0)
            possibleMoves.add(new NavigateTerrain(super.getMaze(), this.getState()[0], this.getState()[1] - 1, this));

        //Move Right
        if(this.getState()[1] + 1 < super.getNumberOfCols() && super.getMaze()[this.getState()[0]][this.getState()[1] + 1] != 0)
            possibleMoves.add(new NavigateTerrain(super.getMaze(), this.getState()[0], this.getState()[1] + 1, this));


        return possibleMoves;
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
