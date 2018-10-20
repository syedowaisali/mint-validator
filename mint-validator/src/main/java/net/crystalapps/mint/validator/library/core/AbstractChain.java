package net.crystalapps.mint.validator.library.core;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.crystalapps.mint.validator.library.models.ProcessModel;
import net.crystalapps.mint.validator.library.utils.ObjectUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Syed Owais Ali on 10/20/2018.
 */

@SuppressWarnings("WeakerAccess")
public abstract class AbstractChain implements FilterChain {

    @NonNull
    private final Queue<ProcessModel> processQueue;
    private FieldProcessor fieldProcessor;

    protected AbstractChain() {
        processQueue = new LinkedList<>();
    }

    protected void init(@NonNull FieldProcessor fieldProcessor, @NonNull ValidationListener validationListener) {
        this.processQueue.clear();
        this.fieldProcessor = ObjectUtil.requireNonNull(fieldProcessor);
        this.fieldProcessor.init(validationListener);
    }

    public void addProcessModel(@NonNull ProcessModel processModel) {
        processQueue.add(processModel);
    }

    @Nullable
    public ProcessModel getProcessModel() {
        return processQueue.poll();
    }

    @NonNull
    public FieldProcessor getFieldProcessor() {
        return fieldProcessor;
    }

    @Override
    public boolean hasNext() {
        return processQueue.size() > 0;
    }
}
