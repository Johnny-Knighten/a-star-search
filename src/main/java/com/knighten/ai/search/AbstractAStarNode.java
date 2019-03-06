package com.knighten.ai.search;

import java.util.Comparator;
import java.util.List;

/**
 * Abstract class that is extended to create problems that are solvable by A* search variants.
 *
 * @param <T> the type of the node's stored state
 */
public abstract class AbstractAStarNode<T> implements Comparator<AbstractAStarNode<T>>{

    /**
     * Node's h() heuristic value.
     */
    private int hScore = Integer.MIN_VALUE;

    /**
     * Node's g() graph cost value.
     */
    private int gScore = 0;

    /**
     * Node's f() total score value. This is h() + g().
     */
    private int fScore = 0;

    /**
     * Node's parent.
     */
    private AbstractAStarNode<T> parent;

    /**
     * Node's state.
     */
    private T state;


    /**
     *  Returns the heuristic score h() for the node. You MUST call calcH() before attempting to retrieve the heuristic
     *  score, otherwise h() = Integer.MIN_VALUE.
     *
     * @return the heuristic score h() for the node
     */
    public int getH() {return this.hScore;}

    /**
     * Sets the node's heuristic score h(). Get h() from A IHeuristicFunction object then store the value using this
     * method.
     *
     * @param hScore the heuristic score h() of the node
     */
    public void setH(int hScore) {this.hScore = hScore;}

    /**
     * Returns the graph link score g(). The total path cost accumulated so far to reach current node.
     *
     * @return  returns graph link score g()
     */
    public int getG() {return this.gScore;}

    /**
     * Set the node's path cost g(). The total path cost accumulated so far to reach current node.
     *
     * @param  gScore the node's path cost g()
     */
    public void setG(int gScore) {this.gScore = gScore;}

    /**
     * Gets the node's parents. Initial nodes should have parent equal to NULL.
     *
     * @return returns the node's parent
     */
    public AbstractAStarNode getParent() {return this.parent;}

    /**
     * Sets the node's parent. Initial nodes should have parent equal to NULL.
     *
     * @param parent the node's parent
     */
    public void setParent(AbstractAStarNode parent) {this.parent = parent;}

    /**
     * Returns the f() value of the node. Note it does not do the f() calculation you must store it in the node using
     * setF().
     *
     * @return the f() value of the node
     */
    public int getF() {return this.fScore;}

    /**
     * Sets the node's f() value.
     *
     * @param fScore the f() value of the node
     */
    public void setF(int fScore) {this.fScore = fScore;}

    /**
     * Returns the node's state
     *
     * @return the node's state
     */
    public T getState() {return this.state;}

    /**
     * Sets the node's state.
     *
     * @param state the state of the node
     */
    public void setState(T state) {this.state = state;}

    /**
     * Compares two AStarNodes' f() values. Used in priority queue.
     *
     * @param node1 a AbstractAStarNode - treated as primary
     * @param node2 another AbstractAStarNode - treated as secondary
     * @return  negative if node1 LT node2; zero if node1==node2; positive if node1 GT node2;
     */
    @Override
    public int compare(AbstractAStarNode<T> node1, AbstractAStarNode<T> node2) {return node1.getF() - node2.getF();}

    //////////////////////
    // Abstract Methods //
    //////////////////////

    /**
     * Generates a list of successor nodes.
     *
     * @return list of successor nodes
     */
    public abstract List<AbstractAStarNode> getSuccessors();

    /**
     * Returns the distance from the node to its parent.
     *
     * @return returns distance to node's parent
     */
    public abstract int distFromParent();

    /**
     * Generates the hash code for the node. The hash should be based on node state. We want nodes with the same
     * state(not same f()) to collide. This enables determining if a node has been visited before but with lower f().
     *
     * @return hash code for node
     */
    public abstract int hashCode();

    /**
     * Determine if another node equals the current node. Equality(just like the hashCode()) should be determined by
     * node state. That is two nodes are equal if their states are the same(not f()).
     *
     * @param otherNode node to compare with
     * @return true if other node is equal, false otherwise
     */
    public abstract boolean equals(Object otherNode);

}
