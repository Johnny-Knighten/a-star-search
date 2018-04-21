import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.knighten.ai.search.AStarNode;
import org.mockito.Mockito;

public class AStarNodeTests {

    private AStarNode oneTestNode;
    private AStarNode negOneTestNode;
    private AStarNode zeroTestNode;

    @Before
    public void setup() {
        oneTestNode = Mockito.mock(AStarNode.class, Mockito.CALLS_REAL_METHODS);
        Mockito.when(oneTestNode.getF())
                .thenReturn(1);

        negOneTestNode = Mockito.mock(AStarNode.class, Mockito.CALLS_REAL_METHODS);
        Mockito.when(negOneTestNode.getF())
                .thenReturn(-1);

        zeroTestNode = Mockito.mock(AStarNode.class, Mockito.CALLS_REAL_METHODS);
        Mockito.when(zeroTestNode.getF())
                .thenReturn(0);
    }

    ///////////////////////////////////////////////////////
    // Tests - compare(AStarNode node1, AStarNode node2) //
    ///////////////////////////////////////////////////////

    @Test
    public void compareFirstNodeHigherFValueThatSecondNode() {
        Assert.assertEquals(1, oneTestNode.compare(oneTestNode, zeroTestNode));
    }

    @Test
    public void compareFirstNodeEqualFValueToSecondNode() {
        Assert.assertEquals(0, zeroTestNode.compare(zeroTestNode, zeroTestNode));
    }

    @Test
    public void compareFirstNodeLowerFValueThanSecondNode() {
        Assert.assertEquals(-1, negOneTestNode.compare(negOneTestNode, zeroTestNode));
    }

}
