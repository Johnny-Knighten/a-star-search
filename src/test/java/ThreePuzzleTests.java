import com.knighten.ai.search.AStarNode;
import com.knighten.ai.search.ThreePuzzle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.ArrayList;

public class ThreePuzzleTests {

    private int[] puzzleBoardSolution;
    private int[] puzzleBoardNoZero;
    private int[] puzzleBoardInvalidLength;
    private int[] puzzleBoardReversedGoal;

    private ThreePuzzle mockParentNode;
    private ThreePuzzle mockGoalNode;
    private ThreePuzzle mockNonEqualNode;

    private Object mockWrongClass;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {

        puzzleBoardSolution = new int[]{0,1,2,3};
        puzzleBoardNoZero = new int[]{1,2,3,4};
        puzzleBoardInvalidLength = new int[]{0};
        puzzleBoardReversedGoal = new int[]{3,2,1,0};

        mockParentNode = Mockito.mock(ThreePuzzle.class);

        mockGoalNode = Mockito.mock(ThreePuzzle.class);
        Mockito.when(mockGoalNode.getState())
                .thenReturn(puzzleBoardSolution);

        mockNonEqualNode = Mockito.mock(ThreePuzzle.class);
        Mockito.when(mockNonEqualNode.getState())
                .thenReturn(puzzleBoardReversedGoal);

        mockWrongClass = Mockito.mock(Object.class);
    }


    //////////////////////////
    // Tests - Constructors //
    //////////////////////////

    @Test
    public void createNodeWithoutParent() throws Exception {
        ThreePuzzle newPuzzle = new ThreePuzzle(puzzleBoardSolution);

        Assert.assertArrayEquals(puzzleBoardSolution, newPuzzle.getState());
        Assert.assertEquals(null, newPuzzle.getParent());
    }

