package com.app.ghazi.recyclerviewexample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.ghazi.recyclerviewexample.adapter.AdapterAuction;
import com.app.ghazi.recyclerviewexample.api.API;
import com.app.ghazi.recyclerviewexample.api.RestAdapter;
import com.app.ghazi.recyclerviewexample.model.CallbackAuction;
import com.app.ghazi.recyclerviewexample.model.Datum;
import com.app.ghazi.recyclerviewexample.utils.NetworkCheck;
import com.app.ghazi.recyclerviewexample.utils.Tools;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String EXTRA_OBJECT = "key.EXTRA_OBJECT";

    // activity transition
    //public static void navigate(Activity activity, Category obj) {
    //    Intent i = new Intent(activity, ActivityCategoryDetails.class);
    //    i.putExtra(EXTRA_OBJECT, obj);
    //    activity.startActivity(i);
    //}

    // extra obj
    //private Category category;

    private Toolbar toolbar;
    private ActionBar actionBar;
    private View parent_view;
    private SwipeRefreshLayout swipe_refresh;
    private Call<CallbackAuction> callbackCall = null;

    private RecyclerView recyclerView;
    private AdapterAuction mAdapter;

    private int post_total = 0;
    private int failed_page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parent_view = findViewById(android.R.id.content);
        //category = (Category) getIntent().getSerializableExtra(EXTRA_OBJECT);
        initComponent();
        initToolbar();

        //displayCategoryData(category);

        requestAction(1);
    }

    private void initComponent() {
        swipe_refresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, Tools.getGridSpanCount(this)));
        recyclerView.setHasFixedSize(true);

        //set data and list adapter
        mAdapter = new AdapterAuction(this, recyclerView, new ArrayList<Datum>());
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterAuction.OnItemClickListener() {
            @Override
            public void onItemClick(View v, Datum obj, int position) {
                //ActivityProductDetails.navigate(ActivityCategoryDetails.this, obj.id, false);
            }
        });

        // detect when scroll reach bottom
        mAdapter.setOnLoadMoreListener(new AdapterAuction.OnLoadMoreListener() {
            @Override
            public void onLoadMore(int current_page) {
                if (post_total > mAdapter.getItemCount() && current_page != 0) {
                    int next_page = current_page + 1;
                    requestAction(next_page);
                } else {
                    mAdapter.setLoaded();
                }
            }
        });

        // on swipe list
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (callbackCall != null && callbackCall.isExecuted()) callbackCall.cancel();
                mAdapter.resetListData();
                requestAction(1);
            }
        });
    }

    /*
    private void displayCategoryData(Category c) {
        ((AppBarLayout) findViewById(R.id.app_bar_layout)).setBackgroundColor(Color.parseColor(c.color));
        ((TextView) findViewById(R.id.name)).setText(c.name);
        ((TextView) findViewById(R.id.brief)).setText(c.brief);
        ImageView icon = (ImageView) findViewById(R.id.icon);
        Tools.displayImageOriginal(this, icon, Constant.getURLimgCategory(c.icon));
        Tools.setSystemBarColorDarker(this, c.color);
        if (AppConfig.TINT_CATEGORY_ICON) {
            icon.setColorFilter(Color.WHITE);
        }

        // analytics track
        ThisApplication.getInstance().saveLogEvent(c.id, c.name, "CATEGORY_DETAILS");
    }
    */


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("");
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_category_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int item_id = item.getItemId();
        if(item_id == android.R.id.home){
            super.onBackPressed();
        } else if(item_id == R.id.action_search){
            ActivitySearch.navigate(ActivityCategoryDetails.this, category);
        } else if(item_id == R.id.action_cart){
            Intent i = new Intent(this, ActivityShoppingCart.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
    */

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void displayApiResult(final List<Datum> items) {
        mAdapter.insertData(items);
        swipeProgress(false);
        if (items.size() == 0) showNoItemView(true);
    }

    private void requestListProduct(final int page_no) {
        API api = RestAdapter.createAPI();
        callbackCall = api.getAuctionList(page_no);
        callbackCall.enqueue(new Callback<CallbackAuction>() {
            @Override
            public void onResponse(Call<CallbackAuction> call, Response<CallbackAuction> response) {
                CallbackAuction resp = response.body();
                if (resp != null) {
                    post_total = resp.meta.getPagination().getTotal();
                    displayApiResult(resp.products);
                } else {
                    onFailRequest(page_no);
                }
            }

            @Override
            public void onFailure(Call<CallbackAuction> call, Throwable t) {
                String message = t.getMessage();
                Log.d("failure", message);
            }

        });
    }

    private void onFailRequest(int page_no) {
        failed_page = page_no;
        mAdapter.setLoaded();
        swipeProgress(false);
        if (NetworkCheck.isConnect(this)) {
            showFailedView(true, getString(R.string.failed_text));
        } else {
            showFailedView(true, getString(R.string.no_internet_text));
        }
    }

    private void requestAction(final int page_no) {
        showFailedView(false, "");
        showNoItemView(false);
        if (page_no == 1) {
            swipeProgress(true);
        } else {
            mAdapter.setLoading();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                requestListProduct(page_no);
            }
        }, 1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        swipeProgress(false);
        if (callbackCall != null && callbackCall.isExecuted()) {
            callbackCall.cancel();
        }
    }

    private void showFailedView(boolean show, String message) {
        View lyt_failed = (View) findViewById(R.id.lyt_failed);
        ((TextView) findViewById(R.id.failed_message)).setText(message);
        if (show) {
            recyclerView.setVisibility(View.GONE);
            lyt_failed.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            lyt_failed.setVisibility(View.GONE);
        }
        ((Button) findViewById(R.id.failed_retry)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestAction(failed_page);
            }
        });
    }

    private void showNoItemView(boolean show) {
        View lyt_no_item = (View) findViewById(R.id.lyt_no_item);
        if (show) {
            recyclerView.setVisibility(View.GONE);
            lyt_no_item.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            lyt_no_item.setVisibility(View.GONE);
        }
    }

    private void swipeProgress(final boolean show) {
        if (!show) {
            swipe_refresh.setRefreshing(show);
            return;
        }
        swipe_refresh.post(new Runnable() {
            @Override
            public void run() {
                swipe_refresh.setRefreshing(show);
            }
        });
    }
}
