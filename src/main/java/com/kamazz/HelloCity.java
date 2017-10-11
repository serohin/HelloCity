package com.kamazz;

import com.kamazz.service.HelloCityService;
import com.kamazz.validator.ParameterValidator;
import com.kamazz.validator.ParameterValidatorImpl;
import org.apache.log4j.Logger;

import java.util.Map;

import static com.kamazz.validator.ParameterValidatorImpl.KEY_ERROR_MESSAGE;


public class HelloCity{
    private static final Logger log = Logger.getLogger(HelloCity.class);

    public static void main( String[] args ){
        ParameterValidator validatorArgs = new ParameterValidatorImpl();
        log.info("The argument's validator is created.");
        Map<String, String> errorMap = validatorArgs.validate(args);
        log.info("The errorMap was created.");

        if (errorMap.isEmpty()) {
            log.info("Validation is successful - the error map is empty.");
            HelloCityService service = new HelloCityService(args);
            log.info("HelloCityService object was created : cityNname = " + service.getCityName() + " timeZone = " + service.getIdTimeZone());
            System.out.println(service.getMessageHelloCity() + service.getCityName() + "!");
            log.info("The final message was displayed.");
            return;
        }
        log.info("An error occurred during validation." + KEY_ERROR_MESSAGE);
        System.out.println(errorMap.get(KEY_ERROR_MESSAGE));


    }
}
