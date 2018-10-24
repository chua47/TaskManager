import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

	
    static Scanner in = new Scanner(System.in);
    //static List<Task> tasks = new ArrayList<>();
    static List<Task> tasks = getTasksFromFile();
    //static Task[] tasks = new Task[100];
    static int count = 0;  // to keep track of number of tasks in the list

    public static void main(String[] args) {
    	//assert args.length > 0 : "No arguments";
    	printWelcome();
        String line;
        boolean isExit = false;
        while (!isExit) {
            System.out.print("your task?");
            try {
                  line = getInput();
                  String command = line.split(" ")[0];
                switch (command) {
                    case "exit":
                        System.out.println("Bye!");
                        isExit = true;
                        break;
                    case "": // exit if user input is empty
                        isExit = true;
                        break;
                    //case "add":
                    //    tasks[count] = new Task(line.substring(line.indexOf(' '),line.length()));
                        //tasks[count].setDescription(line.substring(line.indexOf(' '),line.length()));
                    //    count = count + 1;
                    //    System.out.println("Tasks in the list: " + count);
                    //    break;
                    case "todo":
                        addTodo(line);
                        break;
                    case "deadline":
                        addDeadline(line);
                        break;
                    case "done":
                    	markAsDone(line);
                        break;
                    case "print":
                    	System.out.println("Tasks:");
                    	printTasks();
                        break;
                    default:
                        printError("unknown");
                }
            } catch (TaskManagerException e) {
                printError(e.getMessage());
            }
        }
        exit();

    }

    private static List<Task> getTasksFromFile() {
        List<Task> loadedTasks = new ArrayList<>();
        try {
            List<String> lines = getLines("data/tasks.txt");
            for (String line : lines) {
                if (line.trim().isEmpty()) { //ignore empty lines
                    continue;
                }
                loadedTasks.add(createTask(line)); //convert the line to a task and add to the list
            }
        } catch (FileSystemNotFoundException e) {
            printError("problem encountered while loading data: " + e.getMessage());
        }
        return loadedTasks;
    } 
    
    private static Task createTask(String line) {
    	count = count + 1;
    	//Task task = new Task();
    	Task task = null;
//		System.out.println(line);
		String arrayDes[]= line.split("\\|");
/*		
		for (int i = 0; i < arrayDes.length; i++) {
	         if (i > 0) {
	            System.out.print(", ");
	         }
	         System.out.print(arrayDes[i]);
	      }
        System.out.println("-----");
*/        
		if (arrayDes[0].trim().equals("T")) {
			//System.out.println("todo");
			//System.out.println(arrayDes[2].trim());
			task = new Todo(arrayDes[2].trim());
			if (arrayDes[1].trim().equals("1"))
				task.setDone(true);
			else {task.setDone(false);}
		} else {
			//System.out.println("dateline");
			//System.out.println(arrayDes[2].trim());
			task = new Deadline(arrayDes[2].trim(),arrayDes[3].trim());
			if (arrayDes[1].trim().equals("1"))
				task.setDone(true);
			else {task.setDone(false);}
		}
			
		
		return task;
	}

	private static List<String> getLines(String string) {
		List<String> outString = new ArrayList<>();
        try {
			Scanner in = new Scanner(new FileReader(string));
			while(in.hasNextLine()) {
			    outString.add(in.nextLine());
		}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return outString;
	}

	private static void addTodo(String line) throws TaskManagerException {
        String description = line.substring("todo".length()).trim();
        if (description.isEmpty()){
            throw new TaskManagerException("Empty description for TODO");
        } else {
        	String trimFirstWord = line.substring(line.indexOf(' '),line.length());
        	//Task todo = new Todo(trimFirstWord.trim());
            tasks.add(new Todo(trimFirstWord.trim()));
            //tasks[count]=todo;
            count= count+1;
            System.out.println("Tasks in the list: " + count);
        }
    }

    private static void addDeadline(String line) throws TaskManagerException {
        String arrayDes[]= line.substring(line.indexOf(' '),line.length()).split("/");
        if (arrayDes[0].isEmpty() || arrayDes[1].isEmpty()){
            throw new TaskManagerException("Empty description or deadline");
        } else {
        	//String trimFirstWord = line.substring(line.indexOf(' '),line.length());
        	//Task todo = new Todo(trimFirstWord.trim());
            tasks.add(new Deadline(arrayDes[0], arrayDes[1]));
            //tasks[count]=todo;
            count= count+1;
            System.out.println("Tasks in the list: " + count);
        }
    }

    private static void markAsDone(String line) {
        int index = Integer.parseInt(line.substring("done".length()).trim());
        tasks.get(index - 1).setDone(true);
        System.out.println("Tasks in the list: " + tasks.size());
    }
    

//    private static void todoTask(Task todo) {
//        tasks[count]=todo;
//        count= count+1;
//        System.out.println("Tasks in the list: " + count);
//    }

    private static void exit() {
        // TODO Auto-generated method stub

    }

    private static void printError(String errMsg) {
        // TODO Auto-generated method stub
        if (errMsg == "unknown") {
            System.out.println("Unknown command! please try again");
        }else{
            System.out.println("Error: " + errMsg);
        }

    }

    private static String getInput() {
        //String line = in.nextLine();
        //t.description=line;
        //System.out.println("Tasks in the list: " + count++);
        return in.nextLine();
    }

    private static void printWelcome() {
        System.out.println("Welcome to TaskManager-Level1!");

			
	        //Scanner sc = new Scanner(file);
	        //while (sc.hasNextLine()) {
	        //    i = sc.nextInt();
	        //    System.out.println(i);
	        //}
	        //sc.close();
//	    } catch (FileNotFoundException e) {
//	        e.printStackTrace();
//	    }
    }

    private static void printTasks() {
        //for (int i = 0; i < count; i++) {
        //    System.out.println("[" + (i + 1) + "] " + tasks[i].getDescription());
        //}
        
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + tasks.get(i));
        }
    }

    // todo: add missing methods
}

