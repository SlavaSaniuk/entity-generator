package by.bsac.modifacators;

public class FieldsModifierDecorator extends BaseStateModifierDecorator {

    //Constructor
    public FieldsModifierDecorator(StateModifier a_modifier) {
        super(a_modifier);
    }

    public void withObjectField(String field_name, Object... args) {
        super.modifyObjectField(field_name, args);
    }

    public void withIntegerField(String field_name, Integer... args) {
        super.modifyPrimitiveField(field_name, Integer.class, args);
    }

    public void withCharacterField(String field_name, Character... args) {
        super.modifyPrimitiveField(field_name, Character.class, args);
    }

}
