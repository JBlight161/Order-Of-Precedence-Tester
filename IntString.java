package OoPClasses;
import java.util.function.Predicate;

//---------------------------------------------------------------
// This is a helper class, intended to be generically useful.
// 
// In the case of the OrderOfPrecedenceTester, the String value 
// is an individual operator and a short description of the operator.
// The int value the represents position of the operator in the
// order of precedence hierarchy. 
// Note that this class implements the Predicate functional
// interface function test
// ---------------------------------------------------------------
public class IntString implements Predicate{
	private int    theInt;
	private String theString;
	
	// constructor
	public IntString(int newInt, String newString) {
		this.theInt = newInt;
		this.theString  = newString;
	}
	
	// getters and setters
	public int getTheInt() {
		return theInt;
	}

	public void setTheInt(int theInt) {
		this.theInt = theInt;
	}

	public String getTheString() {
		return theString;
	}

	public void setTheString(String theString) {
		this.theString = theString;
	}

	// implementation of Predicate FI
	// ---------------------------------------------------------------
	public boolean test(Object t) {
		if (this.theInt < ((IntString)t).getTheInt())
			return true;
		return false;
	}
}
