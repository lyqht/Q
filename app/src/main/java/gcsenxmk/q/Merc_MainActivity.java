package gcsenxmk.q;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Merc_MainActivity extends AppCompatActivity {

    private static final String TAG = "Merc_MainActivity";

    private MercSectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merc_outline);

        mSectionsPagerAdapter = new MercSectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.icon_food);
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_food);
        tabLayout.getTabAt(2).setIcon(R.drawable.icon_food);
    }

    private void setupViewPager(ViewPager viewPager) {
        MercSectionsPagerAdapter adapter = new MercSectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MercQueueCreation());
        adapter.addFragment(new MercMainOverview());
        adapter.addFragment(new MercFragment3());
        viewPager.setAdapter(adapter);
    }
}