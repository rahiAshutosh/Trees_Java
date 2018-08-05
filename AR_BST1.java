import java.util.*;
import java.util.Queue;
public class AR_BST1 
{
    Node root;
    AR_BST1(Node root)
    {
        this.root=root;
    }
    static class Node
    {
        Node left;
        Node right;
        int data;        
        Node(int k)
        {
            data=k;
            left=right=null;
        }
    }
    
    Node insert(Node root, int key) // INSERTION IN A B.S.T.
    {
        if(root==null)
        {
            root=new Node(key);
            return root;
        }
        if(key>root.data)
            root.right=insert(root.right,key);
        if(key<root.data)
            root.left=insert(root.left,key);
        return root;
    }
    
    void insertUtil(int key) // THE METHOD THAT CALLS THE INSERT METHOD.
    {
        root=insert(root,key);
    }
    
    void printInOrder(Node root)
    {
        if(root==null)
            return;
        printInOrder(root.left);
        System.out.print(root.data + " ");
        printInOrder(root.right);
    }
    
    boolean search(Node root, int key) // SEARCHING IN A B.S.T.
    {
        if(root==null)
            return false;
        if(key<root.data)
            return search(root.left,key);
        if(key>root.data)
            return search(root.right,key);        
        return true;
    }
    
    int height(Node root) // RETURNS HEIGHT OF B.S.T.
    {
        if(root==null)
            return 0;
        return ((height(root.left)>height(root.right))?height(root.left):height(root.right))+1;
    }
    
    int getMax(Node root) // RETURNS THE MAXIMUM ELEMENT IN A B.S.T.
    {
        Node p=root;
        while(p.right!=null)
            p=p.right;
        return p.data;
    }
    
    int getMin(Node root) // RETURNS THE MINIMUM ELEMENT IN A B.S.T.
    {
        Node p=root;
        while(p.left!=null)
            p=p.left;
        return p.data;
    }
    
    Node delete(Node root,int key) // DELETES A KEY IN A B.S.T.
    {
        if(root==null)
            return root;
        if(key<root.data)
            root.left=delete(root.left,key);
        else if(key>root.data)
            root.right=delete(root.right,key);
        else // KEY TO BE DELETED IS FOUND
        {  
            if(root.left==null && root.right==null)
                return null;
            else if(root.left==null) //NODE WITH ONE CHILD HAS TO BE DELETED
                return root.right;
            else if(root.right==null)
                return root.left;
            
            else // NODE WITH TWO CHILDREN HAS TO BE DELETED
            {
                root.data=getMin(root.right);
                return delete(root.right,root.data);
            }            
        }
        return root;
    }
    
    void deleteUtil(int key)
    {
        root=delete(root,key);
    }
    
    void levelOrderTraversal(Node root) // BREADTH FIRST SEARCH
    {
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        
        while(!q.isEmpty())
        {
            Node p=q.poll();
            System.out.print(p.data + " ");
            if(p.left!=null)
                q.add(p.left);
            if(p.right!=null)
                q.add(p.right);
        }
    }
    
    public static void main(String[] args)
    {        
        Node root=new Node(10);
        AR_BST1 tree = new AR_BST1(root);
        tree.insertUtil(8);
        tree.insertUtil(12);
        tree.insertUtil(2);
        tree.insertUtil(9);
        tree.insertUtil(11);
        tree.insertUtil(15);
        tree.insertUtil(1);        
        tree.insertUtil(100);
        
        tree.printInOrder(root);
        
        System.out.println(tree.search(root,12));
        System.out.println(tree.height(root));
        System.out.println("\nMAXIMUM ELEMENT OF THE B.S.T. : "+tree.getMax(root));
        System.out.println("\nMINIMUM ELEMENT OF THE B.S.T. : " + tree.getMin(root));
        
        //tree.deleteUtil(100);
        //tree.deleteUtil(10);
        System.out.println("\n\n");
        tree.printInOrder(root);
        
        System.out.println("\n\nLEVEL ORDER TRAVERSAL : ");
        tree.levelOrderTraversal(root);
    }
}
