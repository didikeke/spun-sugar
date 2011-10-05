package com.didikeke.spunsugar;
import java.util.List;

import android.app.Application;

import com.didikeke.spunsugar.client.SpunSugarClient;
import com.didikeke.spunsugar.client.domain.Item;


public class ApplicationEx extends Application {

	private SpunSugarClient client;
	private List<Item> items;

	public SpunSugarClient getClient() {
		return client;
	}

	public void setClient(SpunSugarClient client) {
		this.client = client;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	
	
}
