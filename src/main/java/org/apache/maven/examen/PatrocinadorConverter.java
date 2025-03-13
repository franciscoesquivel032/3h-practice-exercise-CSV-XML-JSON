package org.apache.maven.examen;

import java.io.StringReader;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class PatrocinadorConverter extends AbstractBeanField<String, Patrocinador>{

	@Override
	protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		StringReader reader = new StringReader(value);
		
		CSVParser icsvParser = new CSVParserBuilder().
				withSeparator(Separators.PATROCINADORFIELDS.charAt(0)).
				build();
		
		CSVReader csvReader = new CSVReaderBuilder(reader).
				withCSVParser(icsvParser).
				build();
		
		return new CsvToBeanBuilder<Patrocinador>(csvReader).
				withType(Patrocinador.class).
				build().stream().
				findFirst().
				orElseGet(Patrocinador::new);
	}

}
