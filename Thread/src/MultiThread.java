import java.util.Vector;
import java.util.concurrent.CountDownLatch;

public class MultiThread extends Thread {
     static Vector<Integer> list = new Vector<Integer>(100);
     static CountDownLatch count = new CountDownLatch(10);

     public void run() {

          while (list.size() > 0) {
               try {
                    int val = list.remove(0);
                    System.out.println(val);
                    Thread.sleep(100);//ģ�⴦��
               } catch (Exception e) {
                    // ��������Խ�磬����ط�ֻ��Ϊ��˵�����⣬���Դ���
               }

          }
         
          count.countDown(); // ɾ���ɹ���һ

     }

     public static void main(String[] args) throws InterruptedException {
         
          for (int i = 0; i < 100; i++) {
               list.add(i);
          }
         
          long start = System.currentTimeMillis();

          for (int i = 0; i < 10; i++) {
               new MultiThread().start();
          }

         

          count.await();
          long end = System.currentTimeMillis();
          System.out.println("���� " + (end - start) + " ms");

     }

     // ���� 1001 ms
}