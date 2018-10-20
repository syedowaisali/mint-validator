package net.crystalapps.mint.validator.library.filters;

import android.support.annotation.NonNull;
import android.view.View;

import java.lang.annotation.Annotation;

/**
 * Created by Syed Owais Ali on 5/15/2018.
 */

public interface Filter<V extends View, T extends Annotation> {
    boolean isValidated(@NonNull V view, @NonNull T annotation);
}
