package by.bsac.modifacators;

public class BaseStateModifierDecorator implements StateModifier {

    //Class variables
    private StateModifier modifier;

    BaseStateModifierDecorator(StateModifier a_modifier) {
        this.modifier = a_modifier;
    }

    @Override
    public void modifyObjectField(String field_name, Object... args) {
        this.modifier.modifyObjectField(field_name, args);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final <P> void modifyPrimitiveField(String field_name, Class<P> wrapper_type, P... args) {
        this.modifier.modifyPrimitiveField(field_name, wrapper_type, args);
    }
}
