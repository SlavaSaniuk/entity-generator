package generation;

import by.bsac.generation.EntityGenerator;
import entitties.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WithFieldTestCase {

    @Test
    void withField_fieldWithThisNameNotExist_shouldSkipThisField() {

        EntityGenerator<User> generator = new EntityGenerator<>(User.class);

        generator.withField("userName", "Alex", "Jim", "Terry", "Barry", "Billy", "Stan", "Ann", "Holy");

        User generated = generator.generate();

        Assertions.assertNull(generated.getUser_name());

    }

    @Test
    void withField_valuesArray_shouldUseOneFromThisValues() {

        EntityGenerator<User> generator = new EntityGenerator<>(User.class);

        String[] name_values = {"Alex", "Jim", "Terry", "Barry", "Billy", "Stan", "Ann", "Holy"};
        generator.withField("user_name", name_values);

        User generated = generator.generate();
        boolean hasName = false;

        for (String name : name_values) {
            if (name.equals(generated.getUser_name())) {
                hasName = true;
                break;
            }
        }

        Assertions.assertTrue(hasName);

    }

    @Test
    void withField_emptyArray_shouldSkipThisField() {

        EntityGenerator<User> generator = new EntityGenerator<>(User.class);

        generator.withField("user_name", null);

        User generated = generator.generate();

        Assertions.assertNull(generated.getUser_name());

    }

    @Test
    void withField_ifPrimitiveType_shouldUseOneFromThisValues() {

        EntityGenerator<User> generator = new EntityGenerator<>(User.class);

        int[] age_values = {9,10,12};
        generator.withField("userAge", age_values);

        User generated = generator.generate();
        Assertions.assertNotEquals(0, generated.getUserAge());

        boolean hasName = false;

        for (int age : age_values) {
            if (age == generated.getUserAge()) {
                hasName = true;
                break;
            }
        }

        Assertions.assertTrue(hasName);

    }


}
