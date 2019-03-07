package navigation;

import com.knighten.ai.search.AbstractAStarNode;
import com.knighten.ai.search.navigation.NavigateMaze;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

public class NavigateMazeTests {

    private NavigateMaze mockParentNode;

    @Before
    public void setup() {
        mockParentNode = Mockito.mock(NavigateMaze.class);
    }

    ////////////////////////
    // Parameter Checking //
    ////////////////////////

    @Test(expected = IllegalArgumentException.class)
    public void constructorMazeIsNull() {
        new NavigateMaze(null, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorRowPositionNotInMazeSmallerThanZero() {
        new NavigateMaze(new int[][]{{0, 0}, {1, 1}}, -1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorRowPositionNotInMazeGreaterThanNumberOfRows() {
        new NavigateMaze(new int[][]{{0, 0}, {1, 1}}, 10, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorColumnPositionNotInMazeSmallerThanZero() {
        new NavigateMaze(new int[][]{{0, 0}, {1, 1}}, 0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorColumnPositionNotInMazeGreaterThanNumberOfColumns() {
        new NavigateMaze(new int[][]{{0, 0}, {1, 1}}, 0, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMazeWithNon0Or1() {
        new NavigateMaze(new int[][]{{0, 0}, {1, 9}}, 0, 0);
    }

    /////////////////////
    // Method Checking //
    /////////////////////

    @Test
    public void constructorNoParentNode() {
        NavigateMaze testObject = new NavigateMaze(new int[][]{{0, 0}, {1, 1}}, 0, 0);

        Assert.assertArrayEquals(new int[]{0, 0}, testObject.getState());
        Assert.assertNull(testObject.getParent());
    }

    @Test
    public void createNodeWithParentNode() {
        NavigateMaze testObject = new NavigateMaze(new int[][]{{0, 0}, {1, 1}}, 0, 0, mockParentNode);

        Assert.assertArrayEquals(new int[]{0, 0}, testObject.getState());
        Assert.assertEquals(mockParentNode, testObject.getParent());
    }

    @Test
    public void distFromParentIsOne() {
        NavigateMaze testObject = new NavigateMaze(new int[][]{{0, 0}, {1, 1}}, 0, 0);

        Assert.assertEquals(1.0, testObject.distFromParent(), .00001);
    }

    @Test
    public void createSuccessorsWithNoPossibleMoves() {
        NavigateMaze testObject = new NavigateMaze(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}, 1, 1);
        List<AbstractAStarNode> results = testObject.getSuccessors();

        Assert.assertEquals(0, results.size());
    }

    @Test
    public void createSuccessorsWithOnePossibleMove() {
        NavigateMaze testObject = new NavigateMaze(new int[][]{{0, 1, 0}, {0, 1, 0}, {0, 0, 0}}, 1, 1);
        List<AbstractAStarNode> results = testObject.getSuccessors();

        Assert.assertEquals(1, results.size());
        Assert.assertTrue(results.contains(new NavigateMaze(new int[][]{{0, 1, 0}, {0, 1, 0}, {0, 0, 0}}, 0, 1)));
    }

    @Test
    public void createSuccessorsWithTwoPossibleMoves() {
        NavigateMaze testObject = new NavigateMaze(new int[][]{{0, 1, 0}, {0, 1, 0}, {0, 1, 0}}, 1, 1);
        List<AbstractAStarNode> results = testObject.getSuccessors();

        Assert.assertEquals(2, results.size());
        Assert.assertTrue(results.contains(new NavigateMaze(new int[][]{{0, 1, 0}, {0, 1, 0}, {0, 1, 0}}, 0, 1)));
        Assert.assertTrue(results.contains(new NavigateMaze(new int[][]{{0, 1, 0}, {0, 1, 0}, {0, 1, 0}}, 2, 1)));
    }

    @Test
    public void createSuccessorsWithThreePossibleMoves() {
        NavigateMaze testObject = new NavigateMaze(new int[][]{{0, 1, 0}, {1, 1, 0}, {0, 1, 0}}, 1, 1);
        List<AbstractAStarNode> results = testObject.getSuccessors();

        Assert.assertEquals(3, results.size());
        Assert.assertTrue(results.contains(new NavigateMaze(new int[][]{{0, 1, 0}, {1, 1, 0}, {0, 1, 0}}, 0, 1)));
        Assert.assertTrue(results.contains(new NavigateMaze(new int[][]{{0, 1, 0}, {1, 1, 0}, {0, 1, 0}}, 2, 1)));
        Assert.assertTrue(results.contains(new NavigateMaze(new int[][]{{0, 1, 0}, {1, 1, 0}, {0, 1, 0}}, 1, 0)));
    }

    @Test
    public void createSuccessorsWithFourPossibleMoves() {
        NavigateMaze testObject = new NavigateMaze(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 1, 1);
        List<AbstractAStarNode> results = testObject.getSuccessors();

        Assert.assertEquals(4, results.size());
        Assert.assertTrue(results.contains(new NavigateMaze(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 0, 1)));
        Assert.assertTrue(results.contains(new NavigateMaze(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 2, 1)));
        Assert.assertTrue(results.contains(new NavigateMaze(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 1, 0)));
        Assert.assertTrue(results.contains(new NavigateMaze(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 1, 2)));
    }

}
