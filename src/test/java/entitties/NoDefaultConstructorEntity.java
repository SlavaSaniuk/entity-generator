package entitties;

public class NoDefaultConstructorEntity {

    public NoDefaultConstructorEntity(String hello) {
        System.out.println(hello);
    }

    public NoDefaultConstructorEntity(String hello, String s) {
        System.out.println(hello);
    }

    public NoDefaultConstructorEntity(String hello, Boolean b) {
        System.out.println(hello);
    }

}
