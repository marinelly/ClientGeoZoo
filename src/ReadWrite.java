
import javax.microedition.rms.RecordStore;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marinelly R
 */

public class ReadWrite {

        /* To open a record store*/
     RecordStore rs ;
    public void openRecStore(String recordStore_name)
   {
        try{
       rs =
          RecordStore.openRecordStore(recordStore_name,true);
            //rs.addRecordListener(new Lab2RecordListner());
        }
        catch(Exception e)
        {
            System.err.println(e.toString());
        }
    }

/* To close a record store*/
    public void closeRecStore()
    {
        try{
            rs.closeRecordStore();
        }
        catch(Exception e)
        {
            System.err.println(e.toString());

        }
    }

/* To add a new record to a record store*/
    public void writeRecord(String str)
    {
        byte[] rec = str.getBytes();
         try
        {
            rs.addRecord(rec, 0, rec.length);
        }
        catch (Exception e)
        {
            System.err.println(e.toString());

        }
    }


/* To return a copy of the data stored in the given record*/
public String readRecords(int i)
{
    byte[] recData= null;
    try
    {
        recData = rs.getRecord(i);
        return new String(recData);
    }
    catch (Exception e)
    {
        System.err.println(e.toString());
        return null;
    }
}

 /* To delete a record from the record store*/
 public void deleteRecord(int recId)
 {
     try{
         rs.deleteRecord(recId);
     }
     catch(Exception e)
     {
         System.err.println(e.toString());
 	    }
 	 }

 /* To update a record in the record store*/
 public void updateRecords(int recordindex, String str)
 {
    try
    {
        rs.setRecord(recordindex, str.getBytes(), 0, str.length());
    }
catch(Exception e)
  	  {
        System.err.println(e.toString());

   	 }
}

/* To delete the record store*/
public void deleteRecStore( String recordStore_name)
{
    if (RecordStore.listRecordStores() != null)
    {
        try
        {
             RecordStore.deleteRecordStore(recordStore_name);
        }
        catch (Exception e)
        {
            System.err.println(e.toString());

        }
    }
}

/* To check whether a record exists in the record store*/
public boolean isRecordExist(int i)
{
    byte[] recData=null;
    try{
        recData = rs.getRecord(i);
        if(recData!= null)
            return true;
        else
            return false;

    }catch (Exception e)
    {
        System.err.println(e.toString());

         return false;
    }
}

}
