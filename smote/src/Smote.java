import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by cty on 2016/2/21.
 */
public class Smote {
//    Returns (N/100) * n_minority_samples synthetic minority samples.
//
//    Parameters
//    ----------
//    T : array-like, shape = [n_minority_samples, n_features]
//    Holds the minority samples
//    N : percetange of new synthetic samples:
//    n_synthetic_samples = N/100 * n_minority_samples. Can be < 100.
//    k : int. Number of nearest neighbours.
//
//    Returns
//    -------
//    S : array, shape = [(N/100) * n_minority_samples, n_features]
    public static ArrayList<Data> smote(List<Data> dataSet, int N, int k){
        ArrayList<Data> generateDataArray = new ArrayList<>();

        int n_features = dataSet.get(0).getFeatureNum();
        int n_minority_samples = dataSet.size();
        if(((N % 100) != 0) && N > 100){
            System.out.println("N must be < 100 or multiple of 100");
        }
        else{
            if(N < 100){
                N = 100;
            }

            N = N / 100;
            int n_synthetic_samples = N * n_minority_samples;
            while (N > 0){
                KNNTool tool = new KNNTool();
                ArrayList<Data> sortDataArray = null;

                for(int index = 0; index < n_minority_samples; index++) {
                    tool.setTrainData((ArrayList<Data>) dataSet);
                    sortDataArray = tool.knnCompute(k, index);

                    Random rand = new Random();
                    int nn_index = index ;
                    while(nn_index == index){
                        nn_index = rand.nextInt(k);
                    }

                    List<Double> dif = tool.computeEuclideanDistance(dataSet.get(index), sortDataArray.get(nn_index));
                    double gap = rand.nextDouble();

                    List<Double> tempGenerateDataLine = new ArrayList<>();
                    for (int sid = 0; sid < dataSet.get(index).getFeatures().length; sid++) {
                        String num = dataSet.get(index).getFeatures()[sid];
                        double tempGenerateData = Double.parseDouble(num) + gap * dif.get(sid);
                        BigDecimal b = new BigDecimal(tempGenerateData);
                        tempGenerateData = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                        tempGenerateDataLine.add(tempGenerateData);
                    }
                    String[] tempStrDataLine = new String[n_features];
                    Data result = new Data(tempStrDataLine);
                    for (int i = 0; i < n_features; i++) {
                        tempStrDataLine[i] = String.valueOf(tempGenerateDataLine.get(i));
                    }
                    generateDataArray.add(result);
                }
                N--;
            }
        }
        return  generateDataArray;
    }

}
