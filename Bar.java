package bancoBar;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bar {
    private static Lock bathroomLock = new ReentrantLock();

    public static void main(String[] args) {
        Thread borracho1 = new Thread(() -> {
            while (true) {
                System.out.println("Borracho 1: Esta bebiendo en el bar.");
                try {
                    Thread.sleep(2000); // Simular tiempo de bebida
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Borracho 1: Quiere usar el bano.");

                // Seccion critica: Acceso al bano
                bathroomLock.lock();
                System.out.println("Borracho 1: Esta usando el bano.");
                try {
                    Thread.sleep(3000); // Simular tiempo en el ba�o
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Borracho 1: Salio del bano.");
                bathroomLock.unlock();
            }
        });

        Thread borracho2 = new Thread(() -> {
            while (true) {
                System.out.println("Borracho 2: Esta bebiendo en el bar.");
                try {
                    Thread.sleep(1500); // Simular tiempo de bebida
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Borracho 2: Quiere usar el bano.");

                // Seccion critica: Acceso al bano
                bathroomLock.lock();
                System.out.println("Borracho 2: Esta usando el bano.");
                try {
                    Thread.sleep(2500); // Simular tiempo en el ba�o
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Borracho 2: Salio del bano.");
                bathroomLock.unlock();
            }
        });

        borracho1.start();
        borracho2.start();
    }
}

