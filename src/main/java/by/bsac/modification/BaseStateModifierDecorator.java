package by.bsac.modification;

public class BaseStateModifierDecorator implements StateModifier {

    //Class variables
    private StateModifier modifier;

    //Constructors
    public BaseStateModifierDecorator(StateModifier a_modifier) {
        this.modifier = a_modifier;
    }

    //Methods
    @SuppressWarnings("unchecked")
    @Override
    public <P> void withPrimitiveField(String field_name, Class<P> wrapper_type, P... values) {
        modifier.withPrimitiveField(field_name, wrapper_type, values);
    }
}
