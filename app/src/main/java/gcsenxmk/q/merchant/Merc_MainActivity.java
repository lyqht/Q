package gcsenxmk.q.merchant;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import gcsenxmk.q.R;
import gcsenxmk.q.misc.SectionsPagerAdapter;

public class Merc_MainActivity extends AppCompatActivity {

    private static final String TAG = "Merc_MainActivity";

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.merc_outline);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mSectionsPagerAdapter.notifyDataSetChanged();

        mViewPager = (ViewPager) findViewById(R.id.merc_viewpager_container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.merc_tabs);

        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.cog);
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_food);
        tabLayout.getTabAt(2).setIcon(R.drawable.helpicon26);
        //tabLayout.getTabAt(3).setIcon(R.drawable.help);

        mViewPager.setCurrentItem(1);



    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MercQueueConfiguration());
        adapter.addFragment(new MercMainOverview());
        adapter.addFragment(new Merc_HelpPage());
        viewPager.setAdapter(adapter);
    }


}