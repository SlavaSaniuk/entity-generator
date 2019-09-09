package decorators;

import by.bsac.generation.EntityBuilder;
import by.bsac.modification.FieldsDecorator;
import entitties.constructors.TestEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FieldsDecoratorPrimitiveFieldsTestCase {

    private <B> boolean hasFromArray(B value, B[] array) {
        for (B b : array) {
            if (b.equals(value)) return true;
        }
        return false;
    }

    @Test
    void withIntField_ints_shouldSetIntField() {

        Integer[] values = {10,20,30,40,50,60};
        EntityBuilder<TestEntity> generator = new EntityBuilder<>(TestEntity.class);
        FieldsDecorator field_decorator = new FieldsDecorator(generator);
        field_decorator.withIntField("int_field", values);

        Assertions.assertTrue(this.hasFromArray(generator.generate().getIntField(), values));

    }
}
