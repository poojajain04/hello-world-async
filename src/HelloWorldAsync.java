import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class HelloWorldAsync {

    static void sayHello() {
        System.out.print("Hello");
    }

    static void sayWorld() {
        System.out.print("World");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int i = 0;
        while (i < 6) {
            sayHelloWorldAsync();
            Thread.sleep(10000);
            i++;
        }
    }

    private static void sayHelloWorldAsync() {
        CompletableFuture<Void> sayHelloFuture = CompletableFuture.runAsync(() -> {
            sayHello();
        });

        CompletableFuture<Void> sayWorldFuture = CompletableFuture.runAsync(() -> {
            sayWorld();
        });

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(sayHelloFuture, sayWorldFuture);

        try {
            combinedFuture.get(); // waiting for all the async code to be completed.
        }
        catch (InterruptedException  | ExecutionException e) {
            System.out.println("ERROR occurred" + e.getMessage());
        }

        System.out.println();
    }
}
