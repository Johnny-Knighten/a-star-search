package navigation;

import com.knighten.ai.search.navigation.AbstractNavigate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AbstractNavigateTests {

    private AbstractNavigate mockAbstractNavigateGoal;
    private AbstractNavigate mockAbstractNavigateSearch;

    @Before
    public void setup() {
        mockAbstractNavigateGoal = Mockito.mock(AbstractNavigate.class, Mockito.CALLS_REAL_METHODS);
        Mockito.when(mockAbstractNavigateGoal.getNumberOfRows()).thenReturn(2);
        Mockito.when(mockAbstractNavigateGoal.getNumberOfCols()).thenReturn(2);
        Mockito.when(mockAbstractNavigateGoal.getState()).thenReturn(new int[]{0, 0});
        Mockito.when(mockAbstractNavigateGoal.getEnvironment()).thenReturn(new int[][]{{1, 1}, {0, 0}});

        mockAbstractNavigateSearch = Mockito.mock(AbstractNavigate.class, Mockito.CALLS_REAL_METHODS);
    }

    ////////////////////
    // Method Testing //
    ////////////////////

    @Test
    public void equalsWithSameAbstractNavigate() {
        Assert.assertEquals(mockAbstractNavigateGoal, mockAbstractNavigateGoal);
    }

    @Test
    public void equalsWithDifferentAbstractNavigate() {
        Assert.assertNotEquals(mockAbstractNavigateGoal, mockAbstractNavigateSearch);
    }

    @Test
    public void equalsWithInvalidClass() {
        Assert.assertNotEquals(null, mockAbstractNavigateGoal);
    }

    @Test
    public void hashCodeMatchesEqualsWithSameAbstractNavigate() {

        boolean equalsValue = mockAbstractNavigateGoal.equals(mockAbstractNavigateGoal);
        boolean hashEqual = mockAbstractNavigateGoal.hashCode() == mockAbstractNavigateGoal.hashCode();

        Assert.assertTrue(equalsValue == hashEqual);
    }

    @Test
    public void hashCodeMatchesEqualsWithDifferentAbstractNavigate() {

        boolean equalsValue = mockAbstractNavigateGoal.equals(mockAbstractNavigateSearch);
        boolean hashEqual = mockAbstractNavigateGoal.hashCode() == mockAbstractNavigateSearch.hashCode();

        Assert.assertTrue(equalsValue == hashEqual);
    }

    @Test
    public void toStringIsValid() {
        Assert.assertEquals("* 1 \n0 0 ", mockAbstractNavigateGoal.toString());
    }

}
