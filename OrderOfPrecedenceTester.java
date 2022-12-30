package OoPClasses;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// ===================================================================================
//  This program was created to help learn the Order of Precedence of Java 8 
//  operators.  It creates a test by randomly selecting a set of from 3 to 8 operator 
//  levels (out of 19). Each selection is given an associated letter in the order they 
//  are selected. It then requests the user to select their order by listing the 
//  letters in order.
//===================================================================================
public class OrderOfPrecedenceTester
{
	ArrayList<TestLineObject> objectList = new ArrayList<>();
	final int HIERARCHY_LEVELS = 19;
	
	// NOTE: MINIMUM_OPTIONS_PER_QUESTION + MAX_RANDOM_OPTIONS_PER_QUESTION <= 10
	final int MINIMUM_OPTIONS_PER_QUESTION = 3;
	final int MAX_RANDOM_OPTIONS_PER_QUESTION = 5; 
	
	public static void main(String[] args) {
		OrderOfPrecedenceTester testObj = new OrderOfPrecedenceTester();
		testObj.setupObjectLists();
		testObj.testUser();
	}
	
	public OrderOfPrecedenceTester() {
	}
	
	// this function makes sure each precedence level heirarchy is available for
	// random selection in the next test question
	private void resetObjects() {
		for (int cnt = 0; cnt < HIERARCHY_LEVELS; cnt++) {
			TestLineObject newObj = objectList.get(cnt);	
			newObj.setInUse(false);
		}
	}
	
	// This function sets up the hierarchy levels for selection 
	private void setupObjectLists() {
		for (int x = 0; x < HIERARCHY_LEVELS; x++) {
			ArrayList<String> arrList = new ArrayList();
			switch (x) {
			case 0:
				arrList.add("[] array index");
				arrList.add("() method call");
				arrList.add(". member access");
				break;
			case 1:
				arrList.add("x++ postfix increment");
				arrList.add("x-- postfix decrement");
				break;
			case 2:
				arrList.add("+ unary plus");
				arrList.add("- unary minus");
				break;
			case 3:
				arrList.add("++x prefix increment");
				arrList.add("--x prefix decrement");
				break;
			case 4:
				arrList.add("! boolean NOT");
				break;
			case 5:
				arrList.add("~ bitwise NOT");
				break;
			case 6:
				arrList.add("type cast");
				arrList.add("new ");
				break;
			case 7:
				arrList.add("* multiplication");
				arrList.add("/ division");
				arrList.add("% remainder");
				break;
			case 8:
				arrList.add("+ addition");
				arrList.add("- subtraction");
				break;
			case 9:
				arrList.add("+ string concatentaion");
				break;
			case 10:
				arrList.add("<< left shift");
				arrList.add(">> right shift");
				arrList.add(">>> unsigned right shift");
				break;
			case 11:
				arrList.add("< lessthan");
				arrList.add("<= less than or equal to");
				arrList.add("> greater than");
				arrList.add(">= greater than or equal to");
				break;
			case 12:
				arrList.add("instanceof");
				break;
			case 13:
				arrList.add("== value equality");
				arrList.add("!= value inequality");
				break;
			case 14:
				arrList.add("== reference equality");
				arrList.add("!= reference inequality");
				break;
			case 15:
				arrList.add("& bitwise AND");
				arrList.add("^ bitwise XOR");
				arrList.add("| bitwise OR");
				break;
			case 16:
				arrList.add("&& Logical AND");
				arrList.add("|| Logical OR");
				break;
			case 17:
				arrList.add("? : conditionary ternary");
				break;
			case 18:
				arrList.add("= assignment");
				arrList.add("*= compound assignment");
				arrList.add("/=  compound assignment");
				arrList.add("%=  compound assignment");
				arrList.add("+=  compound assignment");
				arrList.add("-=  compound assignment");
				arrList.add("<<=  compound assignment");
				arrList.add(">>=  compound assignment");
				arrList.add(">>>=  compound assignment");
				arrList.add("&=  compound assignment");
				arrList.add("^=  compound assignment");
				arrList.add("|=  compound assignment");
				break;
			}
			TestLineObject newObj = new TestLineObject(arrList);
			objectList.add(newObj);
		}
	}
	
	// This function does the actual testing
	// It creates a test for the user, reads the user response and evaluates the answer
	private void testUser() {
		ArrayList<IntString> correctResults = new ArrayList<>();
		boolean              endTest = false;
		String               sHeaders2 = "ABCDEFGHIJ";
		String               userEntry;
		Scanner              userSelection = new Scanner(System.in);
		int                  questionCount = 0;
		int                  correctAnswerCount = 0;

		Random rand = new Random();
		// create new tests until the user quits
		do {
			resetObjects();	
			// 0-(5+1) is 0-5 result; 0-5 + 3 = 3-8
    		int tests2Use = rand.nextInt(MAX_RANDOM_OPTIONS_PER_QUESTION+1) + MINIMUM_OPTIONS_PER_QUESTION; 
    		int testCount = 0;
    		correctResults.clear();
    		
    		// create test - a list of operatons in random order
    		System.out.println("\nQuestion " + ++questionCount + ":");
    		do {
        		int choice = rand.nextInt(HIERARCHY_LEVELS); // 0-18
        		TestLineObject obj = objectList.get(choice);
        		
        		// If the hierarchy level is in use we don't reuse it in this test
    			if (obj.isInUse() == false) {
        			String sOption =  obj.getAListOption();
         			System.out.println(sHeaders2.charAt(testCount) + ": " + sOption);
         			
        			// create a test line containing a letter and the operator and its description 
         			// then place the test option in its order of precedence in the list in the test
        			IntString newIntString = new IntString(choice, sHeaders2.substring(testCount, testCount+1));
        			int idx = 0;
        			for (; idx < testCount; idx++) {
        				IntString otherIntString = correctResults.get(idx);
        				if (newIntString.test(otherIntString)) {
        					break;
        				}
        			}
        			correctResults.add(idx, newIntString);
            		obj.setInUse(true);
            		testCount++;
    			}
    		} while (testCount < tests2Use);
    		
    		// Now that we have assembled the test and displayed the list of operators to the user we
    		// create the answer string based on the letters associated with each operator
			StringBuilder sb = new StringBuilder();
    		for (int x = 0; x < correctResults.size(); x++) {
    			String nextAns = correctResults.get(x).getTheString();
    			sb.append(nextAns);
    		}

    		// read in answer from user
			System.out.print("Answer (\"Q\" to Quit): ");
			userEntry = userSelection.next();
			
			// give the user the option to quit 
			if ((userEntry.equals("Q")) || (userEntry.equals("q")))
				endTest = true;
			else {
    		// If the user chooses not to quit, compare user answer to correct answer
				if (userEntry.toUpperCase().equals(sb.toString()) == true) {
					System.out.println("\nCorrect!: ");
				    correctAnswerCount++;
				}
				else
					System.out.println("\nIncorrect!: " + sb.toString());
			}
    		 
		} while (endTest == false);
		System.out.println("You answered " + correctAnswerCount + " of " + (questionCount-1) + " questions correctly.");
		
		System.out.println("Quitting");
	}
}



