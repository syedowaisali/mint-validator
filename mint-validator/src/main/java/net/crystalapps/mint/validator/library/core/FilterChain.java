package net.crystalapps.mint.validator.library.core;

/**
 * Created by Syed Owais Ali on 5/14/2018.
 */

@SuppressWarnings("WeakerAccess")
public interface FilterChain {

    void doFilter();
    boolean hasNext();
}