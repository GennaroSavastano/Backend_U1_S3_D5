package it.epicode.archivio;

import it.epicode.catalogoBibliotecario.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainElementoBibliograficoRemoveIsbn {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ArchivioBibliografico");
        EntityManager em = emf.createEntityManager();

        ArchivioBibliograficoDAO archivioDAO = new ArchivioBibliograficoDAO(em);



        String isbn = "978-3-16-148410-0";

        // Rimuovi un elemento dal catalogo dato il suo codice ISBN
       em.getTransaction().begin();
        archivioDAO.rimuoviElementoPerISBN(isbn);
        em.getTransaction().commit();

        System.out.println("Elemento con ISBN " + isbn + " rimosso dal catalogo.");

        em.close();
        emf.close();
    }
}
