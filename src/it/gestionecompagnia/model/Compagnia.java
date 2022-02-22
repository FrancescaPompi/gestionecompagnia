package it.gestionecompagnia.model;

import java.util.Date;
import java.util.List;

public class Compagnia {

	private Long id;
	private String ragioneSociale;
	private long fatturatoAnnuo;
	private Date dataFondazione;
	private List<Impiegato> impiegati;

	public Compagnia() {

	}

	public Compagnia(Long id, String ragioneSociale, long fatturatoAnnuo, Date dataFondazione,
			List<Impiegato> impiegati) {
		super();
		this.id = id;
		this.ragioneSociale = ragioneSociale;
		this.fatturatoAnnuo = fatturatoAnnuo;
		this.dataFondazione = dataFondazione;
		this.impiegati = impiegati;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public long getFatturatoAnnuo() {
		return fatturatoAnnuo;
	}

	public void setFatturatoAnnuo(long fatturatoAnnuo) {
		this.fatturatoAnnuo = fatturatoAnnuo;
	}

	public Date getDataFondazione() {
		return dataFondazione;
	}

	public void setDataFondazione(Date dataFondazione) {
		this.dataFondazione = dataFondazione;
	}

	public List<Impiegato> getImpiegati() {
		return impiegati;
	}

	public void setImpiegati(List<Impiegato> impiegati) {
		this.impiegati = impiegati;
	}

}
