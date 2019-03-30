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
public class Setup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codeSetup;
	
	private String expositionOuverture;
	private double distanceFocale;
	private int numFiltres;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "setup")
	private List<Clap> clap;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="codeScene",referencedColumnName="codeScene")
	private Scene scene;
	
	public int getCodeSetup() {
		return codeSetup;
	}
	
	public void setCodeSetup(int codeSetup) {
		this.codeSetup = codeSetup;
	}
	
	public String getExpositionOuverture() {
		return expositionOuverture;
	}
	
	public void setExpositionOuverture(String expositionOuverture) {
		this.expositionOuverture = expositionOuverture;
	}
	
	public double getDistanceFocale() {
		return distanceFocale;
	}
	
	public void setDistanceFocale(double distanceFocale) {
		this.distanceFocale = distanceFocale;
	}
	
	public int getNumFiltres() {
		return numFiltres;
	}
	
	public void setNumFiltres(int numFiltres) {
		this.numFiltres = numFiltres;
	}
	
	public List<Clap> getClap() {
		return clap;
	}
	
	public void setClap(List<Clap> clap) {
		this.clap = clap;
		
		if(!clap.isEmpty()) {
			for(Clap c : clap) {
				c.setSetup(this);
			}
		}
	}
	
	
	public Scene getScene() {
		return scene;
	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	

}
