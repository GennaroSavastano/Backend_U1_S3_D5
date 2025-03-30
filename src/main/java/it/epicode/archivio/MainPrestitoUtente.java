package it.epicode.archivio;

import it.epicode.catalogoBibliotecario.ElementoBibliotecario;
import it.epicode.catalogoBibliotecario.Libro;
import it.epicode.prestito.Prestito;
import it.epicode.prestito.PrestitoDAO;
import it.epicode.utente.Utente;
import it.epicode.utente.UtenteDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.time.LocalDate;
import java.util.List;

public class MainPrestitoUtente {
    public static void main(String[] args) {
        // Crea la factory e l'EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();

        try {
            // Inizio transazione per la creazione dell'utente
            em.getTransaction().begin();
            System.out.println("Inizio transazione per la creazione dell'utente.");

            // Crea un utente
            Utente utente = new Utente();
            utente.setNome("Mario");
            utente.setCognome("Rossi");
            utente.setDataNascita(java.sql.Date.valueOf(LocalDate.of(1990, 1, 1))); // Uso LocalDate


            // Salva l'utente nel database
            UtenteDAO utenteDAO = new UtenteDAO(em);
            utenteDAO.salvaUtente(utente);
            System.out.println("Utente creato: " + utente.getNome() + " " + utente.getCognome());

            // Commit per rendere persistente l'utente nel database
            em.getTransaction().commit();
            System.out.println("Commit effettuato per l'utente " + utente.getNome() + " " + utente.getCognome());

            // Riapri una nuova sessione per eseguire la query
            em.close();
            em = emf.createEntityManager();

            // Verifica che l'utente sia stato effettivamente salvato nel database
            Query query = em.createQuery("SELECT u FROM Utente u WHERE u.numeroTessera = :numeroTessera");
            query.setParameter("numeroTessera", utente.getNumeroTessera());
            List<Utente> result = query.getResultList();
            if (result.isEmpty()) {
                System.out.println("Utente con numero tessera " + utente.getNumeroTessera() + " non trovato nel database!");
                return;
            } else {
                System.out.println("Utente trovato nel database con numero tessera: " + utente.getNumeroTessera());
            }

            // Inizio nuova transazione per la creazione del prestito
            em.getTransaction().begin();
            System.out.println("Inizio transazione per la creazione del prestito.");

            // Crea un libro (ElementoBibliotecario)
            ElementoBibliotecario elemento = new Libro("Il Signore degli Anelli", 1954, 1178, "J.R.R. Tolkien", "Fantasy");

            // Salva l'Elemento Bibliotecario nel database
            ArchivioBibliograficoDAO archivioDAO = new ArchivioBibliograficoDAO(em);
            archivioDAO.aggiungiElemento(elemento);
            System.out.println("Elemento Bibliotecario Salvato con codice ISBN: " + elemento.getIsbn());

            // Crea un prestito
            Prestito prestito = new Prestito();
            prestito.setUtente(utente);
            prestito.setElementoPrestito((Libro) elemento);
            prestito.setDataInizioPrestito(java.sql.Date.valueOf(LocalDate.of(2023, 1, 1)));
            prestito.setDataRestituzionePrevista(java.sql.Date.valueOf(LocalDate.of(2023, 2, 1)));
            prestito.setDataRestituzioneEffettiva(java.sql.Date.valueOf(LocalDate.of(2023, 2, 2)));

            // Salva il prestito
            PrestitoDAO prestitoDAO = new PrestitoDAO(em);
            prestitoDAO.salvaPrestito(prestito);
            System.out.println("Prestito creato per l'utente " + utente.getNome() + " " + utente.getCognome() + " con numero tessera: " + utente.getNumeroTessera());

            // Commit della transazione per il prestito
            em.getTransaction().commit();
            System.out.println("Commit effettuato per il prestito associato all'utente " + utente.getNome() + " " + utente.getCognome());

            // Verifica se il prestito è stato correttamente salvato
            Query queryPrestito = em.createQuery("SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :numeroTessera");
            queryPrestito.setParameter("numeroTessera", utente.getNumeroTessera());
            List<Prestito> prestiti = queryPrestito.getResultList();
            if (prestiti.isEmpty()) {
                System.out.println("Nessun prestito trovato per l'utente con numero tessera " + utente.getNumeroTessera());
            } else {
                System.out.println("Prestiti trovati per l'utente con numero tessera " + utente.getNumeroTessera() + ": " + prestiti.size());
            }

        } catch (Exception e) {
            // Rollback solo se la transazione è attiva
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Si è verificato un errore: " + e.getMessage());
        } finally {
            // Chiusura dell'EntityManager e della Factory
            em.close();
            emf.close();
        }
    }
}
