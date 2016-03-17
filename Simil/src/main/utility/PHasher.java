package utility;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.SecureRandom;
import java.math.BigInteger;

//bouncycastle support for encryption algorithms
import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;

public class PHasher {

	// The password to encrypt, received from user
	private String password;

	// Constructors

	// no arg constructor, password defaults to null
	public PHasher() {
		this.password = null;
	}

	// argument with the password included
	public PHasher(String password) {
		this.password = password;
	}

	// Hash the password stored in the object using the provided salt
	public String hash(byte[] salt) {
		// If they neglected to provide a salt it will be generated for them
		if (salt == null) {
			salt = this.getSalt();
		}

		// A byte array version of the password is generated, UTF-8 is preferred
		byte[] passwordBytes;
		try {
			passwordBytes = password.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			passwordBytes = password.getBytes();
		}

		// The value to be hashed is the concatenation of the salt and the
		// password
		byte[] toHash = new byte[salt.length + passwordBytes.length];
		// Placing the password to the beginning
		for (int i = 0; i < passwordBytes.length; i++) {
			toHash[i] = passwordBytes[i];
		}
		// Appending the salt to the end of the password
		for (int i = 0; i < salt.length; i++) {
			toHash[i + passwordBytes.length] = salt[i];
		}

		// Take the hash of the password + salt
		DigestSHA3 md = new DigestSHA3(256); // same as DigestSHA3 md = new
												// SHA3.Digest256();
		// Hash performed on toHash array
		md.update(toHash);
		// Place the result in digest
		byte[] digest = md.digest();

		// Append the salt to the beginning of the hash for recovery
		byte[] saltNHash = new byte[digest.length + salt.length];
		// Salt placed at the beginning
		for (int i = 0; i < salt.length; i++) {
			saltNHash[i] = salt[i];
		}
		// Hashed value placed at the end
		for (int i = 0; i < digest.length; i++) {
			saltNHash[salt.length + i] = digest[i];
		}
		// Convert the hash to a hexadecimal string
		// For the unaware hex represents each byte with two chars rom 0-f
		String hashed = org.bouncycastle.util.encoders.Hex.toHexString(saltNHash);
		return hashed;

	}

	// A version that does not change state, but allows specification of
	// password and salt
	public String hash(byte[] salt, String password) {
		String temp = this.password;
		this.password = password;
		String result = hash(salt);
		this.password = temp;
		return result;
	}

	// No arg version, adds salt on callers behalf
	public String hash() {
		return hash(getSalt());
	}

	// Hash just the password provided, provides salt on caller's behalf
	public String hash(String password) {
		return hash(getSalt(), password);

	}

	// See if the database hash matches the password attribute
	public boolean match(String dbHash) {

		// If the hash is shorter than the salt it automatically fails
		if (dbHash.length() < 32) {
			return false;
		}

		// Allocate space for the salt
		byte[] salt = new byte[32];

		// Read the string version of the salt and append it to s
		String s = "";
		for (int i = 0; i < salt.length; i++) {
			s += dbHash.charAt(i);
		}
		// Convert the string to the byte array
		salt = org.bouncycastle.util.encoders.Hex.decode(s);

		// Hash the stored password with the salt to see if it matches the
		// stored hash
		String newHash = hash(salt);
		return dbHash.equals(newHash);

	}

	// Allow the user to match a hash with another password without changing
	// state
	public boolean match(String dbHash, String password) {
		String temp = this.password;
		this.password = password;
		boolean result = match(dbHash);
		this.password = temp;
		return result;
	}

	// Generate the salt, will try the sha1 psuedo random number generator,
	// if that fails, default java secure random generator
	public byte[] getSalt() {
		byte[] salt;
		try {
			// try sha1 prng
			salt = createRandBytes();
		} catch (NoSuchAlgorithmException e) {
			// back up with default random
			salt = generateSalt();
		}
		return salt;
	}

	// Simplistic SecureRandom salt generation
	private static byte[] generateSalt() {
		SecureRandom r = new SecureRandom();
		byte[] salt = new byte[16];
		r.nextBytes(salt);

		return salt;
	}

	// Generate the salt with specified algorithm
	private static byte[] createRandBytes() throws NoSuchAlgorithmException {

		byte[] bytesBuffer = new byte[16];
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		sr.nextBytes(bytesBuffer);

		return bytesBuffer;
	}

	// Re-assign password stored in dbhash
	public void setPassword(String password) {
		this.password = password;
	}

	// Generate a random password of 13 chars
	public String generateRandomPassword() {

		SecureRandom sr = new SecureRandom();
		return new BigInteger(64, sr).toString(32);
	}

}
