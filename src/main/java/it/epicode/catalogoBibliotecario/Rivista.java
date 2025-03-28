package it.epicode.catalogoBibliotecario;


import jakarta.persistence.*;

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

    public Rivista(int numeroPagine, int annoPubblicazione, String titolo, String isbn, Long id, Periodicita periodicita) {
        super(numeroPagine, annoPubblicazione, titolo, isbn, id);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }
}
