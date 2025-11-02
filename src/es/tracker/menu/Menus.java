package es.tracker.menu;

import java.util.Scanner;

import es.tracker.tarea.Tarea;

public class Menus 
{
	private static final Scanner scanIn = new Scanner(System.in);
	
	public static void iniciarPrograma()
	{
		System.out.println("------------------Welcome------------------\n");
		System.out.println("1 -Add a new Task-\n");
		System.out.println("2 -Update an exisiting Task-\n");
		System.out.println("3 -Change a Task's Status-\n");
		System.out.println("4 -Delete an exisiting Task-\n");
		System.out.println("5 -Show List of Tasks-\n");
		System.out.println("6 -Exit-\n");
		navegarPrincipal();
	}
	
	private static void navegarPrincipal()
	{
		String input = Escanear("Input: ");
		switch(input) 
		{
			case "1":
				menuAddTask();
			case "2":
				menuUpdateTask();
			case "3":
				menuStatusTask();
			case "4":
				menuDeleteTask();
			case "5":
				menuListTask();
			case "6":
				System.out.println("The program has been closed succesfully");
				System.exit(-1);
			default:
				System.out.println("Option not valid try again");
				navegarPrincipal();
		}
			
	}
	
	private static void menuAddTask()
	{
		try {
			String input = Escanear("Please give a description to the new task\n");
			if(input.isEmpty())
			{
				input = Escanear("The name can not be empty please try again.");
			}
			Tarea.addTask(input);
			iniciarPrograma();
		}catch(Exception e)
		{
			System.out.println("Something went wrong please try again");
			iniciarPrograma();
		}
		
	}
	
	private static void menuUpdateTask()
	{
		try {
			int id = Integer.parseInt(Escanear("Please type the id of the task you want to modify\n"));
			String newDesc = Escanear("Please give a new description to the task\n");
			Tarea.modifyTask(id, newDesc);
			iniciarPrograma();
		}catch(NumberFormatException e){
			System.out.println("The id input was not a valid number");
			menuUpdateTask();
		}catch(Exception e)
		{
			System.out.println("Something went wrong");
			iniciarPrograma();
		}
		
	}
	
	private static void menuStatusTask()
	{
		try {
			int id = Integer.parseInt(Escanear("Please type the id of the task you want to change the state on\n"));
			int state = Integer.parseInt(Escanear("Indicate the new state by typing: 1 -> In Progress or 2 -> Done\n"));
			Tarea.changeState(id, state);
			iniciarPrograma();
		}catch(NumberFormatException e){
			System.out.println("The input was not a number try again");
			menuStatusTask();
		}catch(Exception e)
		{
			System.out.println("Something went wrong");
			iniciarPrograma();
		}
		
	}
	
	private static void menuDeleteTask()
	{
		try {
			int id = Integer.parseInt(Escanear("Please type the id of the task you want to delete\n"));
			Tarea.deleteTask(id);
			iniciarPrograma();
		}catch(NumberFormatException e){
			System.out.println("The input was not a number try again");
			menuStatusTask();
		}catch(Exception e)
		{
			System.out.println("Something went wrong");
			iniciarPrograma();
		}
		
	}
	
	private static void menuListTask()
	{
		int input = Integer.parseInt(Escanear("Please type the status of the tasks you want to see: 0 -> Todo, 1 -> InProgress,  2 -> Done or 3 -> All Tasks\n"));
		if(input > 3 || input < 0)
		{
			System.out.println("The input was not valid please try again");
			menuListTask();
		}
		Tarea.listTasks(input);
		iniciarPrograma();
	}
	
	public static String Escanear(String cadena) 
	{
		System.out.println(cadena);
		return (scanIn.nextLine());
	}
}
