package getData;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.locks.Lock;

/**
 * Created by cty on 2016/1/28.
 */
class myThread implements Runnable  {
    private Boolean endFlag = true;
    private  String readFileName;
    private  String saveFileName;
    private String storePath = "I:\\code\\getData\\storeResult";

    public void setReadFileName(String readFileName){
        this.readFileName = readFileName;
    }
    public void setSaveFileName(String saveFileName){
        String[] saveFileNames = this.readFileName.split("\\\\");
        this.saveFileName = saveFileNames[saveFileNames.length-1];
    }

    public String getSaveFileName(){
        return saveFileName;
    }

    public void setEndFlag(Boolean endFlag){
        this.endFlag = endFlag;
    }

    public Boolean getEndFlag(){
        return endFlag;
    }
    @Override
    public void run() {
        String result = new String();
        String address = new String();
        String tempAddress = new String();

        List<List> test = null;
        try {
            test = readData.readData(readFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        for(List li : test){
//            for(int i = 1; i < li.size();i++){
//                try{
//                    address = sendGet.getJson((String)li.get(i));
//                    if(!tempAddress.equals(address)){
//                        if(i < li.size()-1){
//                            address += ",";
//                        }
//                        result += address;
//                    }
//                    tempAddress = address;
//                }
//                catch (Exception e){
//                    System.out.println(e);
//                    System.out.println("Exception in "+readFileName+" "+li.get(i)+" ");
//                }
//            }
//            result+="\n";
//        }

        try {
            saveData.save(result,storePath+"\\"+saveFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(saveFileName+" has been processed");
        this.setEndFlag(true);
    }
}
