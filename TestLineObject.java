package OoPClasses;
import java.util.ArrayList;
import java.util.Random;

//--------------------------------------------------------------
// Objects of this class are used for controlling the use of
// levels (or rows) of the operator order of precedence.  Each
// row contains from 1 to 12 operators with different functions.
// The ArrayList contains the different operators of the same
// level.  When the testUser() function is called from the 
// OrderOfPrecedence class it creates a test by randomly 
// selecting different levels in the hierarchy. For each level
// selected, it calls getAListOption() to pick an operator of
// this level.  
// The boolean isInUse is set when a option is selected to 
// prevent operators from the same level from being selected.
// --------------------------------------------------------------
public class TestLineObject {
	private boolean               isInUse;
	private ArrayList<String> lineOptions;

	public TestLineObject(ArrayList<String> aList) {
		isInUse = false;
		lineOptions = aList;
	}

	public boolean isInUse() {
		return isInUse;
	}

	public void setInUse(boolean isInUse) {
		this.isInUse = isInUse;
	}

	// This function selects and returns an operator from this level and
	// sets the isInUse boolean to true so that this level isn't selected
	// again in the same test.
	// -------------------------------------------------------------------
    public String getAListOption() {
    	int optionChoice = 0;
    	int optionCount = lineOptions.size();
    	if (optionCount > 1) {
    		Random rand = new Random();
    		optionChoice = rand.nextInt(optionCount); 
    	}
    	String option = lineOptions.get(optionChoice);
    	setInUse(true);
    	return option;
    }
}
