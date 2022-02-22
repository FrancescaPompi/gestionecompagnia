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
			
//			System.out.println("In tabella compagnia ci sono " + compagniaDAOInstance.list().size() + " elementi.");
//			
//			testInsertCompagnia(compagniaDAOInstance);
//			System.out.println("In tabella compagnia ci sono " + compagniaDAOInstance.list().size() + " elementi.");
//			
//			testUpdateCompagnia(compagniaDAOInstance);
//			System.out.println("In tabella compagnia ci sono " + compagniaDAOInstance.list().size() + " elementi.");
//			
//			System.out.println("Prima dell'eliminazione: in tabella compagnia ci sono " + compagniaDAOInstance.list().size() + " elementi.");
//			testDeleteCompagnia(compagniaDAOInstance);
//			System.out.println("Dopo l'eliminazione: in tabella compagnia ci sono " + compagniaDAOInstance.list().size() + " elementi.");
//			
//			testFindByExample(compagniaDAOInstance);
			
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
	
	private static void testDeleteCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println(".......testDeleteCompagnia inizio.............");
		
		int quantiElementiInseriti = compagniaDAOInstance.insert(new Compagnia("Compagnia Bella", 10000, new SimpleDateFormat("dd-MM-yyyy").parse("03-01-2022")));
		if (quantiElementiInseriti < 1)
			throw new RuntimeException("testDeleteCompagnia : FAILED, non è stato inserito alcun elemento");
		
		List<Compagnia> elencoVociPresenti = compagniaDAOInstance.list();
		int numeroElementiPresentiPrimaDellaRimozione = elencoVociPresenti.size();
		if (numeroElementiPresentiPrimaDellaRimozione < 1)
			throw new RuntimeException("testDeleteCompagnia : FAILED, non ci sono voci sul DB");
		
		Compagnia ultimoDellaLista = elencoVociPresenti.get(numeroElementiPresentiPrimaDellaRimozione - 1);
		compagniaDAOInstance.delete(ultimoDellaLista);
		
		int numeroElementiPresentiDopoDellaRimozione = compagniaDAOInstance.list().size();
		if (numeroElementiPresentiDopoDellaRimozione != numeroElementiPresentiPrimaDellaRimozione - 1)
			throw new RuntimeException("testDeleteCompagnia : FAILED, la rimozione non è avvenuta");

		System.out.println(".......testDeleteCompagnia fine: PASSED.............");
	}
	
	private static void testFindByExample(CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println(".......testFindByExample inizio.............");
		
		List<Compagnia> elencoVociPresenti = compagniaDAOInstance.list();
		if (elencoVociPresenti.size() < 1)
			throw new RuntimeException("testFindByExample : FAILED, non ci sono voci sul DB");
		
		Compagnia compagnia = new Compagnia("Compagnia");
		
		List<Compagnia> elencoVociCreatiPerExample = compagniaDAOInstance.findByExample(compagnia);
		for(Compagnia compagniaItem : elencoVociCreatiPerExample) {
			System.out.println(compagniaItem);
		}
		if(elencoVociCreatiPerExample.size() < 1) {
			throw new RuntimeException("testFindByExample : FAILED, user non trovato");
		}
		System.out.println(".......testFindByExample fine: PASSED.............");
	}

}
