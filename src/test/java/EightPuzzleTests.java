import com.knighten.ai.search.AStarNode;
import com.knighten.ai.search.EightPuzzle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.ArrayList;

public class EightPuzzleTests {

    private int[] puzzleBoardSolution;
    private int[] puzzleBoardNoZero;
    private int[] puzzleBoardInvalidLength;
    private int[] puzzleBoardReversedGoal;

    private EightPuzzle mockParentNode;
    private EightPuzzle mockGoalNode;
    private EightPuzzle mockNonEqualNode;

    private Object mockWrongClass;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {

        puzzleBoardSolution = new int[]{0,1,2,3,4,5,6,7,8};
        puzzleBoardNoZero = new int[]{1,2,3,4,5,6,7,8,10};
        puzzleBoardInvalidLength = new int[]{0};
        puzzleBoardReversedGoal = new int[]{8,7,6,5,4,3,2,1,0};

        mockParentNode = Mockito.mock(EightPuzzle.class);

        mockGoalNode = Mockito.mock(EightPuzzle.class);
        Mockito.when(mockGoalNode.getState())
                .thenReturn(puzzleBoardSolution);

        mockNonEqualNode = Mockito.mock(EightPuzzle.class);
        Mockito.when(mockNonEqualNode.getState())
                .thenReturn(puzzleBoardReversedGoal);

        mockWrongClass = Mockito.mock(Object.class);
    }


    //////////////////////////
    // Tests - Constructors //
    //////////////////////////

    @Test
    public void createNodeWithoutParent() throws Exception {
        EightPuzzle newPuzzle = new EightPuzzle(puzzleBoardSolution);

        Assert.assertArrayEquals(puzzleBoardSolution, newPuzzle.getState());
        Assert.assertEquals(null, newPuzzle.getParent());
    }

