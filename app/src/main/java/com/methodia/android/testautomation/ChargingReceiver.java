package com.methodia.android.testautomation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.methodia.android.testautomation.Model.ChargingEvent;

import java.util.GregorianCalendar;

import de.greenrobot.event.EventBus;

public class ChargingReceiver extends BroadcastReceiver {

    private EventBus bus = EventBus.getDefault();

    public ChargingReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ChargingEvent event = null;
        GregorianCalendar now = new GregorianCalendar();
        String eventData = "At " + now.getTime() + " this device started ";
        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
            event = new ChargingEvent(eventData + "charging.");
        } else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
            event = new ChargingEvent(eventData + "discharging.");
        }
        // Post the event
        bus.post(event);
    }
}