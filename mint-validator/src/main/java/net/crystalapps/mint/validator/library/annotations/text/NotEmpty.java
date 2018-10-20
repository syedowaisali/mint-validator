package net.crystalapps.mint.validator.library.annotations.text;

import net.crystalapps.mint.validator.library.annotations.Filterable;
import net.crystalapps.mint.validator.library.filters.text.NotEmptyFilter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Syed Owais Ali on 5/13/2018.
 */

@SuppressWarnings("unused")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Filterable(NotEmptyFilter.class)
public @interface NotEmpty{
    int[] value();
}