    @Test
    public void createNodeWithInvalidLength() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Puzzle board most have 9 spaces(Array length 9).");
        EightPuzzle faultyPuzzle = new EightPuzzle(puzzleBoardInvalidLength);
    }

    @Test
    public void createNodeWithoutEmptySpace() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Puzzle board most contain an empty space(0 must be in the array).");
        EightPuzzle faultyPuzzle = new EightPuzzle(puzzleBoardNoZero);
    }

    @Test
    public void createNodeWithParent() throws Exception {
        EightPuzzle newPuzzle = new EightPuzzle(puzzleBoardSolution, mockParentNode);

        Assert.assertArrayEquals(puzzleBoardSolution, newPuzzle.getState());
        Assert.assertEquals(mockParentNode, newPuzzle.getParent());
    }


    ////////////////////
    // Tests - Equals //
    ////////////////////

    @Test
    public void checkEqualityWithSameBoard() throws Exception {
        EightPuzzle newPuzzle = new EightPuzzle(puzzleBoardSolution);

        Assert.assertTrue(newPuzzle.equals(mockGoalNode));
    }

    @Test
    public void checkEqualityWithDifferentBoards() throws Exception {
        EightPuzzle newPuzzle = new EightPuzzle(puzzleBoardSolution);

        Assert.assertFalse(newPuzzle.equals(mockNonEqualNode));
    }

    @Test
    public void checkEqualityWithWrongClassNode() throws Exception {
        EightPuzzle newPuzzle = new EightPuzzle(puzzleBoardSolution);

        Assert.assertFalse(newPuzzle.equals(mockWrongClass));
    }


    /////////////////////////////////////
    // Tests - Successor Node Creation //
    /////////////////////////////////////

    @Test
    public void createSuccessorsWithMiddleEmpty() throws Exception{
        EightPuzzle parentPuzzle = new EightPuzzle(new int[]{1,2,3,4,0,5,6,7,8});
        ArrayList<AStarNode<int[]>> successors = parentPuzzle.getSuccessors();

        EightPuzzle successor1 =  new EightPuzzle(new int[]{1,2,3,0,4,5,6,7,8});
        EightPuzzle successor2 =  new EightPuzzle(new int[]{1,2,3,4,5,0,6,7,8});
        EightPuzzle successor3 =  new EightPuzzle(new int[]{1,0,3,4,2,5,6,7,8});
        EightPuzzle successor4 =  new EightPuzzle(new int[]{1,2,3,4,7,5,6,0,8});

        Assert.assertEquals(4, successors.size());
        Assert.assertTrue(successors.contains(successor1));
        Assert.assertTrue(successors.contains(successor2));
        Assert.assertTrue(successors.contains(successor3));
        Assert.assertTrue(successors.contains(successor4));
    }

    @Test
    public void createSuccessorsWithTopMiddleEmpty() throws Exception{
        EightPuzzle parentPuzzle = new EightPuzzle(new int[]{1,0,2,3,4,5,6,7,8});
        ArrayList<AStarNode<int[]>> successors = parentPuzzle.getSuccessors();

        EightPuzzle successor1 =  new EightPuzzle(new int[]{0,1,2,3,4,5,6,7,8});
        EightPuzzle successor2 =  new EightPuzzle(new int[]{1,2,0,3,4,5,6,7,8});
        EightPuzzle successor3 =  new EightPuzzle(new int[]{1,4,2,3,0,5,6,7,8});

        Assert.assertEquals(3, successors.size());
        Assert.assertTrue(successors.contains(successor1));
        Assert.assertTrue(successors.contains(successor2));
        Assert.assertTrue(successors.contains(successor3));
    }

    @Test
    public void createSuccessorsWithBottomMiddleEmpty() throws Exception{
        EightPuzzle parentPuzzle = new EightPuzzle(new int[]{1,2,3,4,5,6,7,0,8});
        ArrayList<AStarNode<int[]>> successors = parentPuzzle.getSuccessors();

        EightPuzzle successor1 =  new EightPuzzle(new int[]{1,2,3,4,5,6,0,7,8});
        EightPuzzle successor2 =  new EightPuzzle(new int[]{1,2,3,4,5,6,7,8,0});
        EightPuzzle successor3 =  new EightPuzzle(new int[]{1,2,3,4,0,6,7,5,8});

        Assert.assertEquals(3, successors.size());
        Assert.assertTrue(successors.contains(successor1));
        Assert.assertTrue(successors.contains(successor2));
        Assert.assertTrue(successors.contains(successor3));
    }

    @Test
    public void createSuccessorsWithLeftMiddleEmpty() throws Exception{
        EightPuzzle parentPuzzle = new EightPuzzle(new int[]{1,2,3,0,4,5,6,7,8});
        ArrayList<AStarNode<int[]>> successors = parentPuzzle.getSuccessors();

        EightPuzzle successor1 =  new EightPuzzle(new int[]{1,2,3,4,0,5,6,7,8});
        EightPuzzle successor2 =  new EightPuzzle(new int[]{0,2,3,1,4,5,6,7,8});
        EightPuzzle successor3 =  new EightPuzzle(new int[]{1,2,3,6,4,5,0,7,8});

        Assert.assertEquals(3, successors.size());
        Assert.assertTrue(successors.contains(successor1));
        Assert.assertTrue(successors.contains(successor2));
        Assert.assertTrue(successors.contains(successor3));
    }

    @Test
    public void createSuccessorsWithRightMiddleEmpty() throws Exception{
        EightPuzzle parentPuzzle = new EightPuzzle(new int[]{1,2,3,4,5,0,6,7,8});
        ArrayList<AStarNode<int[]>> successors = parentPuzzle.getSuccessors();

        EightPuzzle successor1 =  new EightPuzzle(new int[]{1,2,3,4,0,5,6,7,8});
        EightPuzzle successor2 =  new EightPuzzle(new int[]{1,2,0,4,5,3,6,7,8});
        EightPuzzle successor3 =  new EightPuzzle(new int[]{1,2,3,4,5,8,6,7,0});

        Assert.assertEquals(3, successors.size());
        Assert.assertTrue(successors.contains(successor1));
        Assert.assertTrue(successors.contains(successor2));
        Assert.assertTrue(successors.contains(successor3));
    }

    @Test
    public void createSuccessorsWithTopLeftEmpty() throws Exception{
        EightPuzzle parentPuzzle = new EightPuzzle(new int[]{0,1,2,3,4,5,6,7,8});
        ArrayList<AStarNode<int[]>> successors = parentPuzzle.getSuccessors();

        EightPuzzle successor1 =  new EightPuzzle(new int[]{1,0,2,3,4,5,6,7,8});
        EightPuzzle successor2 =  new EightPuzzle(new int[]{3,1,2,0,4,5,6,7,8});

        Assert.assertEquals(2, successors.size());
        Assert.assertTrue(successors.contains(successor1));
        Assert.assertTrue(successors.contains(successor2));
    }

    @Test
    public void createSuccessorsWithTopRightEmpty() throws Exception{
        EightPuzzle parentPuzzle = new EightPuzzle(new int[]{1,2,0,3,4,5,6,7,8});
        ArrayList<AStarNode<int[]>> successors = parentPuzzle.getSuccessors();

        EightPuzzle successor1 =  new EightPuzzle(new int[]{1,0,2,3,4,5,6,7,8});
        EightPuzzle successor2 =  new EightPuzzle(new int[]{1,2,5,3,4,0,6,7,8});

        Assert.assertEquals(2, successors.size());
        Assert.assertTrue(successors.contains(successor1));
        Assert.assertTrue(successors.contains(successor2));
    }

    @Test
    public void createSuccessorsWithBottomLeftEmpty() throws Exception{
        EightPuzzle parentPuzzle = new EightPuzzle(new int[]{1,2,3,4,5,6,0,7,8});
        ArrayList<AStarNode<int[]>> successors = parentPuzzle.getSuccessors();

        EightPuzzle successor1 =  new EightPuzzle(new int[]{1,2,3,4,5,6,7,0,8});
        EightPuzzle successor2 =  new EightPuzzle(new int[]{1,2,3,0,5,6,4,7,8});

        Assert.assertEquals(2, successors.size());
        Assert.assertTrue(successors.contains(successor1));
        Assert.assertTrue(successors.contains(successor2));
    }

    @Test
    public void createSuccessorsWithBottomRightEmpty() throws Exception{
        EightPuzzle parentPuzzle = new EightPuzzle(new int[]{1,2,3,4,5,6,7,8,0});
        ArrayList<AStarNode<int[]>> successors = parentPuzzle.getSuccessors();

        EightPuzzle successor1 =  new EightPuzzle(new int[]{1,2,3,4,5,6,7,0,8});
        EightPuzzle successor2 =  new EightPuzzle(new int[]{1,2,3,4,5,0,7,8,6});

        Assert.assertEquals(2, successors.size());
        Assert.assertTrue(successors.contains(successor1));
        Assert.assertTrue(successors.contains(successor2));
    }


    /////////////////////////
    // Tests - Calculate H //
    /////////////////////////

    @Test
    public void calcHForMatchWithGoalNode() throws Exception {
        EightPuzzle board = new EightPuzzle(puzzleBoardSolution);
        EightPuzzle goalBoard = new EightPuzzle(puzzleBoardSolution);
        Assert.assertEquals(0, board.calcH(goalBoard));
    }

    @Test
    public void calcHForNonGoalNodeBestCase() throws Exception {
        EightPuzzle board = new EightPuzzle(new int[]{1,0,2,3,4,5,6,7,8});
        EightPuzzle goalBoard = new EightPuzzle(puzzleBoardSolution);

        Assert.assertEquals(1, board.calcH(goalBoard));
    }

    @Test
    public void calcHForNonGoalNodeWorstCase() throws Exception {
        EightPuzzle board = new EightPuzzle(new int[]{8,7,6,5,4,3,2,1,0});
        EightPuzzle goalBoard = new EightPuzzle(puzzleBoardSolution);

        Assert.assertEquals(20, board.calcH(goalBoard));
    }

    @Test
    public void calcHForNonGoalNodeCase1() throws Exception {
        EightPuzzle board = new EightPuzzle(new int[]{1,2,0,3,4,5,6,7,8});
        EightPuzzle goalBoard = new EightPuzzle(puzzleBoardSolution);

        Assert.assertEquals(2, board.calcH(goalBoard));
    }

    @Test
    public void calcHForNonGoalNodeCase2() throws Exception {
        EightPuzzle board = new EightPuzzle(new int[]{1,2,3,4,5,0,6,7,8});
        EightPuzzle goalBoard = new EightPuzzle(puzzleBoardSolution);

        Assert.assertEquals(7, board.calcH(goalBoard));
    }

    @Test
    public void calcHForNonGoalNodeCase3() throws Exception {
        EightPuzzle board = new EightPuzzle(new int[]{6,3,2,0,8,1,4,5,7});
        EightPuzzle goalBoard = new EightPuzzle(puzzleBoardSolution);

        Assert.assertEquals(13, board.calcH(goalBoard));
    }


    ///////////////////
    // Tests -  Hash //
    ///////////////////

    @Test
    public void ensureHashCollidesWithSameBoard() throws Exception {
        EightPuzzle board = new EightPuzzle(puzzleBoardSolution);
        EightPuzzle sameBoard = new EightPuzzle(puzzleBoardSolution);

        Assert.assertEquals(board.hashCode(), sameBoard.hashCode());
    }


    ///////////////////////
    // Tests -  toString //
    ///////////////////////

    @Test
    public void ensureProperToString() throws Exception {
        EightPuzzle newPuzzle = new EightPuzzle(puzzleBoardSolution);

        Assert.assertEquals("* 1 2\n3 4 5\n6 7 8\n" , newPuzzle.toString());
    }

}
