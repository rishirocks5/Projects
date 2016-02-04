import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;

public class CipherDES {
	public static void main(String args[]) {
		String method = "";
		switch(args[0]) {
			case "1": method = "OFB";
			break;
			case "2": method = "CFB";
			break;
			case "3": method = "CBC";
			break;
			case "4": method = "ECB";
			break;
			default : System.out.println("Please Enter 1 for OFB mode, 2 for CFB mode , 3 for CBC mode and 4 for ECB mode");
			System.exit(0);
		}
		Scanner scanner = new Scanner (System.in); 
		System.out.println("Please Enter Some Text: ");
		String Wordscan = scanner.nextLine();
		try {
			KeyGenerator kg = KeyGenerator.getInstance("DES");
			Cipher c = Cipher.getInstance("DES/"+method+"/PKCS5Padding");
			Key key = kg.generateKey();
			System.out.println("Key : "+key);
			
			c.init(Cipher.ENCRYPT_MODE, key);
			byte input[] = Wordscan.getBytes();
			byte encrypted[] = c.doFinal(input);
			byte iv[] = c.getIV();

			IvParameterSpec dps = new IvParameterSpec(iv);
			c.init(Cipher.DECRYPT_MODE, key, dps);
			byte output[] = c.doFinal(encrypted);
			System.out.println("The string after Encrytion and Decryption is : ");
			System.out.println(new String(output));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
