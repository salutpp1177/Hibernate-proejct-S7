package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Lieu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codeLieu;
	
	private String endroit;
	private String description;
	private String periode;
	

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "lieu")
	private List<Scene> scene;

	public int getCodeLieu() {
		return codeLieu;
	}

	public void setCodeLieu(int codeLieu) {
		this.codeLieu = codeLieu;
	}

	public String getEndroit() {
		return endroit;
	}

	public void setEndroit(String endroit) {
		this.endroit = endroit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}

	public List<Scene> getScene() {
		return scene;
	}

	public void setScene(List<Scene> scene) {
		this.scene = scene;
		
		if(!scene.isEmpty()) {
			for(Scene s : scene) {
				s.setLieu(this);
			}
		}
	}
	
	
	
	
	
}
