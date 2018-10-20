package net.crystalapps.mint.validator.library.defaults;

import android.support.annotation.NonNull;

import net.crystalapps.mint.validator.library.core.FieldProvider;

import java.lang.reflect.Field;

/**
 * Created by Syed Owais Ali on 10/19/2018.
 */
public class MintFieldProvider implements FieldProvider {

    @Override
    public Field[] getFields(@NonNull Object inspectedObj) {
        return inspectedObj.getClass().getDeclaredFields();
    }
}
