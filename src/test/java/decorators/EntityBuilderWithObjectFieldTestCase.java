package decorators;

import by.bsac.generation.EntityBuilder;
import entitties.constructors.TestEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EntityBuilderWithObjectFieldTestCase {

    private EntityBuilder<TestEntity> builder;

    private <B> boolean hasFromArray(B value, B[] array) {
        for (B b : array) {
            if (b.equals(value)) return true;
        }
        return false;
    }

    @BeforeEach
    void setUpBeforeEach() {
        this.builder = new EntityBuilder<>(TestEntity.class);
    }

    @AfterEach
    void tierDownAfterEach() {
        this.builder = null;
    }

    @Test
    //LOGGER
    void withObjectField_emptyFieldName_shouldSkipThisField() {
        this.builder.withObjectField("", StringBuilder.class, TestEntity.class);
    }

    @Test
    //LOGGER
    void withObjectField_emptyValuesArray_shouldSkipThisField() {
        this.builder.withObjectField("int_field", new Object[]{});
    }

    @Test
    //Debug
    void withObjectField_valuesArray_shouldSkipThisField() {
        this.builder.withObjectField("int_field", 54, 23, 43, 25);
    }

    @Test
    void withObjectField_newValuesArray_shouldSetThisField() {
        String[] available_values = {"Jack", "John", "Mike", "Kate", "Ann", "Timmy", "Stan", "Steve"};
        this.builder.withObjectField("string_field", available_values);
        Assertions.assertTrue(this.hasFromArray(this.builder.generate().getStringField(), available_values));
    }

    @Test
    void withObjectField_otherTypeValues_shouldSkipThisField() {
        Object[] available_values = {56,23,23,45,7};
        this.builder.withObjectField("string_field", available_values);
        Assertions.assertFalse(this.hasFromArray(this.builder.generate().getStringField(), available_values));
    }

}
