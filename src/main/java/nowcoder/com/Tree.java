package nowcoder.com;

import java.util.HashMap;
import java.util.Map;

public class Tree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回
     */
    public TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i != in.length; i++) {
            inMap.put(in[i], i);
        }
        TreeNode root=buildTree(pre, 0, pre.length-1,
                in, 0, in.length-1, inMap);
        return root;
    }

    //直接进行前序遍历递归的构造出树
    TreeNode buildTree(int[] preorder, int preStart, int preEnd,
                       int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {

        if(preStart > preEnd || inStart > inEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;

        root.left = buildTree(preorder, preStart + 1, preStart + numsLeft,
                inorder, inStart, inRoot - 1, inMap);
        root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd,
                inorder, inRoot + 1, inEnd, inMap);
        return root;
    }

    public static void main(String[] arg) {
        int[] pre = {1,2,4,7,3,5,6,8};
        int[] in = {4,7,2,1,5,3,8,6};
        Tree treeAlg = new Tree();
        TreeNode root = treeAlg.reConstructBinaryTree(pre, in);
        System.out.println("root: " + root);
    }
}
