package exercises;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ValidationTest {
	Validation v = new Validation();

	@Test
	void testEmailValidation() throws Exception {

		assertTrue(v.isEmailValid("example@example.com"));
		assertFalse(v.isEmailValid("example@example"));
		
		
	}

	@Test
	void testNumberValidation() throws Exception {

		assertTrue(v.isNumberValid("123/4567890"));
		assertFalse(v.isNumberValid("123-4567890"));
		assertFalse(v.isNumberValid("1234567890"));

	}

}
