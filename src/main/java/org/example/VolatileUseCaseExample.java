package org.example;


/**
 * Locking can guarantee both visibility and atomicity; volatile variables can
 * only guarantee visibility.
 * <p>
 * You can use volatile variables only when all the following criteria are met:
 * <ul>
 * <li>Writes to the variable do not depend on its current value, or you can ensure</li>
 * that only a single thread ever updates the value;
 * <li>The variable does not participate in invariants with other state variables;</li>
 * <li>Locking is not required for any other reason while the variable is being </li>
 * accessed.
 * </ul>
 */
public class VolatileUseCaseExample {

    public static class VolatileFlag {
        private volatile boolean running = true;
        public void run() {
            while (running) {
                // do some work
            }
        }
        public void stop() {
            running = false;
        }
    }

}
