package entitties.constructors;

public class NoDefaultConstructorEntity {

    private String string_field;

    public NoDefaultConstructorEntity(String a_string) {
        this.string_field = a_string;
        System.out.println(this.string_field);
    }
}
