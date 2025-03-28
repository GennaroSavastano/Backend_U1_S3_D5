package it.epicode.catalogoBibliotecario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "libri")
public class Libro extends ElementoBibliotecario {
    public Libro(String autore, String genere) {
        this.autore = autore;
        this.genere = genere;
    }

    public Libro(int numeroPagine, int annoPubblicazione, String titolo, String isbn, Long id, String autore, String genere) {
        super(numeroPagine, annoPubblicazione, titolo, isbn, id);
        this.autore = autore;
        this.genere = genere;
    }

    @Column(name = "autore")
    private String autore;

    @Column(name = "genere")
    private String genere;

    public Libro() {
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }
}
