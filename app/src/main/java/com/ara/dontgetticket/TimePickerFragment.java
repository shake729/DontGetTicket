package com.ara.dontgetticket;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by aRa on 30/4/15.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    String timeString;

    public interface OnCompleteListener {
        void onComplete(Object sender, String time);
    }

    private OnCompleteListener mListener;

    // make sure the Activity implemented it
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnCompleteListener)activity;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCompleteListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Return selected time in format HH:MM
        String formattedHour = "" + hourOfDay;
        String formattedMinute = "" + minute;

        if (hourOfDay < 10)
            formattedHour = "0"+hourOfDay;
        if (minute < 10)
            formattedMinute = "0"+minute;

        timeString = String.format("%s:%s", formattedHour, formattedMinute);
        this.mListener.onComplete(this, timeString);
        System.out.println(timeString);
    }
}
