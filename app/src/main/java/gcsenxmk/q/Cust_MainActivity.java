package gcsenxmk.q;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class Cust_MainActivity extends AppCompatActivity {
    private static final String TAG = "Cust_MainActivity";
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cust_outline);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.cust_viewpager_container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.cust_tabs);

        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.icon_myqueue_noncircular);
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_home_noncircular);
        tabLayout.getTabAt(2).setIcon(R.drawable.icon_user_noncircular);

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CustSearchResult());
        adapter.addFragment(new CustExplore());
        adapter.addFragment(new CustSettings());
        viewPager.setAdapter(adapter);
    }
}

