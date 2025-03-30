package it.epicode.archivio;

import it.epicode.catalogoBibliotecario.ElementoBibliotecario;
import it.epicode.catalogoBibliotecario.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainElementoBibliograficoRemoveIsbn {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();

        ArchivioBibliograficoDAO archivioDAO = new ArchivioBibliograficoDAO(em);

        // Isbn dell'elemento da rimuovere
        long isbnDaRimuovere = 1L;

        em.getTransaction().begin();
        // rimuovi per ISBN
        archivioDAO.rimuoviElemento(isbnDaRimuovere);

        em.getTransaction().commit();

        // verifica se l'elemento è stato rimosso correttamente
        ElementoBibliotecario elementoRimosso = archivioDAO.ricercaPerISBN(isbnDaRimuovere);
        if (elementoRimosso == null) {
            System.out.println("Elemento rimosso correttamente");
        } else {
            System.out.println("Elemento non rimosso");
        }

        em.close();
        emf.close();
    }
}
