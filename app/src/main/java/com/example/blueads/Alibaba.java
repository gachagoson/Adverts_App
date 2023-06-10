package com.example.blueads;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.blueads.fragments.AccountFragment;
import com.example.blueads.fragments.CartFragment;
import com.example.blueads.fragments.CategoryFragment;
import com.example.blueads.fragments.Explore;
import com.example.blueads.fragments.HomeFragment;
import com.example.blueads.fragments.Plus;
import com.example.blueads.fragments.ServiceFragment;
import com.example.blueads.models.CartItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class Alibaba extends AppCompatActivity implements CartFragment.OnAddToCartListener{

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private BottomNavigationView bottomNavigationView;

    private HomeFragment homeFragment;
    private Explore exploreFragment;
    private Plus plusFragment;
    private ServiceFragment serviceFragment;
    private CategoryFragment categoryFragment;
    private CartFragment cart;
    private AccountFragment accountFragment;

    private CartFragment cartFragment;

    private int previousPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alibaba);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        setupViewPager();
        setupTabLayout();
        setupBottomNavigationView();
    }

    private void setupViewPager() {
        homeFragment = new HomeFragment();
        exploreFragment = new Explore();
        serviceFragment = new ServiceFragment();
        plusFragment = new Plus();
        categoryFragment = new CategoryFragment();
        cart = new CartFragment();
        accountFragment = new AccountFragment();


        cartFragment = cart; // Use the existing "cart" instance instead of creating a new one
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), cartFragment);
        viewPager.setAdapter(adapter);

        // Set a listener to detect when the account fragment is selected
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == 2 || position == 3 || position == 4) { // Check if the account fragment is selected
                    tabLayout.setVisibility(View.GONE); // Hide the TabLayout
                } else {
                    tabLayout.setVisibility(View.VISIBLE); // Show the TabLayout for other fragments
                }
            }
        });
    }

    private void setupTabLayout() {
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Explore");
        tabLayout.getTabAt(1).setText("Plus");
    }

    private void setupBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.action_cartegory:
                        viewPager.setCurrentItem(2); // Switch to the category fragment
                        break;
                    case R.id.action_cart:
                        viewPager.setCurrentItem(3); // Switch to the cart fragment
                        break;
                    case R.id.action_account:
                        viewPager.setCurrentItem(4); // Switch to the account fragment
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onAddToCart(CartItem item) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        private final int NUM_PAGES = 5;
        private CartFragment cartFragment;

        public MyPagerAdapter(FragmentManager fm, CartFragment cartFragment) {
            super(fm);
            this.cartFragment = cartFragment;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return exploreFragment;
                case 1:
                    return serviceFragment;
                case 2:
                    return categoryFragment;
                case 3:
                    return cartFragment;
                case 4:
                    return accountFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}