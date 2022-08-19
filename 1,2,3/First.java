// import userdefined.Second; // User-defined package
// import userdefined.nested.Third; // Nested user-defined package


import userdefined.Second;
import userdefined.nested.Third;

public class First {
    public void print() {
        Second second = new Second();
        Third third = new Third();
        second.printsecond();
        third.printthird();
    }

    public static void main(String[] args) {
        new First().print();
    }
}
