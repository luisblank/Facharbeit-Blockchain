import java.security.MessageDigest;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;


public class Block {

	//Attributes
	private String data;
	private String hash;
	private String previousHash = "0000";
	private Block previousBlock;
	
	//Constructor
	public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.hash = calculateHash(data,previousHash);
	}
	
	//Methods
	private String calculateHash(String pData, String pPreviousHash) {
		String data = pData + pPreviousHash;
		String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return bytesToHex(hash); // make it printable
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
	}
	
	private String  bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }
	
	
	// Getter Methods
	public String getPreviousHash() {
		return previousHash;
	}
	
	public String getHash() {
		return hash;
	}
	
	public String getData() {
		return data;
	}
	public Block getPreviousBlock() {
		return previousBlock;
	}
	
	
	//Setter Methods
	public void setPreviousBlock(Block pBlock) {
		previousBlock = pBlock;
	}
	
}
