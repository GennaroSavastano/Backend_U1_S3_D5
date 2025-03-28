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

       //crea un nuovo libro
        Libro libro = new Libro();
        libro.setTitolo("Il Signore degli Anelli");
        libro.setAnnoPubblicazione(1954);
        libro.setNumeroPagine(1000);
        libro.setIsbn("978-3-16-148410-0");
        libro.setAutore("J. R. R. Tolkien");
        libro.setGenere("Fantasy");

        // crea una nuova rivista
        Rivista rivista = new Rivista();
        rivista.setTitolo("National Geographic");
        rivista.setAnnoPubblicazione(2023);
        rivista.setNumeroPagine(100);
        rivista.setIsbn("978-3-16-148410-0");
        rivista.setPeriodicita(Periodicita.MENSILE);


        em.getTransaction().begin();
        archivioDAO.aggiungiElemento(libro);
        archivioDAO.aggiungiElemento(rivista);
        em.getTransaction().commit();

        System.out.println("Elemento aggiunto al catalogo: " + libro.getTitolo());
        System.out.println("Elemento aggiunto al catalogo: " + rivista.getTitolo());

        em.close();
        emf.close();


    }

}
