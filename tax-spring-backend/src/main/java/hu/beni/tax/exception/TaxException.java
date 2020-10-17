package hu.beni.tax.exception;

public class TaxException extends RuntimeException{

	private static final long serialVersionUID = 2740802661895260480L;

	public TaxException(String message) {
		super(message);
	}

	public TaxException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
}
