package it.epicode.utente;

import jakarta.persistence.EntityManager;

import java.util.List;

public class UtenteDAO {

    private EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    // Metodo per salvare un utente
    public void salvaUtente(Utente utente) {
            em.persist(utente);
    }

        // Metodo per cercare un utente per nummero di tessera
        public Utente cercaUtentePerNumeroTessera (Long numeroTessera){
            return em.find(Utente.class, numeroTessera);
        }

        // metodo per cercare tutti gli utenti
        public List<Utente> cercaTuttiGliUtenti () {
            return em.createQuery("SELECT u FROM Utente u", Utente.class).getResultList();
        }

        // metodo per aggiornare un utente
        public void aggiornaUtente (Utente utente){
            em.merge(utente);
        }

        // metodo rimuovi utente per numero di tessera
        public void rimuoviUtentePerNumeroTessera (Long numeroTessera){
            Utente utente = cercaUtentePerNumeroTessera(numeroTessera);
            if (utente != null) {
                em.remove(utente);
            }
        }
    }

