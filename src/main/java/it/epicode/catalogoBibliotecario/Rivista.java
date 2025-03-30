package it.epicode.catalogoBibliotecario;


import it.epicode.prestito.Prestito;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "riviste")
public class Rivista extends ElementoBibliotecario{


@Enumerated(EnumType.STRING)
@Column(name = "periodicita")
private Periodicita periodicita;

    public Rivista() {
    }

    public Rivista(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    public Rivista( String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super( titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Rivista( String titolo, int annoPubblicazione, int numeroPagine, List<Prestito> prestiti) {
        super(titolo, annoPubblicazione, numeroPagine, prestiti);
    }

    public Rivista( String titolo, int annoPubblicazione, int numeroPagine, List<Prestito> prestiti, Periodicita periodicita) {
        super(titolo, annoPubblicazione, numeroPagine, prestiti);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }
}
