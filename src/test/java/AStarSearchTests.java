import com.knighten.ai.search.AStarNode;
import com.knighten.ai.search.AStarSearch;
import com.knighten.ai.search.interfaces.IHeuristicFunction;
import com.knighten.ai.search.npuzzle.EightPuzzle;
import com.knighten.ai.search.npuzzle.NPuzzleManhattanDist;
import com.knighten.ai.search.npuzzle.ThreePuzzle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class AStarSearchTests {

    private int[] standardGoal8Puzz;
    private int[] oneMoveStandardInit8Puzz;
    private int[] twoMoveStandardInit8Puzz;
    private int[] sevenMoveInit8Puzz;
    private int[] sevenMoveGoal8Puzz;
    private int[] twentyTwoMoveInit8Puzz;
    private int[] twentyTwoMoveGoal8Puzz;
    private int[] twentySixMoveInit8Puzz;
    private int[] twentySixMoveGoal8Puzz;
    private int[] thirtyOneMoveInit8Puzz;
    private int[] thirtyOneMoveGoal8Puzz;
    private int[] noSolutionGoal8Puzz;
    private int[] noSolutionInit8Puzz;
    private int[] standardGoal3Puzz;
    private int[] sevenMoveInit3Puzz;
    private int[] noSolutionInit3Puzz;
    private int[] oneMoveInit3Puzz;

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


    @Before
    public void setup() throws Exception {

        standardGoal8Puzz = new int[]{0,1,2,3,4,5,6,7,8};
        standardGoalBoard8Puzz = new EightPuzzle(standardGoal8Puzz);

        oneMoveStandardInit8Puzz = new int[]{1,0,2,3,4,5,6,7,8};
        oneMoveStandardInitBoard8Puzz = new EightPuzzle(oneMoveStandardInit8Puzz);

        twoMoveStandardInit8Puzz = new int[]{1,2,0,3,4,5,6,7,8};
        twoMoveStandardInitBoard8Puzz = new EightPuzzle(twoMoveStandardInit8Puzz);

        sevenMoveGoal8Puzz = new int[]{1,2,3,8,6,4,7,5,0};
        sevenMoveGoalBoard8Puzz = new EightPuzzle(sevenMoveGoal8Puzz);

        sevenMoveInit8Puzz = new int[]{2,8,3,1,6,4,7,0,5};
        sevenMoveInitBoard8Puzz = new EightPuzzle(sevenMoveInit8Puzz);

        twentyTwoMoveGoal8Puzz = new int[]{1,2,3,4,0,5,6,7,8};
        twentyTwoMoveGoalBoard8Puzz = new EightPuzzle(twentyTwoMoveGoal8Puzz);

        twentyTwoMoveInit8Puzz = new int[]{5,4,0,6,1,8,7,3,2};
        twentyTwoMoveInitBoard8Puzz = new EightPuzzle(twentyTwoMoveInit8Puzz);

        twentySixMoveGoal8Puzz = new int[]{0,1,2,3,4,5,6,7,8};
        twentySixMoveGoalBoard8Puzz = new EightPuzzle(twentySixMoveGoal8Puzz);

        twentySixMoveInit8Puzz = new int[]{7,2,4,5,0,6,8,3,1};
        twentySixMoveInitBoard8Puzz = new EightPuzzle(twentySixMoveInit8Puzz);

        thirtyOneMoveGoal8Puzz = new int[]{1,2,3,4,5,6,7,8,0};
        thirtyOneMoveGoalBoard8Puzz = new EightPuzzle(thirtyOneMoveGoal8Puzz);

        thirtyOneMoveInit8Puzz = new int[]{8,6,7,2,5,4,3,0,1};
        thirtyOneMoveInitBoard8Puzz = new EightPuzzle(thirtyOneMoveInit8Puzz);

        noSolutionGoal8Puzz = new int[]{0,1,2,3,4,5,6,7,8};
        noSolutionGoalBoard8Puzz = new EightPuzzle(noSolutionGoal8Puzz);

        noSolutionInit8Puzz = new int[]{0,2,1,3,4,5,6,7,8};
        noSolutionInitBoard8Puzz = new EightPuzzle(noSolutionInit8Puzz);

        standardGoal3Puzz = new int[]{0,1,2,3};
        standardGoalBoard3Puzz = new ThreePuzzle(standardGoal3Puzz);

        oneMoveInit3Puzz = new int[]{1,0,2,3};
        oneMoveInitBoard3Puzz = new ThreePuzzle(oneMoveInit3Puzz);

        sevenMoveInit3Puzz = new int[]{3,2,1,0};
        sevenMoveInitBoard3Puzz = new ThreePuzzle(sevenMoveInit3Puzz);

        noSolutionInit3Puzz = new int[]{3,0,2,1};
        noSolutionInitBoard3Puzz = new ThreePuzzle(noSolutionInit3Puzz);

        manhattanDist = new NPuzzleManhattanDist();


    }

    //////////////////////////////////////////////
    // Guarantee Accurate EightPuzzle Solutions //
    //////////////////////////////////////////////

    @Test
    public void noMovesNeeded8Puzz() throws Exception {
        AStarSearch searcher = new AStarSearch(standardGoalBoard8Puzz, standardGoalBoard8Puzz, manhattanDist);
        AStarNode solution = searcher.search();
        ArrayList<AStarNode> path = searcher.getPath(solution);

        Assert.assertTrue(path.size() == 1);
    }

    @Test
    public void oneMoveNeeded8Puzz() throws Exception {
        AStarSearch searcher = new AStarSearch(standardGoalBoard8Puzz, oneMoveStandardInitBoard8Puzz, manhattanDist);
        AStarNode solution = searcher.search();
        ArrayList<AStarNode> path = searcher.getPath(solution);

        Assert.assertTrue(path.size() == 2);
    }

    @Test
    public void twoMovesNeeded8Puzz() throws Exception {
        AStarSearch searcher = new AStarSearch(standardGoalBoard8Puzz, twoMoveStandardInitBoard8Puzz, manhattanDist);
        AStarNode solution = searcher.search();
        ArrayList<AStarNode> path = searcher.getPath(solution);

        Assert.assertTrue(path.size() == 3);
    }

    @Test
    public void sevenMovesNeeded8Puzz() throws Exception {
        AStarSearch searcher = new AStarSearch(sevenMoveGoalBoard8Puzz, sevenMoveInitBoard8Puzz, manhattanDist);
        AStarNode solution = searcher.search();
        ArrayList<AStarNode> path = searcher.getPath(solution);

        Assert.assertTrue(path.size() == 8);
    }

    @Test
    public void twentyTwoMove8Puzz() throws Exception {
        AStarSearch searcher = new AStarSearch(twentyTwoMoveGoalBoard8Puzz, twentyTwoMoveInitBoard8Puzz, manhattanDist);
        AStarNode solution = searcher.search();
        ArrayList<AStarNode> path = searcher.getPath(solution);

        Assert.assertTrue(path.size() == 23);
    }

    @Test
    public void twentySixMove8Puzz() throws Exception {
        AStarSearch searcher = new AStarSearch(twentySixMoveGoalBoard8Puzz, twentySixMoveInitBoard8Puzz, manhattanDist);
        AStarNode solution = searcher.search();
        ArrayList<AStarNode> path = searcher.getPath(solution);

        Assert.assertTrue(path.size() == 27);
    }

    @Test
    public void thirtyOneMove8Puzz() throws Exception {
        AStarSearch searcher = new AStarSearch(thirtyOneMoveGoalBoard8Puzz, thirtyOneMoveInitBoard8Puzz, manhattanDist);
        AStarNode solution = searcher.search();
        ArrayList<AStarNode> path = searcher.getPath(solution);

        Assert.assertTrue(path.size() == 32);
    }

    @Test
    public void noSolution8Puzz() throws Exception {
        AStarSearch searcher = new AStarSearch(noSolutionGoalBoard8Puzz, noSolutionInitBoard8Puzz, manhattanDist);
        AStarNode solution = searcher.search();

        Assert.assertNull(solution);
    }


    //////////////////////////////////////////////
    // Guarantee Accurate ThreePuzzle Solutions //
    //////////////////////////////////////////////

    @Test
    public void noMovesNeeded3Puzz() throws Exception {
        AStarSearch searcher = new AStarSearch(standardGoalBoard3Puzz, standardGoalBoard3Puzz, manhattanDist);
        AStarNode solution = searcher.search();
        ArrayList<AStarNode> path = searcher.getPath(solution);

        Assert.assertTrue(path.size() == 1);
    }

    @Test
    public void oneMoveNeeded3Puzz() throws Exception {
        AStarSearch searcher = new AStarSearch(standardGoalBoard3Puzz, oneMoveInitBoard3Puzz, manhattanDist);
        AStarNode solution = searcher.search();
        ArrayList<AStarNode> path = searcher.getPath(solution);

        Assert.assertTrue(path.size() == 2);
    }

    @Test
    public void sevenMovesNeeded3Puzz() throws Exception {
        AStarSearch searcher = new AStarSearch(standardGoalBoard3Puzz, sevenMoveInitBoard3Puzz, manhattanDist);
        AStarNode solution = searcher.search();
        ArrayList<AStarNode> path = searcher.getPath(solution);

        Assert.assertTrue(path.size() == 7);
    }

    @Test
    public void noSolution3Puzz() throws Exception {
        AStarSearch searcher = new AStarSearch(standardGoalBoard3Puzz, noSolutionInitBoard3Puzz, manhattanDist);
        AStarNode solution = searcher.search();

        Assert.assertNull(solution);
    }

}
