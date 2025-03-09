package auth_service;

public class Greeting {

    private String message;

    public Greeting() {
    }

    public Greeting(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        System.out.println("vamooooossss");
        return "Greeting{" +
                "message='" + message + '\'' +
                '}';
    }
}