/**
 * 
 */
package helloworld.response;

import java.util.logging.Logger;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.SpeechletResponse;

/**
 * @author ashobande
 *
 */
public class BookingReferenceResponseHandler {
	
		//{BookingRefNumber}
	private static final String BOOKING_REFERENCE_SLOT_NAME = "BookingRefNumber";
	
	private Intent intent;
	
	public BookingReferenceResponseHandler(Intent iIntent) {
		
		this.intent = iIntent;
	}
	
	public String createGenericBookingReferenceResponse() {
		
		final String bookingReferenceResponse = this.processBookingReferenceResponse();
		return bookingReferenceResponse;
	}

	private String processBookingReferenceResponse() {
		
		GenericProcessBookingRefRequest processFlightReq = null;
		
			//{BookingRefNumber}
		final String intentBookingRefSlotName = intent.getSlot(BOOKING_REFERENCE_SLOT_NAME).getName();
		
		
		final String userInputReferenceValue = intent.getSlot(BOOKING_REFERENCE_SLOT_NAME).getValue();
		
		//then pull the name and ifit matches, try and obtain the other side
		
		if (intentBookingRefSlotName.equals(BOOKING_REFERENCE_SLOT_NAME)) {
			
			processFlightReq = new GenericProcessBookingRefRequest();
			return processFlightReq.processGetBookingInfo(userInputReferenceValue);
			
		}
//		LOGGER.
		return null;
	}
	
	

}
