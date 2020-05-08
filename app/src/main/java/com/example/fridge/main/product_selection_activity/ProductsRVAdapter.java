package com.example.fridge.main.product_selection_activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fridge.R;
import com.example.fridge.entity.Product;
import com.example.fridge.entity.managers.ProductManager;

import java.util.List;

public class ProductsRVAdapter extends RecyclerView.Adapter<ProductsRVAdapter.ViewHolder> {
    private Context context;
    private List<Product> products;
    private boolean deleteList;

    public ProductsRVAdapter(Context context,
                             int typeId) {
        this.context    = context;
        this.products   = ProductManager.get().getProductsByTypeFromList(ProductSelector.getProducts(), typeId);
        this.deleteList = false;
    }

    public ProductsRVAdapter(Context context,
                             List<Product> products,
                             boolean removable) {
        this.context    = context;
        this.products   = products;
        this.deleteList = removable;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(final @NonNull ViewGroup parent,
                                         final int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_page_product, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.initialize(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView nameProduct;
        ImageView choiceImage;
        Product product;

        ViewHolder(@NonNull View v) {
            super(v);

            cardView    = v.findViewById(R.id.card_view);
            nameProduct = v.findViewById(R.id.product_name);
            choiceImage = v.findViewById(R.id.choice_image);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ProductSelector.choiceProduct(product.getId());
                    setVisibleChoice();

                    if(deleteList) {
                        products.remove(product);
                        notifyDataSetChanged();
                    }
                }
            });
        }
        void initialize(Product product) {
            this.product    = product;
            this.nameProduct.setText(product.getName());
            setVisibleChoice();
        }

        void setVisibleChoice(){
            if (product.isChoice()) choiceImage.setVisibility(View.VISIBLE);
            else choiceImage.setVisibility(View.GONE);
        }
    }
}
