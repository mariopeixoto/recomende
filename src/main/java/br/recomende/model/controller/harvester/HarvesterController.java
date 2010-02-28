package br.recomende.model.controller.harvester;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import br.recomende.model.document.DocumentHarvester;
import br.recomende.model.harvester.HarvesterDefinition;
import br.recomende.model.harvester.HarvesterType;
import br.recomende.model.repository.HarvesterDefinitionRepository;
import br.recomende.model.repository.HarvesterTypeRepository;

@Controller
@RequestMapping("/harvester")
@Secured("ROLE_ADMIN")
public class HarvesterController {

	private HarvesterDefinitionRepository harvesterDefinitionRepository;
	private HarvesterTypeRepository harvesterTypeRepository;
	private List<Class<? extends DocumentHarvester>> classes;
	
	@Autowired
	public HarvesterController(HarvesterDefinitionRepository harvesterDefinitionRepository,
			HarvesterTypeRepository harvesterTypeRepository,
			List<DocumentHarvester> harvesters) {
		this.harvesterDefinitionRepository = harvesterDefinitionRepository;
		this.harvesterTypeRepository = harvesterTypeRepository;
		this.classes = new ArrayList<Class<? extends DocumentHarvester>>();
		for (DocumentHarvester harvester : harvesters) {
			this.classes.add(harvester.getClass());
		}
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView get() {
		ModelAndView modelAndView = new ModelAndView("harvester/get");
		Collection<HarvesterDefinition> harvesters = this.harvesterDefinitionRepository.list();
		Collection<HarvesterType> types = this.harvesterTypeRepository.list();
		modelAndView.addObject("harvesters", harvesters);
		modelAndView.addObject("types", types);
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView post(@RequestParam String name, @RequestParam String endPoint, @RequestParam String type) {
		HarvesterDefinition harvesterDefinition = new HarvesterDefinition();
		harvesterDefinition.setName(name);
		harvesterDefinition.setEndPoint(endPoint);
		HarvesterType harvesterType = this.harvesterTypeRepository.get(type);
		harvesterDefinition.setHarvesterType(harvesterType);
		this.harvesterDefinitionRepository.put(harvesterDefinition);
		Collection<HarvesterType> types = this.harvesterTypeRepository.list();
		ModelAndView modelAndView = new ModelAndView("harvester/post");
		modelAndView.addObject("types", types);
		modelAndView.addObject("harvester", harvesterDefinition);
		return modelAndView;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.OK)
	public void delete(@PathVariable Integer id) {
		this.harvesterDefinitionRepository.remove(id);
	}

	@RequestMapping(value="/type", method=RequestMethod.GET)
	public ModelAndView getTypes() {
		Collection<HarvesterType> types = this.harvesterTypeRepository.list();
		ModelAndView modelAndView = new ModelAndView("harvester/type/get");
		modelAndView.addObject("types", types);
		modelAndView.addObject("classes", this.classes);
		return modelAndView;
	}
	
	@RequestMapping(value="/type/{type}", method=RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.OK)
	public void deleteType(@PathVariable String type) {
		this.harvesterTypeRepository.remove(type);
	}
	
	@RequestMapping(value="/type", method=RequestMethod.POST)
	public ModelAndView postType(@RequestParam String name, @RequestParam Class<? extends DocumentHarvester> clazz) {
		HarvesterType harvesterType = new HarvesterType();
		harvesterType.setType(name);
		harvesterType.setHarvesterClass(clazz);
		this.harvesterTypeRepository.put(harvesterType);
		ModelAndView modelAndView = new ModelAndView("harvester/type/post");
		modelAndView.addObject("type", harvesterType);
		modelAndView.addObject("classes", this.classes);
		return modelAndView;
	}
	
}
