package org.utopia.internationale.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Article implements Serializable{
	private static final long serialVersionUID = 1911562313656641272L;
	@Id @GeneratedValue
	private Long idArticle;
	@NotEmpty
	@Size(min=3,max=20)
	private String titre;
	@NotEmpty
	@Size(min=3,max=200)
	private String contenu;
	private String image;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
	@ManyToOne
	@JoinColumn(name="idAuteur")
	private Auteur auteur;
	
	public Article() {
		super();
	}

	public Article(String titre, String contenu, String image, Date date, Auteur auteur) {
		super();
		this.titre = titre;
		this.contenu = contenu;
		this.image = image;
		this.date = date;
		this.auteur = auteur;
	}

	public Long getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(Long idArticle) {
		this.idArticle = idArticle;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	@Override
	public String toString() {
		return "Article [idArticle=" + idArticle + ", titre=" + titre + ", contenu=" + contenu + ", image=" + image
				+ ", date=" + date + ", auteur=" + auteur + "]";
	}

}
