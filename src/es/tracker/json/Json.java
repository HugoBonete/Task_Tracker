package es.tracker.json;
import java.io.*;
import java.util.ArrayList;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import es.tracker.tarea.Tarea;

public class Json 
{
    public static final String FILE_PATH = "datos.json";
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public static ArrayList<Tarea> cargarDatos()
	{
		File file = new File(FILE_PATH);
		if(!file.exists())
		{
			return new ArrayList<Tarea>();
		}
		
		try (Reader reader = new FileReader(file))
		{
			Type arrType = new TypeToken<ArrayList<Tarea>>() {}.getType();
			return gson.fromJson(reader, arrType);
		}catch(IOException e)
		{
			e.printStackTrace();
			return new ArrayList<Tarea>();
		}
	}

	public static void guardarDatos(ArrayList<Tarea> arr)
	{
		try(Writer writer = new FileWriter(FILE_PATH))
		{
			gson.toJson(arr, writer);
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
