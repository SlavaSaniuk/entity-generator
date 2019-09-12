package by.bsac.modification;

public interface StateModifier {

    /**
     * Set random value tp primitive fields. Methods get random value form available array of values and set it to field specified by field_name. You must to set wrapper type of primitive field in "wrapper_type" {@link Class} argument.
     * @param field_name - {@link java.lang.reflect.Field} - field name to set.
     * @param wrapper_type - Wrapper type of primitive field.
     * @param values - Array of available values.
     * @param <P> - Wrapper type.
     */
    @SuppressWarnings("unchecked")
    <P> void withPrimitiveField(String field_name, Class<P> wrapper_type, P... values);

    void withObjectField(String field_name, Object... values);
}
