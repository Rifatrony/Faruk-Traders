package com.example.faruqtraders.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.faruqtraders.Adapter.FeatureAdapter;
import com.example.faruqtraders.Adapter.LatestProductAdapter;
import com.example.faruqtraders.Adapter.TopCategoriesMoreProductAdapter;
import com.example.faruqtraders.Model.FeatureModel;
import com.example.faruqtraders.Model.LatestProductModel;
import com.example.faruqtraders.Model.TopCategoriesMoreProductModel;
import com.example.faruqtraders.R;

import java.util.ArrayList;
import java.util.List;

public class TopCategoryActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatImageView imageView;

    RecyclerView.LayoutManager layoutManager;

    RecyclerView top_in_category_recycler_view;
    /*List<TopCategoriesMoreProductModel> topCategoriesMoreProductModelList = new ArrayList<>();
    TopCategoriesMoreProductAdapter topCategoriesMoreProductAdapter;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_category);

        initialization();
        setListener();

        //fetchAllTopCategories();
        fetchTopInCategoryProduct();

    }

    private void initialization(){
        imageView = findViewById(R.id.imageBack);
        top_in_category_recycler_view = findViewById(R.id.top_in_categories_more_product_recyclerView);
    }

    private void setListener(){
        imageView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageBack:
                onBackPressed();
                break;

            default:
                return;
        }
    }

    private void fetchTopInCategoryProduct() {

        /// Feature RecyclerView
        top_in_category_recycler_view = findViewById(R.id.top_in_categories_more_product_recyclerView);
        top_in_category_recycler_view.setHasFixedSize(true);
        top_in_category_recycler_view.setLayoutManager(new GridLayoutManager(this , 1));

        List<TopCategoriesMoreProductModel> featureModelList = new ArrayList<>();

        featureModelList.add(new TopCategoriesMoreProductModel(R.drawable.latest,100,120,"Sundrop Peanut Butter Crunchy - 462 gm"));
        featureModelList.add(new TopCategoriesMoreProductModel(R.drawable.sell,100,120,"Cottee’s Jam Breakfast Marmalade 500g"));
        featureModelList.add(new TopCategoriesMoreProductModel(R.drawable.guest,100,120,"Alshifa Natural Honey 500 gm"));
        featureModelList.add(new TopCategoriesMoreProductModel(R.drawable.top_in_category,100,120,"Sundrop Peanut Butter Crunchy - 462 gm"));
        featureModelList.add(new TopCategoriesMoreProductModel(R.drawable.latest,100,120,"Cottee’s Jam Breakfast Marmalade 500g"));
        featureModelList.add(new TopCategoriesMoreProductModel(R.drawable.latest,100,120,"Alshifa Natural Honey 500 gm"));
        featureModelList.add(new TopCategoriesMoreProductModel(R.drawable.latest,100,120,"Sundrop Peanut Butter Crunchy - 462 gm"));

        TopCategoriesMoreProductAdapter featureAdapter = new TopCategoriesMoreProductAdapter(this,featureModelList);

        top_in_category_recycler_view.setAdapter(featureAdapter);
    }

    /*private void fetchAllTopCategories(){
        top_in_category_recyclerView.setHasFixedSize(true);

        top_in_category_recyclerView.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL  , false));

        topCategoriesMoreProductModelList.add(new TopCategoriesMoreProductModel(R.drawable.latest,1000, 1047, "Sundrop Peanut Butter Crunchy - 462 gm"));
        topCategoriesMoreProductModelList.add(new TopCategoriesMoreProductModel(R.drawable.latest,1000, 1047, "Sundrop Peanut Butter Crunchy - 462 gm"));
        topCategoriesMoreProductModelList.add(new TopCategoriesMoreProductModel(R.drawable.latest,1000, 1047, "Sundrop Peanut Butter Crunchy - 462 gm"));
        topCategoriesMoreProductModelList.add(new TopCategoriesMoreProductModel(R.drawable.latest,1000, 1047, "Sundrop Peanut Butter Crunchy - 462 gm"));
        topCategoriesMoreProductModelList.add(new TopCategoriesMoreProductModel(R.drawable.latest,1000, 1047, "Sundrop Peanut Butter Crunchy - 462 gm"));
        topCategoriesMoreProductModelList.add(new TopCategoriesMoreProductModel(R.drawable.latest,1000, 1047, "Sundrop Peanut Butter Crunchy - 462 gm"));
        topCategoriesMoreProductModelList.add(new TopCategoriesMoreProductModel(R.drawable.latest,1000, 1047, "Sundrop Peanut Butter Crunchy - 462 gm"));

        topCategoriesMoreProductAdapter = new TopCategoriesMoreProductAdapter(this, topCategoriesMoreProductModelList);
        top_in_category_recyclerView.setAdapter(topCategoriesMoreProductAdapter);

    }*/

}