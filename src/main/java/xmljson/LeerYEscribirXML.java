package xmljson;

import java.io.File;
import java.io.Writer;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class LeerYEscribirXML <T>{
	
	public void escribirXML (T raiz, Writer writer)
	{
		JAXBContext context;
		Marshaller ms;
		try {
			
			// Crear contexto
			context = JAXBContext.newInstance(raiz.getClass());
			// Crear marshaller, objeto que se encarga de escribir el XML
			ms = context.createMarshaller();

			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			if (writer != null)
				ms.marshal(raiz, writer);
			else
				ms.marshal(raiz, System.out);
			
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public T leerXML (Class<T> claseRaiz, File file)
	{
		T raiz;
		try {
			JAXBContext context = JAXBContext.newInstance(claseRaiz);
			Unmarshaller ums = context.createUnmarshaller();
			raiz = (T) ums.unmarshal(file);
			}
		
		catch (JAXBException e) {
			e.printStackTrace();
			raiz = null;
		}
		
		return raiz;
	}

}
