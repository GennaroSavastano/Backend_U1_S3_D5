package it.epicode.archivio;

import it.epicode.catalogoBibliotecario.ElementoBibliotecario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MainElementoBibliograficoSearchAuthor {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();

        ArchivioBibliograficoDAO archivioDAO = new ArchivioBibliograficoDAO(em);

        // Autore dell'elemento da cercare
        String autoreCercato = "J.R.R. Tolkien";

        // ricerca per autore
        List<ElementoBibliotecario> elementiCercati = archivioDAO.ricercaPerAutore(autoreCercato);
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
