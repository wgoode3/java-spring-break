package com.hygogg.vacations.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hygogg.vacations.models.Activity;
import com.hygogg.vacations.models.Country;
import com.hygogg.vacations.models.Destination;
import com.hygogg.vacations.services.ActivityService;
import com.hygogg.vacations.services.CountryService;
import com.hygogg.vacations.services.DestinationService;


@Controller
public class HomeController {

	private final CountryService cs;
	private final DestinationService ds;
	private final ActivityService as;
	
	public HomeController(CountryService cs, DestinationService ds, ActivityService as) {
		this.cs = cs;
		this.ds = ds;
		this.as = as;
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("country", new Country());
		model.addAttribute("destination", new Destination());
		model.addAttribute("countries", cs.topCountries());
		model.addAttribute("destinations", ds.getAll());
		return "index.jsp";
	}
	
	@PostMapping("/country")
	public String addCountry(@Valid @ModelAttribute("country") Country c, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("destination", new Destination());
			model.addAttribute("countries", cs.getAll());
			model.addAttribute("destinations", ds.getAll());
			return "index.jsp";
		} else {
			cs.create(c);
			return "redirect:/";
		}
	}
	
	@PostMapping("/destination")
	public String addDestination(@Valid @ModelAttribute("destination") Destination d, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("country", new Country());
			model.addAttribute("countries", cs.getAll());
			model.addAttribute("destinations", ds.getAll());
			return "index.jsp";
		} else {
			// TODO: check for destination uniqueness
			ds.create(d);
			return "redirect:/";
		}
	}
	
	@RequestMapping("/country/{id}")
	public String countryDetails(@PathVariable("id") Long id, Model model) {
		model.addAttribute("country", cs.getOne(id));
		model.addAttribute("destination", new Destination());
		return "country.jsp";
	}
	
	@GetMapping("/destination/{id}")
	public String displayDestination(@PathVariable("id") Long id, Model model) {
		Destination dest = ds.getOne(id);
		model.addAttribute("destination", dest);
		model.addAttribute("activity", new Activity());
		List<Activity> all = as.getAll();
		List<Activity> curr = dest.getActivities();
		for(Activity a : curr) {
			all.remove(a);
		}
		model.addAttribute("activities", all);
		return "destination.jsp";
	}
	
	@PostMapping("/destination/{id}")
	public String addDestinationToCountry(@Valid @ModelAttribute("destination") Destination d, BindingResult result, Model model, @PathVariable("id") Long id) {
		if(result.hasErrors()) {
			model.addAttribute("country", cs.getOne(id));
			return "country.jsp";
		} else {
			// TODO: check for destination uniqueness
			ds.create(d);
			return "redirect:/country/" + id;
		}
	}
	
	@PostMapping("/destination/{id}/planActivity")
	public String planActivity(@Valid @ModelAttribute("activity") Activity a, BindingResult result, @PathVariable("id") Long id, Model model) {
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
		} else {
			Activity newAct = as.create(a.getName());
			Destination d = ds.getOne(id);
			List<Activity> newActivities = d.getActivities();
			newActivities.add(newAct);		
			d.setActivities(newActivities);
			ds.update(d);
		}
		return "redirect:/destination/" + id;
	}
	
	@RequestMapping("/remove/{d_id}/{a_id}")
	public String removeActivity(@PathVariable("d_id") Long d_id, @PathVariable("a_id") Long a_id) {
		Destination d = ds.getOne(d_id);
		Activity toRemove = as.getOne(a_id);
		List<Activity> newActivities = d.getActivities();
		newActivities.remove(toRemove);
		d.setActivities(newActivities);
		ds.update(d);
		return "redirect:/destination/" + d_id;
	}
	
}
