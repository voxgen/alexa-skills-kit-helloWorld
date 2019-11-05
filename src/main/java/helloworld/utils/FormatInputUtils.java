/**
 * 
 */
package helloworld.utils;

/**
 * @author ashobande
 *
 */
public class FormatInputUtils {
	
	//booking reference 'like-matches'
	private static final String BM_PREFIX = "BM";
	private static final String EM_PREFIX = "EM";
	private static final String YN_PREFIX = "YN";
	private static final String BN_PREFIX = "BN";
	private static final String EN_PREFIX = "EN";
	
	//obtain 1st 2 characters. Check against potential matches & replace where applicable
	public static String formatBookingReferenceUserInput(String inputValue) {
		
		String [] inputAsArray = inputValue.split("\\s+");
		StringBuilder formattedString = new StringBuilder();
		formattedString.append(inputValue);
		
		//obtain [0] [1] (prefix)
		final String char1 = inputAsArray[0];
		final String char2 = inputAsArray[1];
		final String prefix = char1 + char2;
		
		if (prefix.equals(EM_PREFIX)) {
			
			return inputValue;
			
		} else {
			
			return EM_PREFIX + formattedString.substring(2, inputValue.length());
			
		}
		
	}

}
