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
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM gestionecompagnia.compagnia WHERE ID=?")) {
			ps.setLong(1, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Compagnia> findByExample(Compagnia input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		if (input == null)
			throw new Exception("Valore di input non ammesso.");
		
		Compagnia compagniaTemp = null;
		List<Compagnia> result = new ArrayList<Compagnia>();
		String query = "select * from gestionecompagnia.compagnia where ";
		String addQuery = "";
		boolean usaRagioneSociale = input.getRagioneSociale() != null && !input.getRagioneSociale().isEmpty();
		boolean usaFatturatoAnnuo = input.getFatturatoAnnuo() != 0;
		boolean usaDataFondazione = input.getDataFondazione() != null;
		
		if(usaRagioneSociale) {
			addQuery += (addQuery.isEmpty() ? "" : " and ") + "ragioneSociale='" + input.getRagioneSociale() + "'";
		}
		if(usaFatturatoAnnuo) {
			addQuery += (addQuery.isEmpty() ? "" : " and ") + "fatturatoAnnuo='" + input.getFatturatoAnnuo() + "'";
		}
		if(usaDataFondazione) {
			addQuery += (addQuery.isEmpty() ? "" : " and ") + "dataFondazione='" + input.getDataFondazione() + "'";
		}
		
		query += addQuery;
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					compagniaTemp = new Compagnia();
					compagniaTemp.setId(rs.getLong("id"));
					compagniaTemp.setRagioneSociale(rs.getString("ragioneSociale"));
					compagniaTemp.setFatturatoAnnuo(rs.getLong("fatturatoAnnuo"));
					compagniaTemp.setDataFondazione(rs.getDate("dataFondazione"));
					result.add(compagniaTemp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Compagnia> findAllByDataAssunzioneMaggioreDi(Date dataAssunzioneInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (dataAssunzioneInput == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Compagnia> result = new ArrayList<Compagnia>();
		Compagnia compagniaTemp = null;

		try (PreparedStatement ps = connection.prepareStatement("select * from gestionecompagnia.compagnia c inner join gestionecompagnia.impiegato i on c.id = i.compagnia_id where i.dataAssunzione > ?;")) {
			
			ps.setDate(1, new java.sql.Date(dataAssunzioneInput.getTime()));

			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					compagniaTemp = new Compagnia();
					compagniaTemp.setId(rs.getLong("id"));
					compagniaTemp.setRagioneSociale(rs.getString("ragioneSociale"));
					compagniaTemp.setFatturatoAnnuo(rs.getLong("fatturatoAnnuo"));
					compagniaTemp.setDataFondazione(rs.getDate("dataFondazione"));
					result.add(compagniaTemp);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Compagnia> findAllByRagioneSocialeContiene(String stringaInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (stringaInput == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Compagnia> result = new ArrayList<Compagnia>();
		Compagnia compagniaTemp = null;

		try (PreparedStatement ps = connection.prepareStatement("select * from gestionecompagnia.compagnia c where c.ragioneSociale like ?")) {
			
			ps.setString(1, "'%" + stringaInput + "%'");

			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					compagniaTemp = new Compagnia();
					compagniaTemp.setId(rs.getLong("id"));
					compagniaTemp.setRagioneSociale(rs.getString("ragioneSociale"));
					compagniaTemp.setFatturatoAnnuo(rs.getLong("fatturatoAnnuo"));
					compagniaTemp.setDataFondazione(rs.getDate("dataFondazione"));
					result.add(compagniaTemp);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Compagnia> findAllByCodFisImpiegatoContiene(String codiceFiscaleInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (codiceFiscaleInput == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Compagnia> result = new ArrayList<Compagnia>();
		Compagnia compagniaTemp = null;

		try (PreparedStatement ps = connection.prepareStatement("select * from gestionecompagnia.compagnia c inner join gestionecompagnia.impiegato i on c.id = i.compagnia_id where i.codiceFiscale like ?")) {
			
			ps.setString(1, "'%" + codiceFiscaleInput + "%'");

			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					compagniaTemp = new Compagnia();
					compagniaTemp.setId(rs.getLong("id"));
					compagniaTemp.setRagioneSociale(rs.getString("ragioneSociale"));
					compagniaTemp.setFatturatoAnnuo(rs.getLong("fatturatoAnnuo"));
					compagniaTemp.setDataFondazione(rs.getDate("dataFondazione"));
					result.add(compagniaTemp);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

}
