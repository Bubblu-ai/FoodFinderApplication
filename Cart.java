package com.tap.model;

import java.util.HashMap;
import java.util.Map;

	public class Cart {

		private Map<Integer,CartItem>items;

		public Cart() {
			this.items = new HashMap<Integer, CartItem>();
		}
		public void addItem(CartItem item){
			int itemid = item.getItemid();
			if(items.containsKey(item.getItemid())) {
				CartItem existingItem= items.get(item.getItemid());
				existingItem.setQuantity(existingItem.getQuantity()+item.getQuantity());
			}
			else {
				items.put(itemid, item);
			}
		}
		
		public void updateItem(int itemid,int quantity){
			if(items.containsKey(itemid)) {
				if(quantity<=0) {
					items.remove(itemid);
				}
				else {
					items.get(itemid).setQuantity(quantity);
				}
			}
		}

		public void removeItem(int itemid){
			items.remove(itemid);
		}
	
		public Map<Integer, CartItem> getItems(){
			return items;
		}

		public void clear(){
			items.clear();
		}	
}
