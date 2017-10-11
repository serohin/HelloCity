package com.kamazz.validator;


import java.util.Map;

public interface Validaotor<T> {

    Map<String, String> validate(T entity);
}
