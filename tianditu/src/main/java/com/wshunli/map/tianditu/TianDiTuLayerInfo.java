package com.wshunli.map.tianditu;

import com.esri.core.geometry.Point;

public class TianDiTuLayerInfo {

	private String url;
	private String layerName;

	private int minZoomLevel = 0;
	private int maxZoomLevel = 17;

	private double xMin;
	private double yMin;
	private double xMax;
	private double yMax;

	private int tileWidth = 256;
	private int tileHeight = 256;

	private double[] scales;
	private double[] resolutions;

	private int dpi = 96;

	private int srid;

	private Point origin;

	private String tileMatrixSet;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLayerName() {
		return layerName;
	}

	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	public int getMinZoomLevel() {
		return minZoomLevel;
	}

	public void setMinZoomLevel(int minZoomLevel) {
		this.minZoomLevel = minZoomLevel;
	}

	public int getMaxZoomLevel() {
		return maxZoomLevel;
	}

	public void setMaxZoomLevel(int maxZoomLevel) {
		this.maxZoomLevel = maxZoomLevel;
	}

	public double getxMin() {
		return xMin;
	}

	public void setxMin(double xMin) {
		this.xMin = xMin;
	}

	public double getyMin() {
		return yMin;
	}

	public void setyMin(double yMin) {
		this.yMin = yMin;
	}

	public double getxMax() {
		return xMax;
	}

	public void setxMax(double xMax) {
		this.xMax = xMax;
	}

	public double getyMax() {
		return yMax;
	}

	public void setyMax(double yMax) {
		this.yMax = yMax;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}

	public double[] getScales() {
		return scales;
	}

	public void setScales(double[] scales) {
		this.scales = scales;
	}

	public double[] getResolutions() {
		return resolutions;
	}

	public void setResolutions(double[] resolutions) {
		this.resolutions = resolutions;
	}

	public int getDpi() {
		return dpi;
	}

	public void setDpi(int dpi) {
		this.dpi = dpi;
	}

	public int getSrid() {
		return srid;
	}

	public void setSrid(int srid) {
		this.srid = srid;
	}

	public Point getOrigin() {
		return origin;
	}

	public void setOrigin(Point origin) {
		this.origin = origin;
	}

	public String getTileMatrixSet() {
		return tileMatrixSet;
	}

	public void setTileMatrixSet(String tileMatrixSet) {
		this.tileMatrixSet = tileMatrixSet;
	}

}
