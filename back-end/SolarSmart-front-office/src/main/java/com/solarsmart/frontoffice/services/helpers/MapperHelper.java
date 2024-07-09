package com.solarsmart.frontoffice.services.helpers;

import jakarta.persistence.Tuple;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@Component
public class MapperHelper {

    public <T> T mapToPojo(Tuple tuple, Class<T> clazz) {
        T object = null;
        try {
            object = clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < tuple.getElements().size(); i++) {
            String fieldName = tuple.getElements().get(i).getAlias();
            Object value = tuple.get(i);
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(object, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return object;
    }
}
