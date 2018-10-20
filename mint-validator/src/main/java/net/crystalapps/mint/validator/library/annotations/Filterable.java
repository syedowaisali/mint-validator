package net.crystalapps.mint.validator.library.annotations;

import android.support.annotation.NonNull;

import net.crystalapps.mint.validator.library.filters.Filter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Syed Owais Ali on 8/4/2018.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
@SuppressWarnings("unused")
public @interface Filterable {
    @NonNull Class<? extends Filter> value();
}
