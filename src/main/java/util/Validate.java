package util;

public class Validate {
	public static void notNull(final Object object, final String message) {
		if ( object == null ) {
			throw new IllegalArgumentException(message);
		}		
	}
	
	public static void notNullOrEmpty(final Object[] array, final String message) {
		if ( array == null || array.length == 0 ) {
			throw new IllegalArgumentException(message);
		}		
	}
	
	public static void notNullOrEmpty(final String string, final String message) {
		if ( string == null || string.isEmpty() ) {
			throw new IllegalArgumentException(message);
		}		
	}
}
