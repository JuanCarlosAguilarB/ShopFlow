package auth_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class TestClass {

//    Objetivo del Ejercicio:
//    Implementar una aplicación que procese una lista de números enteros de manera concurrente
//    utilizando 10 hilos, cada uno procesando un subconjunto de la lista. Se deben identificar
//    números pares y manejar números negativos con una excepción personalizada.

    @GetMapping
    public ResponseEntity prueba(){

        //www.youtube.com/watch?v=I5fukc6MzXo
        int a;
        int b;

        int THREADS = 10;
        // Executor is a interface or framework that it can helps with management of threads
        ExecutorService threadsPoll = Executors.newFixedThreadPool(THREADS);

        List<List<Integer>> numbers = new ArrayList<>();


        threadsPoll.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " vamossss");
            }
        });

        threadsPoll.shutdown();



        return ResponseEntity.ok().build();
    }
}
