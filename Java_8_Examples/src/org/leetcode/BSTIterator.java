package org.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/*
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.*/


public class BSTIterator 
{
	TreeNode root = null;
	TreeNode[] nodesinOrder = null;
	int nextIndex = 0;
	
	
    public BSTIterator(TreeNode root) {
    	this.root = root;
    	
    	List<TreeNode> list = new LinkedList<>();
    	DFS(root, list);
    	nodesinOrder = list.toArray(new TreeNode[] {});
    	
    }
    
    public void DFS(TreeNode node, List<TreeNode> list)
    {
    	if(node==null)
    		return;

    	DFS(node.left, list);
    	list.add(node);
    	DFS(node.right, list);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
		return nextIndex<nodesinOrder.length;
        
    }

    /** @return the next smallest number */
    public int next() {
		return nodesinOrder[nextIndex++].val;
    }
    
}
class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */