import java.util.Random;

public class producerconsumer {

    static final int buffer_size = 50;
    static Producer producer = new Producer();
    static Consumer consumer = new Consumer();
    static Monitor monitor = new Monitor();
    static class Monitor {
        private int buffer[] = new int [buffer_size];
        private int count = 0;
        private int min = 0, max = 0;
        public synchronized void inserting(int val) {
            if (count == buffer_size) gotoSleep("For Removing Items"); // if the buffer is full, go to sleep
            buffer[max] = val; // insert an item into the buffer
            max = (max + 1) % buffer_size; // slot to place next item in
            count = count + 1;
            System.out.println("Count after Inserting " + count);
            if (count == 1) notify(); // if consumer was sleeping, wake it up
        }
        public synchronized int removing() {
            int val;
            if (count==0) gotoSleep("For Adding Items"); // if the buffer is empty, go to sleep
            val = buffer[min]; // fetch an item from the buffer
            min = (min+1) % buffer_size; // slot to fetch next item from
            count = count - 1;
            System.out.println("Count after removing" + count);
            if (count==buffer_size-1) notify(); // if producer was sleeping, wake it up
            return val;
        }
        private void gotoSleep(String val) {
            try {
                System.out.println("Thread is waiting " + val);
                wait(); // wait() can be interrupted
            } catch(InterruptedException exc) {
                System.out.println("Interrupt occurred!");
            };
        }
    }

    static class Producer extends Thread {
        public void run() { // run method with the thread code
            int item;
            while (true) {
                item = produce_Item();
                monitor.inserting(item);
            }
        }
        private int produce_Item() {
            Random rand = new Random();
            int num = rand.nextInt(30) + 1;
            System.out.println("Inserting Items" + num + " ");
            return num;
        }
    }
    static class Consumer extends Thread {
        public void run() { // run method with the thread code
            int item;
            while (true) { // consumer loop : infinite loop
                item = monitor.removing();
                consume_Item(item);
            }
        }
        private void consume_Item(int item) {
            System.out.println("Removing items" + item + " ");
        }
    }

    public static void main(String args[]) {
        producer.start();
        consumer.start();
    }
} 
