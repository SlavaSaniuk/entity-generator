package generation;

import by.bsac.generation.EntityBuilder;
import entitties.ArgsConstructorEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class ConstructorBuilderTestCase {

    @Test
    void build_notSetConstructorTypes_shouldThrowNPE() {
        Assertions.assertThrows(NullPointerException.class, () -> new EntityBuilder.ConstructorBuilder<ArgsConstructorEntity>().withConstructorArguments(5, "Hello").build(ArgsConstructorEntity.class));
    }

    @Test
    void build_notSetConstructorArguments_shouldThrowNPE() {
        Assertions.assertThrows(NullPointerException.class, () -> new EntityBuilder.ConstructorBuilder<ArgsConstructorEntity>().withConstructorArgumentsTypes(Integer.class, String.class).build(ArgsConstructorEntity.class));
    }

    @Test
    void build_invalidConstructorTypes_shouldThrowIAE() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new EntityBuilder.ConstructorBuilder<ArgsConstructorEntity>()
                .withConstructorArgumentsTypes(Character.class, String.class)
                .withConstructorArguments(5, "Hello")
                .build(ArgsConstructorEntity.class));
    }

    @Test
    void build_invalidConstructorArguments_shouldThrowIAE() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new EntityBuilder.ConstructorBuilder<ArgsConstructorEntity>()
                .withConstructorArgumentsTypes(Integer.TYPE, String.class)
                .withConstructorArguments("Hello", "World")
                .build(ArgsConstructorEntity.class));
    }

    @Test
    void build_validConstructor_shouldReturnCreatedEntityBuilder() {
        EntityBuilder<ArgsConstructorEntity> builder = new EntityBuilder.ConstructorBuilder<ArgsConstructorEntity>()
                .withConstructorArgumentsTypes(Integer.TYPE, String.class)
                .withConstructorArguments(5, "Hello world!")
                .build(ArgsConstructorEntity.class);
        Assertions.assertNotNull(builder);
    }

    @RepeatedTest(10)
    void generate_validConstructor_shouldReturnCreatedEntity() {
        EntityBuilder<ArgsConstructorEntity> builder = new EntityBuilder.ConstructorBuilder<ArgsConstructorEntity>()
                .withConstructorArgumentsTypes(Integer.TYPE, String.class)
                .withConstructorArguments(5, "Hello world!")
                .build(ArgsConstructorEntity.class);

        Assertions.assertNotNull(builder.generate());
    }




}
