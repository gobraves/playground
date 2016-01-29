package getData;


import java.io.IOException;
import java.util.*;


/**
 * Created by cty on 2016/1/27.
 */
public class run  {
    public static void main(String[] args) throws IOException {
        //数据文件存放的文件夹名称
        String dataFolderPath = "I:\\code\\getData\\data";


        //所有数据文件存放在链表中
        List<String> fileList = readData.getFileList(dataFolderPath);

        //开启5个线程运行
        myThread myThreadA = new myThread();
        Thread threadA = new Thread(myThreadA);

        myThread myThreadB = new myThread();
        Thread threadB = new Thread(myThreadB);

        myThread myThreadC = new myThread();
        Thread threadC = new Thread(myThreadC);

//        myThread myThreadD = new myThread();
//        Thread threadD = new Thread(myThreadD);
//
//        myThread myThreadE = new myThread();
//        Thread threadE = new Thread(myThreadE);

        int count = 0;

        while (count < fileList.size()) {
            if (myThreadA.getEndFlag()) {
                myThreadA.setEndFlag(false);
                myThreadA.setReadFileName(fileList.get(count));
                myThreadA.setSaveFileName(fileList.get(count));
                System.out.println("threadA will process " + fileList.get(count));
                threadA.start();
                count++;
            }

            if (myThreadB.getEndFlag()) {
                myThreadB.setEndFlag(false);
                myThreadB.setReadFileName(fileList.get(count));
                myThreadB.setSaveFileName(fileList.get(count));
                System.out.println("threadB will process " + fileList.get(count));
                threadB.start();
                count++;
                myThreadB.setEndFlag(true);
            }
//            if (myThreadC.getEndFlag()) {
//                myThreadC.setReadFileName(fileList.get(count));
//                myThreadC.setSaveFileName(fileList.get(count));
//                System.out.println("threadA will process " + fileList.get(count));
//                threadC.start();
//                count++;
//                myThreadC.setEndFlag(false);
//            }
        }

    }
}
