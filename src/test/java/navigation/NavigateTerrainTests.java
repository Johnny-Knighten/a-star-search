package navigation;


import com.knighten.ai.search.AbstractAStarNode;
import com.knighten.ai.search.navigation.NavigateTerrain;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

public class NavigateTerrainTests {

    private NavigateTerrain mockParentNode;

    @Before
    public void setup() {
        mockParentNode = Mockito.mock(NavigateTerrain.class);
    }

    ////////////////////////
    // Parameter Checking //
    ////////////////////////

    @Test(expected = IllegalArgumentException.class)
    public void constructorMazeIsNull() {
        new NavigateTerrain(null, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorRowPositionNotInMazeSmallerThanZero() {
        new NavigateTerrain(new int[][]{{0, 0}, {1, 1}}, -1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorRowPositionNotInMazeGreaterThanNumberOfRows() {
        new NavigateTerrain(new int[][]{{0, 0}, {1, 1}}, 10, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorColumnPositionNotInMazeSmallerThanZero() {
        new NavigateTerrain(new int[][]{{0, 0}, {1, 1}}, 0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorColumnPositionNotInMazeGreaterThanNumberOfColumns() {
        new NavigateTerrain(new int[][]{{0, 0}, {1, 1}}, 0, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMazeWithNon0Or1() {
        new NavigateTerrain(new int[][]{{0, 0}, {1, -1}}, 0, 0);
    }

    /////////////////////
    // Method Checking //
    /////////////////////

    @Test
    public void constructorNoParentNode() {
        NavigateTerrain testObject = new NavigateTerrain(new int[][]{{0, 0}, {1, 1}}, 0, 0);

        Assert.assertArrayEquals(new int[]{0, 0}, testObject.getState());
        Assert.assertNull(testObject.getParent());
    }

    @Test
    public void createNodeWithParentNode() {
        NavigateTerrain testObject = new NavigateTerrain(new int[][]{{0, 0}, {1, 1}}, 0, 0, mockParentNode);

        Assert.assertArrayEquals(new int[]{0, 0}, testObject.getState());
        Assert.assertEquals(mockParentNode, testObject.getParent());
    }

    @Test
    public void distFromParentIsValueOnBoard() {
        NavigateTerrain testObject = new NavigateTerrain(new int[][]{{5, 0}, {1, 1}}, 0, 0);

        Assert.assertEquals(5.0, testObject.distFromParent(), .00001);
    }

    @Test
    public void createSuccessorsWithNoPossibleMoves() {
        NavigateTerrain testObject = new NavigateTerrain(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}, 1, 1);
        List<AbstractAStarNode> results = testObject.getSuccessors();

        Assert.assertEquals(0, results.size());
    }

    @Test
    public void createSuccessorsWithOnePossibleMove() {
        NavigateTerrain testObject = new NavigateTerrain(new int[][]{{0, 1, 0}, {0, 1, 0}, {0, 0, 0}}, 1, 1);
        List<AbstractAStarNode> results = testObject.getSuccessors();

        Assert.assertEquals(1, results.size());
        Assert.assertTrue(results.contains(new NavigateTerrain(new int[][]{{0, 1, 0}, {0, 1, 0}, {0, 0, 0}}, 0, 1)));
    }

    @Test
    public void createSuccessorsWithTwoPossibleMoves() {
        NavigateTerrain testObject = new NavigateTerrain(new int[][]{{0, 1, 0}, {0, 1, 0}, {0, 1, 0}}, 1, 1);
        List<AbstractAStarNode> results = testObject.getSuccessors();

        Assert.assertEquals(2, results.size());
        Assert.assertTrue(results.contains(new NavigateTerrain(new int[][]{{0, 1, 0}, {0, 1, 0}, {0, 1, 0}}, 0, 1)));
        Assert.assertTrue(results.contains(new NavigateTerrain(new int[][]{{0, 1, 0}, {0, 1, 0}, {0, 1, 0}}, 2, 1)));
    }

    @Test
    public void createSuccessorsWithThreePossibleMoves() {
        NavigateTerrain testObject = new NavigateTerrain(new int[][]{{0, 1, 0}, {1, 1, 0}, {0, 1, 0}}, 1, 1);
        List<AbstractAStarNode> results = testObject.getSuccessors();

        Assert.assertEquals(3, results.size());
        Assert.assertTrue(results.contains(new NavigateTerrain(new int[][]{{0, 1, 0}, {1, 1, 0}, {0, 1, 0}}, 0, 1)));
        Assert.assertTrue(results.contains(new NavigateTerrain(new int[][]{{0, 1, 0}, {1, 1, 0}, {0, 1, 0}}, 2, 1)));
        Assert.assertTrue(results.contains(new NavigateTerrain(new int[][]{{0, 1, 0}, {1, 1, 0}, {0, 1, 0}}, 1, 0)));
    }

    @Test
    public void createSuccessorsWithFourPossibleMoves() {
        NavigateTerrain testObject = new NavigateTerrain(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 1, 1);
        List<AbstractAStarNode> results = testObject.getSuccessors();

        Assert.assertEquals(4, results.size());
        Assert.assertTrue(results.contains(new NavigateTerrain(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 0, 1)));
        Assert.assertTrue(results.contains(new NavigateTerrain(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 2, 1)));
        Assert.assertTrue(results.contains(new NavigateTerrain(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 1, 0)));
        Assert.assertTrue(results.contains(new NavigateTerrain(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 1, 2)));
    }

}
