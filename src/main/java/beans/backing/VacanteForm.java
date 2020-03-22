package beans.backing;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import beans.helper.ColoniaHelper;
import beans.model.Candidato;

@Named
@RequestScoped
public class VacanteForm {
	
	private Logger log = LogManager.getRootLogger();
	
	private boolean comentarioEnviado;
	
	@Inject
	private Candidato candidato;
	
	@Inject
	private ColoniaHelper coloniaHelper;
	
	public VacanteForm() {
		log.info("Creando el objeto VacanteForm");
	}

	public void setCandidato(Candidato candidato){
		this.candidato = candidato;
	}
	
	public boolean isComentarioEnviado() {
		return comentarioEnviado;
	}

	public void setComentarioEnviado(boolean comentarioEnviado) {
		this.comentarioEnviado = comentarioEnviado;
	}

	public ColoniaHelper getColoniaHelper() {
		return coloniaHelper;
	}

	public void setColoniaHelper(ColoniaHelper coloniaHelper) {
		this.coloniaHelper = coloniaHelper;
	}

	public String enviar(){
		if(this.candidato.getNombre() != null && this.candidato.getNombre().equals("Juan")){
			if(this.candidato.getApellido() != null && this.candidato.getApellido().equals("Perez")){
				String msg = "Gracias, pero Juan Perez ya trabaja con nosotros";
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
				FacesContext facesContext = FacesContext.getCurrentInstance();
				String componentId = null;
				facesContext.addMessage(componentId, facesMessage);
				
				return "index";
			}
			log.info("Entrando al caso de exito");
			return "exito";
		}
		log.info("Entrando al caso de fallo");
		return "fallo";
	}
	
	public void codigoPostalListener(ValueChangeEvent valueChangeEvent) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot uiViewRoot = facesContext.getViewRoot();
		int nuevoCodigoPostal = ((Long) valueChangeEvent.getNewValue()).intValue();

		UIInput coloniaIdInputText = (UIInput) uiViewRoot.findComponent("j_idt6:vacanteForm:coloniaId");
		int coloniaId = this.coloniaHelper.getColoniaIdPorCodigoPostal(nuevoCodigoPostal);
		coloniaIdInputText.setValue(coloniaId);
		coloniaIdInputText.setSubmittedValue(coloniaId);

		UIInput ciudadInputText = (UIInput) uiViewRoot.findComponent("j_idt6:vacanteForm:ciudad");
		String nuevaCiudad = "Ciudad de Mexico";
		ciudadInputText.setValue(nuevaCiudad);
		ciudadInputText.setSubmittedValue(nuevaCiudad);

		facesContext.renderResponse();

	}
	
	public void ocultarComentario(ActionEvent actionEvent){
		this.comentarioEnviado = !this.comentarioEnviado;
	}
}
