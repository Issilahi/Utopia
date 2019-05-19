package org.utopia.internationale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.utopia.internationale.dao.AuteurRepository;
import org.utopia.internationale.entities.Auteur;

@Controller
public class AuteurController {
	@Autowired
	private AuteurRepository auteurRepository;
	
	@RequestMapping(value="/auteurs")
	public String consulter(Model model,@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size,
			@RequestParam(name="motCle",defaultValue="")String mc) {
		//List<Auteur> listAuteurs = auteurRepository.findAll();
		//Page<Auteur> listAuteurs = auteurRepository.findAll(new PageRequest(p, size));
		Page<Auteur> listAuteurs = auteurRepository.chercher("%"+mc+"%",new PageRequest(page, size));
		int pagesCount = listAuteurs.getTotalPages();
		int[] pages = new int[pagesCount];
		for(int i=0;i<pagesCount;i++) pages[i]=i;
		model.addAttribute("motCle", mc);
		model.addAttribute("pageCourante", page);
		model.addAttribute("pages", pages);
		model.addAttribute("listAuteurs", listAuteurs);
		return "auteurs";
	}
}
