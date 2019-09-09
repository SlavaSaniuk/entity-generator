package by.bsac.modification;

import java.text.ParseException;

public enum PrimitiveTypes {
    BYTE,
    CHAR,
    SHORT,
    INT,
    LONG,
    FLOAT,
    DOUBLE,
    BOOLEAN;

    public static PrimitiveTypes wrapperClassToPrimitiveType(Class<?> wrapper_type) {
        switch (wrapper_type.getSimpleName()) {
            case "Byte":
                return BYTE;
            case "Character":
                return CHAR;
            case "Short":
                return SHORT;
            case "Integer":
                return INT;
            case "Long":
                return LONG;
            case "Float":
                return FLOAT;
            case "Double":
                return DOUBLE;
            case "Boolean":
                return BOOLEAN;
            default:
                throw new IllegalArgumentException("This class [" +wrapper_type.getSimpleName() +"] is not primitive wrapper type.");
        }
    }
}
