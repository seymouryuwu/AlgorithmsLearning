package binaryTree;

/*
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/
 */
import dataStructure.TwoWayTreeNode;

import java.util.HashSet;
import java.util.Set;

public class LowestCommonAncestorOfABinaryTreeIII {
    public TwoWayTreeNode lowestCommonAncestor(TwoWayTreeNode p, TwoWayTreeNode q) {
        Set<TwoWayTreeNode> parentsForP = new HashSet<>();
        TwoWayTreeNode parentForP = p;
        while (parentForP != null) {
            parentsForP.add(parentForP);
            parentForP = parentForP.parent;
        }

        TwoWayTreeNode parentForQ = q;
        while (parentForQ != null) {
            if(parentsForP.contains(parentForQ)) {
                return parentForQ;
            }
            parentForQ = parentForQ.parent;
        }

        return null;
    }
}
