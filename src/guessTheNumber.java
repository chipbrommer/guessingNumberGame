/**
 * @Author Chip Brommer
 * @Date October 21, 2017
 * @Contact Fredrick.Brommer@usm.edu
 * 
 * @Assignment Module 5 - Guess The Number
 * @Description Write an application that plays "guess the number" as follows:  
 * Your program chooses the number to be guessed by selecting a random integer 
 * in the range 1 to 1000.  The application displays the prompt "Guess a number 
 * between 1 and 1000."   The player inputs a first guess.  If the player's guess
 * is incorrect, your program should display "Too high.  Try again."  Or "Too low.  
 * Try again."  to help the player zero in on the correct answer.  The program 
 * should prompt the user for the next guess.  When the user enters the correct answer, 
 * display "Congratulations, you guessed the number!", and allow the user to choose 
 * whether to play agin.  [Note: The guessing technique employed in this problem is 
 * similar to a binary search]
 *
 */

import java.util.Scanner;			// Scanner allowing user input
import java.util.Random;				// Scanner allowing random number generator


public class guessTheNumber {
	
	// Holds the variable to initiate the game. If playGame is changed from 1 to another value
	// the game is ended.
	static int playGame = 1;
	
	// Creates a new scanner called 'input2'
	static Scanner input2 = new Scanner (System.in);
	// Creates a new scanner called 'input3'
	static Scanner input3 = new Scanner (System.in);
	
	// Main method
	public static void main(String[] args) 
	{
		System.out.println("     Welcome to Chips 'Guess The Number' Game!");
		
		// While the playGame variable is 1, the game will continue to play round after round.
		while(playGame == 1)
		{ 
			// Calls the randomNumberGame method.
			randomNumberGame();
		}
		
		// When playGame is not equal to 1, displays this closing message.
		System.out.println("\n    Thank you for playing Chips 'Guess The Number' Game!");
		System.out.println("                    See you soon!");
		
	}
	
	// This method holds the actual random number game. 
	public static void randomNumberGame()
	{	
		int userGuess = 0;			//Holds the users guess
		int guessCounter = 1;		//Holds the number of guesses the user has made
		
		// Message to the user that the program is creating a new random number.
		System.out.println("\nThe program will now generate a random number...");
		
		// Random number generator. Generates a number between 0 and 1000.
		// Then stores the number in randomNumber variable. 
		Random rand = new Random();
		int randomNumber  = rand.nextInt(1001);
		
		//Prints random number for debugging - remove comment bars
		//System.out.println(randomNumber);
		
		// Prompting the User to guess a number between 0 and 1000
		System.out.println("\nGuess a number from 0 to 1000:  ");
		
		// Receiving next line of input from user for their guess
		if( input2.hasNextLine() )
		{
		    userGuess = input2.nextInt();
		}
		
		// Error message if user enters a number less than 0 or more than 1000
		while (userGuess < 0 || userGuess > 1000)
		{
			System.out.print("\nERROR - Please input a number between 0 and 1000:  \n");
			userGuess = input2.nextInt();
		}
		
		// While the users guess is not equal to the random number, stays in this loop.
		while (userGuess != randomNumber)
		{
			if (userGuess < randomNumber)
			{
				
				// Calls boolean to check for userGuess being within 10 digits of the random number
				if ( guessWithinTen(userGuess, randomNumber) == true )
				{
					System.out.print("CLOSE! A little low! Guess again: ");
					userGuess = input2.nextInt();
				}
				
				// Calls boolean to check for userGuess being within 5 digits of the random number
				else if ( guessWithinFive(userGuess, randomNumber) == true )
				{
					System.out.print("SUPER CLOSE! A little low! Guess again: ");
					userGuess = input2.nextInt();
				}
				
				// If the userGuess is not within 10 or 5 digits of randomNumber
				// Displays generic "too high".
				else
				{
					System.out.print("Too Low! Guess again: ");
					userGuess = input2.nextInt();
				}
				
				// Adds one to the guess counter
				guessCounter = guessCounter + 1;
			}
			
			
			// If userGuess is more then randomNumber
			if (userGuess > randomNumber)
			{
				// Calls boolean to check for userGuess being within 10 digits of the random number.
				if ( guessWithinTen(userGuess, randomNumber) == true )
				{
					System.out.print("CLOSE! A little high! Guess again: ");
					userGuess = input2.nextInt();
				}
				
				// Calls boolean to check for userGuess being within 5 digits of the random number.
				else if ( guessWithinFive(userGuess, randomNumber) == true )
				{
					System.out.print("SUPER CLOSE! A little high! Guess again: ");
					userGuess = input2.nextInt();
				}	
				
				// If the userGuess is not within 10 or 5 digits of randomNumber
				// Displays generic "too high".
				else
				{
					System.out.print("Too High! Guess again: ");
					userGuess = input2.nextInt();
				}
				
				// Adds one to the guess counter
				guessCounter = guessCounter + 1;
			}
		}
		
		// If the user guesses correctly, Congrats the user and calls the playAgain method
		if (userGuess == randomNumber)
		{
			System.out.println("CONGRATULATIONS! You guessed the random number!");
			System.out.printf("It took you a total of %d guesses to guess the number.\n", guessCounter);
			
			// Calling the playAgain method
			playAgain();			
		}
	}
	
