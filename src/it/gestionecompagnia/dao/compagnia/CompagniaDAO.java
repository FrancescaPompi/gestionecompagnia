package it.gestionecompagnia.dao.compagnia;

import java.util.Date;
import java.util.List;

import it.gestionecompagnia.dao.IBaseDAO;
import it.gestionecompagnia.model.Compagnia;

public interface CompagniaDAO extends IBaseDAO<Compagnia>{
	
	public List<Compagnia> findAllByDataAssunzioneMaggioreDi(Date dataAssunzioneInput) throws Exception;
	public List<Compagnia> findAllByRagioneSocialeContiene(String stringaInput) throws Exception;
	public List<Compagnia> findAllByCodFisImpiegatoContiene(String codiceFiscaleInput) throws Exception;

}
