package gcsenxmk.q;
import android.graphics.Bitmap;

public class MerchantInformation {

    public String queuename;
    public String queuedescripton;
    public Upload queueimage;
    public int waiting_time;

    public  MerchantInformation(){

    }

    public MerchantInformation(String name, String desc, int waiting_time, Upload queueimage) {
        this.queuename = name;
        this.queuedescripton = desc;
        this.waiting_time=waiting_time;
        this.queueimage=queueimage;
    }
}