package org.apache.maven.examen;

import java.time.LocalDate;
import java.util.List;

import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;

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

public class Equipo {
	@NonNull
	@EqualsAndHashCode.Include
	@CsvBindByPosition(position = 0)
	private String nombre;
	@CsvBindAndSplitByPosition(position = 1,
			converter = CorredorConverter.class,
			splitOn = Separators.CORREDORES,
			writeDelimiter = Separators.CORREDORES,
			elementType = Corredor.class)
	private List<Corredor> corredores;
	@CsvCustomBindByPosition(position = 2, converter = PatrocinadorConverter.class)
	private Patrocinador patrocinador;
}
