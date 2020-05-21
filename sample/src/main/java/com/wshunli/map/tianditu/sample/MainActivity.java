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
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.esri.arcgisruntime.layers.WebTiledLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.BottomListPopupView;
import com.lxj.xpopup.impl.CenterListPopupView;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.wshunli.map.tianditu.TianDiTuLayer;
import com.wshunli.map.tianditu.TianDiTuLayerType;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private MapView mMapView;

    private TianDiTuLayerType mLayerType = TianDiTuLayerType.VECTOR;
    private TianDiTuLayerType.SR mSRType = TianDiTuLayerType.SR.ID_2000;
    private boolean mIsCN = true;
    private BottomListPopupView selectPopupView = null;
    private CenterListPopupView layerPopupView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMapView = findViewById(R.id.mapView);
        mMapView.setAttributionTextVisible(false);

        updateMap();
    }

    public void updateMap() {
        addBaseMapLayer(mLayerType, mSRType);
        updateAnnotationLayer(mIsCN);
    }

    // 添加底图
    private void addBaseMapLayer(TianDiTuLayerType layerType, TianDiTuLayerType.SR srType) {
        Log.d(TAG, "addBaseMapLayer: " + layerType.name() + "-" + srType.name());
        WebTiledLayer webTiledLayer = TianDiTuLayer.getInstance().getLayer(layerType, srType);
        // 加载图层
        webTiledLayer.loadAsync();
        Basemap basemap = new Basemap(webTiledLayer);
        mMapView.setMap(new ArcGISMap(basemap));
    }

    // 添加标注
    private void updateAnnotationLayer(boolean isCN) {
        Log.d(TAG, "updateAnnotationLayer: " + isCN);
        TianDiTuLayerType annotationType = null;
        switch (mLayerType) {
            case VECTOR:
                annotationType = isCN ? TianDiTuLayerType.VECTOR_ANNOTATION_CN : TianDiTuLayerType.VECTOR_ANNOTATION_EN;
                break;
            case IMAGE:
                annotationType = isCN ? TianDiTuLayerType.IMAGE_ANNOTATION_CN : TianDiTuLayerType.IMAGE_ANNOTATION_EN;
                break;
            case TERRAIN:
                annotationType = isCN ? TianDiTuLayerType.TERRAIN_ANNOTATION_CN : null;
                break;
        }
        if (annotationType == null) {
            Toast.makeText(this, "暂不支持此标注", Toast.LENGTH_SHORT).show();
            return;
        }
        WebTiledLayer annotationTiledLayer = TianDiTuLayer.getInstance().getLayer(annotationType, mSRType);

        // 加载图层
        annotationTiledLayer.loadAsync();
        Basemap basemap = mMapView.getMap().getBasemap();
        basemap.getBaseLayers().add(annotationTiledLayer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.pause();
    }

    @Override
    protected void onDestroy() {
        mMapView.dispose();
        super.onDestroy();
    }

    // 选择操作
    public void showPopMenu(View view) {

        if (selectPopupView == null) {
            selectPopupView = new XPopup.Builder(this)
                    .asBottomList("请选择操作",
                            new String[]{"选择图层（矢量、影像、地形）", "切换坐标系（国家 2000 、墨卡托投影）", "切换语言（中文标注 、英文标注）"},
                            new OnSelectListener() {
                                @Override
                                public void onSelect(int position, String text) {
                                    Log.d(TAG, "onSelect: " + text);
                                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                                    switch (position) {
                                        case 0:
                                            showLayerPopMenu();
                                            break;
                                        case 1:
                                            mSRType = mSRType == TianDiTuLayerType.SR.ID_2000 ?
                                                    TianDiTuLayerType.SR.ID_102100 : TianDiTuLayerType.SR.ID_2000;
                                            updateMap();
                                            break;
                                        case 2:
                                            mIsCN = !mIsCN;
                                            updateMap();
                                            break;
                                        default:
                                            Log.w(TAG, "onSelect: " + text + "not support");
                                    }
                                }
                            });
        }

        selectPopupView.show();
    }

    // 选择图层
    private void showLayerPopMenu() {
        if (layerPopupView == null) {
            layerPopupView = new XPopup.Builder(this)
                    .asCenterList("请选择图层",
                            new String[]{"矢量切片图层", "影像切片图层", "地形切片图层"},
                            new OnSelectListener() {
                                @Override
                                public void onSelect(int position, String text) {
                                    Log.d(TAG, "onSelect: " + text);
                                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                                    switch (position) {
                                        case 0:
                                            mLayerType = TianDiTuLayerType.VECTOR;
                                            break;
                                        case 1:
                                            mLayerType = TianDiTuLayerType.IMAGE;
                                            break;
                                        case 2:
                                            mLayerType = TianDiTuLayerType.TERRAIN;
                                            break;
                                        default:
                                            Log.w(TAG, "onSelect: " + text + "not support");
                                    }
                                    updateMap();
                                }
                            });
        }
        layerPopupView.show();
    }
}
