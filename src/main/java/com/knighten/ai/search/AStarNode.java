package com.knighten.ai.search;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Abstract class that is extended to create problems that are solvable by A* search variants.
 *
 * @param <T> the type of the node's stored state
 */
public abstract class AStarNode<T> implements Comparator<AStarNode<T>>{

    /**
     * Node's h() value.
     */
    private int hScore = Integer.MIN_VALUE;

    /**
     * Node's g() value.
     */
    private int gScore = 0;

    /**
     * Node's f() value.
     */
    private int fScore = 0;

    /**
     * Node's parent.
     */
    private AStarNode<T> parent;

    /**
     * Node's state.
     */
    private T state;


    /**
     *  Returns the heuristic score h() for the node. You MUST call calcH() before
     *  attempting to retrieve the heuristic score, otherwise h() = Integer.MIN_VALUE.
     *
     * @return the heuristic score h() for the node
     */
    public int getH() {return this.hScore;}

    /**
     * Sets the node's heuristic score h(). Get h() from calcH() then store value
     * using this method.
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
    public AStarNode getParent() {return this.parent;}

    /**
     * Sets the node's parent. Initial nodes should have parent equal to NULL.
     *
     * @param parent the node's parent
     */
    public void setParent(AStarNode parent) {this.parent = parent;}

    /**
     * Returns the f() value of the node. Note it does not do the f() calculation
     * you must store it in the node using setF().
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
     * @param node1 a AStarNode - treated as primary
     * @param node2 another AStarNode - treated as secondary
     * @return  negative if node1 LT node2; zero if node1==node2; positive if node1 GT node2;
     */
    @Override
    public int compare(AStarNode<T> node1, AStarNode<T> node2) {return node1.getF() - node2.getF();}


    //////////////////////
    // Abstract Methods //
    //////////////////////

    /**
     * Generates a list of successor nodes.
     *
     * @return list of successor nodes
     */
    public abstract ArrayList<AStarNode> getSuccessors() throws Exception;

    /**
     * Returns the distance from the node to its parent.
     *
     * @return returns distance to node's parent
     */
    public abstract int distFromParent();

    /**
     * Calculates the heuristic score h() between the node and a goal node.
     *
     * @param goalNode goal node of search
     * @return heuristic score h() for node
     */
    public abstract int calcH(AStarNode<T> goalNode);

    /**
     * Generates the hash code for the node. The hash should be based on node state. We want nodes with the same
     * state(not same f()) to collide. This allows for determining if a node has been visited before but with lower f().
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
