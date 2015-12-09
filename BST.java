import java.util.Scanner;

public class BST 
{
    private static Node root;
    private static String inOrder = "";    
    
    public BST() 
    {
        root = null;
    }
    
    public boolean isEmpty() 
    {
        return root == null;
    }
    
    public static void main(String[] args) //Provides a GUI for the user.
	{
		Scanner kb = new Scanner(System.in);
		
		System.out.println("Please enter the initial sequence of values: ");
		String initialSeq = kb.nextLine();
		
		String[] values = initialSeq.split(" ");
		int[] newValues = new int[values.length];
		
		for (int i = 0; i < values.length; i++) //Converts the string array into an int array.
		{
			newValues[i] = Integer.parseInt(values[i]);
			
			if (i == 0) //Sets the root node to the first value inputed.
			{
				root = new Node(newValues[i]);
			}
			else //If the value is not the first, it is inserted using the insert() method.
			{
				insert(root, newValues[i]);
			}
		}
		
		preOrder(root);
		System.out.println();
		inOrder(root);
		System.out.println();
		postOrder(root);
		System.out.println();
		
		String option;
		String choice;
		String num;
		int number = 0;
		
		do //The GUI is opened here which allows for easy insertion, deletion, etc.
		{
			System.out.println("I Insert a value");
			System.out.println("D Delete a value");
			System.out.println("P Find predecessor");
			System.out.println("S Find successor");
			System.out.println("E Exit the program");
			System.out.println("H Display this message");
			System.out.print("Command? ");
			option = kb.nextLine();
			choice = option.substring(0, 1);
			int val = 0;
			
			if (!option.equals("E")) //Each if statement checks which option the user chose.
			{
				num = option.substring(2, option.length());
				number = Integer.parseInt(num);
			}
			if (choice.equals("I"))
			{
				insert(root, number);
				inOrder(root);
				System.out.println();
			}
			else if (choice.equals("D"))
			{
				delete(root, number);
				inOrder(root);
				System.out.println();
			}
			else if (choice.equals("P"))
			{
				String bst_inorder = Project1.getInorder();
                String[] inorder_vals = bst_inorder.split(" ");
                int[] inorder_num = new int[inorder_vals.length];
                int predecessor = 0;
                
                for(int i = 0; i < inorder_vals.length; i++) 
                {
                    inorder_num[i] = Integer.parseInt(inorder_vals[i]);
                    
                    if (val == inorder_num[i]) 
                    {
                        if (i > 0) 
                        {
                            predecessor = inorder_num[i-1];
                        }
                        else
                        {
                        	System.out.println("This node does not have a predecessor.");
                        }
                    }
                }         
                
                System.out.println(predecessor);
			}
			else if (choice.equals("S"))
			{
				String Project1_inorder = Project1.getInorder();
                String[] inorder_vals = Project1_inorder.split(" ");
                int[] inorder_num = new int[inorder_vals.length];
                int successor = 0;
                
                for(int i = 0; i < inorder_vals.length; i++) 
                {
                    inorder_num[i] = Integer.parseInt(inorder_vals[i]);
                }
                
                for(int i = 0; i < inorder_num.length; i++) 
                {
                    if (val == inorder_num[i]) 
                    {
                        if (i != inorder_num.length-1) 
                        {
                            successor = inorder_num[i+1];
                        }
                        else
                        { 
                        	System.out.println("This node does not have successor.");
                        }                       
                    }
                }
                
                System.out.println(successor);
                
			}
			else if (choice.equals("E"))
			{
				break;
			}
			else if (choice.equals("H"))
			{
				return;
			}
			
		} while (!choice.equals("E"));
		
		System.out.println("Thanks for using the program!");
	}
    
    public void insert(int data) 
    {    
        if(search(data) == false)
        {         	
        	root = insert(root, data);      	       	
        }
    }
    
    public static Node insert(Node node, int data) 
    {    
        if (node == null)
        {
        	node = new Node(data);
        }  
        else 
        {
            if (data < node.getData())
            {
                node.setLeft(insert(node.getLeft(), data));
            }
            
            else if (data > node.getData())
            {
            	node.setRight(insert(node.getRight(), data));
            }
            else
            {
            	System.out.println(data + " already exists, ignore.");
            }
        }
        
        return node;
    }
    
    public void delete(int i) 
    {    
        if(isEmpty())
        {
            System.out.println("Tree is empty");
        }
        
        else if(search(i) == false)
        {
            System.out.println(i + " does not exist in the tree");
        }
        
        else 
        {
            root = delete(root, i);
            System.out.println(i + " has been deleted from the tree");
        }               
    }
    
    public static Node delete(Node root, int val) {
        
        Node p, p2, n;
        
        if (root == null)
        {
        	System.out.println(val + " doesn't exist!");
        }
        else if(root.getData() == val) 
        {
            Node ln = root.getLeft();
            Node rn = root.getRight();
            
            if(ln == null && rn == null)
            {
                return null;
            }
            else if (ln == null) 
            {
                p = rn;
                return p;
            }
            
            else if (rn == null) 
            {
                p = ln;
                return p;
            }
            
            else 
            {
                p2 = rn;
                p = rn;
                while(p.getLeft() != null) 
                    p = p.getLeft();
                p.setLeft(ln);
                return p2;
            }           
        }
        else if (val < root.getData()) 
        {
            n = delete(root.getLeft(), val);
            root.setLeft(n);
        }
        
        else 
        {
            n = delete(root.getRight(), val);
            root.setRight(n);
        }
        
        return root;
    }
    
    public boolean search(int val) 
    {
        return search(root, val);
    }
    
    public boolean search(Node r, int val) 
    {
        boolean found = false;
        
        while((r != null) && (!found)) 
        {
            int r_val = r.getData();
            
            if(val < r_val)
            {
                r = r.getLeft();
            }
            else if (val > r_val)
            {
                r = r.getRight();
            }
            else 
            {
                found = true;
                break;
            }
            
            found = search(r, val);
        }
        return found;
    }
    
    public void preOrder() 
    {
        System.out.print("Pre-order: ");
        preOrder(root);
    }
    
    public static void preOrder(Node root) 
    {
    	if (root != null) 
    	{
            System.out.print(root.getData() + " ");
            preOrder(root.getLeft());
            preOrder(root.getRight());
        }
    }
    
    public static void inOrder() 
    {
        System.out.print("In-order: ");
        inOrder(root);
    }
    public static void inOrder(Node root) 
    {            
        if (root != null) 
        {
            inOrder(root.getLeft());
            System.out.print(root.getData() + " ");
            inOrder += root.getData() + " ";
            inOrder(root.getRight());
        }
    }
    
    public static String getInorder() 
    {
        return inOrder;
    }
    
    public void postOrder() 
    {       
        System.out.print("Post-order: ");
        postOrder(root);
    }
    
    public static void postOrder(Node root) 
    {    
        if (root != null) 
        {
            postOrder(root.getLeft());
            postOrder(root.getRight());
            System.out.print(root.getData() + " ");
        }
    }
}
