package com.codepath.synkae.hw1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private String username = "1";
    private Context context;
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.codepath.synkae.hw1", appContext.getPackageName());
    }
    @Test
    public void mainActivityIntent(){
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), MainActivity.class);
        intent.putExtra("username", username);
        Bundle bundle = new Bundle();
        bundle = intent.getExtras();
        String user = bundle.getString("username");
        assertEquals(user, "1");
    }
}