package net.crystalapps.mint.validator.library.annotations.text;

import net.crystalapps.mint.validator.library.annotations.Filterable;
import net.crystalapps.mint.validator.library.filters.text.TextLengthRangeFilter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Syed Owais Ali on 5/12/2018.
 */

@SuppressWarnings("unused")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Filterable(TextLengthRangeFilter.class)
public @interface LengthRange {
    long min();
    long max();
    int[] value();
}
