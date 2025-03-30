package it.epicode.prestito;

import it.epicode.utente.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PrestitoDAO {
    private EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    // Salvare  un prestito
    public void salvaPrestito(Prestito prestito) {
        em.persist(prestito);
    }

    // Prestito per numero  di tessera
    public Prestito cercaPrestitoPerNumeroTessera(Long numeroTessera) {
        return em.find(Prestito.class, numeroTessera);
    }

    // Tutti i prestiti di un utente per numero di tessera
    public List<Prestito> ricercaPrestitiPerUtente(Utente utente) {
        TypedQuery<Prestito> query = em.createQuery("SELECT p FROM Prestito p WHERE p.utente = :utente", Prestito.class);
        query.setParameter("utente", utente);
        return query.getResultList();
    }

    // Prestiti scaduti e non ancora restituiti
    public List<Prestito> ricercaPrestitiScadutiNonRestituiti() {
        TypedQuery<Prestito> query = em.createQuery("SELECT p FROM Prestito p WHERE p.dataRestituzioneEffettiva IS NULL AND p.dataRestituzionePrevista < CURRENT_DATE", Prestito.class);
        return query.getResultList();
    }

    // Aggiorna un prestito
    public void aggiornaPrestito(Prestito prestito) {
        em.merge(prestito);
    }

    // Elimina un prestito
    public void eliminaPrestito(Prestito prestito) {
        em.remove(prestito);
    }
}
