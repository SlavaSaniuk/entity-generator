package by.bsac.generation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class EntityBuilder<T> implements Generator<T> {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(EntityBuilder.class);

    //Variables
    private Class<T> entity_type; //Required entity class
    private boolean use_builder; //Builder uses flag
    // In cases when this builder will create new entities via non-default constructor
    private Class[] constructor_types = null; //Constructor arguments types
    private Object[] constructor_args = null; //Constructor arguments

    //Constructors
    // Private constructor uses by ConstructBuilder.
    private EntityBuilder(){}

    /**
     * Construct new EntityBuilder object by required entity type.
     * Use this constructor if required entity has a default (no arguments) constructor. Constructor may be private.
     * @param a_entity_type - required entity constructor.
     */
    public EntityBuilder(Class<T> a_entity_type) {

        //Check whether entity class is null
        if (a_entity_type == null) throw new NullPointerException("Required entity class is null.");

        LOGGER.debug("Try to create new instance of " +a_entity_type.getName() +" class");
        try {

            Constructor<T> default_constructor = a_entity_type.getDeclaredConstructor(); //Get default constructor
            default_constructor.setAccessible(true); //Set it is accessible

            //Try to create new instance
            default_constructor.newInstance();

            LOGGER.debug("Successfully create new instance of " +a_entity_type.getName() +" class.");
            //Set "use_builder" flag to false
            this.use_builder = false;
            //Set required entity type
            this.entity_type = a_entity_type;

        } catch (NoSuchMethodException e) { //If entity don't fave a default constructor.
            LOGGER.error("Required entity class don't have a default constructor or interface.");
            e.printStackTrace();
            throw new IllegalArgumentException(a_entity_type.getName() +" don't have a default constructor.");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Cannot create a entity instance with private default constructor");
        } catch (InstantiationException e) {
            LOGGER.error("Required entity is not class. You can't create abstract classes, interfaces, annotations.");
            e.printStackTrace();
            throw new IllegalArgumentException(a_entity_type.getName() +" is not a class.");
        } catch (InvocationTargetException e) {
            LOGGER.error("Entity instance creation throws with exception: ");
            e.printStackTrace();
            throw new IllegalArgumentException("Entity instance creation throws with exception: " +e.getCause());
        }

    }


    /**
     * "Builder" pattern. Uses to create {@link EntityBuilder<T>} object if required entity don't have a default(No arguments) constructor.
     * You need to specify constructor arguments via {@link ConstructorBuilder#withConstructorArguments(Object...)} and constructor arguments
     * types via {@link ConstructorBuilder#withConstructorArgumentsTypes(Class[])}.
     * Note: If required entity has a primitive constructor arguments you need to specify
     * in withConstructorArgumentsTypes method class VIA type (Integer.TYPE, Character.TYPE, Boolean.Type);
     * @param <T> - Required entity type.
     */
    public static class ConstructorBuilder<T> {

        //Resulting EntityBuilder
        private final EntityBuilder<T> builder = new EntityBuilder<>();

        /**
         * Set required entity constructor arguments.
         * @param arguments - constructor arguments
         * @return - this ConstructorBuilder
         */
        public ConstructorBuilder<T> withConstructorArguments(Object... arguments) {

            //Set EntityBuilder constructor arguments
            this.builder.constructor_args = arguments;

            //Return this builder
            return this;
        }

        /**
         * Set required entity constructor arguments classes. If required entity has a primitive constructor arguments
         * you need to specify class VIA type (Integer.TYPE, Character.TYPE, Boolean.Type);
         * @param arguments_types - constructor arguments classes.
         * @return - this ConstructorBuilder
         */
        public ConstructorBuilder<T> withConstructorArgumentsTypes(Class... arguments_types) {

            //Set EntityBuilder constructor arguments types
            this.builder.constructor_types = arguments_types;

            //Return this builder
            return this;
        }

        /**
         * Create new {@link EntityBuilder} for required entity type You must to set constructor arguments and constructor arguments types via {@link ConstructorBuilder} relevant methods.
         * @param a_entity_type - required entity class.
         * @return - {@link EntityBuilder} object.
         */
        public EntityBuilder<T> build(Class<T> a_entity_type) {

            //Check whether constructor arg and types is set
            if(builder.constructor_types == null || builder.constructor_args == null)
                throw new NullPointerException("Constructor types or arguments is not set.");

            //Try to create entity by required constructor
            LOGGER.debug("Try to create " +a_entity_type.getName() +" by required constructor.");

            try {
                //Get required constructor
                Constructor<T> constructor = a_entity_type.getDeclaredConstructor(builder.constructor_types);
                LOGGER.debug("Found constructor with types " + Arrays.toString(builder.constructor_types));
                constructor.setAccessible(true);
                LOGGER.debug("Set this constructor accessible.");

                //Try to create new instance of required entity
                constructor.newInstance(builder.constructor_args);

                //Set "use builder" flag to true.
                builder.use_builder = true;
                //Set required entity type
                builder.entity_type = a_entity_type;

                //Return EntityBuilder for required entity type
                LOGGER.debug(EntityBuilder.class.getName() +" was created for entity type: " +a_entity_type.getName());
                return this.builder;


            } catch (NoSuchMethodException e) {
                LOGGER.error("Not founded constructor with arguments types " + Arrays.toString(builder.constructor_types));
                e.printStackTrace();
                throw new IllegalArgumentException("Invalid constructor arguments types for" +a_entity_type.getName() +" class.");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Cannot create a entity instance with private default constructor");
            } catch (InstantiationException e) {
                LOGGER.error("Required entity is not class. You can't create abstract classes, interfaces, annotations.");
                e.printStackTrace();
                throw new IllegalArgumentException(a_entity_type.getName() +" is not a class.");
            } catch (InvocationTargetException e) {
                LOGGER.error("Entity instance creation throws with exception: ");
                e.printStackTrace();
                throw new IllegalArgumentException("Entity instance creation throws with exception: " +e.getCause());
            } catch (IllegalArgumentException e) {
                LOGGER.debug("Constructor arguments are invalid.");
                e.printStackTrace();
                throw new IllegalArgumentException(e);
            }
        }
    }

    //Methods
    /*
        Method uses to create new entities for "Generator#generate()" method.
        Method determine how to create new entities(via default or arguments constructor) and try create new entities.
     */
    private T createEntity() {
        try {
            if (this.use_builder) {
                //Create entity via arguments constructor
                Constructor<T> constructor = this.entity_type.getDeclaredConstructor(this.constructor_types); //Get constructor
                constructor.setAccessible(true); //Set it accessible
                return constructor.newInstance(this.constructor_args); //Return new instance
            }else {
                //Create entity via default constructor
                Constructor<T> constructor = this.entity_type.getDeclaredConstructor();
                constructor.setAccessible(true);
                return constructor.newInstance();
            }

        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getCause());
        }
    }

    @Override
    public T generate() {
        return this.createEntity();
    }

}
