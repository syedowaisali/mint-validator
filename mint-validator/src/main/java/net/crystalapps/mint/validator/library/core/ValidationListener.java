package net.crystalapps.mint.validator.library.core;

import android.support.annotation.NonNull;

/**
 * Created by Syed Owais Ali on 5/12/2018.
 */

@SuppressWarnings("WeakerAccess")
public interface ValidationListener {
    void validateSuccess();
    void validateFailed(@NonNull ValidationError validationError, @NonNull FilterChain filterChain);
}
