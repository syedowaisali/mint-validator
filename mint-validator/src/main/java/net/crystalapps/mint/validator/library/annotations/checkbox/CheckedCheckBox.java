package net.crystalapps.mint.validator.library.annotations.checkbox;

import net.crystalapps.mint.validator.library.annotations.Filterable;
import net.crystalapps.mint.validator.library.filters.checkbox.CheckBoxFilter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Syed Owais Ali on 5/16/2018.
 */

@SuppressWarnings("unused")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Filterable(CheckBoxFilter.class)
public @interface CheckedCheckBox {
    int[] value();
}
