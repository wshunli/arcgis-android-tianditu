package com.wshunli.map.tianditu;

import android.util.Log;

import com.esri.android.map.TiledServiceLayer;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.SpatialReference;

import java.util.Map;
import java.util.concurrent.RejectedExecutionException;

public class TianDiTuLayer extends TiledServiceLayer {

	private TianDiTuLayerInfo layerInfo;

	public TianDiTuLayer(int layerType) {
		super(true);
		this.layerInfo = LayerInfoFactory.getLayerInfo(layerType);
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
			Log.e("ArcGIS", "initialization of the layer failed.",
					rejectedexecutionexception);
		}
	}

	protected byte[] getTile(int level, int col, int row) throws Exception {
		if (level > layerInfo.getMaxZoomLevel()
				|| level < layerInfo.getMinZoomLevel())
			return new byte[0];
		String url = layerInfo.getUrl()
				+ "?service=wmts&request=gettile&version=1.0.0&layer="
				+ layerInfo.getLayerName() + "&format=tiles&tilematrixset="
				+ layerInfo.getTileMatrixSet() + "&tilecol=" + col
				+ "&tilerow=" + row + "&tilematrix=" + (level+1);
		Map<String, String> map = null;
		return com.esri.core.internal.io.handler.a.a(url, map);
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
	

}
