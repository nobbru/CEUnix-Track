package com.bawkertech.ceunixtack.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MissingPersonAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, MissingPersonService.class);
        context.startService(serviceIntent);
    }
}