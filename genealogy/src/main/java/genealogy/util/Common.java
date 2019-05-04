package genealogy.util;

import org.json.JSONArray;
import org.json.JSONException;

public final class Common {

	private Common() {}


	public static String[] jsonArrayToStringArray(JSONArray jsonArray) {
		if(jsonArray==null || jsonArray.length()==0)
			return new String[0];
		int len = jsonArray.length();
		String[] arr = new String[len];
		try {
			for(int i=len-1; i>-1; i--)
				arr[i] = jsonArray.get(i).toString();
		}
		catch (JSONException ex) {
			ex.printStackTrace();
		}
		return arr;
	}
}
