package by.bsac.modification;

/**
 * Class represent a primitive field in java {@link Class}.
 * @param <P> - Primitive field wrapper type.
 */
public class PrimitiveField<P> {

    //Class variables
    private String field_name;
    private PrimitiveTypes field_type;
    private P[] field_values;

    /**
     * Construct new {@link PrimitiveField} object.
     * @param a_name - primitive field name.
     * @param type - {@link PrimitiveTypes} primitive type.
     * @param a_values - Array of available values for this field.
     */
    @SafeVarargs
    public PrimitiveField(String a_name, PrimitiveTypes type, P... a_values) {
        this.field_name = a_name;
        this.field_values = a_values;
        this.field_type = type;
    }

    //Getters
    public String getFieldName() {        return field_name;    }
    public P[] getFieldValues() {        return field_values;    }
    public PrimitiveTypes getFieldType() {        return field_type;    }
}
