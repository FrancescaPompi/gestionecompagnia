package it.gestionecompagnia.dao.compagnia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.gestionecompagnia.dao.AbstractMySQLDAO;
import it.gestionecompagnia.model.Compagnia;

public class CompagniaDAOImpl extends AbstractMySQLDAO implements CompagniaDAO{
	
	public CompagniaDAOImpl(Connection connection) {
		super(connection);
	}

	@Override
	public List<Compagnia> list() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		List<Compagnia> result = new ArrayList<Compagnia>();
		Compagnia compagniaTemp = null;
		
		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from gestionecompagnia.compagnia;")) {

			while (rs.next()) {
				compagniaTemp = new Compagnia();
				compagniaTemp.setId(rs.getLong("id"));
				compagniaTemp.setRagioneSociale(rs.getString("ragioneSociale"));
				compagniaTemp.setFatturatoAnnuo(rs.getLong("fatturatoAnnuo"));
				compagniaTemp.setDataFondazione(rs.getDate("dataFondazione"));
				result.add(compagniaTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Compagnia get(Long idInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");		
		
		Compagnia result = null;
		try (PreparedStatement ps = connection.prepareStatement("select * from gestionecompagnia.compagnia where id=?")) {
			ps.setLong(1, idInput);
			
			try (ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					result = new Compagnia();
					result.setId(rs.getLong("id"));
					result.setRagioneSociale("ragioneSociale");
					result.setFatturatoAnnuo(rs.getLong("fatturatoAnnuo"));
					result.setDataFondazione(rs.getDate("dataFondazione"));
				} else {
					result = null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int update(Compagnia input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE gestionecompagnia.compagnia SET ragioneSociale=?, fatturatoAnnuo=?, dataFondazione=? where id=?;")) {
			ps.setString(1, input.getRagioneSociale());
			ps.setLong(2, input.getFatturatoAnnuo());
			ps.setDate(3, new java.sql.Date(input.getDataFondazione().getTime()));
			ps.setLong(4, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Compagnia input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO gestionecompagnia.compagnia (ragioneSociale, fatturatoAnnuo, dataFondazione) VALUES (?, ?, ?);")) {
			ps.setString(1, input.getRagioneSociale());
			ps.setLong(2, input.getFatturatoAnnuo());
			ps.setDate(3, new java.sql.Date(input.getDataFondazione().getTime()));
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Compagnia input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Compagnia> findByExample(Compagnia input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compagnia> findAllByDataAssunzioneMaggioreDi(Date dataAssunzioneInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compagnia> findAllByRagioneSocialeContiene(String stringaInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compagnia> findAllByCodFisImpiegatoContiene(String codiceFiscaleInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
