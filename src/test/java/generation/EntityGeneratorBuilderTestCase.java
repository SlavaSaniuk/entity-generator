package generation;

import by.bsac.generation.EntityGenerator;
import entitties.NoDefaultConstructorEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EntityGeneratorBuilderTestCase {

    @Test
    void build_allParametersAreTrue_shouldReturnEntityGenerator() {
        EntityGenerator<NoDefaultConstructorEntity> gen = new EntityGenerator.Builder<NoDefaultConstructorEntity>()
                .constructorTypes(String.class, Boolean.class).constructorArgs("Hello world", true).build(NoDefaultConstructorEntity.class);

        Assertions.assertNotNull(gen);
    }

    @Test
    void build_noRequiredConstructorFound_shouldThrowIAE() {
        EntityGenerator.Builder<NoDefaultConstructorEntity> gen = new EntityGenerator.Builder<NoDefaultConstructorEntity>()
                .constructorTypes(String.class, Integer.class).constructorArgs("Hello world", true);

        Assertions.assertThrows(IllegalArgumentException.class, () -> gen.build(NoDefaultConstructorEntity.class));
    }

    @Test
    void build_argsArrayIsNotFull_shouldThrowIAE() {
        EntityGenerator.Builder<NoDefaultConstructorEntity> gen = new EntityGenerator.Builder<NoDefaultConstructorEntity>()
                .constructorTypes(String.class, Boolean.class).constructorArgs("Hello world");

        Assertions.assertThrows(IllegalArgumentException.class, () -> gen.build(NoDefaultConstructorEntity.class));
    }
}
