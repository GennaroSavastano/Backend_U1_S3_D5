package it.epicode.catalogoBibliotecario;

import it.epicode.prestito.Prestito;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "libro")
public class Libro extends ElementoBibliotecario {

    @Column(name = "autore", nullable = false)
    private String autore;

    @Column(name = "genere")
    private String genere;

    public Libro() {
    }

    public Libro(String autore, String genere) {
        this.autore = autore;
        this.genere = genere;
    }

    public Libro( String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
        super( titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public Libro( String titolo, int annoPubblicazione, int numeroPagine, List<Prestito> prestiti) {
        super( titolo, annoPubblicazione, numeroPagine, prestiti);
    }

    public Libro( String titolo, int annoPubblicazione, int numeroPagine, List<Prestito> prestiti, String autore, String genere) {
        super( titolo, annoPubblicazione, numeroPagine, prestiti);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }
}
