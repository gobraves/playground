package getData;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cty on 2016/1/29.
 */
public class tempRun {
    public static void main(String[] args){
        //数据文件存放的文件夹名称
        String dataFolderPath = "I:\\code\\getData\\data";
        //存放结果的文件夹名称
        String storePath = "I:\\code\\getData\\storeResult";


        //所有数据文件存放在链表中
        List<String> fileList = readData.getFileList(dataFolderPath);

        //每个文件的序列
        List<List> gpsSequence = new LinkedList<>();

        String address = new String();
        String tempAddress = new String();
        String result = null;

        for (String readFileName : fileList){
            try {
               gpsSequence =  readData.readData(readFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("will process " + readFileName);
            for(List li : gpsSequence){
                for(int i = 1; i < li.size();i++){
                    try{
                        address = sendGet.getJson((String)li.get(i));
                        if(!tempAddress.equals(address)){
                            tempAddress = address;
                            if(result == null){
                                result = address;
                            }
                            else if(i == 1){
                                result += address;
                            }
                            else{
                                result = result + ","+  address;
                            }
                        }

                    }
                    catch (Exception e){
                        System.out.println(e);
                        System.out.println("Exception in "+readFileName+" "+li.get(i)+" ");
                    }
                }
                result+="\n";
            }

            String[] saveFileNames = readFileName.split("\\\\");
            String saveFileName = saveFileNames[saveFileNames.length-1];
            System.out.println(readFileName + "will be saved");
            try {
                saveData.save(result,storePath+"\\"+saveFileName);
                System.out.println(readFileName + "has been saved");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
