package net.crystalapps.mint.validator.library.filters.text;

import android.support.annotation.NonNull;
import android.widget.EditText;

import net.crystalapps.mint.validator.library.annotations.text.MinLength;
import net.crystalapps.mint.validator.library.filters.Filter;

/**
 * Created by Syed Owais Ali on 5/13/2018.
 */

@SuppressWarnings("unused")
public class MinTextLengthFilter implements Filter<EditText, MinLength> {

    @Override
    public boolean isValidated(@NonNull EditText view, @NonNull MinLength annotation) {
        String text = view.getText().toString();
        return text.length() >= annotation.length();
    }
}
