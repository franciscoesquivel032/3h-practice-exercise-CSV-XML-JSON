package xmljson;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement

public class WrapperPruebas {
	@XmlElement(name = "Curso")
	@XmlElementWrapper(name = "Cursos")
	private List<Prueba> pruebas;
}
