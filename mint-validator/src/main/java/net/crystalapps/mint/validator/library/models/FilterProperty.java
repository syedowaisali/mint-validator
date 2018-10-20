package net.crystalapps.mint.validator.library.models;

import android.view.View;

import net.crystalapps.mint.validator.library.filters.Filter;

import java.lang.annotation.Annotation;

/**
 * Created by Syed Owais Ali on 10/20/2018.
 */
public class FilterProperty {

    private int order;
    private String message;
    private boolean isFilterable;
    private Filter<View, Annotation> filter;
    private Annotation annotation;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isFilterable() {
        return isFilterable;
    }

    public void setFilterable(boolean filterable) {
        isFilterable = filterable;
    }

    public Filter<View, Annotation> getFilter() {
        return filter;
    }

    public void setFilter(Filter<View, Annotation> filter) {
        this.filter = filter;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }
}
