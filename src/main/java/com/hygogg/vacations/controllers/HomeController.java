package com.hygogg.vacations.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hygogg.vacations.models.Country;
import com.hygogg.vacations.models.Destination;
import com.hygogg.vacations.services.CountryService;
import com.hygogg.vacations.services.DestinationService;

@Controller
public class HomeController {

	private final CountryService cs;
	private final DestinationService ds;
	
	public HomeController(CountryService cs, DestinationService ds) {
		this.cs = cs;
		this.ds = ds;
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("country", new Country());
		model.addAttribute("destination", new Destination());
		model.addAttribute("countries", cs.getAll());
		return "index.jsp";
	}
	
	@PostMapping("/country")
	public String addCountry(@Valid @ModelAttribute("country") Country c, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("destination", new Destination());
			return "index.jsp";
		} else {
			// TODO: check for country uniqueness
			cs.create(c);
			return "redirect:/";
		}
	}
	
	@PostMapping("/destination")
	public String addDestination(@Valid @ModelAttribute("destination") Destination d, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("country", new Country());
			return "index.jsp";
		} else {
			// TODO: check for destination uniqueness
			ds.create(d);
			return "redirect:/";
		}
	}
	
}
