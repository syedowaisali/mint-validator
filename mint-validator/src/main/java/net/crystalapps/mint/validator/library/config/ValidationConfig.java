package net.crystalapps.mint.validator.library.config;

import android.support.annotation.NonNull;

import net.crystalapps.mint.validator.library.core.AbstractChain;
import net.crystalapps.mint.validator.library.core.FieldProcessor;
import net.crystalapps.mint.validator.library.core.FieldProvider;
import net.crystalapps.mint.validator.library.defaults.MintFieldProcessor;
import net.crystalapps.mint.validator.library.defaults.MintFilterChain;
import net.crystalapps.mint.validator.library.defaults.MintFieldProvider;

/**
 * Created by Syed Owais Ali on 10/19/2018.
 */

@SuppressWarnings("unused")
public class ValidationConfig {

    private final Builder builder;

    private ValidationConfig(@NonNull Builder builder) {
        this.builder = builder;
    }

    @NonNull
    public FieldProvider getFieldProvider() {
        return builder.fieldProvider;
    }

    @NonNull
    public AbstractChain getAbstractChain() {
        return builder.abstractChain;
    }

    @NonNull
    public FieldProcessor getFieldProcessor() {
        return builder.fieldProcessor;
    }

    public static class Builder{

        private FieldProvider fieldProvider;
        private AbstractChain abstractChain;
        private FieldProcessor fieldProcessor;

        public Builder() {
            fieldProvider = new MintFieldProvider();
            abstractChain = new MintFilterChain();
            fieldProcessor = new MintFieldProcessor();
        }

        public Builder setFieldProvider(@NonNull FieldProvider fieldProvider) {
            this.fieldProvider = fieldProvider;
            return this;
        }

        public Builder setAbstractChain(@NonNull AbstractChain abstractChain) {
            this.abstractChain = abstractChain;
            return this;
        }

        public Builder setFieldProcessor(@NonNull FieldProcessor fieldProcessor) {
            this.fieldProcessor = fieldProcessor;
            return this;
        }

        public ValidationConfig build() {
            return new ValidationConfig(this);
        }
    }
}

