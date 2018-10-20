package net.crystalapps.mint.validator.library.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import net.crystalapps.mint.validator.library.annotations.Filterable;
import net.crystalapps.mint.validator.library.filters.Filter;
import net.crystalapps.mint.validator.library.models.FilterProperty;
import net.crystalapps.mint.validator.library.models.ProcessModel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Created by Syed Owais Ali on 5/13/2018.
 */

@SuppressWarnings({"WeakerAccess", "unchecked", "ConstantConditions"})
public abstract class FieldProcessor {

    private boolean areAllFieldsValidated;
    private ValidationListener validationListener;

    public void init(@NonNull ValidationListener validationListener) {
        areAllFieldsValidated = true;
        this.validationListener = validationListener;
    }

    protected Queue<FilterProperty> getFilters(@NonNull ProcessModel processModel) {

        Map<Integer, FilterProperty> filterMap = new LinkedHashMap<>();

        for (Annotation annotation : processModel.getFilteredField().getDeclaredAnnotations()) {
            if (annotation.annotationType().isAnnotationPresent(Filterable.class)) {
                FilterProperty filterProperty = getFilterProperty(processModel.getInspectedView(), annotation);
                filterMap.put(filterProperty.getOrder(), filterProperty);
            }
        }

        return new LinkedList<>(new TreeMap<>(filterMap).values());
    }

    protected FilterProperty getFilterProperty(@NonNull View inspectedView, @NonNull Annotation annotation) {

        FilterProperty property = new FilterProperty();
        property.setFilterable(true);

        Object valObj = null;
        try {
            valObj = annotation.annotationType().getDeclaredMethod("value").invoke(annotation);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        if (!(valObj instanceof int[])) {
            throw new IllegalStateException("value must be int array");
        }

        int[] values = (int[]) valObj;

        if (values.length == 0) {
            throw new IllegalStateException("No order found in value array. note: value array should be contains minimum 2 value 1st is order and the 2nd is string message id");
        }

        else if (values.length == 1) {
            throw new IllegalStateException("No message id found in value array. Note: value array should be contains minimum 2 value 1st is order and the 2nd is string message id");
        }

        else if (values.length == 3) {
            int state = values[2];
            if (state != View.VISIBLE && state != View.INVISIBLE && state != View.GONE) {
                throw new IllegalStateException("third value of value array should be contain one of these value (View.VISIBLE, View.INVISIBLE, View.GONE)");
            }
            else {
                property.setFilterable(inspectedView.getVisibility() == state);
            }
        }

        // try to get filter
        try {

            Filterable filterableAnnotation = annotation.annotationType().getAnnotation(Filterable.class);
            Class<? extends Filter<? extends View, ? extends Annotation>> filterClass = (Class<? extends Filter<? extends View, ? extends Annotation>>) filterableAnnotation.annotationType().getDeclaredMethod("value").invoke(filterableAnnotation);
            property.setFilter(makeFilter(filterClass));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        property.setOrder(values[0]);
        property.setMessage(getMessage(inspectedView.getContext(), values[1]));
        property.setAnnotation(annotation);

        return property;
    }

    protected String getMessage(Context context, int resId) {
        try {
            return context.getResources().getString(resId);
        }
        catch (Exception ex) {
            return "";
        }
    }

    @Nullable
    protected Filter makeFilter(Class<? extends Filter<?, ?>> filterClass) {

        try {
            Constructor<? extends Filter<?, ?>> constructor = filterClass.getDeclaredConstructor();
            try {
                return constructor.newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    protected void validateFailed(@NonNull FilterProperty filterProperty, @NonNull ProcessModel processModel, @NonNull FilterChain filterChain) {
        areAllFieldsValidated = false;
        validationListener.validateFailed(new ValidationError(filterProperty.getMessage(), processModel.getInspectedView(), filterProperty.getAnnotation()), filterChain);
    }

    protected void validationCompleted(@NonNull FilterChain filterChain) {
        if (filterChain.hasNext()) {
            filterChain.doFilter();
        }
        else if (areAllFieldsValidated) {
            validationListener.validateSuccess();
        }
    }

    public abstract void process(@NonNull ProcessModel processModel, @NonNull FilterChain filterChain);

}
