package decorators;

import by.bsac.generation.EntityGenerator;
import entitties.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class StateModifierTestCase {

    @RepeatedTest(5)
    void modifyPrimitiveField_newIntegers_shouldSetInt() {

        Integer[] possible_values = {2,4,6,8,10, 12, 23,43, 52};

        EntityGenerator<User> generator = new EntityGenerator<>(User.class);

        generator.modifyPrimitiveField("userAge", Integer.class, possible_values);

        User generated = generator.generate();
        System.out.println(generated.getUserAge());

        Assertions.assertNotNull(generated);
        Assertions.assertNotEquals(0, generated.getUserAge());

        boolean hasTrue = false;
        for (int i : possible_values) {
            if (i == generated.getUserAge()) {
                hasTrue = true;
                break;
            }
        }

        Assertions.assertTrue(hasTrue);
    }

    @RepeatedTest(5)
    void modifyPrimitiveField_newCharacters_shouldSetChar() {

        Character[] possible_values = {'m', 'f', 'a'};

        EntityGenerator<User> generator = new EntityGenerator<>(User.class);

        generator.modifyPrimitiveField("user_sex", Character.class, possible_values);

        User generated = generator.generate();
        System.out.println(generated.getUser_sex());

        Assertions.assertNotNull(generated);
        Assertions.assertNotEquals('\u0000', generated.getUser_sex());

        boolean hasTrue = false;
        for (char i : possible_values) {
            if (i == generated.getUser_sex()) {
                hasTrue = true;
                break;
            }
        }

        Assertions.assertTrue(hasTrue);
    }

    @Test
    void modifyPrimitiveField_argumentArrayIsEmpty_shouldSkipThisField() {

        EntityGenerator<User> generator = new EntityGenerator<>(User.class);
        generator.modifyPrimitiveField("userAge", Integer.class);

        User generated = generator.generate();

        Assertions.assertNotNull(generated);
        Assertions.assertEquals(0, generated.getUserAge());

    }

    @Test
    void modifyPrimitiveField_argumentArrayIsNull_shouldSkipThisField() {

        EntityGenerator<User> generator = new EntityGenerator<>(User.class);
        generator.modifyPrimitiveField("userAge", Integer.class, null);

        User generated = generator.generate();

        Assertions.assertNotNull(generated);
        Assertions.assertEquals(0, generated.getUserAge());

    }
}
