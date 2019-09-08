package entitties;

public class ArgsConstructorEntity {

    private int int_field;
    private String string_field;

    public ArgsConstructorEntity(int a_int, String a_string) {
        this.int_field = a_int;
        this.string_field = a_string;
        System.out.println(string_field +" " +int_field);
    }
}
