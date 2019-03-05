import com.knighten.ai.search.AStarNode;
import com.knighten.ai.search.npuzzle.EightPuzzle;
import com.knighten.ai.search.npuzzle.NPuzzleManhattanDist;
import com.knighten.ai.search.npuzzle.ThreePuzzle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class NPuzzleManhattanDistTests {

    private AStarNode mockGoalNode;
    private AStarNode mockSearchNode;
    private ThreePuzzle goalThreePuzzle;
    private ThreePuzzle searchThreePuzzleOneH;
    private ThreePuzzle searchThreePuzzleSixH;
    private EightPuzzle goalEightPuzzle;
    private EightPuzzle searchEightPuzzleOneH;
    private EightPuzzle searchEightPuzzleTwoH;
    private EightPuzzle searchEightPuzzleSevenH;
    private EightPuzzle searchEightPuzzleThirteenH;
    private EightPuzzle searchEightPuzzleTwentyH;


    @Before
    public void setup() throws Exception {
        mockGoalNode = Mockito.mock(AStarNode.class);
        Mockito.when(mockGoalNode.getState()).thenReturn(new int[]{1});

        mockSearchNode = Mockito.mock(AStarNode.class);
        Mockito.when(mockGoalNode.getState()).thenReturn(new int[]{0});

        goalThreePuzzle = new ThreePuzzle(new int[]{0, 1, 2, 3});
        searchThreePuzzleOneH = new ThreePuzzle(new int[]{1, 0, 2, 3});
        searchThreePuzzleSixH = new ThreePuzzle(new int[]{3,  2, 1, 0});

        goalEightPuzzle = new EightPuzzle(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
        searchEightPuzzleOneH = new EightPuzzle(new int[]{1, 0, 2, 3, 4, 5, 6, 7, 8});
        searchEightPuzzleTwoH = new EightPuzzle(new int[]{1, 2, 0, 3, 4, 5, 6, 7, 8});
        searchEightPuzzleSevenH = new EightPuzzle(new int[]{1, 2, 3, 4, 5, 0, 6, 7, 8});
        searchEightPuzzleThirteenH = new EightPuzzle(new int[]{6, 3, 2, 0, 8, 1, 4, 5, 7});
        searchEightPuzzleTwentyH = new EightPuzzle(new int[]{8, 7, 6, 5, 4, 3, 2, 1, 0});
    }

    ////////////////////////
    // Parameter Checking //
    ////////////////////////

    @Test(expected = IllegalArgumentException.class)
    public void calculateHeuristicSearchNodeNull() {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist();
        testObject.calculateHeuristic(null, mockGoalNode);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateHeuristicGoalNodeNull() {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist();
        testObject.calculateHeuristic(mockSearchNode, null);
    }


    ////////////////////
    // Method Testing //
    ////////////////////

    @Test
    public void calculateHeuristicVerifyStatesAreUsed() {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist();
        testObject.calculateHeuristic(mockSearchNode, mockGoalNode);

        verify(mockSearchNode, times(1)).getState();
        verify(mockGoalNode, times(1)).getState();
    }


    ///////////////////////////////////
    // Actual Implementation Testing //
    ///////////////////////////////////

    @Test
    public void calculateHeuristicThreePuzzleHZero() {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist();
        int result = testObject.calculateHeuristic(goalThreePuzzle, goalThreePuzzle);

        Assert.assertEquals(0, result);
    }

    @Test
    public void calculateHeuristicThreePuzzleHOne() {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist();
        int result = testObject.calculateHeuristic(searchThreePuzzleOneH, goalThreePuzzle);

        Assert.assertEquals(1, result);
    }

    @Test
    public void calculateHeuristicThreePuzzleHSix() {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist();
        int result = testObject.calculateHeuristic(searchThreePuzzleSixH, goalThreePuzzle);

        Assert.assertEquals(6, result);
    }


    @Test
    public void calculateHeuristicEightPuzzleHZero() throws Exception {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist();
        int result = testObject.calculateHeuristic(goalEightPuzzle, goalEightPuzzle);

        Assert.assertEquals(0, result);
    }

    @Test
    public void calculateHeuristicEightPuzzleHOne() throws Exception {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist();
        int result = testObject.calculateHeuristic(searchEightPuzzleOneH, goalEightPuzzle);

        Assert.assertEquals(1, result);
    }

    @Test
    public void calculateHeuristicEightPuzzleHTwo() throws Exception {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist();
        int result = testObject.calculateHeuristic(searchEightPuzzleTwoH, goalEightPuzzle);

        Assert.assertEquals(2, result);
    }

    @Test
    public void calculateHeuristicEightPuzzleHSeven() throws Exception {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist();
        int result = testObject.calculateHeuristic(searchEightPuzzleSevenH, goalEightPuzzle);

        Assert.assertEquals(7, result);
    }

    @Test
    public void calculateHeuristicEightPuzzleHThirteen() throws Exception {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist();
        int result = testObject.calculateHeuristic(searchEightPuzzleThirteenH, goalEightPuzzle);

        Assert.assertEquals(13, result);
    }

    @Test
    public void calculateHeuristicEightPuzzleHTwenty() throws Exception {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist();
        int result = testObject.calculateHeuristic(searchEightPuzzleTwentyH, goalEightPuzzle);

        Assert.assertEquals(20, result);
    }

}
