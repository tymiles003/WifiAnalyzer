/*
 *    Copyright (C) 2010 - 2015 VREM Software Development <VREMSoftwareDevelopment@gmail.com>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.vrem.wifianalyzer.wifi;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(WifiManager.class)
public class DetailsTest {
    private static final String VENDOR_NAME = "VendorName";

    @Mock private ScanResult scanResult;

    private Details fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new Details(scanResult, VENDOR_NAME);
    }

    @Test
    public void testVendorName() throws Exception {
        // validate
        assertEquals(VENDOR_NAME, fixture.vendorName());
    }

    @Test
    public void testFrequency() throws Exception {
        // setup
        scanResult.frequency = 2470;
        // execute
        int actual = fixture.frequency();
        // validate
        assertEquals(scanResult.frequency, actual);
    }

    @Test
    public void testChannel() throws Exception {
        // setup
        int expected = 5;
        scanResult.frequency = 2435;
        // execute
        int actual = fixture.channel();
        // validate
        assertEquals(expected, actual);
    }

    @Test
    public void testSecurity() throws Exception {
        // setup
        Security expected = Security.WPA;
        scanResult.capabilities = "WPA";
        // execute
        Security actual = fixture.security();
        // validate
        assertEquals(expected, actual);
    }

    @Test
    public void testStrength() throws Exception {
        // setup
        mockStatic(WifiManager.class);
        Strength expected = Strength.TWO;
        scanResult.level = -86;
        when(WifiManager.calculateSignalLevel(scanResult.level, Strength.values().length)).thenReturn(expected.ordinal());
        // execute
        Strength actual = fixture.strength();
        // validate
        assertEquals(expected, actual);
        verifyStatic();
    }

    @Test
    public void testSSID() throws Exception {
        // setup
        scanResult.SSID = "xyzSSID";
        // execute
        String actual = fixture.SSID();
        // validate
        assertEquals(scanResult.SSID, actual);
    }

    @Test
    public void testBSSID() throws Exception {
        // setup
        scanResult.BSSID = "xyzBSSID";
        // execute
        String actual = fixture.BSSID();
        // validate
        assertEquals(scanResult.BSSID, actual);
    }

    @Test
    public void testLevel() throws Exception {
        // setup
        scanResult.level = -3;
        // execute
        int actual = fixture.level();
        // validate
        assertEquals(Math.abs(scanResult.level), actual);
    }

    @Test
    public void testCapabilities() throws Exception {
        // setup
        scanResult.capabilities = "xyzCapabilities";
        // execute
        String actual = fixture.capabilities();
        // validate
        assertEquals(scanResult.capabilities, actual);
    }

    @Test
    public void testDistance() throws Exception {
        // setup
        scanResult.frequency = 2414;
        scanResult.level = -50;
        double expected = Distance.calculate(scanResult.frequency, scanResult.level);
        // execute
        double actual = fixture.distance();
        // validate
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void testEquals() throws Exception {
        // setup
        scanResult.SSID = "SSID";
        scanResult.BSSID = "BSSID";
        Details other = new Details(scanResult, VENDOR_NAME);
        // execute & validate
        // validate
        assertEquals(fixture, other);
        assertNotSame(fixture, other);
    }

    @Test
    public void testHashCode() throws Exception {
        // setup
        scanResult.SSID = "SSID";
        scanResult.BSSID = "BSSID";
        Details other = new Details(scanResult, VENDOR_NAME);
        // execute & validate
        assertEquals(fixture.hashCode(), other.hashCode());
    }

}