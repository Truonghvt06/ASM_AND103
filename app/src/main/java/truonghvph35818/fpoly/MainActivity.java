package truonghvph35818.fpoly;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import truonghvph35818.fpoly.Fragment.DonHangFragment;
import truonghvph35818.fpoly.Fragment.GioHangFragment;
import truonghvph35818.fpoly.Fragment.HomeFragment;
import truonghvph35818.fpoly.Fragment.YeuThichFragment;


public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.frame_layout);
        bottomNavigationView = findViewById(R.id.menu_nav);

        //Frag mặc định
        FragmentManager fragmentManager = getSupportFragmentManager();
        HomeFragment frag_home = new HomeFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, frag_home)
                .commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.home){
                    fragment = new HomeFragment();
                } else if (item.getItemId() == R.id.gio_hang) {
                    fragment = new GioHangFragment();
                }else if (item.getItemId() == R.id.don_hang) {
                    fragment = new DonHangFragment();
                }else  if (item.getItemId() ==R.id.yeu_thich){
                    fragment = new YeuThichFragment();
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, fragment)
                        .commit();
                return true;
            }
        });
    }
}