package SeleniumLibraries;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Hashtable;
import org.json.simple.JSONObject;
import com.google.gson.JsonObject;

public class Parameters 
{
	Parameters oParameters;
	Hashtable<String, String> testdata = new Hashtable<String, String>();
	
	public Parameters()
	{
		preferences();
	}
	
	public String GetParameters(String Key)
	{
		if(testdata.containsKey(Key.toUpperCase()))
		{
			String rVal = testdata.get(Key.toUpperCase());
			//System.out.println("GetParamters : Key :" + Key +" ## Value : " + rVal);
			return rVal;
		}
		else
		{
			return "";
		}
	}
	
		
	public void SetParameters(String Key,String KeyValue)
	{
		if(! Key.equals(null) && !Key.equals("") && ! KeyValue.equals(null) && ! KeyValue.equals(""))
		{
			testdata.put(Key.toUpperCase(), KeyValue);
		}
	}
	
	
	public void hashToString()
	{
		SetParameters("TESTDATA",testdata.toString());
	}
	
	
	public String mapToJSON()
	{
		JSONObject jobj = new JSONObject();
		jobj.putAll(testdata);
		return jobj.toString();
	}
	
	
	public void preferences()
	{
		BufferedReader br;
		try
		{
			br = new BufferedReader(new FileReader(""));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			
			while(line != null)
			{
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			
			SetParameters("Preferences", sb.toString());
			br.close();
			
			Json_Parsor();
		}
		catch(Exception e)
		{
			System.out.println("Exception caught :" + e);
		}
	}
	
	
	public void Json_Parsor()
	{
		String s = new String(GetParameters("Preferences"));
		String[] Array = s.split("\n");
		int len = Array.length;
		
		for(int i=2;i<=len-3;i++)
		{
			String[] ex = Array[i].split(":");
			//System.out.println(ex[0] + "=");
			
			String[] ex2 = ex[1].split(",");
			//System.out.println(ex2[0]);
			
			SetParameters(ex[0].replace("\"", " ").trim(), ex2[0].replace("\""," ").trim());
		}
	}
}