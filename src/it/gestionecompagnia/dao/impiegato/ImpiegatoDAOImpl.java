package it.gestionecompagnia.dao.impiegato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.gestionecompagnia.dao.AbstractMySQLDAO;
import it.gestionecompagnia.model.Compagnia;
import it.gestionecompagnia.model.Impiegato;

public class ImpiegatoDAOImpl extends AbstractMySQLDAO implements ImpiegatoDAO{

	public ImpiegatoDAOImpl(Connection connection) {
		super(connection);
	}

	@Override
	public List<Impiegato> list() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		List<Impiegato> result = new ArrayList<Impiegato>();
		Impiegato impiegatoTemp = null;
		
		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from gestionecompagnia.impiegato;")) {

			while (rs.next()) {
				impiegatoTemp = new Impiegato();
				impiegatoTemp.setId(rs.getLong("id"));
				impiegatoTemp.setNome(rs.getString("nome"));
				impiegatoTemp.setCognome(rs.getString("cognome"));
				impiegatoTemp.setCodiceFiscale(rs.getString("codiceFiscale"));
				impiegatoTemp.setDataDiNascita(rs.getDate("dataDiNascita"));
				impiegatoTemp.setDataAssunzione(rs.getDate("dataAssunzione"));
				result.add(impiegatoTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Impiegato get(Long idInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Impiegato result = null;
		try (PreparedStatement ps = connection.prepareStatement("select * from gestionecompagnia.impiegato where id=?")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Impiegato();
					result.setNome(rs.getString("NOME"));
					result.setCognome(rs.getString("COGNOME"));
					result.setCodiceFiscale(rs.getString("codiceFiscale"));
					result.setDataDiNascita(rs.getDate("dataDiNascita"));
					result.setDataAssunzione(rs.getDate("dataAssunzione"));
					result.setId(rs.getLong("id"));;
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
	public int update(Impiegato input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE gestionecompagnia.impiegato SET nome=?, cognome=?, codiceFiscale=?, dataDiNascita=?, dataAssunzione=? where id=?;")) {
			ps.setString(1, input.getNome());
			ps.setString(2, input.getCognome());
			ps.setString(3, input.getCodiceFiscale());
			ps.setDate(4, new java.sql.Date(input.getDataDiNascita().getTime()));
			ps.setDate(5, new java.sql.Date(input.getDataAssunzione().getTime()));
			ps.setLong(6, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Impiegato input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO gestionecompagnia.impiegato (nome, cognome, codiceFiscale, dataDiNascita, dataAssunzione) VALUES (?, ?, ?, ?, ?);")) {
			ps.setString(1, input.getNome());
			ps.setString(2, input.getCognome());
			ps.setString(3, input.getCodiceFiscale());
			ps.setDate(4, new java.sql.Date(input.getDataDiNascita().getTime()));
			ps.setDate(5, new java.sql.Date(input.getDataAssunzione().getTime()));
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Impiegato input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM gestionecompagnia.impiegato WHERE ID=?")) {
			ps.setLong(1, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Impiegato> findByExample(Impiegato input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		if (input == null)
			throw new Exception("Valore di input non ammesso.");
		
		Impiegato impiegatoTemp = null;
		List<Impiegato> result = new ArrayList<Impiegato>();
		String query = "select * from gestionecompagnia.impiegato where ";
		String addQuery = "";
		boolean usaNome = input.getNome() != null && !input.getNome().isEmpty();
		boolean usaCognome = input.getCognome() != null && !input.getCognome().isEmpty();
		boolean usaCodiceFiscale = input.getCodiceFiscale() != null && !input.getCodiceFiscale().isEmpty();
		boolean usaDataDiNascita = input.getDataDiNascita() != null;
		boolean usaDataAssunzione = input.getDataAssunzione() != null;
		
		if(usaNome) {
			addQuery += (addQuery.isEmpty() ? "" : " and ") + "nome='" + input.getNome() + "'";
		}
		if(usaCognome) {
			addQuery += (addQuery.isEmpty() ? "" : " and ") + "cognome='" + input.getCognome() + "'";
		}
		if(usaCodiceFiscale) {
			addQuery += (addQuery.isEmpty() ? "" : " and ") + "login='" + input.getCodiceFiscale() + "'";
		}
		if(usaDataDiNascita) {
			addQuery += (addQuery.isEmpty() ? "" : " and ") + "password='" + input.getDataDiNascita() + "'";
		}
		if(usaDataAssunzione) {
			addQuery += (addQuery.isEmpty() ? "" : " and ") + "dateCreated='" + input.getDataAssunzione() + "'";
		}
		
		query += addQuery;
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					impiegatoTemp = new Impiegato();
					impiegatoTemp.setId(rs.getLong("id"));
					impiegatoTemp.setNome(rs.getString("nome"));
					impiegatoTemp.setCognome(rs.getString("cognome"));
					impiegatoTemp.setCodiceFiscale(rs.getString("codiceFiscale"));
					impiegatoTemp.setDataDiNascita(rs.getDate("dataDiNascita"));
					impiegatoTemp.setDataAssunzione(rs.getDate("dataAssunzione"));
					result.add(impiegatoTemp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Impiegato> findAllByCompagnia(Compagnia compagniaInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (compagniaInput == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Impiegato> result = new ArrayList<Impiegato>();
		Impiegato impiegatoTemp = null;

		try (PreparedStatement ps = connection.prepareStatement("select * from gestionecompagnia.impiegato i inner join gestionecompagnia.compagnia c on c.id = i.compagnia_id where i.compagnia_id=?;")) {
			
			ps.setLong(1, compagniaInput.getId());

			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					impiegatoTemp = new Impiegato();
					impiegatoTemp.setId(rs.getLong("id"));
					impiegatoTemp.setNome(rs.getString("nome"));
					impiegatoTemp.setCognome(rs.getString("cognome"));
					impiegatoTemp.setCodiceFiscale(rs.getString("codiceFiscale"));
					impiegatoTemp.setDataDiNascita(rs.getDate("dataDiNascita"));
					impiegatoTemp.setDataAssunzione(rs.getDate("dataAssunzione"));
					result.add(impiegatoTemp);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int countByDataFondazioneCompagniaGreaterThan(Date dataInput) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Impiegato> findAllByCompagniaConFatturatoMaggioreDi(long fatturatoInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Impiegato> findAllErroriAssunzioni() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
