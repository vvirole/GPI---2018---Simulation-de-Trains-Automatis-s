package core.xml;

public class UnvalidFileException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public UnvalidFileException(){
		super("Unvalid XML File choosen");
	}
	
}
