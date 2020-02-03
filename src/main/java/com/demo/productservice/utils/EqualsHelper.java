package com.demo.productservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EqualsHelper {
    public static <T> boolean equals(T object1, T object2) {
        if (object1 == object2) {
            return true;
        }
        if(object1 == null || object2 == null || !object1.getClass().isAssignableFrom(object2.getClass())){
            return false;
        }
        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(object1.getClass());
        return Stream.of(propertyDescriptors)
                .map(PropertyDescriptor::getReadMethod)
                .allMatch(m -> {
                    try {
                        Object property1 = m.invoke(object1);
                        Object property2 = m.invoke(object2);
                        if (property1 instanceof Collection && property2 instanceof Collection) {
                            return CollectionUtils.isEqualCollection((Collection) property1, (Collection) property2);
                        } else {
                            return Objects.equals(property1, property2);
                        }
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        throw new RuntimeException("An exception occurs during object comparison", e);
                    }
                });
    }
}
