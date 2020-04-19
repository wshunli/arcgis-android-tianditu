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

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wshunli.map.tianditu.TianDiTuLayerTypes;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();

    }

    private void initData() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new MapsAdapter(getData());
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.maps);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public Map<String, int[]> getData() {
        Map<String, int[]> maps = new TreeMap<>();
        maps.put("天地图矢量中文标注（墨卡托）",
                new int[]{TianDiTuLayerTypes.TIANDITU_VECTOR_MERCATOR,
                        TianDiTuLayerTypes.TIANDITU_VECTOR_ANNOTATION_CHINESE_MERCATOR});
        maps.put("天地图矢量英文标注（墨卡托）",
                new int[]{TianDiTuLayerTypes.TIANDITU_VECTOR_MERCATOR,
                        TianDiTuLayerTypes.TIANDITU_VECTOR_ANNOTATION_ENGLISH_MERCATOR});
        maps.put("天地图影像中文标注（墨卡托）",
                new int[]{TianDiTuLayerTypes.TIANDITU_IMAGE_MERCATOR,
                        TianDiTuLayerTypes.TIANDITU_IMAGE_ANNOTATION_CHINESE_MERCATOR});
        maps.put("天地图影像英文标注（墨卡托）",
                new int[]{TianDiTuLayerTypes.TIANDITU_IMAGE_MERCATOR,
                        TianDiTuLayerTypes.TIANDITU_IMAGE_ANNOTATION_ENGLISH_MERCATOR});
        maps.put("天地图地形中文标注（墨卡托）",
                new int[]{TianDiTuLayerTypes.TIANDITU_TERRAIN_MERCATOR,
                        TianDiTuLayerTypes.TIANDITU_TERRAIN_ANNOTATION_CHINESE_MERCATOR});
        maps.put("天地图矢量中文标注（国家2000坐标系）",
                new int[]{TianDiTuLayerTypes.TIANDITU_VECTOR_2000,
                        TianDiTuLayerTypes.TIANDITU_VECTOR_ANNOTATION_CHINESE_2000});
        maps.put("天地图矢量英文标注（国家2000坐标系）",
                new int[]{TianDiTuLayerTypes.TIANDITU_VECTOR_2000,
                        TianDiTuLayerTypes.TIANDITU_VECTOR_ANNOTATION_ENGLISH_2000});
        maps.put("天地图影像中文标注（国家2000坐标系）",
                new int[]{TianDiTuLayerTypes.TIANDITU_IMAGE_2000,
                        TianDiTuLayerTypes.TIANDITU_IMAGE_ANNOTATION_CHINESE_2000});
        maps.put("天地图影像英文标注（国家2000坐标系）",
                new int[]{TianDiTuLayerTypes.TIANDITU_IMAGE_2000,
                        TianDiTuLayerTypes.TIANDITU_IMAGE_ANNOTATION_ENGLISH_2000});
        maps.put("天地图地形中文标注（国家2000坐标系）",
                new int[]{TianDiTuLayerTypes.TIANDITU_TERRAIN_2000,
                        TianDiTuLayerTypes.TIANDITU_TERRAIN_ANNOTATION_CHINESE_2000});
        return maps;
    }

    private class MapsAdapter extends RecyclerView.Adapter<MapsAdapter.TextViewHolder> {
        Map<String, int[]> maps = new TreeMap<>();
        List<String> titles = new LinkedList<>();

        MapsAdapter(Map<String, int[]> maps) {
            this.maps = maps;
            titles.addAll(maps.keySet());
        }

        @Override
        public MapsAdapter.TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_map, parent, false);
            return new TextViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MapsAdapter.TextViewHolder holder, int position) {
            holder.map_title.setText(titles.get(position));
        }

        @Override
        public int getItemCount() {
            return maps.size();
        }

        class TextViewHolder extends RecyclerView.ViewHolder {

            Button map_title;

            TextViewHolder(View itemView) {
                super(itemView);
                map_title = itemView.findViewById(R.id.map_title);
                map_title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String title = (String) map_title.getText();
                        int[] layers = maps.get(
                                maps.containsKey(title) ? title : "天地图矢量中文标注（墨卡托）");
                        Log.d(TAG, "显示图层: " + title);
                        Intent intent = new Intent(MainActivity.this, TianDiTuActivity.class);
                        intent.putExtra("TIANDITU_LAYERS", layers);
                        startActivity(intent);
                    }
                });
            }
        }
    }
}
