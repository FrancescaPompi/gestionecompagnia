package it.gestionecompagnia.test;

import java.sql.Connection;
import java.util.Date;

import it.gestionecompagnia.dao.compagnia.CompagniaDAO;
import it.gestionecompagnia.dao.compagnia.CompagniaDAOImpl;
import it.gestionecompagnia.model.Compagnia;
import it.gestionecompagnia.connection.MyConnection;
import it.gestionecompagnia.dao.Constants;

public class TestGestioneCompagnia {

	public static void main(String[] args) {
		
		CompagniaDAO compagniaDAOInstance = null;
		
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			
			compagniaDAOInstance = new CompagniaDAOImpl(connection);
			
			System.out.println("In tabella compagnia ci sono " + compagniaDAOInstance.list().size() + " elementi.");
			
			testInsertCompagnia(compagniaDAOInstance);
			System.out.println("In tabella compagnia ci sono " + compagniaDAOInstance.list().size() + " elementi.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static void testInsertCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println(".......testInsertCompagnia inizio.............");
		int quantiElementiInseriti = compagniaDAOInstance.insert(new Compagnia("Compagnia", 10000, new Date()));
		if (quantiElementiInseriti < 1)
			throw new RuntimeException("testInsertCompagnia : FAILED, non è stato inserito alcun elemento");

		System.out.println(".......testInsertCompagnia fine: PASSED.............");
	}

}
