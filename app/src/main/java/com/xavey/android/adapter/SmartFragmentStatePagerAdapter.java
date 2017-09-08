package com.xavey.android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;


// referenced from https://github.com/thecodepath/android_guides/wiki/ViewPager-with-FragmentPagerAdapter
public abstract class SmartFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

	private SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();
	 
	public SmartFragmentStatePagerAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);
	}
 
	// Register the fragment when the item is instantiated
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		Fragment fragment = (Fragment) super.instantiateItem(container, position);
		registeredFragments.put(position, fragment);
		return fragment;
	}
 
	// Unregister when the item is inactive
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		registeredFragments.remove(position);
		super.destroyItem(container, position, object);
	}
 
	// Returns the fragment for the position (if instantiated)
	public Fragment getRegisteredFragment(int position) {
		return registeredFragments.get(position);
	}
	

	
}
