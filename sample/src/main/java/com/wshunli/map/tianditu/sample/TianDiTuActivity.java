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

import androidx.appcompat.app.AppCompatActivity;

import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.wshunli.map.tianditu.TianDiTuLayer;
import com.wshunli.map.tianditu.TianDiTuLayerBuilder;

public class TianDiTuActivity extends AppCompatActivity {
    private static final String TAG = "TianDiTuActivity";

    private MapView mMapView;
    private TianDiTuLayer vec_c;
    private TianDiTuLayer cva_c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tianditu);

        int[] tdtLayers = getIntent().getIntArrayExtra("TIANDITU_LAYERS");
        if ((tdtLayers == null) || (tdtLayers.length == 0)) {
            tdtLayers = new int[]{0, 1};
        }

        mMapView = findViewById(R.id.mapView);

        ArcGISMap map = new ArcGISMap();

        vec_c = new TianDiTuLayerBuilder()
                .setLayerType(tdtLayers[0])
                .build();
        cva_c = new TianDiTuLayerBuilder()
                .setLayerType(tdtLayers[1])
                .build();

        map.getBasemap().getBaseLayers().add(vec_c);
        map.getBasemap().getBaseLayers().add(cva_c);

        mMapView.setMap(map);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.resume();
    }

    @Override
    protected void onDestroy() {
        mMapView.dispose();
        super.onDestroy();
    }

}
