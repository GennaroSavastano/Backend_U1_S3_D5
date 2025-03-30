package it.epicode.archivio;

import it.epicode.catalogoBibliotecario.ElementoBibliotecario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MainElementoBibliograficoSearchTitle {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();

        ArchivioBibliograficoDAO archivioDAO = new ArchivioBibliograficoDAO(em);

        // Titolo dell'elemento da cercare
        String titoloCercato = "Il Signore degli Anelli";

        // ricerca per titolo
        List<ElementoBibliotecario> elementiCercati = archivioDAO.ricercaPerTitolo(titoloCercato);
        if (!elementiCercati.isEmpty()) {
            for (ElementoBibliotecario elemento : elementiCercati) {
                System.out.println("Elementi trovati: " + elemento.getTitolo() + " ISBN: " + elemento.getIsbn());
            }
        } else {
            System.out.println("Nessun risultato per questa ricerca");
        }

        em.close();
        emf.close();
    }
}
