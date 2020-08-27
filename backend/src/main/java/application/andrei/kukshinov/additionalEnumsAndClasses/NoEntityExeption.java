package application.andrei.kukshinov.additionalEnumsAndClasses;

public class NoEntityExeption extends NullPointerException {
    public NoEntityExeption(Long id) {
        System.err.println("User {" + id + "} is not found. Check the correctness of data");
    }
}
