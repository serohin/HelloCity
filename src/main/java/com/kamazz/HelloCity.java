package com.kamazz;

import com.kamazz.service.HelloCityService;
import com.kamazz.validator.ParameterValidator;
import com.kamazz.validator.ParameterValidatorImpl;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.kamazz.validator.ParameterValidatorImpl.KEY_ERROR_MESSAGE;


public class HelloCity{
    private static final Logger log = Logger.getLogger(HelloCity.class);

    public static void main( String[] args ){
        List<String> li = new ArrayList(Arrays.asList(args));
        while(li.contains(",")) {
            li.remove(li.indexOf(","));
        }
        String [] arrayWithoutCommas = li.toArray(new String[li.size()]);

        ParameterValidator validatorArgs = new ParameterValidatorImpl();
        log.info("The argument's validator is created.");
        Map<String, String> errorMap = validatorArgs.validate(arrayWithoutCommas);
        log.info("The errorMap was created.");

        if (errorMap.isEmpty()) {
            log.info("Validation is successful - the error map is empty.");
            HelloCityService service = new HelloCityService(arrayWithoutCommas);
            log.info("HelloCityService object was created : cityNname = " + service.getCityName() + " timeZone = " + service.getIdTimeZone());
            System.out.println(service.getMessageHelloCity() + service.getCityName() + "!");
            log.info("The final message was displayed.");
            return;
        }
        log.info("An error occurred during validation." + KEY_ERROR_MESSAGE);
        System.out.println(errorMap.get(KEY_ERROR_MESSAGE));


    }
}
