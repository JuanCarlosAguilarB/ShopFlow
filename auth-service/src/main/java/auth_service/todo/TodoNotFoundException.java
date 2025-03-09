package auth_service.todo;

public class TodoNotFoundException extends Exception {

    public TodoNotFoundException (String message) {
        super(message);
    }
}
