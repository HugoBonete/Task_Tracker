package es.tracker.tarea;

import java.util.ArrayList;
import es.tracker.json.Json;

public class Tarea 
{
	private int id_tarea;
	private String description;
	private enum State
	{
		Todo, InProgress, Done
	}
	private State status;
	private String createdAt;
	private String updatedAt;
	private static ArrayList<Tarea> arrTarea = Json.cargarDatos();
	private static int contadorId = (!arrTarea.isEmpty()) ? arrTarea.get(arrTarea.size() - 1).getId_tarea() + 1 : 0;
	
	public Tarea(String descripcion)
	{
		this.description = descripcion;
		this.id_tarea = contadorId++;
		this.status = State.Todo;
	}
	
	public ArrayList<Tarea> getArrTarea()
	{
		return arrTarea;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public State getStatus() 
	{
		return status;
	}

	public void setStatus(State status) 
	{
		this.status = status;
	}

	public String getCreatedAt() 
	{
		return createdAt;
	}

	public void setCreatedAt(String createdAt) 
	{
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() 
	{
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) 
	{
		this.updatedAt = updatedAt;
	}

	public int getId_tarea() 
	{
		return id_tarea;
	}
	
	public static void modifyTask(int id, String newDesc)
	{
		int i = checkId(id);
		arrTarea.get(i).setDescription(newDesc);
		Json.guardarDatos(arrTarea);
		
	}
	
	public static void addTask(String desc)
	{
		Tarea task = new Tarea(desc);
		arrTarea.add(task);
		Json.guardarDatos(arrTarea);
	}
	
	public static void deleteTask(int id)
	{
		int i = checkId(id);
		arrTarea.remove(i);
	}
	
	public static void changeState(int input, int id)
	{
		int i = checkId(id);
		if(input == 1)
		{
			arrTarea.get(i).setStatus(State.InProgress);
		}else if(input == 2)
		{
			arrTarea.get(i).setStatus(State.Done);
		}
		Json.guardarDatos(arrTarea);
	}
	
	private static int checkId(int id)
	{
		for(int i = 0; i < arrTarea.size(); i++)
		{
			if(id == arrTarea.get(i).getId_tarea())
			{
				return i;
			}
		}
		return -1;
			
	}
	
	public static void listTasks(int input)
	{
		int cnt = 0;
		for(int i = 0; i < arrTarea.size(); i++)
		{
			if(arrTarea.get(i).getStatus() == State.values()[input])
			{
				 System.out.println(arrTarea.get(i).toString());
				arrTarea.toString();
				cnt++;
			}
		}
		if (cnt == 0)
		{
			System.out.println("There is not any task in the '" + State.values()[input] + "' state.");
		}
		
	}

	@Override
	public String toString() {
		return "Id Tarea: " + id_tarea + "|| description: " + description + "|| status: " + status + "|| createdAt: "
				+ createdAt + "|| updatedAt: " + updatedAt + "\n";
	}
	
}