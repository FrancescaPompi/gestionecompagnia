package it.gestionecompagnia.test;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import it.gestionecompagnia.dao.compagnia.CompagniaDAO;
import it.gestionecompagnia.dao.compagnia.CompagniaDAOImpl;
import it.gestionecompagnia.dao.impiegato.ImpiegatoDAO;
import it.gestionecompagnia.dao.impiegato.ImpiegatoDAOImpl;
import it.gestionecompagnia.model.Compagnia;
import it.gestionecompagnia.model.Impiegato;
import it.gestionecompagnia.connection.MyConnection;
import it.gestionecompagnia.dao.Constants;

public class TestGestioneCompagnia {

	public static void main(String[] args) {
		
		CompagniaDAO compagniaDAOInstance = null;
		ImpiegatoDAO impiegatoDAOInstance = null;
		
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			
			compagniaDAOInstance = new CompagniaDAOImpl(connection);
			impiegatoDAOInstance = new ImpiegatoDAOImpl(connection);
			
			
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
//			
//			System.out.println("In tabella impiegato ci sono "+ impiegatoDAOInstance.list().size() + " elementi.");
//			
//			testInsertiImpiegato(impiegatoDAOInstance);
//			System.out.println("In tabella impiegato ci sono "+ impiegatoDAOInstance.list().size() + " elementi.");
//			
//			testUpdateImpiegato(impiegatoDAOInstance);
//			System.out.println("In tabella impiegato ci sono "+ impiegatoDAOInstance.list().size() + " elementi.");
//			
//			System.out.println("Prima dell'eliminazione: in tabella compagnia ci sono " + impiegatoDAOInstance.list().size() + " elementi.");
//			testDeleteImpiegato(impiegatoDAOInstance);
//			System.out.println("Dopo l'eliminazione: in tabella compagnia ci sono " + impiegatoDAOInstance.list().size() + " elementi.");
			
			testFindByExample(impiegatoDAOInstance);
			System.out.println("In tabella impiegato ci sono "+ impiegatoDAOInstance.list().size() + " elementi.");
			
			
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
	
	
	private static void testInsertiImpiegato(ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		System.out.println(".......testInsertiImpiegato inizio.............");
		Impiegato marioRossi = new Impiegato("Mario", "Rossi", "mrrss57", new SimpleDateFormat("dd-MM-yyyy").parse("03-02-1945"), new SimpleDateFormat("dd-MM-yyyy").parse("03-01-2020"));
		int quantiElementiInseriti = impiegatoDAOInstance.insert(marioRossi);
		if (quantiElementiInseriti < 1)
			throw new RuntimeException("testInsertiImpiegato : FAILED, non è stato inserito alcun elemento");

		System.out.println(".......testInsertiImpiegato fine: PASSED.............");
	}
	
	private static void testUpdateImpiegato(ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		System.out.println(".......testUpdateImpiegato inizio.............");
		
		List<Impiegato> elencoVociPresenti = impiegatoDAOInstance.list();
		Impiegato impiegatoInput = elencoVociPresenti.get(0);
		int quantiElementiInseriti = impiegatoDAOInstance.insert(impiegatoInput);
		if (quantiElementiInseriti < 1)
			throw new RuntimeException("testUpdateImpiegato : FAILED, non è stato inserito alcun elemento");
		
		int quantiElementiAggiornati = impiegatoDAOInstance.update(impiegatoInput);
		if (quantiElementiAggiornati < 1)
			throw new RuntimeException("testUpdateImpiegato : FAILED, non è stato aggiornato alcun elemento");
		
		System.out.println(".......testUpdateImpiegato fine: PASSED.............");
	}
	
	private static void testDeleteImpiegato(ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		System.out.println(".......testDeleteImpiegato inizio.............");
		
		int quantiElementiInseriti = impiegatoDAOInstance.insert(new Impiegato("Roberto", "Verdi", "rbtvrd34", new SimpleDateFormat("dd-MM-yyyy").parse("03-02-1945"), new SimpleDateFormat("dd-MM-yyyy").parse("03-01-2020")));
		if (quantiElementiInseriti < 1)
			throw new RuntimeException("testDeleteImpiegato : FAILED, non è stato inserito alcun elemento");
		
		List<Impiegato> elencoVociPresenti = impiegatoDAOInstance.list();
		int numeroElementiPresentiPrimaDellaRimozione = elencoVociPresenti.size();
		if (numeroElementiPresentiPrimaDellaRimozione < 1)
			throw new RuntimeException("testDeleteImpiegato : FAILED, non ci sono voci sul DB");
		
		Impiegato ultimoDellaLista = elencoVociPresenti.get(numeroElementiPresentiPrimaDellaRimozione - 1);
		impiegatoDAOInstance.delete(ultimoDellaLista);
		
		int numeroElementiPresentiDopoDellaRimozione = impiegatoDAOInstance.list().size();
		if (numeroElementiPresentiDopoDellaRimozione != numeroElementiPresentiPrimaDellaRimozione - 1)
			throw new RuntimeException("testDeleteImpiegato : FAILED, la rimozione non è avvenuta");

		System.out.println(".......testDeleteImpiegato fine: PASSED.............");
	}
	
	private static void testFindByExample(ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		System.out.println(".......testFindByExample inizio.............");
		
		List<Impiegato> elencoVociPresenti = impiegatoDAOInstance.list();
		if (elencoVociPresenti.size() < 1)
			throw new RuntimeException("testFindByExample : FAILED, non ci sono voci sul DB");
		
		Impiegato marioRossi = new Impiegato("Mario", "Rossi");
		
		List<Impiegato> elencoVociCreatiPerExample = impiegatoDAOInstance.findByExample(marioRossi);
		for(Impiegato impiegatoItem : elencoVociCreatiPerExample) {
			System.out.println(impiegatoItem);
		}
		if(elencoVociCreatiPerExample.size() < 1) {
			throw new RuntimeException("testFindByExample : FAILED, impiegato non trovato");
		}
		System.out.println(".......testFindByExample fine: PASSED.............");
	}
}
