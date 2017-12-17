package com.wshunli.map.tianditu;

import android.util.Log;

import com.esri.android.map.TiledServiceLayer;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.SpatialReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;

public class TianDiTuLayer extends TiledServiceLayer {

    private TianDiTuLayerInfo layerInfo;
    private String cachePath;

    public TianDiTuLayer(int layerType) {
        super(true);
        this.layerInfo = LayerInfoFactory.getLayerInfo(layerType);
        this.cachePath = null;
        this.init();
    }

    public TianDiTuLayer(int layerType, String cachePath) {
        super(true);
        this.layerInfo = LayerInfoFactory.getLayerInfo(layerType);
        this.cachePath = cachePath + "/" + layerInfo.getLayerName() + "_" + layerInfo.getTileMatrixSet() + "/";
        this.init();
    }

    private void init() {
        try {
            getServiceExecutor().submit(new Runnable() {
                public void run() {
                    TianDiTuLayer.this.initLayer();
                }
            });
        } catch (RejectedExecutionException rejectedexecutionexception) {
            Log.e("TianDiTuLayer", "initialization of the layer failed.",
                    rejectedexecutionexception);
        }
    }

    protected byte[] getTile(int level, int col, int row) throws Exception {
        if (level > layerInfo.getMaxZoomLevel()
                || level < layerInfo.getMinZoomLevel())
            return new byte[0];
        byte[] bytes = null;
        if (cachePath != null)
            bytes = getOfflineCacheFile(cachePath, level, col, row);
        if (bytes == null) {
            String url = layerInfo.getUrl()
                    + "?service=wmts&request=gettile&version=1.0.0&layer="
                    + layerInfo.getLayerName() + "&format=tiles&tilematrixset="
                    + layerInfo.getTileMatrixSet() + "&tilecol=" + col
                    + "&tilerow=" + row + "&tilematrix=" + (level + 1);
            Map<String, String> map = null;
            bytes = com.esri.core.internal.io.handler.a.a(url, map);
            if (cachePath != null)
                AddOfflineCacheFile(cachePath, level, col, row, bytes);
        }
        return bytes;
    }

    protected void initLayer() {
        if (getID() == 0L) {
            nativeHandle = create();
            changeStatus(com.esri.android.map.event.OnStatusChangedListener.STATUS
                    .fromInt(-1000));
        } else {
            this.setDefaultSpatialReference(SpatialReference.create(layerInfo
                    .getSrid()));
            this.setFullExtent(new Envelope(layerInfo.getxMin(), layerInfo
                    .getyMin(), layerInfo.getxMax(), layerInfo.getyMax()));
            this.setTileInfo(new TileInfo(layerInfo.getOrigin(), layerInfo
                    .getScales(), layerInfo.getResolutions(), layerInfo
                    .getScales().length, layerInfo.getDpi(), layerInfo
                    .getTileWidth(), layerInfo.getTileHeight()));
            super.initLayer();
        }
    }


    // 将图片保存到本地 目录结构可以随便定义 只要你找得到对应的图片
    private byte[] AddOfflineCacheFile(String cachePath, int level, int col, int row, byte[] bytes) {

        File file = new File(cachePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        File levelfile = new File(cachePath + "/" + level);
        if (!levelfile.exists()) {
            levelfile.mkdirs();
        }
        File rowfile = new File(cachePath + "/" + level + "/" + col + "x" + row
                + ".tdt");
        if (!rowfile.exists()) {
            try {
                FileOutputStream out = new FileOutputStream(rowfile);
                out.write(bytes);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return bytes;

    }

    // 从本地获取图片
    private byte[] getOfflineCacheFile(String cachePath, int level, int col, int row) {
        byte[] bytes = null;
        File rowfile = new File(cachePath + "/" + level + "/" + col + "x" + row
                + ".tdt");
        if (rowfile.exists()) {
            try {
                bytes = CopySdcardbytes(rowfile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            bytes = null;
        }
        return bytes;
    }

    // 读取本地图片流
    private byte[] CopySdcardbytes(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        byte[] temp = new byte[1024];
        int size = 0;
        while ((size = in.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        in.close();
        byte[] bytes = out.toByteArray();
        return bytes;
    }

}
