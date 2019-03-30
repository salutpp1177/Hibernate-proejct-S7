package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Theatre {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int codeTheatre;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "theatre")
	private List<Scene> scene;

	public int getCodeTheatre() {
		return codeTheatre;
	}

	public void setCodeTheatre(int codeTheatre) {
		this.codeTheatre = codeTheatre;
	}

	public List<Scene> getScene() {
		return scene;
	}

	public void setScene(List<Scene> scene) {
		this.scene = scene;
		if(!scene.isEmpty()) {
			for(Scene s : scene) {
				s.setTheatre(this);
			}
		}
	}
	
	
	
}
