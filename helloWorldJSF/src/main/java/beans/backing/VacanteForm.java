package beans.backing;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import beans.model.Candidato;

@Named
@RequestScoped
public class VacanteForm {

	@Inject
	private Candidato candidato;

	private Logger log = LogManager.getRootLogger();

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public String enviar() {
		if ("Juan".equals(this.candidato.getNombre())) {
			log.info("Entrando al caso de éxito");
			return "exito";
		}

		log.info("Entrando al caso de fallo");
		return "fallo";
	}

}
