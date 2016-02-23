import java.util.ArrayList;

/**
 * Created by cty on 2016/2/22.
 */
public class Test {
    public static void main(String[] args){
        KNNTool tool = new KNNTool();
        tool.setTrainDataPath("/home/neo/smote/ar4.txt");
        tool.setGenerateDataPath("/home/neo/smote");

        ArrayList<String[]> tempDataArray = new ArrayList<>();
        ArrayList<Data> dataArray = new ArrayList<>();
        tempDataArray = tool.fileDataToArray(tool.getTrainDataPath());
        for(String[] tempStr : tempDataArray){
            Data dataLine = new Data(tempStr);
            dataArray.add(dataLine);
        }
        tool.setTrainData(dataArray);
        ArrayList<Data> generateDataArray = new ArrayList<>();
        generateDataArray =  Smote.smote(dataArray,300,5);
        boolean flag = tool.arrayToFile(generateDataArray,"ar4.txt");
        if(flag){
            System.out.println("处理完毕");
        }



    }
}
