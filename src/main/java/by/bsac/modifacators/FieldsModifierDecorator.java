package by.bsac.modifacators;

public class FieldsModifierDecorator extends BaseStateModifierDecorator {

    //Constructor
    public FieldsModifierDecorator(StateModifier a_modifier) {
        super(a_modifier);
    }

    public void withObjectField(String field_name, Object... args) {
        super.modifyObjectField(field_name, args);
    }


    /*
        Methods that's modify primitive fields
     */
    public void withByteField(String field_name, Byte... args){
        super.modifyPrimitiveField(field_name, Byte.class, args);
    }
    public void withCharField(String field_name, Character... args) {
        super.modifyPrimitiveField(field_name, Character.class, args);
    }
    public void withShortField(String field_name, Short... args) {
        super.modifyPrimitiveField(field_name, Short.class, args);
    }
    public void withIntField(String field_name, Integer... args) {
        super.modifyPrimitiveField(field_name, Integer.class, args);
    }
    public void withLongField(String field_name, Long... args) {
        super.modifyPrimitiveField(field_name, Long.class, args);
    }

    public void withFloatField(String field_name, Float... args){
        super.modifyPrimitiveField(field_name, Float.class, args);
    }
    public void withDoubleField(String field_name, Double... args){
        super.modifyPrimitiveField(field_name, Double.class, args);
    }

    public void withBooleanField(String field_name) {
        super.modifyPrimitiveField(field_name, Boolean.class, true, false);
    }

}
