package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Clap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codeClap;
	
	private double tempsSequence;
	private int codeBobine;
	
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="codeSetup",referencedColumnName="codeSetup")
	private Setup setup;
	

	public int getCodeClap() {
		return codeClap;
	}
	public void setCodeClap(int codeClap) {
		this.codeClap = codeClap;
	}
	public double getTempsSequence() {
		return tempsSequence;
	}
	public void setTempsSequence(double tempsSequence) {
		this.tempsSequence = tempsSequence;
	}
	
	public int getCodeBobine() {
		return codeBobine;
	}
	
	public void setCodeBobine(int codeBobine) {
		this.codeBobine = codeBobine;
	}
	
	public Setup getSetup() {
		return setup;
	}
	
	public void setSetup(Setup setup) {
		this.setup = setup;
	}
}
