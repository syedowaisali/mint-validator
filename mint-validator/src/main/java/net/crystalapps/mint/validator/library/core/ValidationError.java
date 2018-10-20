package net.crystalapps.mint.validator.library.core;

import android.support.annotation.NonNull;
import android.view.View;

import java.lang.annotation.Annotation;

import static net.crystalapps.mint.validator.library.utils.ObjectUtil.requireNonNull;

/**
 * Created by Syed Owais Ali on 5/13/2018.
 */

@SuppressWarnings({"WeakerAccess", "unchecked", "unused"})
public class ValidationError {

    @NonNull private final String message;
    @NonNull private final View view;
    @NonNull private final Annotation annotation;

    public ValidationError(@NonNull String message, @NonNull View view, @NonNull Annotation annotation) {
        this.message = requireNonNull(message);
        this.view = requireNonNull(view);
        this.annotation = requireNonNull(annotation);
    }

    @NonNull
    public String getMessage() {
        return message;
    }

    @NonNull
    public <T extends View> T getView() {
        return (T) view;
    }

    @NonNull
    public Annotation getAnnotation() {
        return annotation;
    }
}
