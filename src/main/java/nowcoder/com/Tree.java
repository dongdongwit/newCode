package nowcoder.com;

import java.util.*;

public class Tree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     *输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
     * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
     * (注意: 在返回值的list中，数组长度大的数组靠前)
     */
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<Integer> pathList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        if (root.val > target) {
            return resultList;
        }
        pathList.add(root.val);
        if (root.val == target) {
            resultList.add(pathList);
        }
        ArrayList<ArrayList<Integer>> leftResult = FindPath(root.left, target - root.val);
        if (! leftResult.isEmpty()) {
            leftResult.forEach(leftPath -> leftPath.add(root.val));
            resultList.addAll(leftResult);
        }
        ArrayList<ArrayList<Integer>> rightResult = FindPath(root.right, target - root.val);
        if (! rightResult.isEmpty()) {
            rightResult.forEach(rightPath -> rightPath.add(root.val));
            resultList.addAll(rightResult);
        }
        return resultList;
    }

    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
     * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
     *
     * tips:后续遍历的遍历顺序是左右根，根据二叉搜索树的基本结构，
     * 只需要不断的确定出左子树区间和右子树区间，并且判断：
     * 左子树区间的所有节点值 < 根节点值 < 右子树区间的所有节点值
     */
    public boolean isBST(int[] sequence) {
        return isBST(sequence, 0, sequence.length - 1);
    }

    private boolean isBST(int[] sequence, int start, int end) {
        if (start >= end) {
            return true;
        }
        int rootKey = sequence[end], leftEnd = start, rightEnd = end - 1;
        //找到root节点在序列中的位置
        while (sequence[leftEnd] < rootKey && leftEnd < end) {
            leftEnd++;
        }
        //判断当前的右子树区间是否满足BST的特点
        for (int i = leftEnd; i < end; i++) {
            if (sequence[i] < rootKey) {
                return false;
            }
        }
        return isBST(sequence, start, leftEnd - 1)
                && isBST(sequence, leftEnd + 1, rightEnd);
    }

    /**
     * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
     *
     * tips:在Java中Queue是和List、Map同等级别的接口，LinkedList中也实现了Queue接口，该接口中的主要函数有：
     * 容量不够或队列为空时不会抛异常：offer（添加队尾元素）、peek（访问队头元素）、poll（访问队头元素并移除）
     * 容量不够或队列为空时抛异常：add、element（访问队列元素）、remove（访问队头元素并移除）
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        //queue用来保存当前遍历到了哪个节点，一次性把一个节点的左右子都入队
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        //list用来保存输出的节点
        ArrayList<Integer> resList = new ArrayList<Integer>();
        if (root==null) {//注意：空树返回一个默认构造的空LinkedList，而不是一个空指针null
            return resList;
        }
        queue.offer(root);
        //广度优先遍历DFS模板
        while (! queue.isEmpty()) {
            TreeNode current = queue.poll();
            resList.add(current.val);
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        return resList;
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

    /**
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
     *
     */
    public TreeNode Convert(TreeNode pRootOfTree) {
        return getDoubleList(getInorder(pRootOfTree));
    }
    //中序遍历，在list中按遍历顺序保存
    private ArrayList<TreeNode> getInorder(TreeNode root) {
        ArrayList<TreeNode> inorder = new ArrayList<>();
        if (root == null) {
            return inorder;
        }
        inorder.addAll(getInorder(root.left));
        inorder.add(root);
        inorder.addAll(getInorder(root.right));
        return inorder;
    }
    //遍历list，修改指针
    private TreeNode getDoubleList(ArrayList<TreeNode> inorder) {
        if (inorder.isEmpty()) {
            return null;
        }
        for (int i = 0; i < inorder.size() - 1; i++) {
            inorder.get(i).right = inorder.get(i + 1);
            inorder.get(i + 1).left = inorder.get(i);
        }
        return inorder.get(0);
    }

    public static TreeNode initNode(int deep) {
        if (deep < 0) {
            return null;
        }
        TreeNode root = new TreeNode(deep);
        root.left = initNode(deep - 1);
        root.right = initNode(deep - 1);
        return root;
    }

    public static void main(String[] arg) {
        Tree treeAlg = new Tree();
/*        //isBST
        int[] sequence = {3, 4, 9, 5, 12, 11, 10};
//        int[] sequence = {6,7,8,5};
        System.out.println("res: " + treeAlg.isBST(sequence));*/
        int[] pre = {1,2,4,7,3,5,6,8};
        int[] in = {4,7,2,1,5,3,8,6};
        TreeNode root = treeAlg.reConstructBinaryTree(pre, in);
//        System.out.println("root: " + root);
//        System.out.println("res: " + treeAlg.PrintFromTopToBottom(root));
        TreeNode initNode = initNode(5);
//        System.out.println("res: " + treeAlg.FindPath(initNode, 12));
        System.out.println("res: " + treeAlg.Convert(initNode));
    }
}
