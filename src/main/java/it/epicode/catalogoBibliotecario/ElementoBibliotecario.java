package it.epicode.catalogoBibliotecario;


import it.epicode.prestito.Prestito;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "elementi_bibliotecari")
public abstract class ElementoBibliotecario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "isbn", unique = true, nullable = false)
    private Long isbn;

    @Column(name = "titolo" , nullable = false, length = 100)
    private String titolo;

    @Column(name = "anno_pubblicazione")
    private int annoPubblicazione;

    @Column(name = "numero_pagine")
    private int numeroPagine;

    @OneToMany(mappedBy = "elementoPrestito")
    private  List<Prestito> prestiti;


    public ElementoBibliotecario() {
    }

    public ElementoBibliotecario(String titolo, int annoPubblicazione, int numeroPagine) {
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public ElementoBibliotecario( String titolo, int annoPubblicazione, int numeroPagine, List<Prestito> prestiti) {
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
        this.prestiti = prestiti;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public List<Prestito> getPrestiti() {
        return prestiti;
    }

    public void setPrestiti(List<Prestito> prestiti) {
        this.prestiti = prestiti;
    }
}
