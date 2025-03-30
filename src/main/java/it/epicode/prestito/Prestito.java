package it.epicode.prestito;


import it.epicode.catalogoBibliotecario.ElementoBibliotecario;
import it.epicode.utente.Utente;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.Date;
import java.util.Locale;

@Entity
@Table(name = "prestiti")
public class Prestito {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idPrestito;

    @ManyToOne
    @JoinColumn(name = "numero_tessera", nullable = false)
    private Utente utente;

    @ManyToOne
    @JoinColumn( nullable = false, referencedColumnName = "isbn")
    private ElementoBibliotecario elementoPrestito;

    @Column(name = "data_inizio_prestito", nullable = false)
    private Date dataInizioPrestito;

    @Column(name = "data_restituzione_prevista", nullable = false)
    private Date dataRestituzionePrevista;

    @Column(name = "data_restituzione_effettiva")
    private Date dataRestituzioneEffettiva;


    public Prestito() {
    }

    public Prestito(Utente utente, ElementoBibliotecario elementoPrestito, Date dataInizioPrestito, Date dataRestituzionePrevista, Date dataRestituzioneEffettiva) {
        this.utente = utente;
        this.elementoPrestito = elementoPrestito;
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = new Date(dataInizioPrestito.getTime() + (30L * 24 * 60 * 60 * 1000));
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }


    public Long getIdPrestito() {
        return idPrestito;
    }

    public void setIdPrestito(Long idPrestito) {
        this.idPrestito = idPrestito;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public ElementoBibliotecario getElementoPrestito() {
        return elementoPrestito;
    }

    public void setElementoPrestito(ElementoBibliotecario elementoPrestito) {
        this.elementoPrestito = elementoPrestito;
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
