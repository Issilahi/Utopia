package org.utopia.internationale.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
public class Auteur implements Serializable{
	private static final long serialVersionUID = -5697924946670767636L;
	
	@Id @GeneratedValue
	private Long idAuteur;
	@NotEmpty
	@Size(min=3, max=20)
	private String nom;
	@NotEmpty
	@Size(min=3, max=20)
	private String prenom;
	@NotEmpty
	@Size(min=3, max=30)
	@Email
	private String email;
	@OneToMany(mappedBy="auteur",fetch=FetchType.LAZY)
	private Collection<Article> articles;

	public Auteur() {
		super();
	}

	public Auteur(String nom, String prenom, String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	public Long getIdAuteur() {
		return idAuteur;
	}

	public void setIdAuteur(Long idAuteur) {
		this.idAuteur = idAuteur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Article> getArticles() {
		return articles;
	}

	public void setArticles(Collection<Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "Auteur [idAuteur=" + idAuteur + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + "]";
	}
	
	
}
