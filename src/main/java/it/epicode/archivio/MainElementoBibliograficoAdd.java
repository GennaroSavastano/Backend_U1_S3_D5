package it.epicode.archivio;

import it.epicode.catalogoBibliotecario.ElementoBibliotecario;
import it.epicode.catalogoBibliotecario.Libro;
import it.epicode.catalogoBibliotecario.Periodicita;
import it.epicode.catalogoBibliotecario.Rivista;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainElementoBibliograficoAdd {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
               EntityManager em = emf.createEntityManager();

        ArchivioBibliograficoDAO archivioDAO = new ArchivioBibliograficoDAO(em);

      // crea elementi libro

        Libro libro1 = new Libro( "Il Signore degli Anelli", 1954, 1178, "J.R.R. Tolkien", "Fantasy");
        Libro libro2 = new Libro("1984", 1949, 328, "George Orwell", "Distopico");



        // genera elementi rivista
        Rivista rivista1 = new Rivista("National Geographic", 1888, 100, Periodicita.MENSILE);
        Rivista rivista2 = new Rivista("Scientific American", 1890, 100, Periodicita.MENSILE);

        em.getTransaction().begin();
        // aggiungi elementi al catalogo
        archivioDAO.aggiungiElemento(libro1);
        archivioDAO.aggiungiElemento(libro2);

        archivioDAO.aggiungiElemento(rivista1);
        archivioDAO.aggiungiElemento(rivista2);

        em.getTransaction().commit();


        em.close();
        emf.close();
    }

}
