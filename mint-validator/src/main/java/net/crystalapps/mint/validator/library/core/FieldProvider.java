package net.crystalapps.mint.validator.library.core;

import android.support.annotation.NonNull;

import java.lang.reflect.Field;

/**
 * Created by Syed Owais Ali on 10/19/2018.
 */
public interface FieldProvider {

    Field[] getFields(@NonNull Object inspectedObj);
}
