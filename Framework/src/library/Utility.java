package library;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utility 
{
	public String timeStamp()
	{
		return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	}

}
