/**
 * 
 */
package helloworld.generic.handlers;

import java.util.HashMap;

/**
 * @author ashobande
 *
 */
public class GenericProcessFlightInfoRequest {
	
	private HashMap<String, String> flightNumberStaticDataset = null;
	
	private static final String APPEND_EXIT_CONVERSATION_PHRASE = " If you would like to try again, say Alexa, open september protocol. Goodbye.";
	
	private static final String FLIGHT_RESPONSE_1101 = "Flight number 1 1 0 1 from London to Paris will arrive tonight at 10:50 PM. " + APPEND_EXIT_CONVERSATION_PHRASE;
	
	private static final String FLIGHT_RESPONSE_8272 = "Flight number 8 2 7 2 from Milan to Moscow arrived earlier today at 5:45 AM. " + APPEND_EXIT_CONVERSATION_PHRASE;
	
	private static final String FLIGHT_RESPONSE_7948 = "Flight number 7 9 4 8 from Brussels to London is delayed by approximately 20 mintues and is scheduled to arrive at 11:30 PM. " + APPEND_EXIT_CONVERSATION_PHRASE;
	
	private static final String FLIGHT_RESPONSE_6194 = "Flight number 6 1 9 4 from Amsterdam to Istanbul will arrive tonight at 9PM. " + APPEND_EXIT_CONVERSATION_PHRASE;
	
	private static final String FLIGHT_RESPONSE_3862 = "Flight number 3 8 6 2 from Dublin to Berlin will arrive in the next 45 minutes. " + APPEND_EXIT_CONVERSATION_PHRASE;
	
	private static final String FLIGHT_RESPONSE_7158 = "Flight number 7 1 5 8 has been cancelled. Please check for further updates online. " + APPEND_EXIT_CONVERSATION_PHRASE;
	
	private static final String FLIGHT_RESPONSE_4380 = "Flight number 4 3 8 0 from Rome to Paris arrived 20 mintues ago. " + APPEND_EXIT_CONVERSATION_PHRASE;
	
	public GenericProcessFlightInfoRequest() {
		
		this.flightNumberStaticDataset = new HashMap<String, String>();
		
		this.flightNumberStaticDataset.put("EJU1101", "EJU 1 1 0 1");
		this.flightNumberStaticDataset.put("EZY8272", "EZY 8 2 7 2");
		this.flightNumberStaticDataset.put("EJU7948", "EJU 7 9 4 8");
		this.flightNumberStaticDataset.put("EZY6194", "EZY 6 1 9 4");
		this.flightNumberStaticDataset.put("EJU3862", "EJU 3 8 6 2");
		this.flightNumberStaticDataset.put("EZY7158", "EZY 7 1 5 8");
		this.flightNumberStaticDataset.put("EJU4380", "EJU 4 3 8 0");
		
	}
	
	public String processGetFlightInfo(final String flightNumber) {
		
		return this.matchRequestToResponse(flightNumber);
		
	}
	
	private String matchRequestToResponse(final String flightNumberRequest) {
		
		for (final String flightReferenceKey : this.flightNumberStaticDataset.keySet()) {
			
			final String flightNumberValue = this.flightNumberStaticDataset.get(flightReferenceKey);
			
			//if the user's provisioned reference num matches the key in map (e.g. EJU1101 == EJU1101.map) then create response based on key...
			if (flightNumberRequest.equals(flightReferenceKey)) {
				
				return this.createResponse(flightNumberRequest);
			}
			
//			if (flightNumberRequest.equals(flightNumberValue)) {
//				
//				return this.createResponse(flightNumberRequest);
//			}
			
		}
		
		//no match, generic response
		final String formattedFlightNumber = this.buildGenericNoMatchResponse(flightNumberRequest);
		
		final String genericResponse = "We've matched your flight number as " + formattedFlightNumber 
				+ ". If you would like to try again, say Alexa, open september protocol. Goodbye.";
		
		return genericResponse;
	}
	
	private String buildGenericNoMatchResponse(String flightNumberRequest) {
		
		String [] flightNumAsArray = flightNumberRequest.split("\\s+");
		StringBuilder formattedString = new StringBuilder();
		
		for (int index = 0; index < flightNumAsArray.length; index++) {
			
			final String inputAtPos = flightNumAsArray[index];
			final String literalValue = this.matchValueToLiteral(inputAtPos);
			
			formattedString.append(literalValue);
			formattedString.append(" ");
			
			/**
			if (index >= 3) {
				
				final String literalNumber = this.matchNumberToLiteral(inputAtPos);
				formattedString.append(literalNumber);
				
			} else {
				
				//append the input, followed by whitepace...
				formattedString.append(inputAtPos);
				formattedString.append(" ");				
			}
			**/
			
		}
		
		return formattedString.toString();
	}
	
	private String matchValueToLiteral(String inputAtPos) {
		
		HashMap<String, String> mapToFormat = new HashMap<String, String>();
		
		//potential character matches (EZY, EJU, EZS)
		mapToFormat.put("E", " E ");
		mapToFormat.put("Z", " Z ");
		mapToFormat.put("Y", " Y ");
		mapToFormat.put("J", " J ");
		mapToFormat.put("U", " U ");
		mapToFormat.put("S", " S ");
		
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
				
//				context.getLogger().log("Match on key: " + key);
				
				return matchingEquivalent;
				
			}
			
		}
		
		return null;
	}
	
	private String matchNumberToLiteral(String inputAtPos) {
		
		HashMap<String, String> mapToFormat = new HashMap<String, String>();
		
		//potential character matches (EZY, EJU, EZS)
		mapToFormat.put("E", " E ");
		mapToFormat.put("Z", " Z ");
		mapToFormat.put("Y", " Y ");
		mapToFormat.put("J", " J ");
		mapToFormat.put("U", " U ");
		mapToFormat.put("S", " S ");
		
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
				
//				context.getLogger().log("Match on key: " + key);
				
				return matchingEquivalent;
				
			}
			
		}
		
		return null;
	}

	private String createResponse(String flightNumberRequest) {
		
		//if user's request matches any key in list below, then provide appropriate response...
		if (flightNumberRequest.equals("EJU1101")) {
			
			return FLIGHT_RESPONSE_1101;
			
		} else if (flightNumberRequest.equals("EZY8272")) {
			
			return FLIGHT_RESPONSE_8272;
			
		} else if (flightNumberRequest.equals("EJU7948")) {
			
			return FLIGHT_RESPONSE_7948;
			
		} else if (flightNumberRequest.equals("EZY6194")) {
			
			return FLIGHT_RESPONSE_6194;
			
		} else if (flightNumberRequest.equals("EJU3862")) {
			
			return FLIGHT_RESPONSE_3862;
			
		} else if (flightNumberRequest.equals("EZY7158")) {
			
			return FLIGHT_RESPONSE_7158;
			
		} else if (flightNumberRequest.equals("EJU4380")) {
			
			return FLIGHT_RESPONSE_4380;
			
		}
		
		return null;
	}
	
	public String simpleGenericResponse() {
		
		return "The flight you have just requested for has not arrived yet. Please check again shortly or look out for Alexa updates.";
		
	}
}
