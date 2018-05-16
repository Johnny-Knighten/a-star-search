import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.knighten.ai.search.AStarNode;
import org.mockito.Mockito;

public class AStarNodeTests {

    private AStarNode testNodeWithF1;
    private AStarNode testNodeWithFNeg1;
    private AStarNode testNodeWithF0;

    @Before
    public void setup() {
        testNodeWithF1 = Mockito.mock(AStarNode.class, Mockito.CALLS_REAL_METHODS);
        Mockito.when(testNodeWithF1.getF())
                .thenReturn(1);

        testNodeWithFNeg1 = Mockito.mock(AStarNode.class, Mockito.CALLS_REAL_METHODS);
        Mockito.when(testNodeWithFNeg1.getF())
                .thenReturn(-1);

        testNodeWithF0 = Mockito.mock(AStarNode.class, Mockito.CALLS_REAL_METHODS);
        Mockito.when(testNodeWithF0.getF())
                .thenReturn(0);
    }

    ///////////////////////////////////////////////////////
    // Tests - compare(AStarNode node1, AStarNode node2) //
    ///////////////////////////////////////////////////////

    @Test
    public void compareFirstNodeHigherFValueThatSecondNode() {
        Assert.assertTrue(testNodeWithF1.compare(testNodeWithF1, testNodeWithF0) > 0);
    }

    @Test
    public void compareFirstNodeEqualFValueToSecondNode() {
        Assert.assertEquals(0, testNodeWithF0.compare(testNodeWithF0, testNodeWithF0));
    }

    @Test
    public void compareFirstNodeLowerFValueThanSecondNode() {
        Assert.assertTrue(testNodeWithFNeg1.compare(testNodeWithFNeg1, testNodeWithF0) < 0);
    }

}
