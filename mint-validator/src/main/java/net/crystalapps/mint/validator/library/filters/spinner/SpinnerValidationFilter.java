package net.crystalapps.mint.validator.library.filters.spinner;

import android.support.annotation.NonNull;
import android.widget.Spinner;

import net.crystalapps.mint.validator.library.annotations.spinner.SpinnerValidation;
import net.crystalapps.mint.validator.library.filters.Filter;

/**
 * Created by Syed Owais Ali on 8/12/2018.
 */

@SuppressWarnings("unused")
public class SpinnerValidationFilter implements Filter<Spinner, SpinnerValidation> {

    @Override
    public boolean isValidated(@NonNull Spinner view, @NonNull SpinnerValidation annotation) {
        return view.getSelectedItemPosition() != annotation.index();
    }
}
