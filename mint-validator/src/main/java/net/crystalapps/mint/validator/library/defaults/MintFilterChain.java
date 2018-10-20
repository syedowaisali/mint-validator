package net.crystalapps.mint.validator.library.defaults;

import net.crystalapps.mint.validator.library.core.AbstractChain;
import net.crystalapps.mint.validator.library.models.ProcessModel;

/**
 * Created by Syed Owais Ali on 10/20/2018.
 */
public class MintFilterChain extends AbstractChain {

    @Override
    public void doFilter() {
        ProcessModel processModel = getProcessModel();
        if (processModel != null) {
            getFieldProcessor().process(processModel, this);
        }
    }
}
