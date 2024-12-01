package hinkula.cardatabase;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hinkula.cardatabase.domain.Car;
import hinkula.cardatabase.domain.CarRepository;
import hinkula.cardatabase.domain.Owner;
import hinkula.cardatabase.domain.OwnerRepository;

@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

	private final CarRepository carRepo;
	private final OwnerRepository ownerRepo;

	public CardatabaseApplication(CarRepository carRepo, OwnerRepository ownerRepo) {
		this.carRepo = carRepo;
		this.ownerRepo = ownerRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("Application started.");
	}

	@Override
	public void run(String... args) throws Exception {

		Owner owner1 = new Owner("John", "Johnson");
		Owner owner2 = new Owner("Mary", "Robinson");
		ownerRepo.saveAll(Arrays.asList(owner1, owner2));

		carRepo.save(new Car("Ford", "Mustang", "Red",
				"ADF-1121", 2023, 59000, owner1));
		carRepo.save(new Car("Nissan", "Leaf", "White",
				"SSJ-3002", 2020, 29000, owner2));
		carRepo.save(new Car("Toyota", "Prius",
				"Silver", "KKO-0212", 2022, 39000, owner2));

		for (Car car : carRepo.findAll()) {
			logger.info("brand: {}, model: {}", car.getBrand(), car.getModel());
		}
	}
}
