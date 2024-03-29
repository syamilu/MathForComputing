/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignment3;
import java.util.Scanner;
import java.lang.Exception;

/**
 *
 * @author user
 */
public class Assignment3_gitHub {
    public static void main(String[] args) {
       Scanner input = new Scanner(System.in);
        int[] tree;
        String[] array;
        String node;
        int len, count = 0;
        String complete, full;
        
        System.out.print("Enter input : ");
        node = input.nextLine();
        len = node.length();
        array = new String[len];
        
        for(int i = 0; i < len; i++){
            if(node.charAt(i)==' '){
                count++;
            }
            else if(array[count]==null){
                array[count] = String.valueOf(node.charAt(i));
            }
            else{
                array[count] = array[count] + node.charAt(i);
            }
        }
        
        int arrLen = count+1;
        tree = new int[arrLen];
        //Conversion elements' data type from String to int.
        for(int i = 0; i < arrLen; i++)
        {
            if("-".equals(array[i])){
                tree[i] = 0;
                if(array[2*i+1]!=null&&2*i+1<arrLen){
                    array[2*i+1]="0";
                }
                if(array[2*i+2]!=null&&2*i+2<arrLen){
                    array[2*i+2]="0";
                }
            }
            else
            tree[i] = Integer.parseInt(array[i]);
        }
        
        //Display root.
        if(tree[0] == 0 || tree[1]==0&&tree[2]==0){
            System.out.println("Not a binary tree. Terminating the system");
            System.exit(0);
        }
        System.out.println("Root: " + tree[0]);
        
        //Display height.
        System.out.println("Height: " + getHeight(arrLen));
        
        //Display nodes.
        System.out.println("Number of Nodes: " + countNodes(tree));
        
        //Display leaves.
        System.out.println("Number of Leaves: " + countLeaves(tree, arrLen, getHeight(arrLen)));
        
        //Checks whether tree is complete or not.
        complete = isCompleteTree(tree)? "Yes":"No";
        System.out.println("Complete binary tree: " + complete);
        
        //Checks whether tree is full or not.
        full = isFullTree(tree)? "Yes":"No";
        System.out.println("Full binary tree: " + full);
        
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
            if(2*j + 1 >= arrLen && 2*j + 2 >= arrLen && tree[j] != 0){
                leaves++;
                continue;
            } else if(tree[j] == 0)
                continue;
            //ELSE count all leaves that have null branch to its left & right.
            else if(tree[2*j + 1] == 0 && tree[2*j + 2] == 0)
                leaves++;
        }
        return leaves;
        }
       public static boolean isFullTree(int[] tree){
        boolean fullTree = false;
           for(int i = 0; i < tree.length/2 - 1; i++){
               if((tree[2 * i + 1] != 0 && tree[2 * i + 2] != 0) || (tree[2 * i + 1] == 0 && tree[2 * i + 2] == 0)){
                   fullTree = true;
               }
               else{
                    fullTree = false;
                    break;
               }
               }
               if(tree.length % 2 == 0)
                fullTree = false;

               return fullTree;
           }
       public static boolean isCompleteTree (int[] tree){
        boolean completeTree = false;
           int row = getHeight(tree.length);
           for(int i = 1; i <= (int) (Math.pow(2, row) - 2); i++){
               if(tree[i] == 0){
                  completeTree = false;  
                  break;}
                else {
                    for(int j = (int) (Math.pow(2, row) - 1); j < tree.length - 1; j++){
                        if(tree[j] != 0 && tree[j + 1] == 0){
                            completeTree = false;
                            break;
                        }
                        else
                            completeTree = true;

                        }
                    }    
           }
           return completeTree;
    }
}    
