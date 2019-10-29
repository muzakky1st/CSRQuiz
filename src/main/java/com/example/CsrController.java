package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CsrController {

	@Autowired
	private CsrService service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<CsrModel>listCsrModel = service.listAll();
		model.addAttribute("listCsrModel", listCsrModel);
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewCsrPage(Model model) {
		CsrModel csrModel = new CsrModel();
		model.addAttribute("csrModel", csrModel);
		
		return "new_csr";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveCsr(@ModelAttribute("csrModel") CsrModel csrModel ) {
		service.save(csrModel);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditCsrPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView ("edit_csr");
		CsrModel csrModel = service.get(id);
		mav.addObject("csrModel", csrModel);
		
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteCsr(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";
	}
	
	@RequestMapping(value ="/update", method = RequestMethod.POST)
	public String updateCsr(@ModelAttribute("csrModel") CsrModel csrModel) {
		service.save(csrModel);
		return "/redirect:/";
	}
}
