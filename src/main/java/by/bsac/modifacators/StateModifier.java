package by.bsac.modifacators;

public interface StateModifier {
    
    void modifyObjectField(String field_name, Object... args);

    /**
     * Methods access to field by specified field name, and set random parameter from specified args to this field.
     * @param field_name - name of field.
     * @param wrapper_type - primitive class wrapper type.
     * @param args - available values for this field.
     * @param <P> - generic represent a class wrapper type.
     */
    <P> void modifyPrimitiveField(String field_name, Class<P> wrapper_type, P... args);
}
