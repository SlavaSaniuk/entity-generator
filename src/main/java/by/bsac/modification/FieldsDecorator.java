package by.bsac.modification;

import by.bsac.generation.EntityBuilder;

public class FieldsDecorator extends BaseStateModifierDecorator {

    public FieldsDecorator(EntityBuilder a_builder) {
        super(a_builder);
    }

    //Methods
    //Of primitives fields
    public void withByteField(String field_name, Byte... values) {
        super.withPrimitiveField(field_name, Byte.class, values);
    }
    public void withCharField(String field_name, Character... values) {
        super.withPrimitiveField(field_name, Character.class, values);
    }
    public void withShortField(String field_name, Short... values) {
        super.withPrimitiveField(field_name, Short.class, values);
    }
    public void withIntField(String field_name, Integer... values) {
        super.withPrimitiveField(field_name, Integer.class, values);
    }
    public void withLongField(String field_name, Long... values) {
        super.withPrimitiveField(field_name, Long.class, values);
    }
    public void withFloatField(String field_name, Float... values) {
        super.withPrimitiveField(field_name, Float.class, values);
    }
    public void withDoubleField(String field_name, Double... values) {
        super.withPrimitiveField(field_name, Double.class, values);
    }
    public void withBooleanField(String field_name, Boolean... values) {
        super.withPrimitiveField(field_name, Boolean.class, values);
    }

    //Of objects fields
    public void withObjectField(String field_name, Object... values) {
        super.withObjectField(field_name, values);
    }
}
