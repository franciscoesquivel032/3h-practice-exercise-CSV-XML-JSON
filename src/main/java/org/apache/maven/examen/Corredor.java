package org.apache.maven.examen;

import java.time.LocalDate;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import com.opencsv.bean.CsvDate;

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


public class Corredor {
	@NonNull
	@EqualsAndHashCode.Include
	@CsvBindByPosition(position = 0)
	private String dni;
	@CsvBindByPosition(position = 1)
	private String nombre;
	@CsvDate("yyyy-MM-dd")
	@CsvCustomBindByPosition(position = 2, converter = LocalDateConverter.class)
	private LocalDate fecha;
	@CsvBindByPosition(position = 3)
	private boolean profesional;
}
