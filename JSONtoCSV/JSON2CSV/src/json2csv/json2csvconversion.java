package json2csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.csvreader.CsvWriter;
import au.com.bytecode.opencsv.CSVWriter;

public class json2csvconversion {

	
	public static boolean regexstr (String S) 
	{
		String t = "[a-zA-Z0-9_#$,.:!;<>'=-?^`~/*@%&(){}+\" ]*";
		
		if(S.matches(t))
		{
			return true;
		}
		else
		{
		return false;
		}
	}
	
	public static boolean regextimezone (String S) 
	{
		String t1 = "[a-zA-Z,&/_. ()-]*";
		
		if(S.matches(t1))
		{
			return true;
		}
		else
		{
		return false;
		}
	}
	
	public static void main(String[] args) {
		 
        try {
        	
        	String csv = "/home/biadmin/Desktop/PB2-2days/csv_output/output9.csv";
			
        	File file = new File(csv);  
            if ( !file.exists() )
                file.createNewFile();

           
            CSVWriter wr = new CSVWriter(new FileWriter(csv, true));        		    
        	String input = "/home/biadmin/Desktop/PB2-2days/json_input/android8.json";
        	FileReader fr = new FileReader(input);
			BufferedReader br = new BufferedReader(fr);
			
			FileReader fr2 = new FileReader(input);
			LineNumberReader lnr = new LineNumberReader(fr2);
			 
		   int totallines = 0;

	            while (lnr.readLine() != null){
	        	totallines++;
	            }
			System.out.println(totallines);
			String line;
			
			int i=0;
			int j=0;
			
			while (i < totallines) {
				
			a:	
				line=br.readLine();
			
				if (line.length() != 0) 
				{
					j++;
					System.out.println(j+" of "+totallines+"\n");
					
					JSONParser jp = new JSONParser();
					JSONObject jo = (JSONObject) jp.parse(line.toString().trim());
					
					
					String tweet1;
					String tweet2;
					String tweet3;
					String tweet4;
					String tweet5;
					String tweet6;
					
					String tweetline;
					
					if (jo.get("created_at") != null && jo.get("id") != null && jo.get("lang") != null && jo.get("timestamp_ms") != null ) 
					{
						String text = (String) jo.get("text");
						
						if (regexstr(text))
						{
						
						String created_at = (String) jo.get("created_at");
						long id = (long) jo.get("id");
						String id_str = (String) jo.get("id_str");
																
						tweet1 = created_at.toString()+"<#>"+id+"<#>"+id_str.toString()+"<#>"+text.toString();
					
						JSONObject user = (JSONObject) jo.get("user");
						
						if(user.get("id") != null && user.get("location") != null && user.get("time_zone") != null && user.get("utc_offset") != null && user.get("name") != null)
						{
							String user_name = (String) user.get("name");
							String user_time_zone = (String) user.get("time_zone");
							String user_location = (String) user.get("location");
							
							if (regexstr(user_name) && regextimezone (user_time_zone) && regextimezone (user_location) && user_location.toString().trim().length() != 0)
							{
								long user_id =  (long) user.get("id");
								String user_id_str = (String) user.get("id_str");
								String user_screen_name = (String) user.get("screen_name");
								
								boolean user_protected = (boolean) user.get("protected");
								boolean user_verified = (boolean) user.get("verified");
								long user_followers_count = (long) user.get("followers_count");
								long user_friends_count = (long) user.get("friends_count");
								long user_listed_count = (long) user.get("listed_count");
								long user_favourites_count = (long) user.get("favourites_count");
								long user_statuses_count = (long) user.get("statuses_count");
								String user_created_at = (String) user.get("created_at");
								long user_utc_offset = (long) user.get("utc_offset");
								boolean user_geo_enabled = (boolean) user.get("geo_enabled");
								String user_lang = (String) user.get("lang");
								boolean user_contributors_enabled = (boolean) user.get("contributors_enabled");
								boolean user_is_translator = (boolean) user.get("is_translator");
								
								tweet2 = user_id+"<#>"+user_id_str.toString().trim()+"<#>"+user_name.toString()+"<#>"+user_screen_name.toString()+"<#>"+user_location.toString()+"<#>"+user_protected+"<#>"+user_verified+"<#>"+user_followers_count+"<#>"+
				    					user_friends_count+"<#>"+user_listed_count+"<#>"+user_favourites_count+"<#>"+user_statuses_count+"<#>"+user_created_at.toString()+"<#>"+user_utc_offset+"<#>"+user_time_zone+"<#>"+user_geo_enabled+"<#>"+user_lang+"<#>"+user_contributors_enabled+"<#>"+user_is_translator;
	
									JSONObject geo = (JSONObject) jo.get("geo");								
									if(geo != null){
									JSONArray geo_coord= (JSONArray) geo.get("coordinates");
									tweet3 = //geo_type.toString()
											geo.get("type")+"<#>"+geo_coord.get(0)+"<#>"+geo_coord.get(1);
									}
									else
									{
									tweet3 = null+"<#>"+null+"<#>"+null;
									}
									
									
									
								//if (jo.get("coordinates") != null) {
									JSONObject coordy = (JSONObject) jo.get("coordinates");
										
									//String coordy_type = (String) coordy.get("type");
											
									if (coordy != null) {
									JSONArray cordy_coord= (JSONArray) coordy.get("coordinates");
											
									tweet4 = //coordy_type.toString()+
											coordy.get("type")+"<#>"+cordy_coord.get(0)+"<#>"+cordy_coord.get(1);
									}
									else
									{
										tweet4 = null+"<#>"+null+"<#>"+null;
									}
								//if (jo.get("place") != null) {
											
									JSONObject place = (JSONObject) jo.get("place");		
								if (place != null) {
									
									tweet5 = //place_id+"<#>"+place_type+"<#>"+place_name+"<#>"+place_ctry_code+"<#>"+place_country+"<#>"+place_full_name;
											place.get("id")+"<#>"+place.get("place_type")+"<#>"+place.get("name")+"<#>"+place.get("country_code")+"<#>"+place.get("country")+"<#>"+place.get("full_name");
								}
								else
								{
									tweet5 = null+"<#>"+null+"<#>"+null+"<#>"+null+"<#>"+null+"<#>"+null;
								}
								tweet6 = jo.get("retweet_count")+"<#>"+jo.get("favorite_count")+"<#>"+jo.get("favorited")+"<#>"+jo.get("retweeted")+"<#>"+
										jo.get("possibly_sensitive")+"<#>"+jo.get("filter_level")+"<#>"+jo.get("lang")+"<#>"+jo.get("timestamp_ms");
													
												
						tweetline = tweet1+"<#>"+tweet2+"<#>"+tweet3+"<#>"+tweet4+"<#>"+tweet5+"<#>"+tweet6;
					
						String[] fh = tweetline.split("<#>");
						wr.writeNext(fh);
						}
						}
						}
						}				
								
				i++;
				}
				else 
				{
					i++;
				}
			}

			br.close();
			System.out.println("End of File Reached closing file conversion");
			wr.close();
			
            } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        } catch (ParseException e) {
			//e.printStackTrace();
        	System.out.println("End of File Reached closing file conversion");
		}
 

	}
}