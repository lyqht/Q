package gcsenxmk.q;

import android.support.v7.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/***
 * The adapter class for the RecyclerView, contains the sports data
 */
class QueueAdapter extends RecyclerView.Adapter<QueueAdapter.ViewHolder>  {

    private ArrayList<AlohaQueue> resultQueues;
    private Context mContext;

    /**
     * Constructor that passes in the sports data and the context
     * @param queueData ArrayList containing the queue data
     * @param context Context of the application
     */
    QueueAdapter(Context context, ArrayList<AlohaQueue> queueData) {
        this.resultQueues = queueData;
        this.mContext = context;
    }


    /**
     * Required method for creating the viewholder objects.
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly create ViewHolder.
     */
    @Override
    public QueueAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.queue_list_item, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     * @param holder The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(QueueAdapter.ViewHolder holder, int position) {
        //Get current queue
        AlohaQueue currentQueue = resultQueues.get(position);
        //Populate the textviews with data
        holder.bindTo(currentQueue);
    }


    /**
     * Required method for determining the size of the data set.
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
         * @param itemView The rootview of the list_item.xml layout file
         */
        ViewHolder(View itemView) {
            super(itemView);

            qName = itemView.findViewById(R.id.queueName);
            qWaitTime = itemView.findViewById(R.id.queueWaitTime);
            qImage = itemView.findViewById(R.id.queueImage);
            qNumPeople = itemView.findViewById(R.id.queueNumPeople);
        }

        void bindTo(AlohaQueue currentQ){
            //Populate the imageview & textviews with data
            qName.setText(currentQ.getName());
            qNumPeople.setText(String.valueOf(currentQ.getNumPeople()));
            qWaitTime.setText(String.valueOf(currentQ.getWaitTime()));
            Glide.with(mContext).load(
                    currentQ.getImageResource()).into(qImage);
            Log.i("Logcat", "BindTo works");

        }
    }
}
