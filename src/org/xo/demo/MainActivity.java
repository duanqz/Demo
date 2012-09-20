package org.xo.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * Display a list of activity which has category of CATEGORY_SAMPLE_CODE. Call
 * this activity with {@link #PATH_RESTRICT} will only display the activities
 * under path restriction.
 * 
 * @author duanqizhi
 * 
 */
public class MainActivity extends ListActivity {

    public static final String PATH_RESTRICT = "org.xo.demo.PATH_RESTRICT";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String path = intent.getStringExtra(PATH_RESTRICT);

        setListAdapter(new SimpleAdapter(this, getData(path),
                android.R.layout.simple_list_item_1, new String[] { "title" },
                new int[] { android.R.id.text1 }));
        getListView().setTextFilterEnabled(true);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);

        Intent intent = (Intent) map.get("intent");
        startActivity(intent);
    }

    private List<Map<String, Object>> getData(String pathRestrict) {
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

        // Resolve sample code activity
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_SAMPLE_CODE);

        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);

        if (list == null) {
            // No sample code
            return data;
        }

        String[] prefixOfPathRestrict = TextUtils.isEmpty(pathRestrict) ? null
                : pathRestrict.split("/");

        Map<String, Boolean> entries = new HashMap<String, Boolean>();
        String label;
        for (ResolveInfo info : list) {
            label = loadLabelofResolution(info, pm);

            if (isUnderPathRestrict(label, pathRestrict)) {
                String[] prefixOfLabel = label.split("/");
                String displayName = (prefixOfPathRestrict == null) ? prefixOfLabel[0]
                        : prefixOfLabel[prefixOfPathRestrict.length];

                if (isActivityName(prefixOfPathRestrict, prefixOfLabel)) {
                    addItem(data,
                            displayName,
                            activityIntent(
                                    info.activityInfo.applicationInfo.packageName,
                                    info.activityInfo.name));
                } else {
                    if (entries.get(displayName) == null) {
                        addItem(data,
                                displayName,
                                browseIntent(TextUtils.isEmpty(pathRestrict) ? displayName
                                        : pathRestrict + "/" + displayName));
                        entries.put(displayName, true);
                    }
                }
            }
        }
        return data;
    }

    private static String loadLabelofResolution(ResolveInfo info,
            PackageManager pm) {
        CharSequence label = info.loadLabel(pm);
        return (label != null) ? label.toString() : info.activityInfo.name;
    }

    /**
     * Tests whether label is under path. If pathRestrict is empty, true will be
     * got.
     * 
     * @param label
     * @param pathRestrict
     */
    private static boolean isUnderPathRestrict(String label, String pathRestrict) {
        return TextUtils.isEmpty(pathRestrict) ? true : label
                .startsWith(pathRestrict);
    }

    private static boolean isActivityName(String[] prefixOfPathRestrict,
            String[] prefixOfLabel) {
        int len = prefixOfPathRestrict != null ? prefixOfPathRestrict.length
                : 0;
        return len == prefixOfLabel.length - 1;
    }

    protected Intent activityIntent(String pkg, String componentName) {
        Intent result = new Intent();
        result.setClassName(pkg, componentName);
        return result;
    }

    protected Intent browseIntent(String pathRetrict) {
        Intent result = new Intent();
        result.setClass(this, MainActivity.class);
        result.putExtra(PATH_RESTRICT, pathRetrict);
        return result;
    }

    /**
     * Item is a key-value like title and intent
     * 
     * @param data
     * @param name
     * @param intent
     */
    protected void addItem(List<Map<String, Object>> data, String name,
            Intent intent) {
        Map<String, Object> temp = new HashMap<String, Object>();
        temp.put("title", name);
        temp.put("intent", intent);
        data.add(temp);
    }

}
