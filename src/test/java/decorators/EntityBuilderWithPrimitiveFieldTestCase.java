package decorators;

import by.bsac.generation.EntityBuilder;
import entitties.constructors.TestEntity;
import org.junit.jupiter.api.*;

class EntityBuilderWithPrimitiveFieldTestCase {

    private EntityBuilder<TestEntity> builder;

    @BeforeEach
    void setUpBeforeEach() {
        this.builder = new EntityBuilder<>(TestEntity.class);
    }

    @AfterEach
    void tierDownAfterEach() {
        this.builder = null;
    }

    private <B> boolean hasFromArray(B value, B[] array) {
        for (B b : array) {
            if (b.equals(value)) return true;
        }
        return false;
    }

    @Test
    void withPrimitiveField_ints_shouldSetRequiredField() {
        Integer[] values = {100045, 234567, 435, 34543, 7546 ,32423 ,2343, 2342,2112};
        this.builder.withPrimitiveField("int_field", Integer.class, values);
        TestEntity generated = this.builder.generate();
        Assertions.assertTrue(this.hasFromArray(generated.getIntField(), values));
    }

    @Test
    void withPrimitiveField_char_shouldSetRequiredField() {
        Character[] values = {'c','f','s','p','q'};
        this.builder.withPrimitiveField("char_field", Character.class, values);
        TestEntity generated = this.builder.generate();
        Assertions.assertTrue(this.hasFromArray(generated.getCharField(), values));
    }

    @Test
    void withPrimitiveField_emptyValuesArray_shouldSkipThisField() {
        Character[] values = {};
        this.builder.withPrimitiveField("char_field", Character.class, values);
        TestEntity generated = this.builder.generate();
        Assertions.assertEquals('\u0000', generated.getCharField());
    }

    @Test
    void withPrimitiveField_nullValuesArray_shouldSkipThisField() {
        Character[] values = null;
        this.builder.withPrimitiveField("char_field", Character.class, values);
        TestEntity generated = this.builder.generate();
        Assertions.assertEquals('\u0000', generated.getCharField());
    }

}
