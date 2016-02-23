import java.io.*;
import java.util.*;

/**
 * Created by cty on 2016/2/21.
 */
public class KNNTool {
    // 训练集数据地址
    private String trainDataPath;
    // 训练集数据列表容器
    private ArrayList<Data> trainData;
    // 生成的数据
    private ArrayList<Data> generateData;
    // 生成数据地址
    private String generateDataPath;


    public ArrayList<Data> getTrainData() {
        return trainData;
    }

    public void setTrainData(ArrayList<Data> trainData) {
        this.trainData = trainData;
    }

    public ArrayList<Data> getGenerateData() {
        return generateData;
    }

    public void setGenerateData(ArrayList<Data> generateData) {
        this.generateData = generateData;
    }

    public String getGenerateDataPath() {
        return generateDataPath;
    }

    public void setGenerateDataPath(String generateDataPath) {
        this.generateDataPath = generateDataPath;
    }

    public String getTrainDataPath() {
        return trainDataPath;
    }

    public void setTrainDataPath(String trainDataPath) {
        this.trainDataPath = trainDataPath;
    }

    /**
     * 将文件转为列表数据输出
     *
     * @param filePath
     *            数据文件的内容
     */
    public ArrayList<String[]> fileDataToArray(String filePath) {
        File file = new File(filePath);
        ArrayList<String[]> dataArray = new ArrayList<String[]>();

        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String str;
            String[] tempArray;
            String[] array = null;
            while ((str = in.readLine()) != null) {
                tempArray = str.split(",");
                if(tempArray[tempArray.length - 1].equals("Y")){
                    array = new String[tempArray.length - 1];
                    System.arraycopy(tempArray,0,array,0,tempArray.length - 1 );
                    dataArray.add(array);
                }
            }
            in.close();
        } catch (IOException e) {
            e.getStackTrace();
        }

        return dataArray;
    }

    /**
     * 将生成的数据保存到文件
     *@param dataArrayList
     */
    public boolean arrayToFile(ArrayList<Data> dataArrayList,String fileName){
        boolean Flag = false;
        String result = new String();
        for(Data data : dataArrayList){
            for(String strData : data.getFeatures()){
                result += strData;
                result += ",";
            }
            result += "Y";
            if(data != dataArrayList.get(dataArrayList.size() - 1)){

                result += System.lineSeparator();
            }
        }

        File file = new File(fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(writer);
        try {
            bw.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        Flag = true;
        return Flag;
    }

    /**
     * 计算样本特征向量的欧几里得距离
     *
     * @param dataLine1
     * @param dataLine2
     */
    private Double computeEuclideanDistanceSum(Data dataLine1, Data dataLine2) {
        String[] f1 = dataLine1.getFeatures();
        String[] f2 = dataLine2.getFeatures();
        // 欧几里得距离
        Double distance = 0.0;

        for (int i = 0; i < f1.length; i++) {
            Double subF1 = Double.parseDouble(f1[i]);
            Double subF2 = Double.parseDouble(f2[i]);

            distance += (subF1 - subF2) * (subF1 - subF2);
        }

        return distance;
    }

    /**
     * 计算样本特征向量的欧几里得距离
     * 返回列表
     *
     * @param dataLine1
     * @param dataLine2
     */
    public List<Double> computeEuclideanDistance(Data dataLine1, Data dataLine2) {
        String[] f1 = dataLine1.getFeatures();
        String[] f2 = dataLine2.getFeatures();
        // 欧几里得距离
        Double distance = 0.0;

        List<Double> distanceArray = new ArrayList<>();

        for (int i = 0; i < f1.length; i++) {
            Double subF1 = Double.parseDouble(f1[i]);
            Double subF2 = Double.parseDouble(f2[i]);

            distance = Math.abs(subF1 - subF2);
            distanceArray.add(distance);
        }

        return distanceArray;
    }

    /**
     * 计算K最近邻
     * @param k
     * 在多少的k范围内
     * @param currIndex
     * 当前所在行数
     */
    public ArrayList<Data> knnCompute(int k, int currIndex) {
        Data temp;

        // 离样本最近排序的的训练集数据
        ArrayList<Data> kNNData= new ArrayList<>();
        // 计算训练数据集中离样本数据最近的K个训练集数据
        for(int index = 0; index < this.getTrainData().size(); index++ ){

            for (Data data : this.getTrainData()) {
                if(this.getTrainData().indexOf(data) != currIndex){
                    Double dis = computeEuclideanDistanceSum(this.getTrainData().get(index), data);
                    data.setDistance(dis);
                }
                else{
                    data.setDistance(Double.MAX_VALUE);
                }
            }
            ArrayList<Data> tempDataArray = new ArrayList<>(this.getTrainData());
            Collections.sort(tempDataArray);
            kNNData.clear();
            // 挑选出前k个数据
            for (int i = 0; i < tempDataArray.size(); i++) {
                if (i < k) {
                    kNNData.add(tempDataArray.get(i));
                } else {
                    break;
                }
            }
        }
        return kNNData;
    }

}
