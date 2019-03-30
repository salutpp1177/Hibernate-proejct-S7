package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Scene {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codeScene;
	@Column(unique = true)
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "scene")
	private List<Setup> setup;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="codeTheatre",referencedColumnName="codeTheatre")
	private Theatre theatre;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="codeLieu",referencedColumnName="codeLieu")
	private Lieu lieu;
	
	public int getCodeScene() {
		return codeScene;
	}
	
	public void setCodeScene(int codeScene) {
		this.codeScene = codeScene;
	}
	
	public List<Setup> getSetup() {
		return setup;
	}
	
	public void setSetup(List<Setup> setup) {
		this.setup = setup;
		
		if(!setup.isEmpty()) {
			for(Setup s : setup) {
				s.setScene(this);
			}
		}
		
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	public Lieu getLieu() {
		return lieu;
	}

	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
