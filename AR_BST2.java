import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AR_BST2 
{
	Node root;
	HashSet<Integer> hs=new HashSet<Integer>(); 
	HashMap<Integer,Integer> hm=new HashMap<Integer,Integer>(); // SCHEME : <NODE.DATA,HSPACE>
	int min_hspace;
	int max_hspace;
	
	AR_BST2(Node n)
	{
		root=n;
		min_hspace=0;
		max_hspace=0;
	}
	static class Node
	{
		Node left;
		Node right;
		int data;
		
		Node(int k)
		{
			data=k;
		}
	}
	Node insert(Node root,int key)
	{
		if(root==null)
		{
			root=new Node(key);
			return root;
		}
		if(key<root.data)
			root.left=insert(root.left,key);
		else if(key>root.data)
			root.right=insert(root.right,key);
		return root;
	}
	
	void insertUtil(int key)
	{
		root=insert(root,key);
	}
	
	void inorder(Node root)
	{
		if(root==null)
			return;
		inorder(root.left);
		System.out.print(root.data + " ");
		inorder(root.right);
	}
	
	void getMinMaxHspace(Node root, int hspace)
	{		
		if(!hm.containsValue(hspace))			
			hm.put(root.data,hspace);		
		if(root.left!=null)
			getMinMaxHspace(root.left,hspace-1);
		if(root.right!=null)
			getMinMaxHspace(root.right,hspace+1);		
	}
	
	void printVerticalLine(Node root, int hspace, int req)
	{
		if(root==null)
			return;
		if(hspace==req)
			System.out.print(root.data + " ");
		printVerticalLine(root.left,hspace-1, req);
		printVerticalLine(root.right,hspace+1, req);
	}
	
	void verticalTraversal()
	{
		getMinMaxHspace(root,0);
		int minhspace = Collections.min(hm.values());
		int maxhspace = Collections.max(hm.values());
		for(int i=minhspace; i<=maxhspace;i++)
		{
			printVerticalLine(root,0,i);
		}
	}
	
	public static void main(String[] args)
	{
		Node root=new Node(10);		
		AR_BST2 tree = new AR_BST2(root);
		
		tree.insertUtil(2);
		tree.insertUtil(100);
		tree.insertUtil(75);
		tree.insertUtil(34);
		tree.insertUtil(80);
		tree.insertUtil(1);		
		tree.verticalTraversal();
	}

}
