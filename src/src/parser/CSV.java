package src.parser;

public class CSV {

	private static final String COMMA = ",";
	private static final String LINE_BREAK = "\n";
	
	private final StringBuilder content;

	private CSV() {
		this.content = new StringBuilder();
	}
	
	public static CSV emptyCSV() {
		return new CSV();
	}
	
	public void addNewLine() {
		removeTrailing(COMMA);
		content.append(LINE_BREAK);
	}
	
	private void removeTrailing(final String toBeRemoved) {
		int from = this.content.length()- toBeRemoved.length();
		int to = this.content.length();
		if( this.content.lastIndexOf(toBeRemoved) == from ) {			
			this.content.delete(from, to);
		}
	}

	public void addStringValue(final String value) {
		if ( value != null ) {
			this.content.append(value).append(COMMA);
		}
	}
	
	public void addIntValue(final int value) {
		this.content.append(value).append(COMMA);		
	}
	
	public void addDoubleValue(final double value) {
		this.content.append(value).append(COMMA);		
	}

	public void addObjectValue(final CSVData csvData) {
		if ( csvData != null ) {
			this.content.append(csvData.toCSV().toString());
		}
	}
	
	@Override
	public String toString() {
		if( !isEmpty() )
			removeTrailing(LINE_BREAK);
		return content.toString();
	}

	private boolean isEmpty() {
		return content.toString().isEmpty();
	}
}
