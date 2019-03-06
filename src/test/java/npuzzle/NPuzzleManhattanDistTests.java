package npuzzle;

import com.knighten.ai.search.AbstractAStarNode;
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

    private AbstractAStarNode mockGoalNode;
    private AbstractAStarNode mockSearchNode;
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
        mockGoalNode = Mockito.mock(AbstractAStarNode.class);
        Mockito.when(mockGoalNode.getState()).thenReturn(new int[]{1});

        mockSearchNode = Mockito.mock(AbstractAStarNode.class);
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
    public void constructorGoalNodeIsNull() {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateHeuristicSearchNodeNull() {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist(mockGoalNode);
        testObject.calculateHeuristic(null);
    }

    ////////////////////
    // Method Testing //
    ////////////////////

    @Test
    public void calculateHeuristicVerifyStatesAreUsed() {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist(mockGoalNode);
        testObject.calculateHeuristic(mockSearchNode);

        verify(mockSearchNode, times(1)).getState();
        verify(mockGoalNode, times(1)).getState();
    }


    ///////////////////////////////////
    // Actual Implementation Testing //
    ///////////////////////////////////

    @Test
    public void calculateHeuristicThreePuzzleHZero() {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist(goalThreePuzzle);
        double result = testObject.calculateHeuristic(goalThreePuzzle);

        Assert.assertEquals(0, result, .00001);
    }

    @Test
    public void calculateHeuristicThreePuzzleHOne() {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist(goalThreePuzzle);
        double result = testObject.calculateHeuristic(searchThreePuzzleOneH);

        Assert.assertEquals(1, result, .00001);
    }

    @Test
    public void calculateHeuristicThreePuzzleHSix() {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist(goalThreePuzzle);
        double result = testObject.calculateHeuristic(searchThreePuzzleSixH);

        Assert.assertEquals(6, result, .00001);
    }


    @Test
    public void calculateHeuristicEightPuzzleHZero() {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist(goalEightPuzzle);
        double result = testObject.calculateHeuristic(goalEightPuzzle);

        Assert.assertEquals(0, result, .00001);
    }

    @Test
    public void calculateHeuristicEightPuzzleHOne() {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist(goalEightPuzzle);
        double result = testObject.calculateHeuristic(searchEightPuzzleOneH);

        Assert.assertEquals(1, result, .00001);
    }

    @Test
    public void calculateHeuristicEightPuzzleHTwo() {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist(goalEightPuzzle);
        double result = testObject.calculateHeuristic(searchEightPuzzleTwoH);

        Assert.assertEquals(2, result, .00001);
    }

    @Test
    public void calculateHeuristicEightPuzzleHSeven() {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist(goalEightPuzzle);
        double result = testObject.calculateHeuristic(searchEightPuzzleSevenH);

        Assert.assertEquals(7, result, .00001);
    }

    @Test
    public void calculateHeuristicEightPuzzleHThirteen() {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist(goalEightPuzzle);
        double result = testObject.calculateHeuristic(searchEightPuzzleThirteenH);

        Assert.assertEquals(13, result, .00001);
    }

    @Test
    public void calculateHeuristicEightPuzzleHTwenty() {
        NPuzzleManhattanDist testObject = new NPuzzleManhattanDist(goalEightPuzzle);
        double result = testObject.calculateHeuristic(searchEightPuzzleTwentyH);

        Assert.assertEquals(20, result, .00001);
    }

}
