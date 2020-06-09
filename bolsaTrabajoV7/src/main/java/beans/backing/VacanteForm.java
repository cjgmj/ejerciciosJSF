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

import beans.model.Candidato;

@Named
@RequestScoped
public class VacanteForm {

	private Logger log = LogManager.getRootLogger();

	@Inject
	private Candidato candidato;

	private boolean comentarioEnviado;

	public VacanteForm() {
		log.info("Creando el objeto VacanteForm");
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public boolean isComentarioEnviado() {
		return comentarioEnviado;
	}

	public void setComentarioEnviado(boolean comentarioEnviado) {
		this.comentarioEnviado = comentarioEnviado;
	}

	public void ocultarComentario(ActionEvent actionEvent) {
		this.comentarioEnviado = !this.comentarioEnviado;
	}

	public String enviar() {
		if ("Juan".equals(this.candidato.getNombre())) {
			if ("Pérez".equals(this.candidato.getApellido())) {
				String msg = "Gracias, pero Juan Pérez ya trabaja con nosotros.";
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
				FacesContext facesContext = FacesContext.getCurrentInstance();
				String componentId = null; // Este es un mensaje global
				facesContext.addMessage(componentId, facesMessage);
				return "index";
			}
			log.info("Entrando al caso de éxito");
			return "exito";
		}

		log.info("Entrando al caso de fallo");
		return "fallo";
	}

	public void codigoPostalListener(ValueChangeEvent valueChangeEvent) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot uiViewRoot = facesContext.getViewRoot();

		String nuevoCodigoPostal = (String) valueChangeEvent.getNewValue();

		if ("03810".equals(nuevoCodigoPostal)) {
			UIInput coloniaIdInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:coloniaId");
			int coloniaId = 1;
			coloniaIdInputText.setValue(coloniaId);
			coloniaIdInputText.setSubmittedValue(coloniaId);

			UIInput ciudadInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:ciudad");
			String nuevaCiudad = "Ciudad de México";
			ciudadInputText.setValue(nuevaCiudad);
			ciudadInputText.setSubmittedValue(nuevaCiudad);

			facesContext.renderResponse();
		}
	}

}
