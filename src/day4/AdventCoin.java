package day4;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;

public class AdventCoin {
	
	AdventCoin(){

	}

	public static Integer mine(String secretKey, String targetPrefix){
		Integer result = 1;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			Boolean foundResult = false;

			while(!foundResult){
				String currentHash = secretKey + result;
				byte[] hashResult = md.digest(currentHash.getBytes());
				String hashInHex = Hex.encodeHexString( hashResult );
				if(hashInHex.startsWith(targetPrefix)){
					foundResult = true;
				} else {
//					System.out.println("No match for :" + hashInHex + ":" + result);
					result+= 1;
				}
				
			}

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.out.println("Earth shattering kaboom.");
			e.printStackTrace();
		}

		return result;
	}
	
	public static void main(String[] args){
		System.out.println("Looking for md5 hash starting with 00000 for secret key bgvyzdsv");
		Integer result1 = AdventCoin.mine("bgvyzdsv", "00000");
		System.out.println(result1);
		
		System.out.println("Looking for md5 hash starting with 000000 for secret key bgvyzdsv");
		Integer result2 = AdventCoin.mine("bgvyzdsv", "000000");
		System.out.println(result2);
	}

}
