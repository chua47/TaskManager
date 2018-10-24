public class TaskManagerException extends Exception {
	private String message;
	
    public TaskManagerException(String message) {
        super(message);
        //setMessage(message);
    }

    //public void setMessage(String message) {
        // TODO Auto-generated method stub
      //  this.message =  message;
    //}
    public String getMessage() {
        // TODO Auto-generated method stub
        return super.getMessage();
    }

}
