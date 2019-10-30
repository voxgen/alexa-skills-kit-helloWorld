package helloworld;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

/**
 * This class could be the handler for an AWS Lambda function powering an Alexa Skills Kit
 * experience. To do this, simply set the handler field in the AWS Lambda console to
 * "helloworld.HelloWorldSpeechletRequestStreamHandler" For this to work, you'll also need to build
 * this project using the {@code lambda-compile} Ant task and upload the resulting zip file to power
 * your function.
 */
public final class HelloWorldSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    
	//https://docs.aws.amazon.com/lambda/latest/dg/java-logging.html
	private static final Logger logger = LogManager.getLogger(HelloWorldSpeechletRequestStreamHandler.class);
	
	private static final Set<String> supportedApplicationIds = new HashSet<>();
	
    static {
        /*
         * This Id can be found on https://developer.amazon.com/edw/home.html#/ "Edit" the relevant
         * Alexa Skill and put the relevant Application Ids in this Set.
         */
//        supportedApplicationIds.add("amzn1.ask.skill.xxxxxxxx");
    	supportedApplicationIds.add("amzn1.ask.skill.dbab7e7e-cc08-4f11-974f-e302f92cb074");
    	supportedApplicationIds.add("amzn1.ask.skill.0127b4dc-4c90-498c-9821-85719b4523f1");
    	
    	// amzn1.ask.skill.dbab7e7e-cc08-4f11-974f-e302f92cb074
        
    }

    public HelloWorldSpeechletRequestStreamHandler() {
    	
        super(new HelloWorldSpeechlet(), supportedApplicationIds);
        
        logger.debug("Lambda function invoked, request handling on SpeechletRequestStreamHandler");
    }
}
