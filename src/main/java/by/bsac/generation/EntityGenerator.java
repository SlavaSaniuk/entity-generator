package by.bsac.generation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class EntityGenerator<T> implements Generator<T> {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(EntityGenerator.class);

    //Required entity
    private T entity;

    /**
     * Construct new EntityGenerator<T> object.
     * @param t - Needed entity object.
     */
    public EntityGenerator(T t) {

        //Check on null
        if (t == null) throw new NullPointerException("Constructor argument is null");

        LOGGER.debug("Create  new instance of " +getClass().getSimpleName() +"<" +t.getClass().getSimpleName() +"> entity generator.");
        this.entity = t;
    }

    /**
     * Construct new EntityGenerator<T> object by entity type. Entity class must have public default (no argument) constructor.
     * @param clazz - Entity type (clazz).
     */
    public EntityGenerator(Class<T> clazz) {

        //Check on null
        if (clazz == null) throw new NullPointerException("Constructor argument is null");

        //Try to initialize entity instance
        try {
            LOGGER.debug("Create  new instance of " +getClass().getSimpleName() +"<" +clazz.getSimpleName() +"> entity generator.");
            this.entity = this.newInstance(clazz);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace(System.out);
            LOGGER.error(e.getMessage());
            throw new IllegalArgumentException(" Cannot instantiate instance. ");
        }
    }

    public T generate() {
        return this.entity;
    }

    private T newInstance(Class<T> clazz) throws IllegalAccessException, InstantiationException {
            return clazz.newInstance();
    }

    /**
     *  Static inner class work as "Builder" design pattern.
     * Uses for creating EntityGenerators object in cases when required
     * entity don't have default public constructor.
     * @param <V> - Required entity type.
     */
    public static class Builder<V> {

        private Class[] constructor_types; //Types in entity constructor
        private Object[] constructor_arguments; //Arguments for entity constructor

        /**
         * Set required entity constructor types. Method use reflection far searching and getting required constructor.
         * @param classes - constructor types.
         * @return - This {@link EntityGenerator.Builder<V>}
         */
        public Builder<V> constructorTypes(Class... classes) {
            this.constructor_types = classes;
            return this;
        }

        /**
         * Set arguments to required constructor. Note: if you don't want to concrete argument then use null value.
         * @param args - array of objects for constructor.
         * @return - This {@link EntityGenerator.Builder<V>}
         */
        public Builder<V> constructorArgs(Object... args) {
            this.constructor_arguments = args;
            return this;
        }

        /**
         * Build new {@link EntityGenerator<V>} object. User this builder for cases when entity don't have default or public constructors.
         * @param entity_class - Required entity type.
         * @return - new entity generator for required entity.
         */
        public EntityGenerator<V> build(Class<V> entity_class) {

            //Try to create entity_generator by entity constructor
            LOGGER.debug("Create " +EntityGenerator.class.getSimpleName() +" by entity constructor via builder");

            try {
                //Get entity required constructor
                LOGGER.debug("Get required entity constructor");
                Constructor<V> constructor = entity_class.getDeclaredConstructor(this.constructor_types);

                //Create new instance of required entity
                LOGGER.debug("Create new instance of required entity class");
                V entity = constructor.newInstance(this.constructor_arguments);

                LOGGER.debug("Create entity generator for required entity class");
                return new EntityGenerator<>(entity);

            } catch (NoSuchMethodException e) {
                LOGGER.error("Entity class don't have a constructor with specified types: " + Arrays.toString(constructor_types));
                throw new IllegalArgumentException(e.getMessage());

            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                LOGGER.error("Instantiation object failed. Cause:" +e.getCause().getMessage());
                throw new IllegalArgumentException(e.getMessage());
            }

        }

    }


}
