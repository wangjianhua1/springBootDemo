package com.example.demo.Thread;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    public int sumOfLeftLeaves(TreeNode root) {
        return getLeft(root.left)+getByRight(root.right);
    }

    public int getLeft(TreeNode left) {
        if (left.left == null) {
            return left.val;
        }
        return getLeft(left.left);
    }

    public int getByRight(TreeNode right) {
        if (right != null) {
            return getLeft(right.left) + getByRight(right.right);
        }
        return 0;
    }
}
