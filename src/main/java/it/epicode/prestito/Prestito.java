package it.epicode.prestito;


import it.epicode.catalogoBibliotecario.ElementoBibliotecario;
import it.epicode.utente.Utente;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "prestiti")
public class Prestito {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "elemento_bibliotecario_id", nullable = false)
    private ElementoBibliotecario elementoBibliotecario;

    @Column(name = "data_inizio_prestito", nullable = false)
    private Date dataInizioPrestito;

    @Column(name = "data_restituzione_prevista", nullable = false)
    private Date dataRestituzionePrevista;

    @Column(name = "data_restituzione_effettiva")
    private Date dataRestituzioneEffettiva;


    public Prestito() {
    }

    public Prestito(Date dataRestituzioneEffettiva, Date dataRestituzionePrevista, Date dataInizioPrestito, ElementoBibliotecario elementoBibliotecario, Utente utente, Long id) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
        this.dataRestituzionePrevista = dataRestituzionePrevista;
        this.dataInizioPrestito = dataInizioPrestito;
        this.elementoBibliotecario = elementoBibliotecario;
        this.utente = utente;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public ElementoBibliotecario getElementoBibliotecario() {
        return elementoBibliotecario;
    }

    public void setElementoBibliotecario(ElementoBibliotecario elementoBibliotecario) {
        this.elementoBibliotecario = elementoBibliotecario;
    }

    public Date getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(Date dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
    }

    public Date getDataRestituzionePrevista() {
        return dataRestituzionePrevista;
    }

    public void setDataRestituzionePrevista(Date dataRestituzionePrevista) {
        this.dataRestituzionePrevista = dataRestituzionePrevista;
    }

    public Date getDataRestituzioneEffettiva() {
        return dataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(Date dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }
}
