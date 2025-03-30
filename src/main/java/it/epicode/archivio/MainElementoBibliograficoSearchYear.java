package it.epicode.archivio;

import it.epicode.catalogoBibliotecario.ElementoBibliotecario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MainElementoBibliograficoSearchYear {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();

        ArchivioBibliograficoDAO archivioDAO = new ArchivioBibliograficoDAO(em);

        // Anno di pubblicazione dell'elemento da cercare
        int annoCercato = 1954;

        // ricerca per anno di pubblicazione
        List<ElementoBibliotecario> elementiCercati = archivioDAO.ricercaPerAnnoPubblicazione(annoCercato);
        if (!elementiCercati.isEmpty()) {
            for (ElementoBibliotecario elemento : elementiCercati) {
                System.out.println("Elemento trovato: " + elemento.getTitolo() + " ISBN: " + elemento.getIsbn() + " Anno di pubblicazione: " + elemento.getAnnoPubblicazione());
            }
        } else {
            System.out.println("Elemento non trovato");
        }

        em.close();
        emf.close();
    }
}
