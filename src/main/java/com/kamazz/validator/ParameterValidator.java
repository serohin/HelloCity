package com.kamazz.validator;

import java.util.Map;

public interface ParameterValidator extends Validaotor<String[] > {

    Map<String, String> validate(String[] entity);
}
