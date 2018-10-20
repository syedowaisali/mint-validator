package net.crystalapps.mint.validator.library.filters.image;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import net.crystalapps.mint.validator.library.annotations.image.EmptyImage;
import net.crystalapps.mint.validator.library.filters.Filter;

/**
 * Created by Syed Owais Ali on 9/19/2018.
 */

@SuppressWarnings("unused")
public class EmptyImageFilter implements Filter<ImageView, EmptyImage> {

    @Override
    public boolean isValidated(@NonNull ImageView view, @NonNull EmptyImage annotation) {
        return view.getDrawable() != null;
    }
}
