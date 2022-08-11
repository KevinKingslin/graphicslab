// Question 2 and 3

class Second {
    public void print(){
        System.out.println("This is second class (Default package)");
    }
}

public class First {
    public static void main(String[] args){
        Second s = new Second();
        s.print();
    }
}
