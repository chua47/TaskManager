public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String des) {
        setDescription(des);
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        // TODO Auto-generated method stub
        return description;
    }

    public void print(){
        System.out.println(description);
        //return description;
    }
    
    public void setDone(boolean isDone){
        this.isDone = isDone;
    }

}
