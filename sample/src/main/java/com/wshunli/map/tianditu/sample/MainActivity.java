package com.wshunli.map.tianditu.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.esri.android.map.MapView;
import com.esri.android.map.event.OnZoomListener;
import com.wshunli.map.tianditu.TianDiTuLayer;
import com.wshunli.map.tianditu.TianDiTuLayerTypes;

public class MainActivity extends AppCompatActivity {

    MapView mMapView;

    //矢量地图
    public TianDiTuLayer vec_c;
    //矢量标注
    public TianDiTuLayer cva_c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMapView = (MapView) findViewById(R.id.map);

        vec_c = new TianDiTuLayer(TianDiTuLayerTypes.TIANDITU_VECTOR_MERCATOR);
        mMapView.addLayer(vec_c);
        cva_c = new TianDiTuLayer(TianDiTuLayerTypes.TIANDITU_VECTOR_ANNOTATION_CHINESE_MERCATOR);
        mMapView.addLayer(cva_c);


        /**
         * 解决天地图标注覆盖问题
         */
        mMapView.setOnZoomListener(new OnZoomListener() {
            @Override
            public void preAction(float v, float v1, double v2) {

            }

            @Override
            public void postAction(float v, float v1, double v2) {

                cva_c.clearTiles();

            }
        });
    }
}
