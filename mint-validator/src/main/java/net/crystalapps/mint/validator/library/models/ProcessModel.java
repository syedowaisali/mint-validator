package net.crystalapps.mint.validator.library.models;

import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by Syed Owais Ali on 5/14/2018.
 */

@SuppressWarnings("WeakerAccess")
public class ProcessModel {

    private final View inspectedView;
    private final Field filteredField;

    public ProcessModel (View inspectedView, Field filteredField) {
        this.inspectedView = inspectedView;
        this.filteredField = filteredField;
    }

    public View getInspectedView() {
        return inspectedView;
    }

    public Field getFilteredField() {
        return filteredField;
    }

}
