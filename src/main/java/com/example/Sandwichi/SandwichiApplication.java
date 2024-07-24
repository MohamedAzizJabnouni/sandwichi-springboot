package com.example.Sandwichi;

import com.example.Sandwichi.entities.*;
import com.example.Sandwichi.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SandwichiApplication implements CommandLineRunner , RepositoryRestConfigurer {
	@Autowired
	private IngredientRepository ingredientRepository;
	@Autowired
	private CommandeRepository commandeRepository;
	@Autowired
	private SandwitchRepository sandwitchRepository;

	@Autowired
	private AdministrateurRepository administrateurRepository;

	@Autowired
	ClientRepository clientRepository;

	public static void main(String[] args) {
		SpringApplication.run(SandwichiApplication.class, args);
	}

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

		RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
		config.exposeIdsFor(Client.class,Administrateur.class, Commande.class,Ingredient.class,Sandwitch.class);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		// Administrateurs
		Administrateur administrateur1 = new Administrateur("Doe", "John", "john.doe@example.com");
		Administrateur administrateur2 = new Administrateur("Smith", "Alice", "alice.smith@example.com");
		administrateurRepository.save(administrateur1);
		administrateurRepository.save(administrateur2);

		// Clients
		Client client1 = new Client("Johnson", "Michael", "michael.johnson@example.com", null);
		Client client2 = new Client("Brown", "Emma", "emma.brown@example.com", null);
		Client client3 = new Client("Williams", "Olivia", "olivia.williams@example.com", null);
		Client client4 = new Client("Jones", "William", "william.jones@example.com", null);
		clientRepository.save(client1);
		clientRepository.save(client2);
		clientRepository.save(client3);
		clientRepository.save(client4);


		List<Client> clientList1 = new ArrayList();
		clientList1.add(client1);
		List<Client> clientList2 = new ArrayList();
		clientList2.add(client2);
		List<Client> clientList3 = new ArrayList();
		clientList3.add(client3);
		List<Client> clientList4 = new ArrayList();
		clientList4.add(client4);




		// Commandes
		Commande commande1 = new Commande(new Date(), "Pending", null,client1);
		Commande commande2 = new Commande(new Date(), "Completed",null , client2);
		Commande commande3 = new Commande(new Date(), "Pending", null , client3);
		Commande commande4 = new Commande(new Date(), "Shipped",null, client4);
		commandeRepository.save(commande1);
		commandeRepository.save(commande2);
		commandeRepository.save(commande3);
		commandeRepository.save(commande4);

		// Sandwitches
		Sandwitch sandwitch1 = new Sandwitch("Lablabi", "Tunisian chickpea stew", "photo1", 22.2, commande1, null);
		Sandwitch sandwitch2 = new Sandwitch("Thon", "Tuna sandwich", "photo2", 22.2, commande1, null);
		Sandwitch sandwitch3 = new Sandwitch("Poulet", "Chicken sandwich", "photo3", 18.5, commande2, null);
		Sandwitch sandwitch4 = new Sandwitch("Jambon", "Ham sandwich", "photo4", 20.0, commande2, null);
		Sandwitch sandwitch5 = new Sandwitch("Vegan", "Vegan sandwich", "photo5", 19.0, commande3, null);
		Sandwitch sandwitch6 = new Sandwitch("BLT", "Bacon, lettuce, and tomato", "photo6", 21.5, commande4, null);
		sandwitchRepository.save(sandwitch1);
		sandwitchRepository.save(sandwitch2);
		sandwitchRepository.save(sandwitch3);
		sandwitchRepository.save(sandwitch4);
		sandwitchRepository.save(sandwitch5);
		sandwitchRepository.save(sandwitch6);

		// Ingredients
		Ingredient ingredient1 = new Ingredient("Chickpeas", 5.0, 2, "photo1", sandwitch1);
		Ingredient ingredient2 = new Ingredient("Tuna", 11.2, 1, "photo2", sandwitch2);
		Ingredient ingredient3 = new Ingredient("Chicken", 8.5, 1, "photo3", sandwitch3);
		Ingredient ingredient4 = new Ingredient("Ham", 7.0, 1, "photo4", sandwitch4);
		Ingredient ingredient5 = new Ingredient("Vegan patty", 9.0, 1, "photo5", sandwitch5);
		Ingredient ingredient6 = new Ingredient("Bacon", 10.0, 1, "photo6", sandwitch6);
		ingredientRepository.save(ingredient1);
		ingredientRepository.save(ingredient2);
		ingredientRepository.save(ingredient3);
		ingredientRepository.save(ingredient4);
		ingredientRepository.save(ingredient5);
		ingredientRepository.save(ingredient6);

		// Associate ingredients with sandwiches
		List<Ingredient> ingredients1 = new ArrayList<>();
		ingredients1.add(ingredient1);
		ingredients1.add(ingredient2);
		sandwitch1.setIngredients(ingredients1);
		sandwitch2.setIngredients(ingredients1);

		List<Ingredient> ingredients2 = new ArrayList<>();
		ingredients2.add(ingredient3);
		ingredients2.add(ingredient4);
		sandwitch3.setIngredients(ingredients2);
		sandwitch4.setIngredients(ingredients2);

		List<Ingredient> ingredients3 = new ArrayList<>();
		ingredients3.add(ingredient5);
		sandwitch5.setIngredients(ingredients3);

		List<Ingredient> ingredients4 = new ArrayList<>();
		ingredients4.add(ingredient6);
		sandwitch6.setIngredients(ingredients4);

		sandwitchRepository.save(sandwitch1);
		sandwitchRepository.save(sandwitch2);
		sandwitchRepository.save(sandwitch3);
		sandwitchRepository.save(sandwitch4);
		sandwitchRepository.save(sandwitch5);
		sandwitchRepository.save(sandwitch6);

		// Associate sandwiches with commandes
		List<Sandwitch> sandwitches1 = new ArrayList<>();
		sandwitches1.add(sandwitch1);
		sandwitches1.add(sandwitch2);
		commande1.setSandwitches(sandwitches1);

		List<Sandwitch> sandwitches2 = new ArrayList<>();
		sandwitches2.add(sandwitch3);
		sandwitches2.add(sandwitch4);
		commande2.setSandwitches(sandwitches2);

		List<Sandwitch> sandwitches3 = new ArrayList<>();
		sandwitches3.add(sandwitch5);
		commande3.setSandwitches(sandwitches3);

		List<Sandwitch> sandwitches4 = new ArrayList<>();
		sandwitches4.add(sandwitch6);
		commande4.setSandwitches(sandwitches4);

		commandeRepository.save(commande1);
		commandeRepository.save(commande2);
		commandeRepository.save(commande3);
		commandeRepository.save(commande4);

		// Associate commandes with clients
		List<Commande> commandes1 = new ArrayList<>();
		commandes1.add(commande1);
		client1.setCommandes(commandes1);

		List<Commande> commandes2 = new ArrayList<>();
		commandes2.add(commande2);
		client2.setCommandes(commandes2);

		List<Commande> commandes3 = new ArrayList<>();
		commandes3.add(commande3);
		client3.setCommandes(commandes3);

		List<Commande> commandes4 = new ArrayList<>();
		commandes4.add(commande4);
		client4.setCommandes(commandes4);


	}

























	}

