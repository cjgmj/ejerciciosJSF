package beans.backing;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import beans.model.Candidato;

@Named
@RequestScoped
public class VacanteForm {

	@Inject
	private Candidato candidato;

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public String enviar() {
		if ("Juan".equals(this.candidato.getNombre())) {
			return "exito";
		}

		return "fallo";
	}

}
