package by.bsac.modification;

public class PrimitiveField<P> {

    //Class variables
    private String field_name;
    private PrimitiveTypes field_type;
    private P[] field_values;

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
