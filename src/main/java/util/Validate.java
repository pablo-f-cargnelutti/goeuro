package util;

public class Validate {
	public static void notNull(final Object object, final String message) {
		if ( object == null ) {
			throw new IllegalArgumentException(message);
		}		
	}	
}
