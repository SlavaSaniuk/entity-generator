package decorators;

import by.bsac.generation.EntityGenerator;
import by.bsac.modifacators.FieldsDecorator;
import entitties.constructors.TestEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FieldsDecoratorsPrimitiveFieldsTestCase {

    private EntityGenerator<TestEntity> generator;

    @BeforeEach
    void setUpBeforeEach() {
        this.generator = new EntityGenerator<>(TestEntity.class);
    }

    @AfterEach
    void tierDownAfterEach() {
        this.generator = null;
    }

    private boolean oneOfTheSuggest(Object[] array, Object actual) {
        for (Object obj : array) if (obj == actual) return true;
        return false;
    }

    @Test
    void withByteField_byteArray_shouldUseSuggestValue() {

        Byte[] args = {100, 127, 50, 30};

        new FieldsDecorator(this.generator).withByteField("byte_field",args);

        Assertions.assertTrue(this.oneOfTheSuggest(args, this.generator.generate().getByteField()));
    }
}
