// Cheung Joshua November 2017
// NQueens.java

import java.util.*;
import java.io.*;
import java.lang.*;
class NQueens
{
	public static void main(String[] args) throws IOException
	{

		if(args.length < 2) //checking for file input
		{
			System.out.println("Usage: java -jar NQueens.jar <input file> <output file>");
			System.exit(1);
		}

		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new File(args[1]));

		while(in.hasNextLine()) // iterate through every line
		{
				Stack<Queen> stack = new Stack<Queen>(); //making a stack with Queen object type
				String input = in.nextLine();
				int currentSize = 0;
				boolean isAttacking = false;
				int begSize = 0;
				int i = 0;
				String[] inputArray = input.split("\\s+");
				int boardSize = Integer.parseInt(inputArray[0]);
	  		Stack<Queen> dummyStack = new Stack<Queen>();
				Stack<Queen> dummyStack2 = new Stack<Queen>();
				int[] num = new int[inputArray.length];
				int col;
				int row;

				for(int j = 1; j < num.length; j++) //put the input into an integer array
				{
					num[j] = Integer.parseInt(inputArray[j]);
				}

				for(i = 1; i < num.length; i+=2) //push queens in the file to the stack
				{
					currentSize++;
					begSize++;
				 	col = num[i]; 		// column is the index of the array
					row = num[i+1];		// row is the element at the index
					Queen q = new Queen(col, row);
					stack.push(q);
          dummyStack.push(q);
					dummyStack2.push(q);
				}

				boolean notValidPlacement = false; //check for invalid placement or out of bounds
				for(Queen b: stack){
					Queen dummy1 = dummyStack2.pop();
					if(dummy1.col > boardSize || dummy1.row > boardSize){
						notValidPlacement = true;
					}
				}

        for(Queen k : stack)  // loop through the current stack to see if any
        {											// queens are attacking each other
          Queen dummy = dummyStack.pop();
          isAttacking = dummy.isAttacking(dummyStack);
          if(isAttacking == true){
            break;
          }
        }

        if(isAttacking == true || notValidPlacement == true)  //if queens in the current stack is attacking
				{
          out.println("No solution");
        }
				else
				{
					if(nQueens(currentSize, begSize, boardSize, stack))
					{
						int[] solvedBoard = new int[boardSize];
						for(Queen j : stack)
						{
							solvedBoard[j.col - 1] = j.row;
						}
						for(int j = 0; j < solvedBoard.length; j++)  // if solved, print out the
						{																						 // coordinates of queens
							out.print((j + 1) + " " + solvedBoard[j] + " ");
						}
						out.println();
					}
					else
					{
						out.println("No solution");
					}
        }
			}
		in.close();
		out.close();
	}

	// This function will return true if all queens are placed, otherwise will return false
	static boolean nQueens(int currentSize, int begSize, int boardSize, Stack<Queen> stack)
	{
		for(int i = 1; i <= boardSize; i++)
		{
			for(int j = 1; j <= boardSize; j++)
			{
				if(begSize > currentSize) // if reached the file input position when backtracking
				{													// then that means that it is unable to place n amount of
					return false;						// queens validly
				}
				else
				{
					Queen q = new Queen(i, j);
					if(twoQueens(q, stack)) // if there is already a queen in the column, move
					{												// to the next column
						break;
					}
					if(q.isAttacking(stack) == false) // place queen if not attacking any other queens
					{
						stack.push(q);
						currentSize++;
						break;
					}
					while(j == boardSize && currentSize >= begSize) // if reached the end of the board without any options
					{											// to place a queen then pop the queen and backtrack
						Queen fail = stack.pop();
						i = fail.col;
						j = fail.row;
						currentSize--;
					}
					if(begSize > currentSize) // if reached the file input position when backtracking
					{													// then that means that it is unable to place n amount of
						return false;						// queens validly
					}
				}
			}
		}
		return true;
	}

	// This function is to check if there is a queen in the column before attempting
	// to place a queen
	static boolean twoQueens(Queen queen, Stack<Queen> stack)
	{
		boolean queenPresent = false;
		for(Queen i : stack)  // iterate through the stack
		{
			if(queen.col == i.col)
			{
				queenPresent = true;
			}
		}
		return queenPresent;
	}
}
