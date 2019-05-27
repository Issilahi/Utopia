package org.utopia.internationale.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.utopia.internationale.dao.ArticleRepository;
import org.utopia.internationale.dao.AuteurRepository;
import org.utopia.internationale.entities.Article;
import org.utopia.internationale.entities.Auteur;

@Controller
@RequestMapping(value="/admin")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private AuteurRepository auteurRepository;
	
	@Value("${dir.images}")
	private String imagesDir;
	
	@RequestMapping(value="/articles")
	public String consulter(Model model) {
		List<Article> listArticles = articleRepository.findAll();
		model.addAttribute("listArticles", listArticles);
		return "article/articles";
	}
	
	@RequestMapping(value="/formArticle",method=RequestMethod.GET)
	public String formAuteur(Model model) {
		model.addAttribute("listAuteurs",auteurRepository.findAll());
		model.addAttribute("article", new Article());
		return "article/FormArticle";
	}
	
	/*@RequestMapping(value="/SaveArticle",method=RequestMethod.POST)
	public String save(Model model,@Valid Article article, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()){
			return "article/FormArticle";
		}
		articleRepository.save(article);
		model.addAttribute("article",article);
		return "redirect:/articles";
	}*/
	
	@RequestMapping(value="/SaveArticle",method=RequestMethod.POST)
	public String save(Model model,@Valid Article article, 
			BindingResult bindingResult,
			@RequestParam(name="picture")MultipartFile file) throws Exception {
		if(bindingResult.hasErrors()) {
			model.addAttribute("listAuteurs", auteurRepository.findAll());
			return "article/FormArticle";
		}
		if(!(file.isEmpty())){
			article.setImage(file.getOriginalFilename());
		}
		articleRepository.save(article);
		if(!(file.isEmpty())){
			article.setImage(file.getOriginalFilename());
			//file.transferTo(new File(System.getProperty("user.home")+"/sco/"+file.getOriginalFilename()));
			//file.transferTo(new File(imagesDir+file.getOriginalFilename()));
			//Correcte
			file.transferTo(new File(imagesDir+article.getIdArticle()));
		}
		
		return "redirect:/articles";
	}
	
	@RequestMapping(value="/editArticle",method=RequestMethod.GET)
	public String edit(Model model, Long id) {
		Article article = articleRepository.findOne(id);
		model.addAttribute("article", article);
		model.addAttribute("listAuteurs",auteurRepository.findAll());
		
		
		
		return "article/EditArticle";
	}
	
	@RequestMapping(value="/UpdateArticle",method=RequestMethod.POST)
	public String update(@Valid Article article, 
			BindingResult bindingResult,
			@RequestParam(name="picture")MultipartFile file) throws Exception{
		if(bindingResult.hasErrors()) {
			return "EditArticle";
		}
		if(!(file.isEmpty())){
			article.setImage(file.getOriginalFilename());
		}
		articleRepository.save(article);
		
		if(!(file.isEmpty())){
			article.setImage(file.getOriginalFilename());
			//file.transferTo(new File(System.getProperty("user.home")+"/sco/"+file.getOriginalFilename()));
			//file.transferTo(new File(scolariteDir+file.getOriginalFilename()));
			//Correcte
			file.transferTo(new File(imagesDir+article.getIdArticle()));
		}
		
		return "redirect:/articles";
	}
	
	@RequestMapping(value="/supprimerArticle",method=RequestMethod.GET)
	public String supprimer(Long id) {
		Article article = articleRepository.findOne(id);
		articleRepository.delete(article);
		return "redirect:/articles";
	}
	
	@RequestMapping(value="/getPhoto", produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(Long id) throws Exception{
		File f = new File(imagesDir+id);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
}
