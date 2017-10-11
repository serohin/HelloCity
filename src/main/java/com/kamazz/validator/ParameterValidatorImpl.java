package com.kamazz.validator;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


public class ParameterValidatorImpl implements ParameterValidator {
    public static final String REGEX = "[A-Za-z][A-Za-z0-9~/._+-]+";
    public static final String KEY_ERROR_MESSAGE = "errorMessage";
    public static final String WRONG_NUMBER_ARGUMENTS_MESSAGE = "The number of arguments is wrong.";
    public static final String WRONG_LENGTH_ARGUMENTS_MESSAGE = "Arguments are of short length.";
    public static final String ARGUMENTS_NOT_MATCH_REGEX_MESSAGE = "The arguments must contain English letters.";
    public static final int VALID_LENGTH_ELEM_ARRAY = 2;
    private static final Logger log = Logger.getLogger(ParameterValidatorImpl.class);


    public Map<String, String> validate(String[] args) {

        Map<String, String> errorMap = new HashMap();
        validateLengthArgs(args, errorMap);
        if (errorMap.isEmpty()) {
            log.info("Length arguments successfully validated.");
            validateMatchRegex(args, errorMap);
        }
        log.info("The errorMap was returned.");
        return errorMap;
    }

    private void validateMatchRegex(String[] args, Map<String, String> errorMap) {
        if (args.length == 1 & !args[0].matches(REGEX)) {
            errorMap.put(KEY_ERROR_MESSAGE, ARGUMENTS_NOT_MATCH_REGEX_MESSAGE);
        } else if (args.length == 2) {
            if (!args[0].matches(REGEX) || !args[1].matches(REGEX)) {
                errorMap.put(KEY_ERROR_MESSAGE, ARGUMENTS_NOT_MATCH_REGEX_MESSAGE);
            }
        } else if (args.length == 3) {
            if ((!args[0].matches(REGEX)) || (!args[1].matches(REGEX)) || (!args[2].matches(REGEX))) {
                errorMap.put(KEY_ERROR_MESSAGE, ARGUMENTS_NOT_MATCH_REGEX_MESSAGE);
            }
        } else if (args.length == 4) {
            if (!args[0].matches(REGEX) || !args[1].matches(REGEX) || !args[2].matches(REGEX) || !args[3].matches(REGEX)) {
                errorMap.put(KEY_ERROR_MESSAGE, ARGUMENTS_NOT_MATCH_REGEX_MESSAGE);
            }

        }
        log.info("The arguments are checked for compliance with the regular expression. Errors = " + errorMap.size());
    }

    private void validateLengthArgs(String[] args, Map<String, String> errorMap) {
        if (args.length == 0 || args.length > 4) {
            errorMap.put(KEY_ERROR_MESSAGE, WRONG_NUMBER_ARGUMENTS_MESSAGE);
        } else if (args.length == 1 && args[0].length() < 3) {
            errorMap.put(KEY_ERROR_MESSAGE, WRONG_LENGTH_ARGUMENTS_MESSAGE);
        } else if (args.length == 2) {
            if (args[0].length() < VALID_LENGTH_ELEM_ARRAY || args[1].length() < VALID_LENGTH_ELEM_ARRAY) {
                errorMap.put(KEY_ERROR_MESSAGE,WRONG_LENGTH_ARGUMENTS_MESSAGE);
            }
        } else if (args.length == 3) {
            if (args[0].length() < VALID_LENGTH_ELEM_ARRAY || args[1].length() < VALID_LENGTH_ELEM_ARRAY || args[2].length() < VALID_LENGTH_ELEM_ARRAY) {
                errorMap.put(KEY_ERROR_MESSAGE, WRONG_LENGTH_ARGUMENTS_MESSAGE);
            }
        } else if (args.length == 4) {
            if (args[0].length() < VALID_LENGTH_ELEM_ARRAY || args[1].length() < VALID_LENGTH_ELEM_ARRAY || args[2].length() < VALID_LENGTH_ELEM_ARRAY || args[3].length() < VALID_LENGTH_ELEM_ARRAY) {
                errorMap.put(KEY_ERROR_MESSAGE, WRONG_LENGTH_ARGUMENTS_MESSAGE);
            }

        }
        log.info("The length arguments were validated. Errors = " + errorMap.size());
    }

}