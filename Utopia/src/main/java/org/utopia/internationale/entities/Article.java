package org.utopia.internationale.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Article implements Serializable{
	private static final long serialVersionUID = 1911562313656641272L;
	@Id
	private Long idArticle;
	private String titre;
	private String contenu;
	private Date date;
	@OneToMany(mappedBy="article",fetch=FetchType.LAZY)
	private Collection<Auteur> auteurs;
	
	public Article() {
		super();
	}

	public Article(String titre, String contenu, Date date) {
		super();
		this.titre = titre;
		this.contenu = contenu;
		this.date = date;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Collection<Auteur> getAuteurs() {
		return auteurs;
	}

	public void setAuteurs(Collection<Auteur> auteurs) {
		this.auteurs = auteurs;
	}
	
}
