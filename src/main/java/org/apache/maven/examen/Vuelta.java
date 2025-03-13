package org.apache.maven.examen;

import java.util.List;

import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByPosition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Vuelta {
	@NonNull
	@EqualsAndHashCode.Include
	@CsvBindByPosition(position = 0)
	private String nombre;
	@CsvBindByPosition(position = 1)
	private float premio;
	@CsvBindByPosition(position = 2)
	private String director;
	@CsvBindByPosition(position = 3)
	private int nEtapas;
	@CsvBindByPosition(position = 4)
	private int anio;
	@CsvBindAndSplitByPosition(position = 5,
			converter = EquipoConverter.class,
			splitOn = Separators.EQUIPOS,
			writeDelimiter = Separators.EQUIPOS,
			elementType = Equipo.class)
	private List<Equipo> equipos;
}
