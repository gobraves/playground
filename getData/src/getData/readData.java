package getData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by cty on 2016/1/27.
 */
public class readData {
    public static List<String> getFileList(String path){
        List<String> fileList = new LinkedList<>();
        File file = new File(path);
        if(file.exists()){
            File[] files = file.listFiles();
            for(File dataFile : files){
                String filePath = dataFile.getAbsolutePath();
                fileList.add(filePath);
            }
        }
        return fileList;
    }
    public static List<List> readData(String fileName) throws IOException {


        //每个出租车的行驶记录，string为ID，list存储地点,vector[0]存储时间，vector[2]存储gps
        List<List> record = new LinkedList<>();

        //临时存储出租车ID
        String tempID = null;
        List<String> list = new LinkedList<>();

        BufferedReader br = null;
        br = new BufferedReader(new FileReader(fileName));
        String recordLine = null;
        String[] tempRecord = null;
        while ((recordLine = br.readLine())!= null){
            tempRecord = recordLine.split(",");

            //车上是否有人
            Boolean flag = false;
            if(tempRecord[2].equals("1")) {
                flag = true;
            }

           if(flag){
               //此时时间
               String time = tempRecord[3];

               //经纬度，应api要求，经度在前，纬度在后
               String lat = tempRecord[4];
               String lon = tempRecord[5];
               String location = lon+","+lat;

               if(tempID == null) {
                   tempID = tempRecord[0];
                   list.add(tempID);
               }
               //当ID改变时，先存储上个ID的记录
               else  if(!tempID.equals(tempRecord[0])){
                   record.add(list);
                   list.clear();
               }
               //该车的地点编号
               list.add(location);
           }
            else {
               List tempLi = new LinkedList<String>(list);
               if(list.size() > 1){
                   record.add(tempLi);
               }
               list.clear();
               tempID = null;
           }
        }
        if(tempRecord[2].equals("1")) {
            record.add(list);
        }
        return record;

    }

    public static void main(String[] args) throws Exception {
        List<List> test = readData.readData("I:\\code\\getData\\data\\001143.txt");
        for(List li : test){
//            for(Object li2 : li){
//                System.out.print(li2.toString()+" ");
//            }
            for(int i = 1; i < li.size();i++){
                System.out.print(li.get(i)+" ");
            }
            System.out.println();
        }

    }
}
