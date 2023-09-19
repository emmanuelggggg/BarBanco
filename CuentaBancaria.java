package bancoBar;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CuentaBancaria {
    private double saldo;
    private Lock lock = new ReentrantLock();

    public CuentaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void depositar(double cantidad) {
        lock.lock();
        try {
            saldo += cantidad;
            System.out.println("Deposito de " + cantidad + " realizado. Saldo total: " + saldo);
        } finally {
            lock.unlock();
        }
    }

    public void retirar(double cantidad) {
        lock.lock();
        try {
            if (saldo >= cantidad) {
                saldo -= cantidad;
                System.out.println("Retiro de " + cantidad + " realizado. Saldo total: " + saldo);
            } else {
                System.out.println("Fondos insuficientes para retirar " + cantidad);
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria(100.0);

        Thread cliente1 = new Thread(() -> {
            cuenta.retirar(80.0);
        });

        Thread cliente2 = new Thread(() -> {
            cuenta.depositar(70.0);
        });
        Thread cliente3 = new Thread(() -> {
            cuenta.retirar(300.0);
        });


        cliente1.start();
        cliente2.start();
        cliente3.start();

    }
}

