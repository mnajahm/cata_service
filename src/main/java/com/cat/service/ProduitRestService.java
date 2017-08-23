package com.cat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.cat.dao.ProduitRepository;
import com.cat.entities.Produit;

@RestController
public class ProduitRestService {
	@Autowired
	private ProduitRepository produitRepository;
	@RequestMapping(value="/produits", method=RequestMethod.GET)
	public List<Produit> listProduit(){
		
		return produitRepository.findAll();
		
	}
	
	@RequestMapping(value="/pageproduits", method=RequestMethod.GET)
	public Page<Produit> pageProduit(int page,int size){
		
		return produitRepository.findAll(new PageRequest(page, size));
		
	}
	
	
	
	@RequestMapping(value="/chercherproduits", method=RequestMethod.GET)
	public Page<Produit> chercherProduit(
			
			String mc,
			//pour donner a la page une valeur par defaut
			@RequestParam(name="page",defaultValue="0")
			int page,
			@RequestParam(name="size",defaultValue="5")
			int size)
	{
		
		return produitRepository.chercherProduits("%"+mc+"%", new PageRequest(page,size));
		
	}
	
	
	@RequestMapping(value="/produits/{id}", method=RequestMethod.GET)
	public Produit listProduit(@PathVariable("id") Long id){
		
		return produitRepository.findOne(id);
		
	}
	
	@RequestMapping(value="/produits", method=RequestMethod.POST)
	public Produit save(@RequestBody Produit p){
		
		return produitRepository.save(p);
		
	}
	
	@RequestMapping(value="/produits/{id}", method=RequestMethod.PUT)
	public Produit update(@RequestBody Produit p,@PathVariable Long id){
		
		return produitRepository.saveAndFlush(p);
		
	}
	
	@RequestMapping(value="/produits/{id}", method=RequestMethod.DELETE)
	public  void delete(@PathVariable Long id){
		
	 produitRepository.delete(id);
		
	}

}
