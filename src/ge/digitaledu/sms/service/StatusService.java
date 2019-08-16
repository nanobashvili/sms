package ge.digitaledu.sms.service;

public class StatusService extends Thread implements Runnable {

    private int studentQuantity;

    @Override
    public void run() {
        System.out.println("Student size count process started");
        try {
            System.out.println("Loading data ...");
            Thread.sleep(10000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        studentQuantity = 5300;
        System.out.println("Student size is: " + studentQuantity);
    }

    public int getStudentQuantity() {
        return studentQuantity;
    }

    public void cheMistart() throws Exception {
        if (!isAlive()) {
            start();
        }
    }

    public void chemiJoin() throws InterruptedException {
        if (isAlive()) {
            join();
        }
    }
}
