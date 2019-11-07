package kit.com.mvvm.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import kit.com.mvvm.BaseActivity;
import kit.com.mvvm.MyApplication;
import kit.com.mvvm.R;
import kit.com.mvvm.adapter.ShaadiListAdapter;
import kit.com.mvvm.database.modal.Shaadi;
import kit.com.mvvm.architecture.ProjectViewModel;
import kit.com.mvvm.util.AndroidNetworkUtility;

public class ShaadiActivity extends BaseActivity {

    private RecyclerView mShaadiRecyclerView;

    private ShaadiListAdapter mShaadiListAdapter;

    private ProgressBar mProgressBar;

    private List<Shaadi> mShaadiList = new ArrayList<>();
    private AlertDialog dialog;
    private AlertDialog.Builder builder;
    private ProjectViewModel projectViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shaadi);
        init();
        getShaadiData();
        clickListeners();
    }

    private boolean checkConnectivity() {
        if (!AndroidNetworkUtility.getInstance().isConnected(ShaadiActivity.this)) {
            showNoInternetAlert();
            return false;
        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkConnectivity()) {
            MyApplication.getProjectRepository().getShaadiListData();
        } else if (!AndroidNetworkUtility.getInstance().isConnected(ShaadiActivity.this) &&
                MyApplication.getDatabseManager().getListInDb().size() == 0) {
            showNoInternetAlert();
        }
    }

    @Override
    protected void init() {
        super.init();
        projectViewModel = new ProjectViewModel();
        builder = new AlertDialog.Builder(ShaadiActivity.this);
        mProgressBar = new ProgressBar(ShaadiActivity.this);
        mShaadiRecyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ShaadiActivity.this);
        mShaadiRecyclerView.setLayoutManager(mLayoutManager);
        mShaadiRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mShaadiListAdapter = new ShaadiListAdapter(ShaadiActivity.this, mShaadiList);
        mShaadiRecyclerView.setAdapter(mShaadiListAdapter);
    }

    @Override
    protected void clickListeners() {
        super.clickListeners();
        mShaadiRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
                int totalItemCount = 0;
                if (layoutManager != null) {
                    totalItemCount = layoutManager.getItemCount();
                    int lastVisible = layoutManager.findLastVisibleItemPosition();

                    boolean endHasBeenReached = lastVisible + 3 >= totalItemCount;
                    if (totalItemCount > 0 && endHasBeenReached) {
                        if (checkConnectivity()) {
                            MyApplication.getProjectRepository().getShaadiListData();
                        }
                    }

                }

            }
        });

    }

    private void getShaadiData() {
        projectViewModel.getShaadiList().observe(this, new Observer<List<Shaadi>>() {
            @Override
            public void onChanged(List<Shaadi> shaadiList) {
                mProgressBar.setVisibility(View.VISIBLE);
                if (shaadiList == null)
                    return;

                mShaadiList.clear();
                mShaadiList.addAll(shaadiList);
                mShaadiListAdapter.notifyDataSetChanged();
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }

        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    public void showNoInternetAlert() {
        builder.setTitle(getString(R.string.alertmessage_no_internet_title))
                .setMessage(getString(R.string.alertmessage_no_internet_msg))
                .setCancelable(true)
                .setPositiveButton("Settings",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                            }
                        });
        if (dialog == null) {
            dialog = builder.create();
        }

        if (!dialog.isShowing()) {
            dialog.show();
        }

    }

}
