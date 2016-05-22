package com.github.gregwhitaker.catnap.core.query.processor;

import com.github.gregwhitaker.catnap.core.query.model.Query;
import com.github.gregwhitaker.catnap.core.util.ClassUtil;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

public class NullQueryProcessor extends SortableQueryProcessor {

    @Override
    public boolean supports(Class<? extends Query<?>> query) {
        return (query == null);
    }

    @Override
    public <T> List<Property<T>> processInternal(Query query, T instance, Class<T> instanceClazz) {
        List<Property<T>> properties = new ArrayList<Property<T>>();

        //No query specified so we need to return all fields
        for(PropertyDescriptor descriptor : ClassUtil.getReadableProperties(instanceClazz)) {
            if(!ignoreProperty(descriptor)) {
                properties.add(new SimpleProperty<T>(instance, descriptor));
            }
        }

        return properties;
    }
}
