package com.kamazz.validator;

import org.apache.log4j.Logger;

import java.util.*;


public class ParameterValidatorImpl implements ParameterValidator {
    public static final String REGEX = "[A-Za-z][A-Za-z0-9~/._+-]+";
    public static final String KEY_ERROR_MESSAGE = "errorMessage";
    public static final String WRONG_NUMBER_ARGUMENTS_MESSAGE = "The number of arguments is wrong.";
    public static final String WRONG_LENGTH_ARGUMENTS_MESSAGE = "Arguments are of short length.";
    public static final String ARGUMENTS_NOT_MATCH_REGEX_MESSAGE = "The arguments must contain English letters.";
    public static final int VALID_LENGTH_ELEM_ARRAY = 2;
    private static final Logger log = Logger.getLogger(ParameterValidatorImpl.class);


    public Map<String, String> validate(String[] arg) {

        Map<String, String> errorMap = new HashMap();
        validateLengthArgs(arg, errorMap);
        if (errorMap.isEmpty()) {
            log.info("Length arguments successfully validated.");
            validateMatchRegex(arg, errorMap);
        }
        log.info("The errorMap was returned.");
        return errorMap;
    }



    private void validateMatchRegex(String[] arg, Map<String, String> errorMap) {
        String cityWithoutSpace = allSpacesReplaceWithUnderscore (arg[0]);
        if (arg.length == 1 & !cityWithoutSpace.matches(REGEX)) {
            errorMap.put(KEY_ERROR_MESSAGE, ARGUMENTS_NOT_MATCH_REGEX_MESSAGE);
        } else if (arg.length == 2) {
            if (!cityWithoutSpace.matches(REGEX) || !arg[1].matches(REGEX)) {
                errorMap.put(KEY_ERROR_MESSAGE, ARGUMENTS_NOT_MATCH_REGEX_MESSAGE);
            }
        } else if (arg.length == 3) {
            if ((!cityWithoutSpace.matches(REGEX)) || (!arg[1].matches(REGEX)) || (!arg[2].matches(REGEX))) {
                errorMap.put(KEY_ERROR_MESSAGE, ARGUMENTS_NOT_MATCH_REGEX_MESSAGE);
            }
        } else if (arg.length == 4) {
            if (!cityWithoutSpace.matches(REGEX) || !arg[1].matches(REGEX) || !arg[2].matches(REGEX) || !arg[3].matches(REGEX)) {
                errorMap.put(KEY_ERROR_MESSAGE, ARGUMENTS_NOT_MATCH_REGEX_MESSAGE);
            }

        }
        log.info("The arguments are checked for compliance with the regular expression. Errors = " + errorMap.size());
    }

    private void validateLengthArgs(String[] arg, Map<String, String> errorMap) {
        if (arg.length == 0 || arg.length > 4) {
            errorMap.put(KEY_ERROR_MESSAGE, WRONG_NUMBER_ARGUMENTS_MESSAGE);
        } else if (arg.length == 1 && arg[0].length() < 2) {
            errorMap.put(KEY_ERROR_MESSAGE, WRONG_LENGTH_ARGUMENTS_MESSAGE);
        } else if (arg.length == 2) {
            if (arg[0].length() < VALID_LENGTH_ELEM_ARRAY || arg[1].length() < VALID_LENGTH_ELEM_ARRAY) {
                errorMap.put(KEY_ERROR_MESSAGE,WRONG_LENGTH_ARGUMENTS_MESSAGE);
            }
        } else if (arg.length == 3) {
            if (arg[0].length() < VALID_LENGTH_ELEM_ARRAY || arg[1].length() < VALID_LENGTH_ELEM_ARRAY || arg[2].length() < VALID_LENGTH_ELEM_ARRAY) {
                errorMap.put(KEY_ERROR_MESSAGE, WRONG_LENGTH_ARGUMENTS_MESSAGE);
            }
        } else if (arg.length == 4) {
            if (arg[0].length() < VALID_LENGTH_ELEM_ARRAY || arg[1].length() < VALID_LENGTH_ELEM_ARRAY || arg[2].length() < VALID_LENGTH_ELEM_ARRAY || arg[3].length() < VALID_LENGTH_ELEM_ARRAY) {
                errorMap.put(KEY_ERROR_MESSAGE, WRONG_LENGTH_ARGUMENTS_MESSAGE);
            }

        }
        log.info("The length arguments were validated. Errors = " + errorMap.size());
    }
    private String allSpacesReplaceWithUnderscore(String str){
        return str.replaceAll("\\s+", "_");

    }

}