    @Test
    public void createNodeWithInvalidLength() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Puzzle board most have 4 spaces(Array length 4).");
        ThreePuzzle faultyPuzzle = new ThreePuzzle(puzzleBoardInvalidLength);
    }

    @Test
    public void createNodeWithoutEmptySpace() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Puzzle board most contain an empty space(0 must be in the array).");
        ThreePuzzle faultyPuzzle = new ThreePuzzle(puzzleBoardNoZero);
    }

    @Test
    public void createNodeWithParent() throws Exception {
        ThreePuzzle newPuzzle = new ThreePuzzle(puzzleBoardSolution, mockParentNode);

        Assert.assertArrayEquals(puzzleBoardSolution, newPuzzle.getState());
        Assert.assertEquals(mockParentNode, newPuzzle.getParent());
    }


    ////////////////////
    // Tests - Equals //
    ////////////////////

    @Test
    public void checkEqualityWithSameBoard() throws Exception {
        ThreePuzzle newPuzzle = new ThreePuzzle(puzzleBoardSolution);

        Assert.assertTrue(newPuzzle.equals(mockGoalNode));
    }

    @Test
    public void checkEqualityWithDifferentBoards() throws Exception {
        ThreePuzzle newPuzzle = new ThreePuzzle(puzzleBoardSolution);

        Assert.assertFalse(newPuzzle.equals(mockNonEqualNode));
    }

    @Test
    public void checkEqualityWithWrongClassNode() throws Exception {
        ThreePuzzle newPuzzle = new ThreePuzzle(puzzleBoardSolution);

        Assert.assertFalse(newPuzzle.equals(mockWrongClass));
    }


    /////////////////////////////////////
    // Tests - Successor Node Creation //
    /////////////////////////////////////

    @Test
    public void createSuccessorsWithTopLeftEmpty() throws Exception{
        ThreePuzzle parentPuzzle = new ThreePuzzle(new int[]{0,1,2,3});
        ArrayList<AStarNode> successors = parentPuzzle.getSuccessors();

        ThreePuzzle successor1 =  new ThreePuzzle(new int[]{1,0,2,3});
        ThreePuzzle successor2 =  new ThreePuzzle(new int[]{2,1,0,3});

        Assert.assertEquals(2, successors.size());
        Assert.assertTrue(successors.contains(successor1));
        Assert.assertTrue(successors.contains(successor2));
    }

    @Test
    public void createSuccessorsWithTopRightEmpty() throws Exception {
        ThreePuzzle parentPuzzle = new ThreePuzzle(new int[]{1,0,2,3});
        ArrayList<AStarNode> successors = parentPuzzle.getSuccessors();

        ThreePuzzle successor1 =  new ThreePuzzle(new int[]{0,1,2,3});
        ThreePuzzle successor2 =  new ThreePuzzle(new int[]{1,3,2,0});

        Assert.assertEquals(2, successors.size());
        Assert.assertTrue(successors.contains(successor1));
        Assert.assertTrue(successors.contains(successor2));
    }

    @Test
    public void createSuccessorsWithBottomLeftEmpty() throws Exception{
        ThreePuzzle parentPuzzle = new ThreePuzzle(new int[]{1,2,0,3});
        ArrayList<AStarNode> successors = parentPuzzle.getSuccessors();

        ThreePuzzle successor1 =  new ThreePuzzle(new int[]{0,2,1,3});
        ThreePuzzle successor2 =  new ThreePuzzle(new int[]{1,2,3,0});

        Assert.assertEquals(2, successors.size());
        Assert.assertTrue(successors.contains(successor1));
        Assert.assertTrue(successors.contains(successor2));
    }

    @Test
    public void createSuccessorsWithBottomRightEmpty() throws Exception{
        ThreePuzzle parentPuzzle = new ThreePuzzle(new int[]{1,2,3,0});
        ArrayList<AStarNode> successors = parentPuzzle.getSuccessors();

        ThreePuzzle successor1 =  new ThreePuzzle(new int[]{1,2,0,3});
        ThreePuzzle successor2 =  new ThreePuzzle(new int[]{1,0,3,2});

        Assert.assertEquals(2, successors.size());
        Assert.assertTrue(successors.contains(successor1));
        Assert.assertTrue(successors.contains(successor2));
    }


    /////////////////////////
    // Tests - Calculate H //
    /////////////////////////

    @Test
    public void calcHForMatchWithGoalNode() throws Exception {
        ThreePuzzle board = new ThreePuzzle(puzzleBoardSolution);
        ThreePuzzle goalBoard = new ThreePuzzle(puzzleBoardSolution);
        Assert.assertEquals(0, board.calcH(goalBoard));
    }

    @Test
    public void calcHForNonGoalNodeBestCase() throws Exception {
        ThreePuzzle board = new ThreePuzzle(new int[]{1,0,2,3});
        ThreePuzzle goalBoard = new ThreePuzzle(puzzleBoardSolution);

        Assert.assertEquals(1, board.calcH(goalBoard));
    }

    @Test
    public void calcHForNonGoalNodeWorstCase() throws Exception {
        ThreePuzzle board = new ThreePuzzle(new int[]{3,2,1,0});
        ThreePuzzle goalBoard = new ThreePuzzle(puzzleBoardSolution);

        Assert.assertEquals(6, board.calcH(goalBoard));
    }

    ///////////////////
    // Tests -  Hash //
    ///////////////////

    @Test
    public void ensureHashCollidesWithSameBoard() throws Exception {
        ThreePuzzle board = new ThreePuzzle(puzzleBoardSolution);
        ThreePuzzle sameBoard = new ThreePuzzle(puzzleBoardSolution);

        Assert.assertEquals(board.hashCode(), sameBoard.hashCode());
    }


    ///////////////////////
    // Tests -  toString //
    ///////////////////////

    @Test
    public void ensureProperToString() throws Exception {
        ThreePuzzle newPuzzle = new ThreePuzzle(puzzleBoardSolution);

        Assert.assertEquals("* 1\n2 3\n" , newPuzzle.toString());
    }

}
