package com.gdmatstaffs.pets;

import com.gdmatstaffs.pets.pet.PetController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PetsApplicationTests
{
	@Autowired
	private PetController petController;

	@Test
	void contextLoads()
	{
		assertNotNull(petController);
	}
}
