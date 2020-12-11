/*
 * Copyright (c) 2020, The Magma Authors
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.openschema.mma.metrics;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.core.util.Pair;

/**
 * Collects metrics related to Wi-Fi networks.
 */
public class WifiNetworkMetrics {

    private static final String TAG = "WifiNetworkMetrics";

    /**
     * Metric family name to be used for the collected Wi-Fi information.
     */
    public static final String METRIC_FAMILY_NAME = "openschema_android_wifi_network_info";

    private static final String METRIC_SSID = "ssid";
    private static final String METRIC_BSSID = "bssid";

    private final Context mContext;
    private WifiManager mWifiManager;

    public WifiNetworkMetrics(Context context) {
        mContext = context;
        mWifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
    }

    /**
     * Collects information about current Wi-Fi and generates a list of pairs to
     * be used in {@link MetricsManager#collect(String, List)}.
     */
    public List<Pair<String, String>> retrieveNetworkMetrics() {
        Log.d(TAG, "MMA: Generating Wi-Fi network metrics...");

        List<Pair<String, String>> metricsList = new ArrayList<>();
        WifiInfo wifiInfo = mWifiManager.getConnectionInfo();

        metricsList.add(new Pair<>(METRIC_SSID, wifiInfo.getSSID()));
        metricsList.add(new Pair<>(METRIC_BSSID, wifiInfo.getBSSID()));

//        Log.d(TAG, "MMA: Collected metrics:\n" + metricsList.toString());
        return metricsList;
    }
}