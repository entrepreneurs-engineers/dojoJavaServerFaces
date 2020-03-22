package beans.configuracion;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;

@FacesConfig(
		//Activa CDI build en beans
		version = Version.JSF_2_3)
@ApplicationScoped
public class ConfiguracionJSF {

}
