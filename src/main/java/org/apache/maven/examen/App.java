package org.apache.maven.examen;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.opencsv.bean.CsvToBeanBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class App 
{
	private static final Consumer<Object> PRINTER = System.out::println;
	
	private static final File CSV = new File("vueltas.csv");
	
	private List<Vuelta> vueltas;
	
	private void readCSV() {
		
		try (Reader reader = new FileReader(CSV)){
			List<Vuelta> beans = new CsvToBeanBuilder<Vuelta>(reader).
					withType(Vuelta.class).
					withSeparator(Separators.VUELTAFIELDS.charAt(0)).
					build().
					parse();
			
			setVueltas(beans);
		} catch (IOException e) {
			System.out.println("Error leyendo csv...");
		}
			
	}
	
	private void queries() {
		
		query1();
		
		query2();
		
		query3();
		
		query4();
		
		query5();
		
	}
	
	private void query5() {
		System.out.println("\nListado del nombre y dni de los corredores mayores de edad por nombre de vuelta ciclista");
		
		Predicate<Corredor> mayores = c -> c.getFecha().plusYears(18).isBefore(LocalDate.now());
		
		Function<Corredor, String> nombredni = c -> c.getNombre() + " , " + c.getDni();
		
		Function<Vuelta, List<String>> function = v -> v.getEquipos().stream().
				flatMap(e -> e.getCorredores().stream()).
				filter(mayores).
				map(nombredni).
				collect(Collectors.toList());
		
		vueltas.stream().
		collect(Collectors.toMap(Vuelta::getNombre, function)).
		entrySet().
		forEach(PRINTER);
	}

	private void query4() {
		System.out.println("\nListado del director de las vueltas que tengan patrocinadores de nacionalidad “española” desde 2010 a 2020 ambos incluidos.");
		
		Predicate<Equipo> espannol = e -> e.getPatrocinador().getNacionalidad().equalsIgnoreCase("española");
		
		vueltas.stream().
		filter(v -> v.getAnio() > 2009 && v.getAnio() < 2021).
		filter(v -> v.getEquipos().stream().anyMatch(espannol)).
		map(Vuelta::getDirector).
		forEach(PRINTER);
	}

	private void query3() {
		System.out.println("\nListado de todos equipos con corredores profesionales menores de edad." );
		
		vueltas.stream().
		flatMap(v -> v.getEquipos().stream()).
		filter(e -> e.getCorredores().stream().anyMatch(c -> c.isProfesional() && 
				c.getFecha().plusYears(18).isAfter(LocalDate.now()))).
		forEach(PRINTER);
	}

	private void query2() {
		System.out.println("\nListado del nombre de todos los corredores que participen en vueltas ciclistas con un premio superior a 30000 euros.");
		
		vueltas.stream().
			filter(v -> v.getPremio() > 30000).
			flatMap(v -> v.getEquipos().stream()).
			flatMap(e -> e.getCorredores().stream()).
			map(Corredor::getNombre).
			forEach(PRINTER);
	}

	private void query1() {
		System.out.println("Listado del nombre, año y director de todas las vueltas con más de 10 etapas.,"
				+ " ordenado por año en sentido creciente (de menor a mayor) (1 punto)");
		
		vueltas.stream().
			filter(v -> v.getNEtapas() > 10).
			sorted(Comparator.comparingInt(Vuelta::getAnio).reversed()).
			map(v -> v.getNombre() + " , " + v.getAnio() + " , " + v.getDirector()).
			forEach(PRINTER);
	}

	private void run() {
		readCSV();
		queries();
	}
	
    public static void main( String[] args )
    {
        App app = new App();
        
        app.run();
    }
}
