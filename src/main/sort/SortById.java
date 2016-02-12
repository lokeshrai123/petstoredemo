package main.sort;

import java.util.Comparator;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class implementing Comparator to sort objects based on Id field
 *
 * @author Lokesh Rai
 */

public class SortById implements Comparator<JSONObject> {
 
    /**
    *
    * Sort objects based on Id field
    *
    * @param JSONObjects
    * @return int
    */
    @Override
    public int compare(JSONObject lhs, JSONObject rhs) {
        try {
            return lhs.getLong("id") > rhs.getLong("id") ? 1 : (lhs
                .getLong("id") < rhs.getLong("id") ? -1 : 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;

    }
}