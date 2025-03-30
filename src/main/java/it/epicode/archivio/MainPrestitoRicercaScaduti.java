package it.epicode.archivio;

import it.epicode.catalogoBibliotecario.Libro;
import it.epicode.prestito.Prestito;
import it.epicode.prestito.PrestitoDAO;
import it.epicode.utente.Utente;
import it.epicode.utente.UtenteDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Date;
import java.util.List;

public class MainPrestitoRicercaScaduti {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();

        // Crea un utente
        Utente utente = new Utente();
        utente.setNome("Mario");
        utente.setCognome("Rossi");
        utente.setDataNascita(new Date(90, 2, 1)); // 1990-01-01

        // Salva l'utente nel database
        em.getTransaction().begin();
        UtenteDAO utenteDAO = new UtenteDAO(em);
        utenteDAO.salvaUtente(utente);
        em.getTransaction().commit();

        System.out.println("Utente creato: " + utente.getNome() + " " + utente.getCognome());

        // Crea un libro
        Libro libro = new Libro("Il Signore degli Anelli", 1954, 1178, "J.R.R. Tolkien", "Fantasy");

        // Salva il libro nel database
        em.getTransaction().begin();
        em.persist(libro);  // Salva il libro
        em.getTransaction().commit();

        System.out.println("Libro salvato: " + libro.getTitolo());

        // Crea un prestito
        Prestito prestito = new Prestito();
        prestito.setUtente(utente);
        prestito.setElementoPrestito(libro);  // Usa il libro salvato
        prestito.setDataInizioPrestito(new Date(2025, 03, 01));
        prestito.setDataRestituzionePrevista(new Date(2025, 04, 02));

        // Salva il prestito nel database
        em.getTransaction().begin();
        PrestitoDAO pre = new PrestitoDAO(em);
        pre.salvaPrestito(prestito);
        em.getTransaction().commit();
        System.out.println("Prestito creato per " + prestito.getUtente().getNome() + " con il libro " + prestito.getElementoPrestito().getTitolo());

        // Ricerca dei prestiti scaduti
        try {
            PrestitoDAO prestitoDAO1 = new PrestitoDAO(em);
            List<Prestito> prestitiScaduti = prestitoDAO1.ricercaPrestitiScadutiNonRestituiti();
            if (!prestitiScaduti.isEmpty()) {
                System.out.println("Prestiti scaduti trovati:");
                for (Prestito p : prestitiScaduti) {
                    System.out.println("Prestito con libro: " + p.getElementoPrestito().getTitolo());
                    System.out.println("Data inizio prestito: " + p.getDataInizioPrestito());
                    System.out.println("Restituzione prevista: " + p.getDataRestituzionePrevista());
                }
            } else {
                System.out.println("Nessun prestito scaduto trovato");
            }
        } catch (Exception e) {
            System.err.println("Errore durante la ricerca dei prestiti scaduti: " + e.getMessage());
            e.printStackTrace();
        }

        em.close();
        emf.close();
    }
}
