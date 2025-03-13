package org.apache.maven.examen;

import java.io.StringReader;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.AbstractCsvConverter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class EquipoConverter extends AbstractCsvConverter {

	@Override
	public Object convertToRead(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		StringReader reader = new StringReader(value);
		CSVParser parser = new CSVParserBuilder().withSeparator(Separators.EQUIPOFIELDS.charAt(0)).build();
		CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(parser).build();
		return new CsvToBeanBuilder<Equipo>(csvReader).withType(Equipo.class).build().stream().findFirst().orElseGet(Equipo::new);
	}

}
