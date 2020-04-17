package com.overdose.homeschooljoint.base;

import android.view.View;

import com.overdose.homeschooljoint.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import butterknife.BindView;
import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnLoadMoreListener;
import tl.com.easy_recycleview_library.interfaces.OnRefreshListener;

public abstract class BaseListFragment <T> extends BaseFragment {

    @BindView(R.id.base_recycleView)
    protected BaseRecyclerView recycleView;
    @BindView(R.id.empty_view)
    protected View empty_view;

    public final int LOAD_MORE = 0X0100;
    public final int REFRESH = 0X0101;
    protected List<T> listData = new ArrayList<T>();
    protected final int PAGE_SIZE = 30;//一页拉取的数据条数
    protected final int FIRST_PAGE = 1;
    protected int currentPage = FIRST_PAGE;//当前页数
    protected int currentType = REFRESH;
    protected BaseRecyclerViewAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_list;
    }

    @Override
    public void initialization() {
        recycleView.setEmptyView(empty_view);
        initView();
        canPull();
        canLoadMore();
    }

    protected abstract void initView();

    protected abstract void refresh();

    protected abstract void loadMore();

    protected abstract boolean isPullEnable();

    protected abstract boolean isLoadMoreEnable();

    /**
     * 是否开启下拉刷新 (默认开启)
     *
     */
    protected void canPull() {
        if (isPullEnable()) {
            recycleView.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh() {
                    currentType = REFRESH;
                    refresh();
                }
            });
        }
        recycleView.setPullRefreshEnabled(isPullEnable());
    }

    /**
     * 是否开启上拉加载 (默认开启)
     *
     */
    protected void canLoadMore() {
        if (isLoadMoreEnable()) {
            recycleView.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    currentType = LOAD_MORE;
                    loadMore();
                }
            });
        }
        recycleView.setLoadMoreEnabled(isLoadMoreEnable());
    }

    public void setListData(List<T> dataList) {
        switch (currentType) {
            case REFRESH:
                listData.clear();
                listData.addAll(dataList);
                adapter.refreshData();
                recycleView.refreshComplete(PAGE_SIZE);
                break;

            case LOAD_MORE:
                listData.addAll(dataList);
                adapter.refreshData();
                recycleView.refreshComplete(PAGE_SIZE);
                break;
        }
    }


    /**
     * 添加单个header
     *
     * @param baseRecyclerViewAdapter adapter
     * @param view                    header
     */
    protected void addHeader(BaseRecyclerViewAdapter baseRecyclerViewAdapter, View view) {
        baseRecyclerViewAdapter.addHeaderView(view);
    }

    /**
     * 添加多个header
     *
     * @param baseRecyclerViewAdapter adapter
     * @param views                   headers
     */
    protected void addHeaders(BaseRecyclerViewAdapter baseRecyclerViewAdapter, @NonNull List<View>
            views) {
        for (View view : views) {
            baseRecyclerViewAdapter.addHeaderView(view);
        }
    }

    /**
     * 添加单个footer
     *
     * @param baseRecyclerViewAdapter adapter
     * @param footer                  footer
     */
    protected void addFooter(BaseRecyclerViewAdapter baseRecyclerViewAdapter, View footer) {
        baseRecyclerViewAdapter.addFooterView(footer);
    }

    /**
     * 添加多个footer
     *
     * @param baseRecyclerViewAdapter adapter
     * @param footers                 footer
     */
    protected void addFooters(BaseRecyclerViewAdapter baseRecyclerViewAdapter, List<View> footers) {
        for (View view : footers) {
            baseRecyclerViewAdapter.addFooterView(view);
        }
    }


}

