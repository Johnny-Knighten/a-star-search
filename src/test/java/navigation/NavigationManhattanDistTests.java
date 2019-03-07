package navigation;

import com.knighten.ai.search.navigation.AbstractNavigate;
import com.knighten.ai.search.navigation.NavigationManhattanDist;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class NavigationManhattanDistTests {

    private AbstractNavigate mockGoalNode;
    private AbstractNavigate mockSearchNode2Score;
    private AbstractNavigate mockSearchNode4Score;
    private AbstractNavigate mockSearchNode8Score;
    private AbstractNavigate mockSearchNode16Score;
    private AbstractNavigate mockSearchNode32Score;

    @Before
    public void setup() {

        mockGoalNode = Mockito.mock(AbstractNavigate.class);
        Mockito.when(mockGoalNode.getState()).thenReturn(new int[]{0, 0});

        mockSearchNode2Score = Mockito.mock(AbstractNavigate.class);
        Mockito.when(mockSearchNode2Score.getState()).thenReturn(new int[]{1, 1});

        mockSearchNode4Score = Mockito.mock(AbstractNavigate.class);
        Mockito.when(mockSearchNode4Score.getState()).thenReturn(new int[]{2, 2});

        mockSearchNode8Score = Mockito.mock(AbstractNavigate.class);
        Mockito.when(mockSearchNode8Score.getState()).thenReturn(new int[]{4, 4});

        mockSearchNode16Score = Mockito.mock(AbstractNavigate.class);
        Mockito.when(mockSearchNode16Score.getState()).thenReturn(new int[]{8, 8});

        mockSearchNode32Score = Mockito.mock(AbstractNavigate.class);
        Mockito.when(mockSearchNode32Score.getState()).thenReturn(new int[]{16, 16});

    }

    ////////////////////////
    // Parameter Checking //
    ////////////////////////

    @Test(expected = IllegalArgumentException.class)
    public void constructorGoalNodeIsNull() {
        NavigationManhattanDist testObject = new NavigationManhattanDist(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateHeuristicSearchNodeNull() {
        NavigationManhattanDist testObject = new NavigationManhattanDist(mockGoalNode);
        testObject.calculateHeuristic(null);
    }

    ////////////////////
    // Method Testing //
    ////////////////////
    @Test
    public void calculateHeuristicVerifyStatesAreUsed() {
        NavigationManhattanDist testObject = new NavigationManhattanDist(mockGoalNode);
        testObject.calculateHeuristic(mockSearchNode2Score);

        verify(mockSearchNode2Score, times(1)).getState();
        verify(mockGoalNode, times(1)).getState();
    }

    ///////////////////////////////////
    // Actual Implementation Testing //
    ///////////////////////////////////

    @Test
    public void calculateHeuristicScoreEquals0() {
        NavigationManhattanDist testObject = new NavigationManhattanDist(mockGoalNode);
        double result = testObject.calculateHeuristic(mockGoalNode);

        Assert.assertEquals(0.0, result, .000001);
    }

    @Test
    public void calculateHeuristicScoreEquals2() {
        NavigationManhattanDist testObject = new NavigationManhattanDist(mockGoalNode);
        double result = testObject.calculateHeuristic(mockSearchNode2Score);

        Assert.assertEquals(2.0, result, .000001);
    }

    @Test
    public void calculateHeuristicScoreEquals4() {
        NavigationManhattanDist testObject = new NavigationManhattanDist(mockGoalNode);
        double result = testObject.calculateHeuristic(mockSearchNode4Score);

        Assert.assertEquals(4.0, result, .000001);
    }

    @Test
    public void calculateHeuristicScoreEquals8() {
        NavigationManhattanDist testObject = new NavigationManhattanDist(mockGoalNode);
        double result = testObject.calculateHeuristic(mockSearchNode8Score);

        Assert.assertEquals(8.0, result, .000001);
    }

    @Test
    public void calculateHeuristicScoreEquals16() {
        NavigationManhattanDist testObject = new NavigationManhattanDist(mockGoalNode);
        double result = testObject.calculateHeuristic(mockSearchNode16Score);

        Assert.assertEquals(16.0, result, .000001);
    }

    @Test
    public void calculateHeuristicScoreEquals32() {
        NavigationManhattanDist testObject = new NavigationManhattanDist(mockGoalNode);
        double result = testObject.calculateHeuristic(mockSearchNode32Score);

        Assert.assertEquals(32.0, result, .000001);
    }

}
