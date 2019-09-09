package by.bsac.generation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Main class that implements {@link Generator<T>} interface.
 * @param <T> - Required entity type.
 */
public class EntityGenerator<T> implements Generator<T> {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(EntityGenerator.class);
    //Class fields
    private T entity; //Required entity
    private Map<String, PrimitiveField> primitive_fields = new HashMap<>();

    //Constructors
    /**
     * Construct new EntityGenerator<T> object.
     * @param t - Needed entity object.
     */
    private EntityGenerator(T t) {

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
            LOGGER.debug("Create  new instance of " + getClass().getSimpleName() + "<" + clazz.getSimpleName() + "> entity generator");
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            this.entity = constructor.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            LOGGER.error("Entity class does not have default constructor " +e.getMessage());
            e.printStackTrace();
            throw new IllegalArgumentException("Request entity class " +clazz.getSimpleName() +" does not has default constructor");
        }
    }

    @Override
    public T generate() {
        this.modifyPrimitiveFields();
        return this.entity;
    }
    public final void modifyObjectField(String field_name, Object... args) {

    }

    @SuppressWarnings("unchecked")
    public final  <P> void modifyPrimitiveField(String field_name, Class<P> wrapper_type, P... args) {
        this.primitive_fields.put(field_name, new PrimitiveField<>(wrapper_type, args));
    }



    private void modifyPrimitiveFields() {

        //Iterate map of primitive fields
        for (Map.Entry<String, PrimitiveField> entry : this.primitive_fields.entrySet()) {

            //Set field to value
            LOGGER.debug("Try to set [" +entry.getKey() +"] field to [" +this.entity.getClass().getSimpleName() +"] entity.");
            try{

                //Get field from entity class and set it accessible
                final Field field = this.entity.getClass().getDeclaredField(entry.getKey()); //Get this declared field
                LOGGER.debug("Set field [" +entry.getKey() +"] accessible");
                field.setAccessible(true); //Set this field accessible

                //Try to set field with new value
                PrimitiveField primitive_field = entry.getValue();
                //Check values array on emptiness
                if (primitive_field.getArguments() == null || primitive_field.getArguments().length == 0) {
                    LOGGER.debug("Values array for field [" +entry.getKey() +"] are not specified. Skip this field.");
                    continue;
                }

                //Determine primitive type
                //Byte type
                if (primitive_field.getWrapperType() == Byte.class) {
                    LOGGER.debug("Primitive value is [byte].");

                    Byte[] values = (Byte[])  primitive_field.getArguments(); //Get and cast values to byte array
                    Byte value = values[new Random().nextInt(values.length)]; //Get random byte value
                    LOGGER.debug("Selected value to field [" +entry.getKey() +"] is \"" +value.toString() +"\"");
                    field.set(this.entity, value); //Set value
                }

                //Integer type
                if (primitive_field.getWrapperType() == Integer.class) {
                    LOGGER.debug("Primitive value is [int].");

                    Integer[] values = (Integer[]) primitive_field.getArguments(); //Get and cast values to ints
                    Integer value = values[new Random().nextInt(values.length)]; //Get random int value
                    field.set(this.entity, value);
                }

                //Character type
                if (primitive_field.getWrapperType() == Character.class) {
                    LOGGER.debug("Primitive value is [char].");

                    Character[] values = (Character[]) primitive_field.getArguments(); //Get and cast values to characters
                    Character value = values[new Random().nextInt(values.length)]; //Get random character value
                    field.set(this.entity, value);
                }

            } catch (NoSuchFieldException e) {
                LOGGER.debug("Entity " +this.entity.getClass().getSimpleName() +" doesn't have a field  with name " +entry.getKey() +". Skip this field.");

            } catch (IllegalAccessException e) {
                LOGGER.debug("Field " +entry.getKey() +" is final or inaccessible. ");
            }

        }
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

    private static class PrimitiveField<P>{

        private Class<P> wrapper_type;
        private P[] arguments;

        PrimitiveField(Class<P> a_wrapper_type, P[] a_arguments) {
            this.wrapper_type = a_wrapper_type;
            this.arguments = a_arguments;
        }

        Class<P> getWrapperType() {
            return this.wrapper_type;
        }
        P[] getArguments() {
            return this.arguments;
        }
    }

}
