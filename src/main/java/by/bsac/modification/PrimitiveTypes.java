package by.bsac.modification;

/**
 * Enum which determine primitive types of java language. Also has a convert
 * methods that's convert {@link Class} type object to this constants and vice versa.
 */
public enum PrimitiveTypes {
    BYTE, CHAR, SHORT, INT, LONG, FLOAT, DOUBLE, BOOLEAN;

    /**
     * Convert from {@link Class} type to {@link PrimitiveTypes} constant.
     * @param wrapper_type - Primitive wrapper {@link Class} class.
     * @return - Constant for specified wrapper type.
     */
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
