package xmljson;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

import com.google.gson.annotations.SerializedName;

@XmlEnum(String.class)
public enum TipoFormacion {
	@XmlEnumValue("Presencial")
	@SerializedName("Presencial")
	PRESENCIAL{public String toString() {return "Presencial";}},
	
	@XmlEnumValue("Presencial")
	@SerializedName("Presencial")
	NOPRESENCIAL{public String toString() {return "NoPresencial";}},
	
	@XmlEnumValue("Presencial")
	@SerializedName("Presencial")
	SEMIPRESENCIAL{public String toString() {return "SemiPresencial";}}
}
