package net.crystalapps.mint.validator.library.filters.checkbox;

import android.support.annotation.NonNull;
import android.widget.CheckBox;

import net.crystalapps.mint.validator.library.annotations.checkbox.CheckedCheckBox;
import net.crystalapps.mint.validator.library.filters.Filter;

/**
 * Created by Syed Owais Ali on 5/16/2018.
 */

@SuppressWarnings("unused")
public class CheckBoxFilter implements Filter<CheckBox, CheckedCheckBox> {

    @Override
    public boolean isValidated(@NonNull CheckBox view, @NonNull CheckedCheckBox annotation) {
        return view.isChecked();
    }
}
