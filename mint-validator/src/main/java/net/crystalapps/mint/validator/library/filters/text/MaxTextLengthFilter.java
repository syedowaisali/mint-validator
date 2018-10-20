package net.crystalapps.mint.validator.library.filters.text;

import android.support.annotation.NonNull;
import android.widget.EditText;

import net.crystalapps.mint.validator.library.annotations.text.MaxLength;
import net.crystalapps.mint.validator.library.filters.Filter;

/**
 * Created by Syed Owais Ali on 5/13/2018.
 */

@SuppressWarnings("unused")
public class MaxTextLengthFilter implements Filter<EditText, MaxLength> {

    @Override
    public boolean isValidated(@NonNull EditText view, @NonNull MaxLength annotation) {
        String text = view.getText().toString();
        return text.length() <= annotation.length();
    }
}
