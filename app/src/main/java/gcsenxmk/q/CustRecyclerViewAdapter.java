package gcsenxmk.q;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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

    /**
     * Constructor that passes in the sports data and the context
     *
     * @param queueData ArrayList containing the queue data
     * @param context   Context of the application
     */
    CustRecyclerViewAdapter(Context context, ArrayList<AlohaQueue> queueData) {
        this.resultQueues = queueData;
        this.mContext = context;
    }


    /**
     * Required method for creating the viewholder objects.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly create ViewHolder.
     */
    @Override
    public CustRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.cust_list_item, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder   The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(CustRecyclerViewAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        //Get current queue
        AlohaQueue currentQueue = resultQueues.get(position);
        //Populate the textviews with data
        holder.bindTo(currentQueue);
    }


    /**
     * Required method for determining the size of the data set.
     *
     * @return Size of the data set.
     */

    @Override
    public int getItemCount() {
        return resultQueues.size();
    }

    /**
     * ViewHolder class that represents each row of data in the RecyclerView
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        //Member Variables for the TextViews
        private TextView qName;
        private TextView qWaitTime;
        private TextView qNumPeople;
        private ImageView qImage;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file
         */
        ViewHolder(View itemView) {
            super(itemView);

            qName = itemView.findViewById(R.id.queueName);
            qWaitTime = itemView.findViewById(R.id.queueWaitTime);
            qImage = itemView.findViewById(R.id.queueImage);
            qNumPeople = itemView.findViewById(R.id.queueNumPeople);

        }

        void bindTo(AlohaQueue currentQ) {
            //Populate the imageview & textviews with data
            qName.setText(currentQ.getName());
            qNumPeople.setText(String.valueOf(currentQ.getNumPeople()));
            qWaitTime.setText(String.valueOf(currentQ.getWaitTime()));
            Glide.with(mContext).load(
                    currentQ.getImageResource()).into(qImage);
            Log.i("Logcat", "BindTo works");

            // TODO: joinQButton to link to Cust_Gallery, REFER TO MERCHANT SIDE
/*            joinQButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext,Cust_Gallery.class);
                    i.putExtra("image_url", currentQ.getImageResource());
                    i.putExtra("queue_name", currentQ.getName());
                    i.putExtra("queue_waiting_time", currentQ.getWaitTime());
                    i.putExtra("queue_num_people", currentQ.getNumPeople());
                    mContext.startActivity(i);
                }
            });*/
        }
    }
}