package by.bsac.modifacators;

public interface StateModifier {
    
    void modifyObjectField(String field_name, Object... args);

    <P> void modifyPrimitiveField(String field_name, Class<P> wrapper_type, P... args);
}
