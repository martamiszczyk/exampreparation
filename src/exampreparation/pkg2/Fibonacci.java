/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exampreparation.pkg2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author martamiszczyk
 */
public class Fibonacci
{

    private static BlockingQueue<Integer> s1 = new ArrayBlockingQueue<>(11);
    private static BlockingQueue<Long> s2 = new ArrayBlockingQueue<>(11);

    private static long fib(long n)
    {
        if ((n == 0) || (n == 1))
        {
            return n;
        } else
        {
            return fib(n - 1) + fib(n - 2);
        }
    }

    public static class Producer extends Thread
    {

        public void run()
        {
            try
            {
                while (!s1.isEmpty())
                {
                    int n;
                    n = s1.poll();
                    long result = fib(n);
                    s2.put(result);
                }

            } catch (InterruptedException ex)
            {
                Logger.getLogger(Fibonacci.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static class Consumer extends Thread
    {

        public void run()
        {

            try
            {
                Thread.sleep(1000);
                while (!s2.isEmpty())
                {
                    int sum = s2.take().intValue();
                    int totalSum = 0;
                    totalSum = totalSum + sum;

                    System.out.println(sum);
                }
                
            } catch (InterruptedException ex)
            {
                Logger.getLogger(Fibonacci.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        s1.put(4);
        s1.put(5);
        s1.put(8);
        s1.put(12);
        s1.put(21);
        s1.put(22);
        s1.put(34);
        s1.put(35);
        s1.put(36);
        s1.put(37);
        s1.put(42);

        Producer t1 = new Producer();
        Producer t2 = new Producer();
        Producer t3 = new Producer();
        Producer t4 = new Producer();
        Consumer c1 = new Consumer();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        c1.start();

    }

}
