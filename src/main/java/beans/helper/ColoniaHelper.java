package beans.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import beans.model.Colonia;

@RequestScoped
@Named
public class ColoniaHelper {

	private List<Colonia> colonias;

	public List<Colonia> getColonias() {
		if (this.colonias == null) {
			this.colonias = Arrays.asList(new Colonia(1, "Napoles", 3810), new Colonia(2, "Polanco", 11530),
					new Colonia(3, "Del valle centro", 3100));
		}

		return colonias;
	}

	public int getColoniaIdPorNombre(String nombreColonia) {
		int coloniaiId = getColonias().stream().filter(colonia -> nombreColonia.equals(colonia.getNombreColonia()))
				.findFirst().orElse(new Colonia()).getColoniaId();

		return coloniaiId;
	}

	public int getColoniaIdPorCodigoPostal(int codigoPostal) {
		int coloniaId = this.getColonias().stream().filter(colonia -> codigoPostal == colonia.getCodigoPostal())
				.findFirst().orElse(new Colonia()).getColoniaId();
		return coloniaId;
	}

	public List<SelectItem> getSelectItems() {
		List<SelectItem> selectItems = new ArrayList<>();
		this.getColonias().forEach(
				colonia -> selectItems.add(new SelectItem(colonia.getColoniaId(), colonia.getNombreColonia())));
		return selectItems;
	}
}
