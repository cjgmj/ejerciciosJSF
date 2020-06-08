package beans.backing;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

	public VacanteForm() {
		log.info("Creando el objeto VacanteForm");
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
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

}
