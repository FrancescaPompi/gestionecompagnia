package it.gestionecompagnia.model;

import java.util.Date;

public class Impiegato {

	private Long id;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private Date dataDiNascita;
	private Date dataAssunzione;
	private Compagnia compagnia;

	public Impiegato() {

	}
	
	public Impiegato(String nome, String cognome) {
		super();
		this.nome = nome;
		this.cognome = cognome;
	}

	public Impiegato(String nome, String cognome, String codiceFiscale, Date dataDiNascita, Date dataAssunzione) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataDiNascita = dataDiNascita;
		this.dataAssunzione = dataAssunzione;
	}

	public Impiegato(String nome, String cognome, String codiceFiscale, Date dataDiNascita,
			Date dataAssunzione, Compagnia compagnia) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataDiNascita = dataDiNascita;
		this.dataAssunzione = dataAssunzione;
		this.compagnia = compagnia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public Date getDataAssunzione() {
		return dataAssunzione;
	}

	public void setDataAssunzione(Date dataAssunzione) {
		this.dataAssunzione = dataAssunzione;
	}

	public Compagnia getCompagnia() {
		return compagnia;
	}

	public void setCompagnia(Compagnia compagnia) {
		this.compagnia = compagnia;
	}

	@Override
	public String toString() {
		return "Impiegato [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", codiceFiscale=" + codiceFiscale
				+ ", dataDiNascita=" + dataDiNascita + ", dataAssunzione=" + dataAssunzione + "]";
	}
}
