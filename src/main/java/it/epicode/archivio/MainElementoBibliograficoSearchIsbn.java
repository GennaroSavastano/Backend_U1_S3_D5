package it.epicode.archivio;

import it.epicode.catalogoBibliotecario.ElementoBibliotecario;
import it.epicode.catalogoBibliotecario.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainElementoBibliograficoSearchIsbn {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();

        ArchivioBibliograficoDAO archivioDAO = new ArchivioBibliograficoDAO(em);


        // Isbn dell'elemento da cercare

        String isbnDaCercare = "978-3-16-148410-0";

        // cerca per ISBN
        ElementoBibliotecario elementoPerISBN = archivioDAO.cercaElementoPerISBN(isbnDaCercare);

        if (elementoPerISBN != null) {
            System.out.println("Elemento trovato per ISBN " + isbnDaCercare + ": " + elementoPerISBN);
        } else {
            System.out.println("Nessun elemento trovato per ISBN " + isbnDaCercare);
        }

        em.close();
        emf.close();
    }
}
