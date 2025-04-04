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
        long isbnCercato = 52L;

        // ricerca per ISBN
        ElementoBibliotecario elementoCercato = archivioDAO.ricercaPerISBN(isbnCercato);
        if (elementoCercato != null) {
            System.out.println("Elemento trovato: " + elementoCercato.getTitolo());
        } else {
            System.out.println("Elemento non trovato");
        }

        em.close();
        emf.close();
    }
}
