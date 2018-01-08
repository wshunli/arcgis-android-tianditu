/*
 * Copyright 2017 wshunli
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wshunli.map.tianditu.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.esri.android.map.MapView;
import com.esri.android.map.event.OnZoomListener;
import com.wshunli.map.tianditu.TianDiTuLayer;

public class TianDiTuActivity extends AppCompatActivity {
    private static final String TAG = "TianDiTuActivity";

    private MapView mMapView;
    private TianDiTuLayer vec_c, cva_c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tianditu);

        int[] tianditu_layers = getIntent().getIntArrayExtra("TIANDITU_LAYERS");
        if ((tianditu_layers == null) || (tianditu_layers.length == 0)) {
            tianditu_layers = new int[]{0, 1};
        }

        String cachePath = getCacheDir().getAbsolutePath() + "/TianDiTuCache";
//        String cachePath = Environment.getExternalStorageDirectory().getAbsoluteFile() + "/TianDiTuCache";

        mMapView = findViewById(R.id.map);

        vec_c = new TianDiTuLayer(tianditu_layers[0], cachePath);
        mMapView.addLayer(vec_c);
        cva_c = new TianDiTuLayer(tianditu_layers[1]);
        mMapView.addLayer(cva_c);

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

    @Override
    protected void onResume() {
        mMapView.unpause();
        super.onResume();

    }

    @Override
    protected void onPause() {
        mMapView.pause();
        super.onPause();
    }

}
