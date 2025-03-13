package xmljson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import lombok.Data;

@Data
public class App {

	private static final File JSON = new File("pruebas.json");
	private static final File JSON_WRITE = new File("pruebas2.json");
	private static final File XML = new File("pruebas.xml");
	
	private List<Prueba> pruebas;
	
	private void leerJSON() {
		
		try (Reader reader = new FileReader(JSON)){
			
			GsonBuilder builder = new GsonBuilder().
					registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJSONAdapter()).
					serializeNulls().
					setPrettyPrinting();
			
			Gson gson = builder.create();
			
			Type token = TypeToken.getParameterized(List.class, Prueba.class).getType();
			
			setPruebas(gson.fromJson(reader, token));
			
		} catch (IOException e) {
			System.out.println("Error leyendo ficheros...");
		}
		
	}
	
	private void escribirXML() {
		WrapperPruebas wrapper = WrapperPruebas.builder().pruebas(pruebas).build();
		
		try (Writer writer = new FileWriter(XML)){
			LeerYEscribirXML<WrapperPruebas> handler = new LeerYEscribirXML<>();
			handler.escribirXML(wrapper, writer);
		} catch (IOException e) {
			System.out.println("Error escribiendo XML...");
		}
	}
	
	private void leerXML() {

		LeerYEscribirXML<WrapperPruebas> handler = new LeerYEscribirXML<>();
			
		setPruebas(handler.leerXML(WrapperPruebas.class, XML).getPruebas());
	
		System.out.println(pruebas);
	}
	
	private void escribirJSON() {
		
		try (Writer writer = new FileWriter(JSON_WRITE)){
			GsonBuilder builder = new GsonBuilder().
					setPrettyPrinting().
					registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJSONAdapter()).
					serializeNulls();
			
			Gson gson = builder.create();
			
			gson.toJson(pruebas, writer);
		} catch (IOException e) {
			System.out.println("Error escribiendo JSON...");
		}
		
	}
	
	private void run() {
		leerJSON();
		escribirXML();
		leerXML();
		escribirJSON();
	}
	
	public static void main(String[] args) {
		
		
		App app = new App();
		app.run();
		

	}

}
