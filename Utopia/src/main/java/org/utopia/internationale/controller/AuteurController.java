package org.utopia.internationale.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.utopia.internationale.dao.AuteurRepository;
import org.utopia.internationale.entities.Auteur;

@Controller
@RequestMapping(value="/admin")
public class AuteurController {
	@Autowired
	private AuteurRepository auteurRepository;
	
	@RequestMapping(value="/indexAuteur")
	public String pageIndex() {
		return "indexAuteur";
	}
	
	/*@RequestMapping(value="/auteurs")
	public String index(Model model,@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size,
			@RequestParam(name="motCle",defaultValue="")String mc) {
		//List<Auteur> listAuteurs = auteurRepository.findAll();
		//Page<Auteur> listAuteurs = auteurRepository.findAll(new PageRequest(p, size));
		Page<Auteur> listAuteurs = auteurRepository.chercher("%"+mc+"%",new PageRequest(page, size));
		int pagesCount = listAuteurs.getTotalPages();
		int[] pages = new int[pagesCount];
		for(int i=0;i<pagesCount;i++) pages[i]=i;
		model.addAttribute("motCle", mc);
		model.addAttribute("size", size);
		model.addAttribute("pageCourante", page);
		model.addAttribute("pages", pages);
		model.addAttribute("listAuteurs", listAuteurs);
		return "tables";
	}*/
	
	@RequestMapping(value="/auteurs")
	public String consulter(Model model) {
		List<Auteur> listAuteurs = auteurRepository.findAll();
		model.addAttribute("listAuteurs", listAuteurs);
		return "auteur/auteurs";
	}
	
	@RequestMapping(value="/formAuteur",method=RequestMethod.GET)
	public String formAuteur(Model model) {
		model.addAttribute("auteur", new Auteur());
		return "auteur/FormAuteur";
	}
	
	@RequestMapping(value="/SaveAuteur",method=RequestMethod.POST)
	public String save(Model model,@Valid Auteur auteur, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()){
			return "auteur/FormAuteur";
		}
		auteurRepository.save(auteur);
		model.addAttribute("auteur",auteur);
		return "redirect:/auteurs";
	}
	
	@RequestMapping(value="/editAuteur",method=RequestMethod.GET)
	public String edit(Model model, Long id) {
		Auteur auteur = auteurRepository.findOne(id);
		model.addAttribute("auteur", auteur);
		return "EditAuteur";
	}
	
	@RequestMapping(value="/UpdateAuteur",method=RequestMethod.POST)
	public String update(@Valid Auteur auteur, 
			BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return "EditAuteur";
		}
		auteurRepository.save(auteur);
		return "redirect:/auteurs";
	}
	
	@RequestMapping(value="/supprimerAuteur",method=RequestMethod.GET)
	public String delete(Long id, int page, int size, String motCle) {
		Auteur auteur = auteurRepository.findOne(id);
		System.out.println(auteur);
		auteurRepository.delete(auteur);
		return "redirect:/auteurs?page="+page+"&size="+size+"&motCle="+motCle;
	}
	
}
