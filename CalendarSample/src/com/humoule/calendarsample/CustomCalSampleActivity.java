package com.humoule.calendarsample;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.humoule.customcal.CustomCalFragment;
import com.humoule.customcal.adapter.CustomCalGridAdapter;
import com.humoule.customcal.callback.CustomCalListener;

public class CustomCalSampleActivity extends FragmentActivity
{
	private CustomCalFragment customCalFragment;

	private void setCustomResourceForDates()
	{
		Calendar cal = Calendar.getInstance();

		// Min date is last 7 days
		cal.add(Calendar.DATE, -18);
		Date blueDate = cal.getTime();

		// Max date is next 7 days
		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 16);

		if (customCalFragment != null)
		{
			customCalFragment.setBackgroundResourceForDate(R.color.blue, blueDate);
			customCalFragment.setTextColorForDate(R.color.white, blueDate);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
		customCalFragment = new CustomCalFragment();

		// If Activity is created after rotation
		if (savedInstanceState != null)
		{
			customCalFragment.restoreStatesFromKey(savedInstanceState, "CUSTOM_CAL_SAVED_STATE");
		}
		// If activity is created from fresh
		else
		{
			Bundle args = new Bundle();
			Calendar cal = Calendar.getInstance();
			args.putInt(CustomCalFragment.MONTH, cal.get(Calendar.MONTH) + 1);
			args.putInt(CustomCalFragment.YEAR, cal.get(Calendar.YEAR));
			args.putBoolean(CustomCalFragment.ENABLE_SWIPE, true);

			customCalFragment.setArguments(args);
		}

		setCustomResourceForDates();

		// Attach to the activity
		FragmentTransaction t = getSupportFragmentManager().beginTransaction();
		t.replace(R.id.calendar1, customCalFragment);
		t.commit();

		// Setup listener
		final CustomCalListener listener = new CustomCalListener()
		{

			@Override
			public void onSelectDate(Date date, View view)
			{
				Toast.makeText(getApplicationContext(), formatter.format(date), Toast.LENGTH_SHORT).show();
				if (CustomCalGridAdapter.selected != null)
				{
					CustomCalGridAdapter.selected.findViewById(R.id.calendar_iv).setVisibility(View.INVISIBLE);
				}
				view.findViewById(R.id.calendar_iv).setVisibility(View.VISIBLE);
				CustomCalGridAdapter.setSelectedDate(date);
				CustomCalGridAdapter.setSelectedView(view);

			}

			@Override
			public void onCustomCalViewCreated()
			{
				Toast.makeText(getApplicationContext(), "View is ready !", Toast.LENGTH_SHORT).show();

			}

		};

		customCalFragment.setCustomCalListener(listener);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);

		if (customCalFragment != null)
		{
			customCalFragment.saveStatesToKey(outState, "CUSTOM_CAL_SAVED_STATE");
		}

	}

}
