/**
 * 
 */
package helloworld.response;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author ashobande
 *
 */
public class GenericProcessBookingRefRequest {
	
	private static final Logger LOGGER = LogManager.getLogger(GenericProcessBookingRefRequest.class);
	
	private static final String BOOKING_REF_5665X = "Booking reference E M 5 6 6 5 X from London to Sochi is for 1 adult only.";
	private static final String BOOKING_REF_6124X = "Booking reference E M 6 1 2 4 from Paris to Frankfurt is for 2 adults and 2 infants.";
	private static final String BOOKING_REF_3774Y = "Booking reference E M 3 7 7 4 Y from Lisbon to Bordeaux is for 4 adults.";
	private static final String BOOKING_REF_2229Y = "Booking reference E M 2 2 2 9 Y from Porto to Bristol is for 2 adults and 1 infant.";
	private static final String BOOKING_REF_3555X = "Booking reference E M 3 5 5 5 X from Athens to Moscow is for 1 adult only.";
	
	private HashMap<String, String> bookingRefStaticDataset = null;
	
	public GenericProcessBookingRefRequest() {
		
		this.bookingRefStaticDataset = new HashMap<>();
		
		this.bookingRefStaticDataset.put("EM5665X", "EM 5 6 6 5 X");
		this.bookingRefStaticDataset.put("EM6124X", "EM 6 1 2 4 X");
		this.bookingRefStaticDataset.put("EM3774Y", "EM 3 7 7 4 X");
		this.bookingRefStaticDataset.put("EM2229Y", "EM 2 2 2 9 X");
		this.bookingRefStaticDataset.put("EM3555X", "EM 3 5 5 5 X");
		
	}

	public String processGetBookingInfo(String bookingRefRequest) {
		
		LOGGER.info("\n\nProcessing booking req value: " + bookingRefRequest);
		
		return this.matchRequestToResponse(bookingRefRequest);
	}
	
	private String matchRequestToResponse(String bookingRefRequest) {
		
		for (final String bookingRefKey : this.bookingRefStaticDataset.keySet()) {
			
			final String bookingRefValueAtPos = this.bookingRefStaticDataset.get(bookingRefKey);
			
			//we need it to match against 'k' in 'k-v' pair
			if (bookingRefRequest.equals(bookingRefKey)) {
				
				return this.createResponse(bookingRefRequest);
			}
			
		}
		
		//TODO -->> here, implement what was matched / resolved against...
		final String formattedBookingRef = this.buildGenericNoMatchResponse(bookingRefRequest);
		
		LOGGER.info("\n\nFormatted booking reference: " + formattedBookingRef);
		
		final String genericResponse = "We've resolved your booking reference as " + formattedBookingRef
				+ ". If you would like to try again, say Alexa, open september protocol. Goodbye.";
		
		return genericResponse;
	}

	private String buildGenericNoMatchResponse(String bookingRefRequest) {
		
		String [] bookingRefAsArray = bookingRefRequest.split("\\s+");
		StringBuilder formattedString = new StringBuilder();
		
		for (int index = 0; index < bookingRefAsArray.length; index++) {
			
			final String inputAtPos = bookingRefAsArray[index];
			final String literalValue = this.matchValueToLiteral(inputAtPos);
			
			formattedString.append(literalValue);
			formattedString.append(" ");
			
		}
		
		return formattedString.toString();
	}

	private String matchValueToLiteral(String inputAtPos) {
		
		HashMap<String, String> mapToFormat = new HashMap<String, String>();
		
		//potential character matches (EZY, EJU, EZS)
		mapToFormat.put("E", " E ");
		mapToFormat.put("M", " M ");
		mapToFormat.put("Y", " Y ");
		mapToFormat.put("X", " X ");
		
		//numbers
		mapToFormat.put("1", "one");
		mapToFormat.put("2", "two");
		mapToFormat.put("3", "three");
		mapToFormat.put("4", "four");
		mapToFormat.put("5", "five");
		mapToFormat.put("6", "six");
		mapToFormat.put("7", "seven");
		mapToFormat.put("8", "eight");
		mapToFormat.put("9", "nine");
		mapToFormat.put("0", "zero");
		
		//match number to relevant match
		for (String key : mapToFormat.keySet()) {
			
			final String matchingEquivalent = mapToFormat.get(key);
			if (inputAtPos.equals(key)) {
				
				return matchingEquivalent;
			}
			
		}
		return null;
	}

	private String createResponse(String bookingRefValue) {
		
		if (bookingRefValue.equals("EM5665X")) {
			
			return BOOKING_REF_5665X;
			
		} else if (bookingRefValue.equals("EM6124X")) {
			
			return BOOKING_REF_6124X;
			
		} else if (bookingRefValue.equals("EM3774Y")) {
			
			return BOOKING_REF_3774Y;
			
		} else if (bookingRefValue.equals("EM2229Y")) {
			
			return BOOKING_REF_2229Y;
			
		} else if (bookingRefValue.equals("EM3555X")) {
			
			return BOOKING_REF_3555X;
		}
		
		return null;
	}

}
