package it.epicode.archivio;

import it.epicode.catalogoBibliotecario.ElementoBibliotecario;
import it.epicode.prestito.Prestito;
import it.epicode.utente.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ArchivioBibliograficoDAO {
    private EntityManager em;

    public ArchivioBibliograficoDAO(EntityManager em) {
        this.em = em;
    }


 // Aggiungi Elemento
    public void aggiungiElemento(ElementoBibliotecario elemento) {
        em.persist(elemento);
        System.out.println("Elemento Bibliotecario Salvato con codice ISBN : " + elemento.getIsbn());
    }

 //  Rimuovi Elemento per isbn
    public void rimuoviElemento(Long isbn) {
        ElementoBibliotecario elemento = em.find(ElementoBibliotecario.class, isbn);
            em.remove(elemento);
        }


    // Ricerca per isbn
    public ElementoBibliotecario ricercaPerISBN(Long isbn) {
        return em.find(ElementoBibliotecario.class, isbn);
    }

    // Ricerca per anno di pubblicazione
    public List<ElementoBibliotecario> ricercaPerAnnoPubblicazione(int anno) {
        TypedQuery<ElementoBibliotecario> query = em.createQuery("SELECT e FROM ElementoBibliotecario e WHERE e.annoPubblicazione = :anno", ElementoBibliotecario.class);
        query.setParameter("anno", anno);
        return query.getResultList();
    }

    // Ricerca per autore
    public List<ElementoBibliotecario> ricercaPerAutore(String autore) {
        TypedQuery<ElementoBibliotecario> query = em.createQuery("SELECT e FROM ElementoBibliotecario e WHERE e.autore = :autore", ElementoBibliotecario.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }

    // Ricerca per titolo
    public List<ElementoBibliotecario> ricercaPerTitolo(String titolo) {
        TypedQuery<ElementoBibliotecario> query = em.createQuery("SELECT e FROM ElementoBibliotecario e WHERE e.titolo LIKE :titolo", ElementoBibliotecario.class);
        query.setParameter("titolo", "%" + titolo + "%");
        return query.getResultList();
    }

}
