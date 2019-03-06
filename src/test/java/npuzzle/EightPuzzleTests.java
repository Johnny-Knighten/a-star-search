package npuzzle;

import com.knighten.ai.search.AbstractAStarNode;
import com.knighten.ai.search.npuzzle.EightPuzzle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class EightPuzzleTests {

    private int[] puzzleBoardSolution;
    private int[] puzzleBoardNoZero;
    private int[] puzzleBoardInvalidLength;
    private int[] middlEmpty;
    private int[] middleLeftEmpty;
    private int[] middleRightEmpty;
    private int[] topMiddleEmpty;
    private int[] topLeftEmpty;
    private int[] topRightEmpty;
    private int[] bottomMiddleEmpty;
    private int[] bottomLeftEmpty;
    private int[] bottomRightEmpty;
    private EightPuzzle mockParentNode;

    @Before
    public void setup() {

        puzzleBoardSolution = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
        puzzleBoardNoZero = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 10};
        puzzleBoardInvalidLength = new int[]{0};

        mockParentNode = Mockito.mock(EightPuzzle.class);

        middlEmpty = new int[]{1, 2, 3, 4, 0, 5, 6, 7, 8};
        middleLeftEmpty = new int[]{1, 2, 3, 0, 4, 5, 6, 7, 8};
        middleRightEmpty = new int[]{1, 2, 3, 4, 5, 0, 6, 7, 8};
        topMiddleEmpty = new int[]{1, 0, 2, 3, 4, 5, 6, 7, 8};
        topLeftEmpty = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
        topRightEmpty = new int[]{1, 2, 0, 3, 4, 5, 6, 7, 8};
        bottomMiddleEmpty = new int[]{1, 2, 3, 4, 5, 6, 7, 0, 8};
        bottomLeftEmpty = new int[]{1, 2, 3, 4, 5, 6, 0, 7, 8};
        bottomRightEmpty = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0};
    }

    ////////////////////////
    // Parameter Checking //
    ////////////////////////

    @Test(expected = IllegalArgumentException.class)
    public void constructorInvalidBoardLength() {
        new EightPuzzle(puzzleBoardInvalidLength);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorBoardDoesNotContainEmptySpace() {
        new EightPuzzle(puzzleBoardNoZero);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorNullBoard() {
        new EightPuzzle(null);
    }

    ////////////////////
    // Method Testing //
    ////////////////////

    @Test
    public void constructorNoParentNode() throws Exception {
        EightPuzzle newPuzzle = new EightPuzzle(puzzleBoardSolution);

        Assert.assertArrayEquals(puzzleBoardSolution, newPuzzle.getState());
        Assert.assertEquals(null, newPuzzle.getParent());
    }

    @Test
    public void createNodeWithParentNode() throws Exception {
        EightPuzzle newPuzzle = new EightPuzzle(puzzleBoardSolution, mockParentNode);

        Assert.assertArrayEquals(puzzleBoardSolution, newPuzzle.getState());
        Assert.assertEquals(mockParentNode, newPuzzle.getParent());
    }

    @Test
    public void createSuccessorsWithMiddleEmpty() throws Exception{
        EightPuzzle parentPuzzle = new EightPuzzle(middlEmpty);
        ArrayList<AbstractAStarNode> successors = parentPuzzle.getSuccessors();

        Assert.assertEquals(4, successors.size());
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{1, 2, 3, 0, 4, 5, 6, 7, 8})));
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{1, 2, 3, 4, 5, 0, 6, 7, 8})));
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{1, 0, 3, 4, 2, 5, 6, 7, 8})));
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{1, 2, 3, 4, 7, 5, 6, 0, 8})));
    }

    @Test
    public void createSuccessorsWithMiddleLeftEmpty() throws Exception{
        EightPuzzle parentPuzzle = new EightPuzzle(middleLeftEmpty);
        ArrayList<AbstractAStarNode> successors = parentPuzzle.getSuccessors();

        Assert.assertEquals(3, successors.size());
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{1, 2, 3, 4, 0, 5, 6, 7, 8})));
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{0, 2, 3, 1, 4, 5, 6, 7, 8})));
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{1, 2, 3, 6, 4, 5, 0, 7, 8})));
    }

    @Test
    public void createSuccessorsWithMiddleRightEmpty() throws Exception{
        EightPuzzle parentPuzzle = new EightPuzzle(middleRightEmpty);
        ArrayList<AbstractAStarNode> successors = parentPuzzle.getSuccessors();

        Assert.assertEquals(3, successors.size());
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{1, 2, 3, 4, 0, 5, 6, 7, 8})));
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{1, 2, 0, 4, 5, 3, 6, 7, 8})));
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{1, 2, 3, 4, 5, 8, 6, 7, 0})));
    }

    @Test
    public void createSuccessorsWithTopMiddleEmpty() throws Exception{
        EightPuzzle parentPuzzle = new EightPuzzle(topMiddleEmpty);
        ArrayList<AbstractAStarNode> successors = parentPuzzle.getSuccessors();

        Assert.assertEquals(3, successors.size());
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8})));
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{1, 2, 0, 3, 4, 5, 6, 7, 8})));
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{1, 4, 2, 3, 0, 5, 6, 7, 8})));
    }

    @Test
    public void createSuccessorsWithTopLeftEmpty() throws Exception{
        EightPuzzle parentPuzzle = new EightPuzzle(topLeftEmpty);
        ArrayList<AbstractAStarNode> successors = parentPuzzle.getSuccessors();

        Assert.assertEquals(2, successors.size());
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{1, 0, 2, 3, 4, 5, 6, 7, 8})));
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{3, 1, 2, 0, 4, 5, 6, 7, 8})));
    }

    @Test
    public void createSuccessorsWithTopRightEmpty() throws Exception{
        EightPuzzle parentPuzzle = new EightPuzzle(topRightEmpty);
        ArrayList<AbstractAStarNode> successors = parentPuzzle.getSuccessors();

        Assert.assertEquals(2, successors.size());
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{1, 0, 2, 3, 4, 5, 6, 7, 8})));
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{1, 2, 5, 3, 4, 0, 6, 7, 8})));
    }

    @Test
    public void createSuccessorsWithBottomMiddleEmpty() throws Exception{
        EightPuzzle parentPuzzle = new EightPuzzle(bottomMiddleEmpty);
        ArrayList<AbstractAStarNode> successors = parentPuzzle.getSuccessors();

        Assert.assertEquals(3, successors.size());
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{1, 2, 3, 4, 5, 6, 0, 7, 8})));
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0})));
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{1, 2, 3, 4, 0, 6, 7, 5, 8})));
    }

    @Test
    public void createSuccessorsWithBottomLeftEmpty() throws Exception{
        EightPuzzle parentPuzzle = new EightPuzzle(bottomLeftEmpty);
        ArrayList<AbstractAStarNode> successors = parentPuzzle.getSuccessors();

        Assert.assertEquals(2, successors.size());
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{1, 2, 3, 4, 5, 6, 7, 0, 8})));
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{1, 2, 3, 0, 5, 6, 4, 7, 8})));
    }

    @Test
    public void createSuccessorsWithBottomRightEmpty() throws Exception{
        EightPuzzle parentPuzzle = new EightPuzzle(bottomRightEmpty);
        ArrayList<AbstractAStarNode> successors = parentPuzzle.getSuccessors();

        Assert.assertEquals(2, successors.size());
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{1, 2, 3, 4, 5, 6, 7, 0, 8})));
        Assert.assertTrue(successors.contains(new EightPuzzle(new int[]{1, 2, 3, 4, 5, 0, 7, 8, 6})));
    }

}
