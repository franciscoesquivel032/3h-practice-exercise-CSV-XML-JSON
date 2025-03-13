package xmljson;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.google.gson.annotations.SerializedName;

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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement

public class Prueba {
	@NonNull
	@EqualsAndHashCode.Include
	@XmlAttribute(name = "id")
	@SerializedName("$id")
	private String id;
	@XmlElement(name = "MCER")
	@SerializedName("MCER")
	private String mcer;
	@XmlElement(name = "Nivel")
	@SerializedName("Nivel")
	private String nivel;
	@XmlElement(name = "ID")
	@SerializedName("ID")
	private String code;
	@XmlElement(name = "Titulo")
	@SerializedName("Titulo")
	private String titulo;
	@XmlElement(name = "Horario")
	@SerializedName("Horario")
	private String horario;
	@XmlJavaTypeAdapter(LocalDateTimeXMLAdatper.class)
	@XmlElement(name = "InicioImparticion")
	@SerializedName("InicioImparticion")
	private LocalDateTime inicioImparticion;
	@XmlJavaTypeAdapter(LocalDateTimeXMLAdatper.class)
	@XmlElement(name = "FinImparticion")
	@SerializedName("FinImparticion")
	private LocalDateTime finImparticion;
	@XmlElement(name = "Horas")
	@SerializedName("Horas")
	private int horas;
	@XmlElement(name = "URL")
	@SerializedName("URL")
	private String url;
	@XmlElement(name = "TipoFormacion")
	@SerializedName("TipoFormacion")
	private TipoFormacion tipo;
	@XmlElement(name = "ECTS")
	@SerializedName("ECTS")
	private String ects;
	@XmlElement(name = "Categoria")
	@SerializedName("Categoria")
	private String categoria;
	@XmlElementWrapper(name = "Profesorado")
	@XmlElement(name = "Profesor")
	@SerializedName("Profesorado")
	private List<Profesor> profesorado;
}