	// Method for playAgain. Receives a Y or N from user. If Y, playGame == 1, starts new game.
	// if the user enters N, playGame == 0, and game ends. 
	public static int playAgain()
	{	
		// Variable to accept user input for Y or N to play game again.
		char playAgain = 'a';
		
		// Asking the user if they would like to play the game again.
		// Then receives the user input.
		System.out.println("\nWould you like to play again?  ");
		System.out.print("Input 'Y' for Yes or 'N' for No:   ");
		
		// Receiving next line of input from user.
		if( input3.hasNextLine() )
		{
		    playAgain = input3.next().charAt(0);
		}
		
		// Converts playAgain to upper case.
		char playAgainUpper = Character.toUpperCase(playAgain);
		
		
		// Checks for a Y or N entry.
		// If not Y or N, throws error and requests input again.
		while(playAgainUpper != 'Y' &&  playAgainUpper != 'N')
		{
			System.out.print("Error: Input 'Y' for Yes or 'N' for No: ");
			
			// Receiving next line of input from user.
			if( input3.hasNextLine() )
			{
			    playAgain = input3.next().charAt(0);
			}
		}
		
		// Checks the playAgain char variable for a Y entry. 
		// If Y, sets up the game to play again.
		// If N, sets up the game to close.
		if (playAgainUpper == 'Y')
		{ 		
			// Sets the playGame variable to 1 so the game plays again
			// Returns playGame
			playGame = 1;
			return playGame;
		}
		else 
		{ 
			// Sets the playGame variable to 0 so the game ends
			// Returns playGame
			playGame = 0;
			return playGame;
		}		
	}
	
	//Checks if the userGuess is within 5 numbers of the randomNumber
		public static boolean guessWithinFive(int userGuess, int randomNumber)
		{
			// For loop for vales 1-5
			for (int i = 1; i < 6; i++)
			{
				// Checks if randomNumber is equal to userGuess + 1-5.
				// If the userGuess + i is equal to randomNumber, returns true.
				if (userGuess + i == randomNumber)
				{
					return true;
				}
				
				// Checks if randomNumber is equal to userGuess - 1-5.
				// If the userGuess - i is equal to randomNumber, returns true.
				if(userGuess - i == randomNumber)
				{
					return true;
				}	
			}	
			
			// If the for loop completely executes and does not return true
			// defaults to false.
			return false;
		};
	
	//Checks if the userGuess is within 10 numbers of the randomNumber
	public static boolean guessWithinTen(int userGuess, int randomNumber)
		{
			// For loop  vales 1-5
			for (int i = 6; i < 11; i++)
			{
				// Checks if randomNumber is equal to userGuess + 6-10.
				// If the userGuess + i is equal to randomNumber, returns true.
				if (userGuess + i  == randomNumber)
				{
					return true;
				}
				
				// Checks if randomNumber is equal to userGuess - 6-10.
				// If the userGuess - i is equal to randomNumber, returns true.
				if(userGuess - i == randomNumber)
				{
					return true;
				}
				
			}	
			// If the for loop completely executes and does not return true
			// defaults to false.
			return false;
		};
}
		
	
