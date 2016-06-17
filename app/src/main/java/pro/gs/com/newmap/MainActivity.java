package pro.gs.com.newmap;

import android.app.Service;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {
    private GoogleMap gMap;
    private LocationManager mLocationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.map_btn);
        Button btn2 = (Button) findViewById(R.id.map_cls);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mLocationManager = (LocationManager) this.getSystemService(Service.LOCATION_SERVICE);
    }

    public void onClick(View v) {
        View animview = findViewById(R.id.map_view);
        Animation slide_up = AnimationUtils.loadAnimation(this, R.anim.slideup);
        Animation slide_down = AnimationUtils.loadAnimation(this, R.anim.slidedown);
        if (v.getId() == R.id.map_btn) {
            updateLocation();
            animview.startAnimation(slide_up);
            animview.setVisibility(View.VISIBLE);
        } else if (v.getId() == R.id.map_cls) {
            animview.startAnimation(slide_down);
            animview.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        gMap = map;
        gMap.setMyLocationEnabled(true);
        map.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));
    }

    public void updateLocation() {

        Location myLocate = mLocationManager.getLastKnownLocation("gps");
        CameraUpdate cu = CameraUpdateFactory.newLatLng(
                new LatLng(40,120)
        );
        gMap.moveCamera(cu);
    }
}
