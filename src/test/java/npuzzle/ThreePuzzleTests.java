package npuzzle;

import com.knighten.ai.search.AbstractAStarNode;
import com.knighten.ai.search.npuzzle.ThreePuzzle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class ThreePuzzleTests {

    private int[] puzzleBoardSolution;
    private int[] puzzleBoardNoZero;
    private int[] puzzleBoardInvalidLength;
    private int[] topLeftEmpty;
    private int[] topRightEmpty;
    private int[] bottomLeftEmpty;
    private int[] bottomRightEmpty;
    private ThreePuzzle mockParentNode;

    @Before
    public void setup() {

        puzzleBoardSolution = new int[]{0, 1, 2, 3};
        puzzleBoardNoZero = new int[]{1, 2, 3, 4};
        puzzleBoardInvalidLength = new int[]{0};
        topLeftEmpty = new int[]{0, 1, 2, 3};
        topRightEmpty = new int[]{1, 0, 2, 3};
        bottomLeftEmpty = new int[]{1, 2, 0, 3};
        bottomRightEmpty = new int[]{1, 2, 3, 0};

        mockParentNode = Mockito.mock(ThreePuzzle.class);
    }

    ////////////////////////
    // Parameter Checking //
    ////////////////////////

    @Test(expected = IllegalArgumentException.class)
    public void constructorInvalidBoardLength() {
        new ThreePuzzle(puzzleBoardInvalidLength);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorBoardDoesNotContainEmptySpace() {
        new ThreePuzzle(puzzleBoardNoZero);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorNullBoard() {
        new ThreePuzzle(null);
    }

    ////////////////////
    // Method Testing //
    ////////////////////

    @Test
    public void constructorNoParentNode() {
        ThreePuzzle newPuzzle = new ThreePuzzle(puzzleBoardSolution);

        Assert.assertArrayEquals(puzzleBoardSolution, newPuzzle.getState());
        Assert.assertEquals(null, newPuzzle.getParent());
    }

    @Test
    public void createNodeWithParentNode() {
        ThreePuzzle newPuzzle = new ThreePuzzle(puzzleBoardSolution, mockParentNode);

        Assert.assertArrayEquals(puzzleBoardSolution, newPuzzle.getState());
        Assert.assertEquals(mockParentNode, newPuzzle.getParent());
    }

    @Test
    public void createSuccessorsWithTopLeftEmpty() {
        ThreePuzzle parentPuzzle = new ThreePuzzle(topLeftEmpty);
        ArrayList<AbstractAStarNode> successors = parentPuzzle.getSuccessors();

        Assert.assertEquals(2, successors.size());
        Assert.assertTrue(successors.contains(new ThreePuzzle(new int[]{1, 0, 2, 3})));
        Assert.assertTrue(successors.contains(new ThreePuzzle(new int[]{2, 1, 0, 3})));
    }

    @Test
    public void createSuccessorsWithTopRightEmpty() {
        ThreePuzzle parentPuzzle = new ThreePuzzle(topRightEmpty);
        ArrayList<AbstractAStarNode> successors = parentPuzzle.getSuccessors();

        Assert.assertEquals(2, successors.size());
        Assert.assertTrue(successors.contains(new ThreePuzzle(new int[]{0, 1, 2, 3})));
        Assert.assertTrue(successors.contains(new ThreePuzzle(new int[]{1, 3, 2, 0})));
    }

    @Test
    public void createSuccessorsWithBottomLeftEmpty() {
        ThreePuzzle parentPuzzle = new ThreePuzzle(bottomLeftEmpty);
        ArrayList<AbstractAStarNode> successors = parentPuzzle.getSuccessors();

        Assert.assertEquals(2, successors.size());
        Assert.assertTrue(successors.contains(new ThreePuzzle(new int[]{0, 2, 1, 3})));
        Assert.assertTrue(successors.contains(new ThreePuzzle(new int[]{1, 2, 3, 0})));
    }

    @Test
    public void createSuccessorsWithBottomRightEmpty() {
        ThreePuzzle parentPuzzle = new ThreePuzzle(bottomRightEmpty);
        ArrayList<AbstractAStarNode> successors = parentPuzzle.getSuccessors();

        Assert.assertEquals(2, successors.size());
        Assert.assertTrue(successors.contains(new ThreePuzzle(new int[]{1, 2, 0, 3})));
        Assert.assertTrue(successors.contains(new ThreePuzzle(new int[]{1, 0, 3, 2})));
    }

}
