//package com.example.android.weatherp;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.List;
//
///**
// * Created by Jindal on 11/24/2017.
// */
//
////public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
//    private Model products;
//    private Context context;
//    private Model data;
//
//    public MyAdapter(Model products, Context context) {
//        this.products = products;
//        this.context = context;
//
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.productview, parent,false);
//        return new ViewHolder(v);
//    }
//
////    @Override
////    public void onBindViewHolder(ViewHolder holder, int position) {
////         data=products.get(position);
////        Log.v("ABCD",""+data);
////         holder.latitude.setText(data.getEngName());
////         holder.longitude.setText(data.getHindiName());
////         holder.timezone.setText(data.getProductId());
////
////    }
//
////    @Override
////    public int getItemCount() {
////
////        return products.size();
////    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView latitude;
//        public TextView longitude;
//        public TextView timezone;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            latitude= itemView.findViewById(R.id.latitude);
//            longitude= itemView.findViewById(R.id.longitude);
//            timezone= itemView.findViewById(R.id.timezone);
//        }
//    }
//}
