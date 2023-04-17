/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignment3;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class Assignment3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Scanner input = new Scanner(System.in);
        
        /* Command line arguments method:
        int[] tree = new int[args.length];
        int arrLen = tree.length;*/
        
        // User input based:
        System.out.print("Enter number of input: ");
        int arrLen = input.nextInt();
        String[] array = new String[arrLen];
        int[] tree = new int[arrLen];
        
        //Prompting input in String type to accept "-" input
        System.out.print("Input tree nodes: ");
        for(int i = 0; i < arrLen; i++)
            array[i] = input.next();
        
        //Conversion elements' data type from String to int.
        for(int i = 0; i < arrLen; i++)
        {
            if("-".equals(array[i]))
                tree[i] = 0;
            else
            tree[i] = Integer.parseInt(array[i]);
        }
        
        //Display root.
        System.out.println("Root: " + tree[0]);
        
        //Display height.
        System.out.println("Height: " + getHeight(arrLen));
        
        //Display nodes.
        System.out.println("Number of Nodes: " + countNodes(tree));
        
        //Display leaves.
        System.out.println("Number of Leaves: " + countLeaves(tree, arrLen, getHeight(arrLen)));
        
        //Checks whether tree is complete or not. NOT YET FINISHED!!
        System.out.println("Complete binary tree: ??");
        
        //Checks whether tree is full or not. NOT YET FINISHED!!
        System.out.println("Full binary tree: ??");
        
        //Display pre-order traversal by invoking preOrder() method.
        System.out.print("Pre-order: ");
        preOrder(0, tree, arrLen);
        System.out.println();
        
        //Display in-order traversal by invoking inOrder() method.
        System.out.print("In-order: ");
        inOrder(0, tree, arrLen);
        System.out.println();
        
        //Display post-order traversal by invoking postOrder() method.
        System.out.print("Post-order: ");
        postOrder(0, tree, arrLen);
        System.out.println();
        
        //Good practice lol.
        input.close();
    }
    
    public static int getHeight(int arrLen){
        
        int height = 0, count = arrLen;
        
        //Going thru & count (int height) every row in the tree by dividing the no. of elements by 2 until it reaches 0(index for root).
        while(count/2 != 0){
            count /= 2;
            height++;
        }
        return height;
    }
    
    public static int countNodes(int[] tree){
        
        int nodes = 0;
        
        //Checks for null value in the array. Count all non-null value (int nodes)
        for(int i = 0; i < tree.length; i++){
            if(tree[i] != 0)
                nodes++;
        }
        return nodes;
    }
    
    //recursive method (method that calls itself in its execution)
    public static void preOrder(int n, int[] tree, int arrLen){
        
        //base condition: end the recursion if arrayElementOutOfBound or if element is null
        if(n >= arrLen || tree[n] == 0){
            return;
        } //recursive case: display nodes when first time traversing it based on N-L-R
        else{
        System.out.print(tree[n] + " ");
        preOrder(2*n + 1, tree, arrLen);
        preOrder(2*n + 2, tree, arrLen);
        //2n + 1 looks to the left of the node, 2n + 2 looks to the right.
        }
    }
    
    public static void inOrder(int n, int[] tree, int arrLen){
        
        //base condition: end the recursion if arrayElementOutOfBound or if element is null
        if(n >= arrLen || tree[n] == 0){
            return ;
        }//recursive case: display nodes when second time traversing it based on L-N-R
        else{
        inOrder(2*n + 1, tree, arrLen);
        System.out.print(tree[n] + " ");
        inOrder(2*n + 2, tree, arrLen);
        }
    }
    
    public static void postOrder(int n, int[] tree, int arrLen){
        
        //base condition: end the recursion if arrayElementOutOfBound or if element is null
        if(n >= arrLen || tree[n] == 0){
            return;
        }//recursive case: display nodes when third time traversing it based on L-R-N
        else{
            postOrder(2*n + 1, tree, arrLen);
            postOrder(2*n + 2, tree, arrLen);
            System.out.print(tree[n] + " ");
        }
    }
    
        public static int countLeaves(int[] tree, int arrLen, int height){
        
        int lastRow, leaves = 0;
        lastRow = (int) Math.pow(2, height) - 1;
        
        //Count all nodes in the last row
        for(int i = arrLen - 1; i >= lastRow; i--){
            if(tree[i] != 0)
            leaves++;
        }
        
        //Check if there is any leaves that is not in the last element
        for(int j = lastRow - 1; j >= 0; j--){
            //Skip iteration IF arrayElementOutOfBound
            if(2*j + 1 >= arrLen && 2*j + 2 >= arrLen)
                continue;
            //ELSE count all leaves that have null branch to its left & right.
            else if(tree[2*j + 1] == 0 && tree[2*j + 2] == 0)
                leaves++;
        }
        return leaves;
        }
}
