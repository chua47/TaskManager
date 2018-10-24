public class Deadline extends Todo {

    //private String isDone ="No";
    private String when ="";

    public Deadline(String des, String when) {
        super(des);
        setWhen(when);
    }

    //@Override
    //public void setDescription(String des) {
    //    description = des;
    //}

    //public void setDone() {
      //  this.isDone = "Yes";
    //}
    //public String getDone() {
     //   return isDone;
    //}
    public void setWhen(String when) {
    	
    	String arrayWhen[]= when.split(" ");
        if (arrayWhen[0].equals("by")) {
        	this.when = (when.substring(when.indexOf(' '),when.length())).trim();
        } else {
        	this.when = (when.trim());
        	
        }
    }

    public String getWhen() {
        return when;
    }

    @Override
    public String toString() {
    	String misdone;
    	if (isDone) {
    		misdone = "Yes";
        } else {
    		misdone = "No";
        }        
    	return "description: " + description + "\n" + "is done? " + misdone + "\n" + "do by: " + getWhen();
    } 

   // @Override
   // public void print() {
    //    System.out.println("description: " + description);
    //    if (isDone) {
    //    	System.out.println("is done? Yes");
     //   } else {
      //  	System.out.println("is done? No");
       // }        
        //System.out.println("do by: " + getWhen());
   // }

}
