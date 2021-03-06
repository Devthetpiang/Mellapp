package com.xavey.android.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.xavey.android.R;
import com.xavey.android.db.XaveyDBHelper;
import com.xavey.android.model.SyncImage;
import com.xavey.android.util.DisplayManager;
import com.xavey.android.util.XaveyUtils;

public class ImageAdapter extends BaseAdapter {

	Context activity;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data_set_values;
	XaveyDBHelper dbHelper;
	XaveyUtils xaveyUtils;
	DisplayManager displayManager;
	int width;
	int height;

	public ImageAdapter(Context a,
			ArrayList<HashMap<String, String>> data_set_values) {
		activity = a;
		this.data_set_values = data_set_values;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		dbHelper = new XaveyDBHelper(activity);
		xaveyUtils = new XaveyUtils(a);
		displayManager = new DisplayManager((Activity) activity);
	}

	@Override
	public int getCount() {
		return data_set_values.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public static class ViewHolder {
		TextView imageTitle;
		boolean isSelected;
	}

	@Override
	public View getView(int position, View convertView, final ViewGroup parent) {
		ViewHolder holder = new ViewHolder();
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.image_dataset_item, parent,
					false);
			holder.imageTitle = (TextView) convertView
					.findViewById(R.id.tvImageData);
			holder.isSelected = false;
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.imageTitle.setTag(R.id.is_griditem_selected, false);
		HashMap<String, String> map = data_set_values.get(position);
		// not used the following so far
		final String value = map.get("value");
		// String field_skip = map.get("field_skip");
		// String extra = map.get("extra");
		String label = map.get("label");
		String image = map.get("image");

		SyncImage syncImage = dbHelper.getSyncImageByImageID(image);
        if(syncImage!=null) {
            byte[] byteArray = syncImage.getImgByte();
            BitmapDrawable bd = xaveyUtils
                    .convertByteArrayToBitmapDrawable(byteArray);
            width = displayManager.getWidth(30);
            height = displayManager.getHeigth(25);
            bd.setBounds(0, 0, width, height);
            holder.imageTitle.setCompoundDrawables(null, bd, null, null);
        }
		holder.imageTitle.setText(label);
		// holder.imageTitle.setTag(R.id.grid_item_value, value);
		if (holder.isSelected) {
			//holder.imageTitle.setBackgroundColor(Color.BLUE);
			// holder.imageTitle.setTextColor(Color.parseColor("#abcdef"));
			holder.isSelected = false;
		} else {
			//holder.imageTitle.setBackgroundColor(Color.WHITE);
			// holder.imageTitle.setTextColor(Color.parseColor("#000000"));
			holder.isSelected = true;
		}
		holder.imageTitle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				TextView tv = (TextView) v;
				boolean is_griditem_selected = (Boolean) v
						.getTag(R.id.is_griditem_selected);
				ArrayList<String> valueList = (ArrayList<String>) parent.getTag(R.id.selected_grid_values);

				if (parent.getTag(R.id.selected_grid_values) != null) {
					String gridViewType = parent.getTag(R.id.grid_view_type)
							.toString();
					if (gridViewType.equals("multipleSelection")) {
						if (!is_griditem_selected) {
							valueList.add(value);
							v.setTag(R.id.is_griditem_selected, true);
							tv.setTextColor(Color.parseColor("#000000"));
							tv.setBackgroundColor(Color.parseColor("#D3D9CC"));
						} else {
							valueList.remove(value);
							v.setTag(R.id.is_griditem_selected, false);
							//tv.setTextColor(Color.parseColor("#A5ABA4"));
							tv.setBackgroundColor(Color.parseColor("#FFFFFF"));
						}
						parent.setTag(R.id.selected_grid_values, valueList);
					} else if (gridViewType.equals("singleSelection")) {

						GridView gridView = (GridView) parent;
						int fstPos = gridView.getFirstVisiblePosition();
						int lstPos = gridView.getLastVisiblePosition();

						for (int i = fstPos; i <= lstPos; i++) {

							// Object o = gridView.getItemAtPosition(i);

							View childV = (View) gridView.getChildAt(i);
							ViewHolder adapterVH = (ViewHolder) childV.getTag();
							//adapterVH.imageTitle.setTextColor(Color.parseColor("#A5ABA4"));
							adapterVH.imageTitle.setBackgroundColor(Color.parseColor("#FFFFFF"));
							adapterVH.imageTitle.setTag(R.id.is_griditem_selected, false);
						}

						valueList = new ArrayList<String>();

						valueList.add(value);

						tv.setTextColor(Color.parseColor("#000000"));
						tv.setBackgroundColor(Color.parseColor("#D3D9CC"));
						gridView.setTag(R.id.selected_grid_values, valueList);
						tv.setTag(R.id.is_griditem_selected, true);

					}

				}
			}
		});

		return convertView;
	}

}
