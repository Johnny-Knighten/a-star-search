package npuzzle;

import com.knighten.ai.search.npuzzle.AbstractNPuzzle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AbstractNPuzzleTests {

    private AbstractNPuzzle mockPuzzleStateIsGoal;
    private AbstractNPuzzle getMockPuzzleStateIsGoalReversed;

    @Before
    public void setup() {
        mockPuzzleStateIsGoal = Mockito.mock(AbstractNPuzzle.class, Mockito.CALLS_REAL_METHODS);
        Mockito.when(mockPuzzleStateIsGoal.getState()).thenReturn(new int[]{0, 1, 2, 3});

        getMockPuzzleStateIsGoalReversed = Mockito.mock(AbstractNPuzzle.class, Mockito.CALLS_REAL_METHODS);
        Mockito.when(getMockPuzzleStateIsGoalReversed.getState()).thenReturn(new int[]{3, 2, 1, 0});
    }

    ////////////////////////
    // Parameter Checking //
    ////////////////////////

    @Test(expected = IllegalArgumentException.class)
    public void setEmptySpaceLocationSpaceLessThanZero() {
        mockPuzzleStateIsGoal.setEmptySpaceLocation(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setEmptySpaceLocationSpaceGreaterThanN() {
        mockPuzzleStateIsGoal.setEmptySpaceLocation(8);
    }


    ////////////////////
    // Method Testing //
    ////////////////////

    @Test
    public void equalsWithSamePuzzle() {
        Assert.assertTrue(mockPuzzleStateIsGoal.equals(mockPuzzleStateIsGoal));
    }

    @Test
    public void equalsWithDifferentPuzzle() {
        Assert.assertFalse(mockPuzzleStateIsGoal.equals(getMockPuzzleStateIsGoalReversed));
    }

    @Test
    public void equalsWithInvalidClass() {

        Assert.assertFalse(mockPuzzleStateIsGoal.equals(null));
    }

    @Test
    public void hashCodeMatchesEqualsWithSamePuzzle() {

        boolean equalsValue = mockPuzzleStateIsGoal.equals(mockPuzzleStateIsGoal);
        boolean hashEqual = mockPuzzleStateIsGoal.hashCode() == mockPuzzleStateIsGoal.hashCode();

        Assert.assertTrue(equalsValue == hashEqual);
    }

    @Test
    public void hashCodeMatchesEqualsWithDifferentPuzzle() {

        boolean equalsValue = mockPuzzleStateIsGoal.equals(getMockPuzzleStateIsGoalReversed);
        boolean hashEqual = mockPuzzleStateIsGoal.hashCode() == getMockPuzzleStateIsGoalReversed.hashCode();

        Assert.assertTrue(equalsValue == hashEqual);
    }

    @Test
    public void toStringIsValid() {
        Assert.assertEquals("* 1\n2 3\n", mockPuzzleStateIsGoal.toString());
        Assert.assertEquals("3 2\n1 *\n", getMockPuzzleStateIsGoalReversed.toString());
    }

}
