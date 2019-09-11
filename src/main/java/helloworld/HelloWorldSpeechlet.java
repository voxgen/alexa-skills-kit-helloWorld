package helloworld;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;


import helloworld.generic.handlers.GenericProcessFlightInfoRequest;

/**
 * This sample shows how to create a simple speechlet for handling speechlet
 * requests.
 */
public class HelloWorldSpeechlet implements Speechlet {
	
//	private static final Logger log = LoggerFactory.getLogger(HelloWorldSpeechlet.class);
	
	private static final Logger log = LogManager.getLogger(HelloWorldSpeechlet.class);
	
	@Override
	public void onSessionStarted(final SessionStartedRequest request, final Session session) throws SpeechletException {
		log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
		// any initialization logic goes here
	}

	@Override
	public SpeechletResponse onLaunch(final LaunchRequest request, final Session session) throws SpeechletException {
		log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
		return getWelcomeResponse();
	}
	
	//this is the main method invoked when an 'utterance' resolves to an intent (similar to LexBot)
	@Override
	public SpeechletResponse onIntent(final IntentRequest request, final Session session) throws SpeechletException {
		
//        LambdaLogger logger = this.
		
		log.info("onIntent requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
		
		Intent intent = request.getIntent();
		String intentName = (intent != null) ? intent.getName() : null;

		if ("HelloWorldIntent".equals(intentName)) {
			
			log.debug("Logging on resolved to 'HelloWorldIntent'");
			
			return getHelloResponse(request.getIntent());
			
		} else if ("AMAZON.HelpIntent".equals(intentName)) {
			
			return getHelpResponse();
			
		} else {
			
			throw new SpeechletException("Invalid Intent");
		}
	}

	@Override
	public void onSessionEnded(final SessionEndedRequest request, final Session session) throws SpeechletException {
		log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
		// any cleanup logic goes here
	}

	/**
	 * Creates and returns a {@code SpeechletResponse} with a welcome message.
	 *
	 * @return SpeechletResponse spoken and visual response for the given intent
	 */
	private SpeechletResponse getWelcomeResponse() {
		String speechText = "Welcome to the Alexa Skills Kit, you can say hello";

		// Create the Simple card content.
		SimpleCard card = new SimpleCard();
		card.setTitle("HelloWorld");
		card.setContent(speechText);

		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		// Create reprompt
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);

		return SpeechletResponse.newAskResponse(speech, reprompt, card);
	}

	/**
	 * Creates a {@code SpeechletResponse} for the hello intent.
	 * @param intent 
	 *
	 * @return SpeechletResponse spoken and visual response for the given intent
	 */
	private SpeechletResponse getHelloResponse(Intent intent) {
//		String speechText = "Hello world";
		
		//default fallback msg...
		String fallbackSpeechText = "If you are seeing this, then the lambda is responding to HelloWorldIntent...";
		
		final String flightNumberResponse = this.processFlightNumberResponse(intent);
		
		// Create the Simple card content.
		SimpleCard card = new SimpleCard();
		card.setTitle("HelloWorld");
		card.setContent(flightNumberResponse);
//		card.setContent(fallbackSpeechText);
		
		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(flightNumberResponse);
//		speech.setText(fallbackSpeechText);
		
		return SpeechletResponse.newTellResponse(speech, card);
	}
	
	/**
	 * 
	 * @param intent
	 * @return
	 */
	private String processFlightNumberResponse(Intent intent) {
		
		GenericProcessFlightInfoRequest processFlightRequest = null;
		
		final String flightNumberSlotName = "FlightNum";
		final String userInputFlightValue = intent.getSlot(flightNumberSlotName).getValue();
		
		//if slotName from 'intent' obj matches 'FlightNum' then process a flight num
		if (flightNumberSlotName.equals(intent.getSlot(flightNumberSlotName).getName())) {
			
			//query datset HashMap for matching flight number...
			processFlightRequest = new GenericProcessFlightInfoRequest();
			return processFlightRequest.processGetFlightInfo(userInputFlightValue);
			
//			return processFlightRequest.simpleGenericResponse();
			
		}
		
		return null;
	}

	/**
	 * Creates a {@code SpeechletResponse} for the help intent.
	 *
	 * @return SpeechletResponse spoken and visual response for the given intent
	 */
	private SpeechletResponse getHelpResponse() {
		String speechText = "You can say hello to me!";

		// Create the Simple card content.
		SimpleCard card = new SimpleCard();
		card.setTitle("HelloWorld");
		card.setContent(speechText);

		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		// Create reprompt
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);

		return SpeechletResponse.newAskResponse(speech, reprompt, card);
	}
}
