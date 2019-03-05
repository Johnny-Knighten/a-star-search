package npuzzle;

import com.knighten.ai.search.AbstractAStarNode;
import com.knighten.ai.search.npuzzle.ThreePuzzle;
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

    /////////////////////////////////////
    // Tests - Successor Node Creation //
    /////////////////////////////////////

    @Test
    public void createSuccessorsWithTopLeftEmpty() throws Exception{
        ThreePuzzle parentPuzzle = new ThreePuzzle(new int[]{0,1,2,3});
        ArrayList<AbstractAStarNode> successors = parentPuzzle.getSuccessors();

        ThreePuzzle successor1 =  new ThreePuzzle(new int[]{1,0,2,3});
        ThreePuzzle successor2 =  new ThreePuzzle(new int[]{2,1,0,3});

        Assert.assertEquals(2, successors.size());
        Assert.assertTrue(successors.contains(successor1));
        Assert.assertTrue(successors.contains(successor2));
    }

    @Test
    public void createSuccessorsWithTopRightEmpty() throws Exception {
        ThreePuzzle parentPuzzle = new ThreePuzzle(new int[]{1,0,2,3});
        ArrayList<AbstractAStarNode> successors = parentPuzzle.getSuccessors();

        ThreePuzzle successor1 =  new ThreePuzzle(new int[]{0,1,2,3});
        ThreePuzzle successor2 =  new ThreePuzzle(new int[]{1,3,2,0});

        Assert.assertEquals(2, successors.size());
        Assert.assertTrue(successors.contains(successor1));
        Assert.assertTrue(successors.contains(successor2));
    }

    @Test
    public void createSuccessorsWithBottomLeftEmpty() throws Exception{
        ThreePuzzle parentPuzzle = new ThreePuzzle(new int[]{1,2,0,3});
        ArrayList<AbstractAStarNode> successors = parentPuzzle.getSuccessors();

        ThreePuzzle successor1 =  new ThreePuzzle(new int[]{0,2,1,3});
        ThreePuzzle successor2 =  new ThreePuzzle(new int[]{1,2,3,0});

        Assert.assertEquals(2, successors.size());
        Assert.assertTrue(successors.contains(successor1));
        Assert.assertTrue(successors.contains(successor2));
    }

    @Test
    public void createSuccessorsWithBottomRightEmpty() throws Exception{
        ThreePuzzle parentPuzzle = new ThreePuzzle(new int[]{1,2,3,0});
        ArrayList<AbstractAStarNode> successors = parentPuzzle.getSuccessors();

        ThreePuzzle successor1 =  new ThreePuzzle(new int[]{1,2,0,3});
        ThreePuzzle successor2 =  new ThreePuzzle(new int[]{1,0,3,2});

        Assert.assertEquals(2, successors.size());
        Assert.assertTrue(successors.contains(successor1));
        Assert.assertTrue(successors.contains(successor2));
    }


}
