package com.cat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cat.dao.ProduitRepository;
import com.cat.entities.Produit;

@SpringBootApplication
public class CataServiceApplication implements CommandLineRunner{
	@Autowired
	private ProduitRepository produitRepository;
	public static void main(String[] args) {
		
		
		SpringApplication.run(CataServiceApplication.class, args);
		
		//ProduitRepository produitRepository=ctx.getBean(ProduitRepository.class);
		
	}

	@Override
	public void run(String... arg0) throws Exception {
		produitRepository.save(new Produit("LX 556", 456, 23));
		produitRepository.save(new Produit("HP 556", 106, 12));
		produitRepository.save(new Produit("HT 556", 357, 90));
		produitRepository.save(new Produit("OP 556", 567, 100));
		List<Produit> prods=produitRepository.findAll();
		prods.forEach(p->System.out.println(p.getDesignation()));
		
	}
}
