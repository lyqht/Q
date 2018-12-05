package gcsenxmk.q;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/***
 * The adapter class for the RecyclerView, contains the sports data
 */
class CustRecyclerViewAdapter extends RecyclerView.Adapter<CustRecyclerViewAdapter.ViewHolder> {
    public static final String TAG = "CustRecyclerViewAdapter";

    private ArrayList<AlohaQueue> resultQueues;
    private Context mContext;

    CustRecyclerViewAdapter(Context context, ArrayList<AlohaQueue> queueData) {
        this.resultQueues = queueData;
        this.mContext = context;
    }

    @Override
    public CustRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.cust_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CustRecyclerViewAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        AlohaQueue currentQueue = resultQueues.get(position);
        holder.bindTo(currentQueue);
    }


    @Override
    public int getItemCount() {
        return resultQueues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView qName;
        private TextView qWaitTime;
        private TextView qNumPeople;
        private ImageButton qImage;
        private Button joinQButton;

        ViewHolder(View itemView) {
            super(itemView);

            qName = itemView.findViewById(R.id.queueName);
            qWaitTime = itemView.findViewById(R.id.queueWaitTime);
            qImage = itemView.findViewById(R.id.queueImage);
            qNumPeople = itemView.findViewById(R.id.queueNumPeople);
            joinQButton = itemView.findViewById(R.id.joinQ);

        }

        void bindTo(AlohaQueue currentQ) {
            qName.setText(currentQ.getName());
            qNumPeople.setText(String.valueOf(currentQ.getNumPeople()));
            qWaitTime.setText(String.valueOf(currentQ.getWaitTime()));
            Glide.with(mContext).load(
                    currentQ.getImageResource()).into(qImage);
            Log.i("Logcat", "BindTo works");

            qImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Card's image Button Clicked.");
                    Intent i = new Intent(mContext,Cust_Gallery.class);
                    i.putExtra("image_url", String.valueOf(currentQ.getImageResource()));
                    i.putExtra("queue_name", currentQ.getName());
                    i.putExtra("queue_waiting_time", String.valueOf(currentQ.getWaitTime()));
                    i.putExtra("queue_num_people", String.valueOf(currentQ.getNumPeople()));
                    mContext.startActivity(i);
                }
            });

            //TODO: JOIN QUEUE BUTTON - BUTTON WILL HAVE ERROR.
            //TODO EITHER CONVERT TO IMAGE OR FIGURE OUT WHY THIS ERROR OCCUS
            //  java.lang.NullPointerException:
            //  Attempt to invoke virtual method 'void android.view.View.setOnClickListener(android.view.View$OnClickListener)'
            // on a null object reference

/*            joinQButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Join Queue Button Clicked.");
                }
            });*/
        }
    }
}