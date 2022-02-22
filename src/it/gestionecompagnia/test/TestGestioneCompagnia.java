package it.gestionecompagnia.test;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
			
			testUpdateCompagnia(compagniaDAOInstance);
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
	
	private static void testUpdateCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println(".......testUpdateCompagnia inizio.............");
		
		List<Compagnia> elencoVociCompagnia = compagniaDAOInstance.list();
		Compagnia compagniaInput = elencoVociCompagnia.get(0);
		int quantiElementiInseriti = compagniaDAOInstance.insert(compagniaInput);
		if (quantiElementiInseriti < 1)
			throw new RuntimeException("testUpdateCompagnia : FAILED, non è stato inserito alcun elemento");
		
		int quantiElementiAggiornati = compagniaDAOInstance.update(compagniaInput);
		if (quantiElementiAggiornati < 1)
			throw new RuntimeException("testUpdateCompagnia : FAILED, non è stato aggiornato alcun elemento");
		
		System.out.println(".......testUpdateCompagnia fine: PASSED.............");
	}

}
