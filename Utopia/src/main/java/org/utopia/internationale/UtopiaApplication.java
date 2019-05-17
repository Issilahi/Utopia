package org.utopia.internationale;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.utopia.internationale.dao.ArticleRepository;
import org.utopia.internationale.dao.AuteurRepository;
import org.utopia.internationale.entities.Article;
import org.utopia.internationale.entities.Auteur;

@SpringBootApplication
public class UtopiaApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(UtopiaApplication.class, args);
		AuteurRepository auteurRepository = ctx.getBean(AuteurRepository.class);
		ArticleRepository articleRepository = ctx.getBean(ArticleRepository.class);
		Auteur auteur1 = auteurRepository.save(new Auteur("Dupont","david","dupont@free.fr"));
		Auteur auteur2 = auteurRepository.save(new Auteur("Durand","celine","durand@orange.fr"));
		Auteur auteur3 = auteurRepository.save(new Auteur("Batho","delphine","batho@free.fr"));
		
		Article article1 = articleRepository.save(new Article("Article1","Bonjour Dupont","https://fakeimg.pl/300/",new Date(), auteur1));
		Article article2 = articleRepository.save(new Article("Article2","Bonjour Dupont","https://fakeimg.pl/250x100/",new Date(), auteur1));
		Article article3 = articleRepository.save(new Article("Article3","Bonjour Durand","https://fakeimg.pl/250x100/ff0000/",new Date(), auteur2));
		Article article4 = articleRepository.save(new Article("Article4","Bonjour Durand","https://fakeimg.pl/250x100/ff0000/",new Date(), auteur2));
		
	}

}
