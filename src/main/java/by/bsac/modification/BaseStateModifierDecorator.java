package by.bsac.modification;

import by.bsac.generation.EntityBuilder;

public class BaseStateModifierDecorator implements StateModifier {

    //Class variables
    private EntityBuilder builder;

    //Constructors
    public BaseStateModifierDecorator(EntityBuilder a_builder) {
        this.builder = a_builder;
    }

    //Methods
    @SuppressWarnings("unchecked")
    @Override
    public <P> void withPrimitiveField(String field_name, Class<P> wrapper_type, P... values) {
        this.builder.withPrimitiveField(field_name, wrapper_type, values);
    }

    @Override
    public void withObjectField(String field_name, Object... values) {
        this.builder.withObjectField(field_name, values);
    }
}
