package by.bsac.modification;

public interface StateModifier {

    @SuppressWarnings("unchecked")
    <P> void withPrimitiveField(String field_name, Class<P> wrapper_type, P... values);
}
