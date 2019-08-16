package ge.digitaledu.sms.service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Launcher {

    private List<String> elementList;

    Launcher() {
        elementList = Collections.synchronizedList(new LinkedList<>());
        System.out.println("Element is initialized: " + elementList.size());
    }

    private void removeElement() {
        synchronized (elementList) {
            while (elementList.isEmpty()) {
                try {
                    System.out.println("Waiting for add element");
                    elementList.wait();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        elementList.remove(0);
        System.out.println("Remove element from list: " + elementList.size());
    }

    private void addElement(String element) {
        synchronized (elementList) {
            try {
                elementList.add(element);
                elementList.notifyAll();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        System.out.println("Adding element to list: " + elementList.size());
    }

    Runnable addThread = new Runnable() {
        @Override
        public void run() {
            addElement("Element 1");
        }
    };

    Runnable removeThread = new Runnable() {
        @Override
        public void run() {
            removeElement();
        }
    };

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        Thread th1 = new Thread(launcher.removeThread);
        Thread th2 = new Thread(launcher.addThread);

        th1.start();
        th2.start();

        th1.interrupt();
        th2.interrupt();
    }
}
