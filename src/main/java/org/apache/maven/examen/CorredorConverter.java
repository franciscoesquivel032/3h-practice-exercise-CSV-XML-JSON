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

public class CorredorConverter extends AbstractCsvConverter {

	@Override
	public Object convertToRead(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		
		StringReader reader = new StringReader(value);
		CSVParser icsvParser = new CSVParserBuilder().withSeparator(Separators.CORREDORFIELDS.charAt(0)).build();
		CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(icsvParser).build();
		
		
		return new CsvToBeanBuilder<Corredor>(csvReader).withType(Corredor.class).build().stream().findFirst().orElseGet(Corredor::new);
	}

}
