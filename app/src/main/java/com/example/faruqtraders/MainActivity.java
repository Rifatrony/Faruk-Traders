package com.example.faruqtraders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.example.faruqtraders.API.RetrofitClient;
import com.example.faruqtraders.Activities.AboutUsActivity;
import com.example.faruqtraders.Activities.CartActivity;
import com.example.faruqtraders.Activities.CategoryActivity;
import com.example.faruqtraders.Activities.ContactUsActivity;
import com.example.faruqtraders.Activities.LoginActivity;
import com.example.faruqtraders.Activities.TopCategoryActivity;
import com.example.faruqtraders.Adapter.BestSellingAdapter;
import com.example.faruqtraders.Adapter.FeatureAdapter;
import com.example.faruqtraders.Adapter.ImageAdapter;
import com.example.faruqtraders.Adapter.LatestProductAdapter;

import com.example.faruqtraders.Adapter.SellProductAdapter;
import com.example.faruqtraders.Adapter.TopInCategoriesAdapter;
import com.example.faruqtraders.Model.BestSellingModel;
import com.example.faruqtraders.Model.FeatureModel;
import com.example.faruqtraders.Model.ImageModel;
import com.example.faruqtraders.Model.LatestProductModel;
import com.example.faruqtraders.Model.SellProductModel;
import com.example.faruqtraders.Model.TopInCategoriesModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView featureRecyclerView , bestSellingRecyclerView,
            sellProductRecyclerView, topInCategoriesRecyclerView, latestProductRecyclerView;

    private long backPressedTime;
    private Toast backToast;

    EditText searchEditText;
    TextView all_category_text_view, top_in_categories_more_product;

    TextView name, email;

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;

    ViewPager2 viewPager2;
    List<ImageModel> imageModelList;
    ImageAdapter adapter;
    Handler slideHandler = new Handler();

    List<LatestProductModel> latestProductList = new ArrayList<>();
    LatestProductAdapter latestProductAdapter;

    List<FeatureModel> featureModelList = new ArrayList<>();
    FeatureAdapter featureAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();

        fetchLatestProduct();
        fetchBestSellingProduct();
        fetchSellProduct();
        fetchFeatureProduct();
        fetchTopInCategoriesProduct();
        ImageSlider();
        setListener();


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);

                switch (id){
                    case R.id.nav_home:
                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_categories:
                        startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
                        break;

                    case R.id.nav_track_order:
                        Toast.makeText(MainActivity.this, "Track Order", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_cart:
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        break;

                    case R.id.nav_favourite:
                        Toast.makeText(MainActivity.this, "Favourite", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_login:
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        break;

                    case R.id.nav_share:
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT,"Check this application");
                        intent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=com.whatsapp");
                        startActivity(Intent.createChooser(intent,"Share Via"));
                        break;

                    case R.id.nav_contact:
                        startActivity(new Intent(getApplicationContext(), ContactUsActivity.class));
                        //overridePendingTransition(0,0);
                        break;

                    case R.id.nav_about_us:
                        startActivity(new Intent(getApplicationContext(), AboutUsActivity.class));
                        break;

                    default:
                        return true;
                }
                return true;
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                switch (id){
                    case R.id.nav_home_bottom:
                        Toast.makeText(MainActivity.this, "Home Bottom", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_cart_bottom:
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        break;

                    case R.id.nav_track_order_bottom:
                        Toast.makeText(MainActivity.this, "My Order Bottom", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        return true;
                }
                return true;
            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

    }

    private void setListener(){
        all_category_text_view.setOnClickListener(this);
        top_in_categories_more_product.setOnClickListener(this);
    }

    private void filter(String text){
        List<LatestProductModel> list = new ArrayList<>();

        for (LatestProductModel item : latestProductList){
            if (item.getName().toLowerCase().contains(text.toLowerCase())){
                list.add(item);
            }
        }
        latestProductAdapter.filterList(list);


    }

    private void initialization() {

        toolbar = findViewById(R.id.topAppBar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        bottomNavigationView = findViewById(R.id.bottom_nav);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);

        viewPager2 = findViewById(R.id.viewPagerImageSlider);
        imageModelList = new ArrayList<>();

        searchEditText = findViewById(R.id.mainActivitySearch);

        all_category_text_view = findViewById(R.id.all_category);
        top_in_categories_more_product = findViewById(R.id.top_in_categories_more_product);

        /// Feature RecyclerView
        featureRecyclerView = findViewById(R.id.featureRecyclerView);
        featureRecyclerView.setHasFixedSize(true);

        featureRecyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));
        featureAdapter = new FeatureAdapter(featureModelList);

        featureRecyclerView.setAdapter(featureAdapter);
    }


    private void fetchTopInCategoriesProduct() {
        // Top in Categories RecyclerView

        topInCategoriesRecyclerView = findViewById(R.id.topCategoriesRecyclerView);
        topInCategoriesRecyclerView.setHasFixedSize(true);
        topInCategoriesRecyclerView.setLayoutManager(new GridLayoutManager(this , 3));

        List<TopInCategoriesModel> topInCategoriesModelList = new ArrayList<>();

        topInCategoriesModelList.add(new TopInCategoriesModel("","Capilano Manuka Active Honey 340g","Honey&Jam Spread","1057.3৳"));
        topInCategoriesModelList.add(new TopInCategoriesModel("","Capilano Manuka Active Honey 340g","Honey&Jam Spread","1057.3৳"));
        topInCategoriesModelList.add(new TopInCategoriesModel("","Capilano Manuka Active Honey 340g","Honey&Jam Spread","1057.3৳"));
        topInCategoriesModelList.add(new TopInCategoriesModel("","Capilano Manuka Active Honey 340g","Honey&Jam Spread","1057.3৳"));
        topInCategoriesModelList.add(new TopInCategoriesModel("","Capilano Manuka Active Honey 340g","Honey&Jam Spread","1057.3৳"));
        topInCategoriesModelList.add(new TopInCategoriesModel("","Capilano Manuka Active Honey 340g","","1057.3৳"));
        topInCategoriesModelList.add(new TopInCategoriesModel("","Capilano Manuka Active Honey 340g","Honey&Jam Spread","1057.3৳"));
        topInCategoriesModelList.add(new TopInCategoriesModel("","Capilano Manuka Active Honey 340g","Honey&Jam Spread","1057.3৳"));
        topInCategoriesModelList.add(new TopInCategoriesModel("","Capilano Manuka Active Honey 340g","Honey&Jam Spread","1057.3৳"));

        TopInCategoriesAdapter topInCategoriesAdapter = new TopInCategoriesAdapter(topInCategoriesModelList);

        topInCategoriesRecyclerView.setAdapter(topInCategoriesAdapter);
    }

    private void fetchFeatureProduct() {

       /* RetrofitClient.getRetrofitClient().getFeatureProduct().enqueue(new Callback<List<FeatureModel>>() {
            @Override
            public void onResponse(Call<List<FeatureModel>> call, Response<List<FeatureModel>> response) {
                try {
                    if (response.isSuccessful() && response.body() != null){

                        featureModelList.addAll(response.body());
                        adapter.notifyDataSetChanged();

                        Toast.makeText(MainActivity.this, "Success...", Toast.LENGTH_SHORT).show();
                        //Log.e("failure", t.getLocalizedMessage());

                    }
                    else {
                        Toast.makeText(MainActivity.this, response.errorBody().toString(), Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    Log.e("exception", e.getLocalizedMessage());
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<FeatureModel>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());

                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/

        /*featureModelList.add(new FeatureModel("","Fox’s Crystal Clear Fruits 180g","Chocolate & More",200,100));
        featureModelList.add(new FeatureModel("","Fox’s Crystal Clear Fruits 180g","Chocolate & More",200,100));
        featureModelList.add(new FeatureModel("Fox’s Crystal Clear Fruits 180g","","Chocolate & More",200,100));*/

    }

    private void fetchSellProduct() {

        // Sell Product RecyclerView

        sellProductRecyclerView = findViewById(R.id.sellProductRecyclerview);
        sellProductRecyclerView.setHasFixedSize(true);
        sellProductRecyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));

        List<SellProductModel> sellProductModelList = new ArrayList<>();

        sellProductModelList.add(new SellProductModel("", "Up to 30% off","",""));
        sellProductModelList.add(new SellProductModel("", "Up to 30% off","",""));
        sellProductModelList.add(new SellProductModel("", "Up to 30% off","",""));

        SellProductAdapter sellProductAdapter = new SellProductAdapter(sellProductModelList);
        sellProductRecyclerView.setAdapter(sellProductAdapter);
    }

    private void fetchBestSellingProduct() {
        // best RecyclerView

        bestSellingRecyclerView = findViewById(R.id.bestSellingRecyclerview);
        bestSellingRecyclerView.setHasFixedSize(true);
        bestSellingRecyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));

        List<BestSellingModel> bestSellerList = new ArrayList<>();

        bestSellerList.add(new BestSellingModel("", "Lindt Dark Chocolate 90% 100gm","Chocolate & More","297 tk"));
        bestSellerList.add(new BestSellingModel("", "Lindt Dark Chocolate 90% 100gm","Chocolate & More","297 tk"));
        bestSellerList.add(new BestSellingModel("", "Lindt Dark Chocolate 90% 100gm","Chocolate & More","297 tk"));
        bestSellerList.add(new BestSellingModel("", "Lindt Dark Chocolate 90% 100gm","Chocolate & More","297 tk"));
        bestSellerList.add(new BestSellingModel("", "Lindt Dark Chocolate 90% 100gm","Chocolate & More","297 tk"));

        BestSellingAdapter bestSellerAdapter = new BestSellingAdapter(bestSellerList);

        bestSellingRecyclerView.setAdapter(bestSellerAdapter);
    }

    private void fetchLatestProduct() {
        // Latest Product RecyclerView

        latestProductRecyclerView = findViewById(R.id.latest_product_Recyclerview);
        latestProductRecyclerView.setHasFixedSize(true);
        latestProductRecyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));

        //List<LatestProductModel> latestProductList = new ArrayList<>();

        latestProductList.add(new LatestProductModel("Test & Harvey Juicy Apple Drops", "Chocolate & More 1","120",1, R.drawable.latest));
        latestProductList.add(new LatestProductModel("Cavendish & Harvey Juicy Apple Drops", "Chocolate & More 2","130",2, R.drawable.feature));
        latestProductList.add(new LatestProductModel("Cavendish & Harvey Juicy Apple Drops", "Chocolate & More 3","140",3, R.drawable.certified));
        latestProductList.add(new LatestProductModel("Cavendish & Harvey Juicy Apple Drops", "Chocolate & More 4","150",4, R.drawable.img));
        latestProductList.add(new LatestProductModel("Cavendish & Harvey Juicy Apple Drops", "Chocolate & More 5","160",5, R.drawable.sell));
        latestProductList.add(new LatestProductModel("Cavendish & Harvey Juicy Apple Drops", "Chocolate & More 6","170",6, R.drawable.best_selling));

        latestProductAdapter = new LatestProductAdapter(latestProductList, this);
        latestProductRecyclerView.setAdapter(latestProductAdapter);
    }



    private void ImageSlider(){
        imageModelList.add(new ImageModel(R.drawable.first));
        imageModelList.add(new ImageModel(R.drawable.second));
        imageModelList.add(new ImageModel(R.drawable.third));
        imageModelList.add(new ImageModel(R.drawable.fourth));

        adapter = new ImageAdapter(imageModelList, viewPager2);
        viewPager2.setAdapter(adapter);

        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setClipChildren(false);
        viewPager2.setClipToPadding(false);

        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(4));


        viewPager2.setPageTransformer(transformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                slideHandler.removeCallbacks(slideRunnable);
                slideHandler.postDelayed(slideRunnable, 10000);

            }
        });

    }

    private Runnable slideRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(slideRunnable);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        slideHandler.postDelayed(slideRunnable, 2000);
    }

    private void loginUser() {

    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 >System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }
        else {
            backToast = Toast.makeText(this, "Tap Again to Exit...", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.all_category:
                startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
                break;

            case R.id.top_in_categories_more_product:
                startActivity(new Intent(getApplicationContext(), TopCategoryActivity.class));
                break;

            default:
                return;
        }
    }
}