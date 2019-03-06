package baseclasses;

import com.knighten.ai.search.AbstractAStarNode;
import com.knighten.ai.search.AStarSearch;
import com.knighten.ai.search.interfaces.IHeuristicFunction;
import com.knighten.ai.search.npuzzle.EightPuzzle;
import com.knighten.ai.search.npuzzle.NPuzzleManhattanDist;
import com.knighten.ai.search.npuzzle.ThreePuzzle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AStarSearchTests {

    private EightPuzzle standardGoalBoard8Puzz;
    private EightPuzzle oneMoveStandardInitBoard8Puzz;
    private EightPuzzle twoMoveStandardInitBoard8Puzz;
    private EightPuzzle sevenMoveInitBoard8Puzz;
    private EightPuzzle sevenMoveGoalBoard8Puzz;
    private EightPuzzle twentyTwoMoveInitBoard8Puzz;
    private EightPuzzle twentyTwoMoveGoalBoard8Puzz;
    private EightPuzzle twentySixMoveInitBoard8Puzz;
    private EightPuzzle twentySixMoveGoalBoard8Puzz;
    private EightPuzzle thirtyOneMoveInitBoard8Puzz;
    private EightPuzzle thirtyOneMoveGoalBoard8Puzz;
    private EightPuzzle noSolutionGoalBoard8Puzz;
    private EightPuzzle noSolutionInitBoard8Puzz;
    private ThreePuzzle standardGoalBoard3Puzz;
    private ThreePuzzle sevenMoveInitBoard3Puzz;
    private ThreePuzzle noSolutionInitBoard3Puzz;
    private ThreePuzzle oneMoveInitBoard3Puzz;
    private IHeuristicFunction manhattanDist;

    private AbstractAStarNode mockGoal;
    private AbstractAStarNode mockInitial;
    private AbstractAStarNode successor1;
    private AbstractAStarNode successor2;
    private IHeuristicFunction mockHeuristic;


    @Before
    public void setup() {

        standardGoalBoard8Puzz = new EightPuzzle(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
        oneMoveStandardInitBoard8Puzz = new EightPuzzle(new int[]{1, 0, 2, 3, 4, 5, 6, 7, 8});
        twoMoveStandardInitBoard8Puzz = new EightPuzzle(new int[]{1, 2, 0, 3, 4, 5, 6, 7, 8});
        sevenMoveGoalBoard8Puzz = new EightPuzzle(new int[]{1, 2, 3, 8, 6, 4, 7, 5, 0});
        sevenMoveInitBoard8Puzz = new EightPuzzle(new int[]{2, 8, 3, 1, 6, 4, 7, 0, 5});
        twentyTwoMoveGoalBoard8Puzz = new EightPuzzle(new int[]{1, 2, 3, 4, 0, 5, 6, 7, 8});
        twentyTwoMoveInitBoard8Puzz = new EightPuzzle(new int[]{5, 4, 0, 6, 1, 8, 7, 3, 2});
        twentySixMoveGoalBoard8Puzz = new EightPuzzle(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
        twentySixMoveInitBoard8Puzz = new EightPuzzle(new int[]{7, 2, 4, 5, 0, 6, 8, 3, 1});
        thirtyOneMoveGoalBoard8Puzz = new EightPuzzle(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0});
        thirtyOneMoveInitBoard8Puzz = new EightPuzzle(new int[]{8, 6, 7, 2, 5, 4, 3, 0, 1});
        noSolutionGoalBoard8Puzz = new EightPuzzle(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
        noSolutionInitBoard8Puzz = new EightPuzzle(new int[]{0, 2, 1, 3, 4, 5, 6, 7, 8});

        standardGoalBoard3Puzz = new ThreePuzzle(new int[]{0, 1, 2, 3});
        oneMoveInitBoard3Puzz = new ThreePuzzle(new int[]{1, 0, 2, 3});
        sevenMoveInitBoard3Puzz = new ThreePuzzle(new int[]{3 ,2 ,1, 0});
        noSolutionInitBoard3Puzz = new ThreePuzzle(new int[]{3, 0, 2, 1});

        manhattanDist = new NPuzzleManhattanDist();

        mockGoal = Mockito.mock(AbstractAStarNode.class);

        mockInitial = Mockito.mock(AbstractAStarNode.class);
        Mockito.when(mockInitial.getG()).thenReturn(0);

        mockHeuristic = Mockito.mock(IHeuristicFunction.class);

        successor1 = Mockito.mock(AbstractAStarNode.class);
        Mockito.when(successor1.distFromParent()).thenReturn(1);
        Mockito.when(successor1.getParent()).thenReturn(mockInitial);

        successor2 = Mockito.mock(AbstractAStarNode.class);
        Mockito.when(successor2.distFromParent()).thenReturn(1);
        Mockito.when(successor2.getParent()).thenReturn(mockInitial);

        List<AbstractAStarNode> listOfMocks = new ArrayList<>();
        listOfMocks.add(successor1);
        listOfMocks.add(successor2);
        Mockito.when(mockInitial.getSuccessors()).thenReturn(listOfMocks);
    }

    ////////////////////
    // Method testing //
    ////////////////////

    @Test
    public void searchUseCorrectAndGoalFound() {
        AStarSearch searcher = new AStarSearch(mockGoal, mockGoal, mockHeuristic);

        AbstractAStarNode solution = searcher.search();

        // Verify Initial Nodes F Value Is Set To Its Heuristic
        verify(mockHeuristic, times(1)).calculateHeuristic(mockGoal, mockGoal);
        verify(mockGoal, times(1)).setF(anyInt());

        // Now The Queue Should Be In Use

        // Check For Goal - Not Sure How To Correctly Test
        // verify(mockInitial, times(1)).equals(mockGoal);

        // Algorithm Ends Due To Goal Being Found
        Assert.assertEquals(solution, mockGoal);
    }

    @Test
    public void searchUseCorrectMethodsWithNoSuccessors() {
        AStarSearch searcher = new AStarSearch(mockInitial, mockGoal, mockHeuristic);

        AbstractAStarNode solution = searcher.search();

        // Verify Initial Nodes F Value Is Set To Its Heuristic
        verify(mockHeuristic, times(1)).calculateHeuristic(mockInitial, mockGoal);
        verify(mockInitial, times(1)).setF(anyInt());

        // Now The Queue Should Be In Use

        // Check For Goal - Not Sure How To Correctly Test
        // verify(mockInitial, times(1)).equals(mockGoal);

        //Generate Successors
        verify(mockInitial, times(1)).getSuccessors();

        // Algorithm Ends Due To Goal Not Being Found
        Assert.assertNull(solution);
    }

    @Test
    public void searchUseCorrectMethodsWithSuccessors() {
        AStarSearch searcher = new AStarSearch(mockInitial, mockGoal, mockHeuristic);

        AbstractAStarNode solution = searcher.search();

        // Verify Initial Nodes F Value Is Set To Its Heuristic
        verify(mockHeuristic, times(1)).calculateHeuristic(mockInitial, mockGoal);
        verify(mockInitial, times(1)).setF(anyInt());

        // Now The Queue Should Be In Use

        // Check For Goal - Not Sure How To Correctly Test
        // verify(mockInitial, times(1)).equals(mockGoal);

        //Generate Successors
        verify(mockInitial, times(1)).getSuccessors();

        //Explore Both Children set G, H, F
        verify(mockInitial, times(2)).getG();

        verify(successor1, times(1)).distFromParent();
        verify(successor1, times(1)).setG(anyInt());
        verify(mockHeuristic, times(1)).calculateHeuristic(successor1, mockGoal);
        verify(successor1, times(1)).setH(anyInt());
        verify(successor1, times(1)).getG();
        verify(successor1, times(1)).getH();
        verify(successor1, times(1)).setF(anyInt());

        verify(successor2, times(1)).distFromParent();
        verify(successor2, times(1)).setG(anyInt());
        verify(mockHeuristic, times(1)).calculateHeuristic(successor2, mockGoal);
        verify(successor2, times(1)).setH(anyInt());
        verify(successor2, times(1)).getG();
        verify(successor2, times(1)).getH();
        verify(successor2, times(1)).setF(anyInt());

        // Algorithm Ends Due To No Goal Not Being Found
        Assert.assertNull(solution);
    }

    //////////////////////////////////////////////
    // Guarantee Accurate EightPuzzle Solutions //
    //////////////////////////////////////////////

    @Test
    public void noMovesNeeded8Puzzle() {
        AStarSearch searcher = new AStarSearch(standardGoalBoard8Puzz, standardGoalBoard8Puzz, manhattanDist);
        AbstractAStarNode solution = searcher.search();
        ArrayList<AbstractAStarNode> path = searcher.getPath(solution);

        Assert.assertEquals(1, path.size());
    }

    @Test
    public void oneMoveNeeded8Puzzle() {
        AStarSearch searcher = new AStarSearch(standardGoalBoard8Puzz, oneMoveStandardInitBoard8Puzz, manhattanDist);
        AbstractAStarNode solution = searcher.search();
        ArrayList<AbstractAStarNode> path = searcher.getPath(solution);

        Assert.assertEquals(2, path.size());
    }

    @Test
    public void twoMovesNeeded8Puzzle() {
        AStarSearch searcher = new AStarSearch(standardGoalBoard8Puzz, twoMoveStandardInitBoard8Puzz, manhattanDist);
        AbstractAStarNode solution = searcher.search();
        ArrayList<AbstractAStarNode> path = searcher.getPath(solution);

        Assert.assertEquals(3, path.size());
    }

    @Test
    public void sevenMovesNeeded8Puzzle() {
        AStarSearch searcher = new AStarSearch(sevenMoveGoalBoard8Puzz, sevenMoveInitBoard8Puzz, manhattanDist);
        AbstractAStarNode solution = searcher.search();
        ArrayList<AbstractAStarNode> path = searcher.getPath(solution);

        Assert.assertEquals(8, path.size());
    }

    @Test
    public void twentyTwoMove8Puzzle() {
        AStarSearch searcher = new AStarSearch(twentyTwoMoveGoalBoard8Puzz, twentyTwoMoveInitBoard8Puzz, manhattanDist);
        AbstractAStarNode solution = searcher.search();
        ArrayList<AbstractAStarNode> path = searcher.getPath(solution);

        Assert.assertEquals(23, path.size());
    }

    @Test
    public void twentySixMove8Puzzle() {
        AStarSearch searcher = new AStarSearch(twentySixMoveGoalBoard8Puzz, twentySixMoveInitBoard8Puzz, manhattanDist);
        AbstractAStarNode solution = searcher.search();
        ArrayList<AbstractAStarNode> path = searcher.getPath(solution);

        Assert.assertEquals(27, path.size());
    }

    @Test
    public void thirtyOneMove8Puzzle() {
        AStarSearch searcher = new AStarSearch(thirtyOneMoveGoalBoard8Puzz, thirtyOneMoveInitBoard8Puzz, manhattanDist);
        AbstractAStarNode solution = searcher.search();
        ArrayList<AbstractAStarNode> path = searcher.getPath(solution);

        Assert.assertEquals(32, path.size());
    }

    @Test
    public void noSolution8Puzzle() {
        AStarSearch searcher = new AStarSearch(noSolutionGoalBoard8Puzz, noSolutionInitBoard8Puzz, manhattanDist);
        AbstractAStarNode solution = searcher.search();

        Assert.assertNull(solution);
    }


    //////////////////////////////////////////////
    // Guarantee Accurate ThreePuzzle Solutions //
    //////////////////////////////////////////////

    @Test
    public void noMovesNeeded3Puzzle() {
        AStarSearch searcher = new AStarSearch(standardGoalBoard3Puzz, standardGoalBoard3Puzz, manhattanDist);
        AbstractAStarNode solution = searcher.search();
        ArrayList<AbstractAStarNode> path = searcher.getPath(solution);

        Assert.assertEquals(1, path.size());
    }

    @Test
    public void oneMoveNeeded3Puzzle() {
        AStarSearch searcher = new AStarSearch(standardGoalBoard3Puzz, oneMoveInitBoard3Puzz, manhattanDist);
        AbstractAStarNode solution = searcher.search();
        ArrayList<AbstractAStarNode> path = searcher.getPath(solution);

        Assert.assertEquals(2, path.size());
    }

    @Test
    public void sevenMovesNeeded3Puzzle() {
        AStarSearch searcher = new AStarSearch(standardGoalBoard3Puzz, sevenMoveInitBoard3Puzz, manhattanDist);
        AbstractAStarNode solution = searcher.search();
        ArrayList<AbstractAStarNode> path = searcher.getPath(solution);

        Assert.assertEquals(7, path.size());
    }

    @Test
    public void noSolution3Puzzle() {
        AStarSearch searcher = new AStarSearch(standardGoalBoard3Puzz, noSolutionInitBoard3Puzz, manhattanDist);
        AbstractAStarNode solution = searcher.search();

        Assert.assertNull(solution);
    }

}
