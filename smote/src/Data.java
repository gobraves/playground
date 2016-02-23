/**
 * Created by cty on 2016/2/21.
 */
public class Data implements Comparable<Data>{

    private String[] features;
    private Double distance;
    private int featureNum;

    public int getFeatureNum() {
        featureNum = features.length    ;
        return featureNum;
    }

    public void setFeatureNum(int featureNum) {
        this.featureNum = featureNum;
    }

    public Data(String[] features){
        this.features = features;
    }

    public String[] getFeatures() {
        return features;
    }

    public void setFeatures(String[] features) {
        this.features = features;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(Data o) {
        return this.getDistance().compareTo(o.getDistance());
    }

}
