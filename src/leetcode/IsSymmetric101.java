package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class IsSymmetric101 {


    /**
     * 递归方法解决
     * @param root
     * @return
     */
    public boolean solution1(TreeNode root){
        return helper(root,root);
    }

    public boolean helper(TreeNode left,TreeNode right){
        if(left==null&&right==null) return true;
        if(left==null||right==null) return false;
        return (left.val==right.val)&&(helper(left.left,right.right))&&(helper(left.right,right.left));
    }

    /**
     * 借助于队列进行迭代解决
     * @param root
     * @return
     */
    public boolean solution2(TreeNode root){
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        queue.offer(root);
        while(queue.size()>0){
            TreeNode p1=queue.poll();
            TreeNode p2=queue.poll();
            if(p1==null&&p2==null) continue;
            if(p1==null||p2==null) return false;
            if(p1.val!=p2.val) return false;
            queue.offer(p1.left);
            queue.offer(p2.right);
            queue.offer(p1.right);
            queue.offer(p2.left);
        }
        return true;
    }

}
