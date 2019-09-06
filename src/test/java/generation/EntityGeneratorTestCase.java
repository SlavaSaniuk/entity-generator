package generation;

import by.bsac.generation.EntityGenerator;
import by.bsac.generation.Generator;
import entitties.NoDefaultConstructorEntity;
import entitties.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class EntityGeneratorTestCase {

    @Test
    void entityGenerator_newEntity_shouldReturnThisEntity() {
        //Test entity
        User user = new User();
        EntityGenerator<User> gen = new EntityGenerator<>(user);
        Assertions.assertEquals(user, gen.generate());
    }

    @Test
    void entityGenerator_nullEntity_shouldThrowNPE() {
        //Test entity
        User user = null;
        Assertions.assertThrows(NullPointerException.class, () -> new EntityGenerator<>(user));
    }

    @Test
    void entityGenerator_newClass_shouldReturnThisEntity() {
        //Test entity
        EntityGenerator<User> gen = new EntityGenerator<>(User.class);
        Assertions.assertNotNull(gen.generate());
    }

    @Test
    void entityGenerator_nullClass_shouldThrowNPE() {
        Assertions.assertThrows(NullPointerException.class, () -> new EntityGenerator<>(null));
    }

    @Test
    void entityGenerator_classIsInterface_shouldThrowIAE() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new EntityGenerator<>(Generator.class));
    }

    @Test
    void entityGenerator_classHasNotDefaultConstructor_shouldThrowIAE() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new EntityGenerator<>(NoDefaultConstructorEntity.class));
    }

}
