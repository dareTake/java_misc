
/**
 * An exception to signify underflow
 * in a data structure such as stack etc.
 * @author dare
 *
 */
public final class UnderFlowException extends Exception{


	private static final long serialVersionUID = 7917240849424367011L;
	
	UnderFlowException(){
		super("Underflow");
	}

}
