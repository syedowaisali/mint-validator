package net.crystalapps.mint.validator.library.filters.text;

import android.support.annotation.NonNull;
import android.widget.EditText;

import net.crystalapps.mint.validator.library.annotations.text.NotEmpty;
import net.crystalapps.mint.validator.library.filters.Filter;
import net.crystalapps.mint.validator.library.utils.TextUtil;

/**
 * Created by Syed Owais Ali on 5/13/2018.
 */

@SuppressWarnings("unused")
public class NotEmptyFilter implements Filter<EditText, NotEmpty> {

    @Override
    public boolean isValidated(@NonNull EditText view, @NonNull NotEmpty annotation) {
        return TextUtil.isNotEmpty(view.getText());
    }
}
