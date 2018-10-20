package net.crystalapps.mint.validator.library.filters.text;

import android.support.annotation.NonNull;
import android.widget.EditText;

import net.crystalapps.mint.validator.library.annotations.text.ValidEmail;
import net.crystalapps.mint.validator.library.filters.Filter;
import net.crystalapps.mint.validator.library.utils.TextUtil;

import java.util.regex.Pattern;

/**
 * Created by Syed Owais Ali on 5/13/2018.
 */

@SuppressWarnings("unused")
public class ValidEmailFilter implements Filter<EditText, ValidEmail> {

    @Override
    public boolean isValidated(@NonNull EditText view, @NonNull ValidEmail annotation) {
        String text = view.getText().toString();
        if (TextUtil.isNotEmpty(annotation.regex())) {
            return TextUtil.isValidWithRegex(text, Pattern.compile(annotation.regex(), Pattern.CASE_INSENSITIVE));
        }
        else {
            return TextUtil.isValidEmail(text);
        }
    }
}
