package net.crystalapps.mint.validator.library.defaults;

import android.support.annotation.NonNull;
import android.view.View;

import net.crystalapps.mint.validator.library.core.FieldProcessor;
import net.crystalapps.mint.validator.library.core.FilterChain;
import net.crystalapps.mint.validator.library.filters.Filter;
import net.crystalapps.mint.validator.library.models.FilterProperty;
import net.crystalapps.mint.validator.library.models.ProcessModel;

import java.lang.annotation.Annotation;
import java.util.Queue;

/**
 * Created by Syed Owais Ali on 5/13/2018.
 */

@SuppressWarnings({"unchecked", "WeakerAccess"})
public class MintFieldProcessor extends FieldProcessor {

    @Override
    public void process(@NonNull ProcessModel processModel, @NonNull FilterChain filterChain) {
        start(getFilters(processModel), processModel, filterChain);
    }

    private void start(@NonNull Queue<FilterProperty> filterPropertyQueue, @NonNull ProcessModel processModel, @NonNull FilterChain filterChain) {

        FilterProperty filterProperty = filterPropertyQueue.poll();

        if (filterProperty != null) {
            if (filterProperty.isFilterable()) {
                Filter<View, Annotation> filter = filterProperty.getFilter();
                if (filter.isValidated(processModel.getInspectedView(), filterProperty.getAnnotation())) {
                    start(filterPropertyQueue, processModel, filterChain);
                }
                else {
                    validateFailed(filterProperty, processModel, filterChain);
                }
            }
            else {
                start(filterPropertyQueue, processModel, filterChain);
            }
        }
        else {
            validationCompleted(filterChain);
        }
    }
}