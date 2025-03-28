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


    // Aggiungi un elemento al catalogo
    public void aggiungiElemento(ElementoBibliotecario elemento) {
        em.persist(elemento);
    }

    // Rimuovi un elemento dal catalogo dato il suo codice ISBN
    public void rimuoviElementoPerISBN(String isbn) {
        ElementoBibliotecario elemento = em.find(ElementoBibliotecario.class, isbn);
        if (elemento != null) {
            em.remove(elemento);
        }
    }

    // cerca un elemento dal catalogo dato il suo codice ISBN
    public ElementoBibliotecario cercaElementoPerISBN(String isbn) {
        return em.find(ElementoBibliotecario.class, isbn);
    }

    // ricerca per anno di pubblicazione
    public List<ElementoBibliotecario> cercaElementiPerAnnoPubblicazione(int anno) {
        TypedQuery<ElementoBibliotecario> query = em.createQuery("SELECT e FROM ElementoBibliotecario e WHERE e.annoPubblicazione = :anno", ElementoBibliotecario.class);
        query.setParameter("anno", anno);
        return query.getResultList();
    }

    // ricerca per autore
    public List<ElementoBibliotecario> cercaElementiPerAutore(String autore) {
        TypedQuery<ElementoBibliotecario> query = em.createQuery("SELECT e FROM ElementoBibliotecario e WHERE e.autore = :autore", ElementoBibliotecario.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }

    // ricerca per titolo o parte di esso
    public List<ElementoBibliotecario> cercaElementiPerTitolo(String titolo) {
        TypedQuery<ElementoBibliotecario> query = em.createQuery("SELECT e FROM ElementoBibliotecario e WHERE e.titolo LIKE :titolo", ElementoBibliotecario.class);
        query.setParameter("titolo", "%" + titolo + "%");
        return query.getResultList();
    }

    // prestito scaduto
    public List<Prestito> cercaPrestitiScaduti() {
        TypedQuery<Prestito> query = em.createQuery("SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < CURRENT_DATE", Prestito.class);
        return query.getResultList();
    }

    // prestiti per utente
    public List<Prestito> cercaPrestitiPerUtente(Utente utente) {
        TypedQuery<Prestito> query = em.createQuery("SELECT p FROM Prestito p WHERE p.utente = :utente", Prestito.class);
        query.setParameter("utente", utente);
        return query.getResultList();
    }
}
