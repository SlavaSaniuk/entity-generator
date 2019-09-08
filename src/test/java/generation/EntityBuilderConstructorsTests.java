package generation;

import by.bsac.generation.EntityBuilder;
import entitties.constructors.TestEntity;
import entitties.constructors.ExceptionConstructor;
import entitties.constructors.InterfaceEntity;
import entitties.constructors.NoDefaultConstructorEntity;
import entitties.constructors.PrivateDefaultConstructorEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EntityBuilderConstructorsTests {

    @Test
    void entityBuilder_requiredEntityClassIsNull_shouldThrowNPE() {
        Assertions.assertThrows(NullPointerException.class, () -> new EntityBuilder<TestEntity>(null));
    }

    @Test
    void entityBuilder_requiredEntityDoNotHaveDefaultConstructor_shouldThrowIAE() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new EntityBuilder<>(NoDefaultConstructorEntity.class));
    }

    @Test
    void entityBuilder_requiredEntityHasPrivateConstructor_shouldCreateEntityBuilder() {
        EntityBuilder<PrivateDefaultConstructorEntity> builder = new EntityBuilder<>(PrivateDefaultConstructorEntity.class);
        Assertions.assertNotNull(builder);
    }

    @Test
    void entityBuilder_requiredEntityIsInterface_shouldThrowIAE() {
        Assertions.assertThrows(IllegalArgumentException.class, ()->  new EntityBuilder<>(InterfaceEntity.class));
    }

    @Test
    void entityBuilder_requiredEntityConstructorThrowNewException_shouldThrowIAE() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new EntityBuilder<>(ExceptionConstructor.class));
    }

    @Test
    void entityBuilder_requiredEntityHasDefaultConstructor_shouldCreateEntityBuilder() {
        EntityBuilder<TestEntity> builder = new EntityBuilder<>(TestEntity.class);
        Assertions.assertNotNull(builder);
    }

    @Test
    void generate_requiredEntityHasDefaultConstructor_shouldRetuenCreatedEntity() {
        EntityBuilder<TestEntity> builder = new EntityBuilder<>(TestEntity.class);
        Assertions.assertNotNull(builder);

        Assertions.assertNotNull(builder.generate());
    }


}
