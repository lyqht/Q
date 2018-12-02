package gcsenxmk.q;

import android.graphics.Bitmap;

public class MerchantInformation {

    public String queuename;
    public String queuedescripton;
    public Upload queueimage;


    public  MerchantInformation(){

    }

    public MerchantInformation(String name, String desc, Upload queueimage) {
        this.queuename = name;
        this.queuedescripton = desc;
        this.queueimage=queueimage;
    }
}