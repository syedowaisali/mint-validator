package net.crystalapps.mint.validator.library.filters.text;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;

import net.crystalapps.mint.validator.library.annotations.text.ConfirmPassword;
import net.crystalapps.mint.validator.library.filters.Filter;

/**
 * Created by Syed Owais Ali on 8/12/2018.
 */

@SuppressWarnings("unused")
public class ConfirmPasswordFilter implements Filter<EditText, ConfirmPassword> {

    @Override
    public boolean isValidated(@NonNull EditText view, @NonNull ConfirmPassword annotation) {

        if (view.getContext() instanceof Activity) {
            View password = ((Activity) view.getContext()).findViewById(annotation.id());
            if (password instanceof EditText) {
                return ((EditText) password).getText().toString().equals(view.getText().toString());
            }
        }
        return false;
    }
}
