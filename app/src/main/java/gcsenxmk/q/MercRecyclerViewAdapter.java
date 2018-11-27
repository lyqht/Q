package gcsenxmk.q;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MercRecyclerViewAdapter extends RecyclerView.Adapter<MercRecyclerViewAdapter.ViewHolder> {

    public static final String TAG = "MercRecyclerViewAdapter";

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();
    private Context mContext;

    Toast mToast;

    //Recycles the view, putting them into positions that they should be put into
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.merc_list_item,parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public MercRecyclerViewAdapter(ArrayList<String> mImageNames, ArrayList<String> mImage, Context mContext) {
        this.mImageNames = mImageNames;
        this.mImage = mImage;
        this.mContext = mContext;
    }

    @Override

    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //for debugging, called the amt of times the view is created.
        Log.d(TAG,"onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()     //tells glide obtained as bit map
                .load(mImage.get(position)) //image url, resource it is coming from
                .into(holder.image);    //references viewhold with image id in layout_listitem

        holder.imageName.setText(mImageNames.get(position));


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: clicked on: " + mImageNames.get(position));
                if (mToast != null){
                    mToast.cancel();
                }
                mToast = Toast.makeText(mContext, mImageNames.get(position),Toast.LENGTH_SHORT);
                mToast.show();

                Intent i = new Intent(mContext,Merc_Gallery.class);
                i.putExtra("image_url",mImage.get(position));
                i.putExtra("image_name",mImageNames.get(position));
                mContext.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView imageName;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

}
