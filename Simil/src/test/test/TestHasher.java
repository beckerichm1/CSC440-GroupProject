package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utility.PHasher;

public class TestHasher {

	PHasher hash;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testDefault() {
		String tempPass = "password";
		PHasher hash = new PHasher(tempPass);
		// Send the pass to the database
		String pass = hash.hash(hash.getSalt());
	}

}
