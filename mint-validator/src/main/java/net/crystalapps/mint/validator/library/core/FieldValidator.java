package net.crystalapps.mint.validator.library.core;

import android.support.annotation.NonNull;
import android.view.View;

import net.crystalapps.mint.validator.library.annotations.Order;
import net.crystalapps.mint.validator.library.config.ValidationConfig;
import net.crystalapps.mint.validator.library.models.ProcessModel;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static net.crystalapps.mint.validator.library.utils.ObjectUtil.requireNonNull;


/**
 * Created by Syed Owais Ali on 5/12/2018.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class FieldValidator {

    private static ValidationConfig validationConfig;
    private static Map<Object, List<Field>> fieldCache;

    private FieldValidator() {
        throw new SecurityException();
    }

    public static void validate(@NonNull Object inspectionObject, @NonNull ValidationListener listener) {
        validate(inspectionObject, listener, getValidationConfig());
    }

    public static void validate(@NonNull Object inspectionObject, @NonNull ValidationListener validationListener, @NonNull ValidationConfig config) {

        requireNonNull(inspectionObject);
        requireNonNull(validationListener);
        requireNonNull(config);

        AbstractChain abstractChain = config.getAbstractChain();
        abstractChain.init(config.getFieldProcessor(), validationListener);

        List<Field> sortedFields = loadFields(inspectionObject, config);
        for (Field field : sortedFields) {
            try {
                abstractChain.addProcessModel(new ProcessModel((View) field.get(inspectionObject), field));
            }
            catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }

        abstractChain.doFilter();

    }

    private static List<Field> loadFields(@NonNull Object inspectionObject, @NonNull ValidationConfig config) {

        if (getFieldCache().containsKey(inspectionObject)) {
            return getFieldCache().get(inspectionObject);
        }
        else {
            Map<Integer, Field> annotatedFieldsMap = new LinkedHashMap<>();

            try {
                for (Field field : config.getFieldProvider().getFields(inspectionObject)) {
                    field.setAccessible(true);
                    if (View.class.isAssignableFrom(field.getType()) && field.isAnnotationPresent(Order.class)) {
                        int order = (int) field.getAnnotation(Order.class).annotationType().getDeclaredMethod("value").invoke(field.getAnnotation(Order.class));
                        annotatedFieldsMap.put(order, field);
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

            List<Field> sortedFields = new LinkedList<>(new TreeMap<>(annotatedFieldsMap).values());
            getFieldCache().put(inspectionObject, sortedFields);
            return sortedFields;
        }
    }

    @NonNull
    private static Map<Object, List<Field>> getFieldCache() {
        if (fieldCache == null) {
            fieldCache = new HashMap<>();
        }
        return fieldCache;
    }

    @NonNull
    public static ValidationConfig getValidationConfig() {
        if (validationConfig == null) {
            validationConfig = new ValidationConfig.Builder().build();
        }

        return validationConfig;
    }

    public static void setDefaultConfig(@NonNull ValidationConfig validationConfig) {
        FieldValidator.validationConfig = requireNonNull(validationConfig);
    }
}
