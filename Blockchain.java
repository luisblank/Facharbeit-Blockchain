
public class Blockchain {
	
	//Attributes
	private Block erster, letzter, current;
    private int anzahl;
	
    //Constructor
	public Blockchain() {
		erster = null;
		letzter = null;
		current = null;
		anzahl = 0;
	}
	
	//Methods
	
	//Checks if blockchain is empty and returns bool
	public boolean isEmpty() {
        if (anzahl == 0)
            return true;
        else
            return false;
    }
	
	//Counts Blocks
	public int countBlocks() {
        return anzahl;
    }
	
	//Sets current to First
	public void toFirst() {
        current = erster;
    }
	
	// Sets current to last
	public void toLast() {
        current = letzter;
    }
	
	//Checks that current isn't null
	public boolean hasAccess() {
        if(current != null) return true;
        else return false;
    }
	
	//Sets Current to its previous block
	public void toPreviousBlock() {
        if(hasAccess())current = current.getPreviousBlock();
    }
	
	//Creates Genesisblock or appends new block
	public void createBlock(String data) {
        
        // 1. Fall: Liste ist leer
        if (this.isEmpty()) {
        	Block b = createGenesisBlock(data);
            erster = b;
            letzter = erster;
            erster.setPreviousBlock(null);
        }
        // 2. Fall: Liste ist NICHT leer
        else {
        	Block b = new Block(data, letzter.getHash());
        	b.setPreviousBlock(letzter);
            letzter = b;
            
        }
        anzahl++;
    }
	
	//prints out the current Block on the console
	public void printBlock() {
		System.out.println("\n=====================\n\nBlockhash: " + current.getHash() 
		+ "\nPreviousHash: " + current.getPreviousHash() + "\nData: " + current.getData() + 
		"\n\n=====================");
		current = current.getPreviousBlock();
	}
	
	//Helperfunction that creates Genesis Block
	private Block createGenesisBlock(String data) {
		return new Block(data, "0000");
	}
	
	//Prints whole Blockchain, starting with its last and going to its first
	public void showWholeBlockchian() {
		current = letzter;
		while (current != null) {
			printBlock();
			if (current == null) {
				System.out.println("\n\nGENESISBLOCK REACHED!");
				return;
			}
			current = current.getPreviousBlock();
			
		}
	}
	
	//Checks that every previousHash matches the Hash of the previous Block
	public void checkHashChain() {
		current = letzter;
		while (current.getPreviousBlock() != null) {
			if (current.getPreviousHash() == current.getPreviousBlock().getHash()) {
				System.out.println("Hash matches!");
				System.out.println("Hash: " + current.getPreviousHash() + "\npreviousHash: " 
						+ current.getPreviousBlock().getHash());
			} else {
				System.out.println("Hash does not match!");
				System.out.println("Hash: " + current.getPreviousHash() + "\npreviousHash: " 
						+ current.getPreviousBlock().getHash());
				return;
			}
			current = current.getPreviousBlock();
		} 
	}
	
	//Searches the whole Blockchain for its Data
	public Block searchData(String pData) {
		boolean found = false;
		current = letzter;
		while(current != null) {
			if (current.getData().equals(pData)) {
				printBlock();
				found = true;
				return current;
			}
			current = current.getPreviousBlock();
		}
		if (found == false) {
			System.out.println("Data not Found");
		}
		return null;
	}
	
}
