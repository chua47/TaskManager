public class Todo extends Task {
    public Todo(String des) {
        super(des);
    }

    @Override
    public String toString() {
    	String misdone;
    	if (isDone) {
    		misdone = "Yes";
        } else {
    		misdone = "No";
        }        
    	return "description: " + description + "\n" + "is done? " + misdone;
    } 
}
