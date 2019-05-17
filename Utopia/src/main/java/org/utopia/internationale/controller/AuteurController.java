package org.utopia.internationale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.utopia.internationale.dao.AuteurRepository;
import org.utopia.internationale.entities.Auteur;

@Controller
public class AuteurController {
	@Autowired
	private AuteurRepository auteurRepository;
	
	@RequestMapping(value="/auteurs")
	public String consulter(Model model) {
		List<Auteur> listAuteurs = auteurRepository.findAll();
		model.addAttribute("listAuteurs", listAuteurs);
		return "auteurs";
	}
}
