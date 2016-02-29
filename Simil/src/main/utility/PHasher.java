import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;


public class PHasher {
	
	private String password;
	
	
	public PHasher(String password){
		this.password = password;
	}
	
	public String encrypt(byte[] salt){
		
		byte[] passwordBytes;
		try {
			passwordBytes = password.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			passwordBytes = password.getBytes();
		}
		byte[] toHash = new byte[salt.length + passwordBytes.length];
		for(int i = 0; i < passwordBytes.length; i++){
			toHash[i] = passwordBytes[i];
		}
		for(int i = 0; i < salt.length; i++){
			toHash[i + passwordBytes.length] = salt[i];
		}
		
		DigestSHA3 md = new DigestSHA3(256); //same as DigestSHA3 md = new SHA3.Digest256();
		md.update(toHash);
		byte[] digest = md.digest();
		byte[] saltNHash = new byte[digest.length + salt.length];
		for(int i = 0; i < salt.length; i++){
			saltNHash[i] = salt[i];
		}
		for(int i = 0; i < digest.length; i++){
			saltNHash[salt.length + i] = digest[i];
		}
		
		String hashed = org.bouncycastle.util.encoders.Hex.toHexString(saltNHash);
		return hashed;
		
	}
	
	public boolean match(String dbHash){
		if(dbHash.length() < 32){
			return false;
		}
		
		byte [] salt = new byte[32];
		
		String s = "";
		for(int i = 0; i < salt.length; i++){
			s += dbHash.charAt(i);
		}
		salt = org.bouncycastle.util.encoders.Hex.decode(s);
		
		
		String newHash = encrypt(salt);
		return dbHash.equals(newHash);
		
	}
	
	public byte[] getSalt(){
		byte[] salt;
		try{
			salt = createRandBytes();
		}
		catch(NoSuchAlgorithmException e){
			
			salt = generateSalt();
		}
		return salt;
	}
	
	private static byte[] generateSalt() {
		SecureRandom r = new SecureRandom();
		byte[] salt = new byte[16];
		r.nextBytes(salt);
		
		return salt;
	}
	
	 private static byte [] createRandBytes() 
				throws NoSuchAlgorithmException {
		 
		byte [] bytesBuffer = new byte [16];
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		sr.nextBytes(bytesBuffer);
		
		return bytesBuffer;
	}
	 
	 public void setPassword(String password){
		 this.password = password;
	 }

}
