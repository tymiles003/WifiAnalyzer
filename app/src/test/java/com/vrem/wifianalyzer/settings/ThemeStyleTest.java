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
package com.vrem.wifianalyzer.settings;

import com.vrem.wifianalyzer.R;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ThemeStyleTest {

    @Test
    public void testFind() throws Exception {
        assertEquals(ThemeStyle.LIGHT, ThemeStyle.find(null));
        assertEquals(ThemeStyle.LIGHT, ThemeStyle.find(""));
        assertEquals(ThemeStyle.LIGHT, ThemeStyle.find("xYz"));

        assertEquals(ThemeStyle.LIGHT, ThemeStyle.find(ThemeStyle.LIGHT.name()));
        assertEquals(ThemeStyle.LIGHT, ThemeStyle.find(ThemeStyle.LIGHT.name().toLowerCase()));

        assertEquals(ThemeStyle.DARK, ThemeStyle.find(ThemeStyle.DARK.name()));
        assertEquals(ThemeStyle.DARK, ThemeStyle.find(ThemeStyle.DARK.name().toLowerCase()));
    }

    @Test
    public void testThemeAppCompatStyle() throws Exception {
        assertEquals(R.style.ThemeAppCompatLight, ThemeStyle.LIGHT.themeAppCompatStyle());
        assertEquals(R.style.ThemeAppCompatDark, ThemeStyle.DARK.themeAppCompatStyle());
    }

    @Test
    public void testThemeDeviceDefaultStyle() throws Exception {
        assertEquals(R.style.ThemeDeviceDefaultLight, ThemeStyle.LIGHT.themeDeviceDefaultStyle());
        assertEquals(R.style.ThemeDeviceDefaultDark, ThemeStyle.DARK.themeDeviceDefaultStyle());
    }
}