/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 *
 * @author Anspro
 */
public class DBFormat {
    
    public String INSERT(String[] arr,String table_name)
    {
        
        String data = "";
        for(int i=0;i<arr.length;i++)
        {
            String tmp = "'"+arr[i]+"'";
            data+=tmp+",";
        }
        data = data.substring(0, data.lastIndexOf(","));
        String query = "insert into "+table_name+" values("+data+")";
        return query;
    }
   
    public String DELETE(String[] data,String[] column_name,String table_name,String delete_type)
    {
        String query = "";
        if(delete_type.equalsIgnoreCase("without condition"))
        {
            query = "Delete * from "+table_name;
        }
        else if(delete_type.equalsIgnoreCase("with condition"))
        {
        String q1 = "";
        if(data.length==1 && column_name.length==1)
        {
           q1 = column_name[0]+"='"+data[0]+"'";
        }
        else
        {
            for(int i=0;i<data.length;i++)
            {
                q1+=column_name[i]+"='"+data[i]+"' AND";
            }
            q1 = q1.substring(0, q1.lastIndexOf("AND"));
        }
        
         query="DELETE FROM "+table_name+" where "+q1;
        }
        return query;
    }
   
    public String SELECT(String[] data,String[] column_name,String table_name,String select_type)
    {
        String query = "";
        if(select_type.equalsIgnoreCase("without condition"))
        {
            query = "select * from "+table_name;
        }
        else if(select_type.equalsIgnoreCase("with condition"))
        {
        String q1 = "";
        if(data.length==1 && column_name.length==1)
        {
          q1 = column_name[0]+"='"+data[0]+"'";  
        }
        else
        {
            for(int i=0;i<data.length;i++)
            {
                q1+=column_name[i]+"='"+data[i]+"' AND ";
            }
            q1 = q1.substring(0, q1.lastIndexOf("AND "));
        }
         query = "SELECT * from "+table_name+" where "+q1;
        }
        return query;
    }
    
    public String SELECT_WITH_LIKE(String[] data,String[] column_name,String table_name){
    	String query = "";
    	String q1 = "";
    	if(column_name.length == 1 && data.length == 1){
    		q1 = column_name[0]+" LIKE '%"+data[0]+"%'";
    	}
    	else{
    		for(int i=0;i<data.length;i++)
    		{
    			q1+=column_name[i]+" LIKE '%"+data[i]+"%' AND ";
    		}
    		q1 = q1.substring(0, q1.lastIndexOf("AND"));
    	}
    	query = "SELECT * FROM "+table_name+" where "+q1;
    	return query;
    }
    
    public String UPDATE(String[] data,String[] column_name,String[] condition_data,String[] condition_column,String table_name)
    {
        String q1 = "",temp="",temp1="";
    if(condition_column.length==1 && condition_data.length==1)
     {
        if(data.length==1 && column_name.length ==1)
        {
            q1 = column_name[0]+"='"+data[0]+"' where "+condition_column[0]+"='"+condition_data[0]+"'";
        }
        else if(data.length!=1 && column_name.length!=1)
        {
            for(int i=0;i<data.length;i++)
            {
                temp+=column_name[i]+"='"+data[i]+",";
            }
            temp = temp.substring(0, temp.lastIndexOf(","));
            q1 = temp+"where "+condition_column[0]+"='"+condition_data[0]+"'";
        }
     }
    
    else if(condition_column.length!=1 && condition_data.length!=1)
     {
              if(data.length==1 && column_name.length ==1)
               {
                  temp = column_name[0]+"='"+data[0]+"' where "+condition_column[0]+"='"+condition_data[0]+"'";
               }
              else if(data.length!=1 && column_name.length!=1)
               {
                  for(int i=0;i<data.length;i++)
                  {
                      temp+=column_name[i]+"='"+data[i]+",";
                  }
               }
            for(int i=0;i<condition_column.length;i++)
             {
                 temp1+=condition_column[0]+"='"+condition_data[0]+"' AND ";
             }
              temp1 = temp1.substring(0, temp1.lastIndexOf("AND "));
              
              q1 = temp+" where "+temp1;
       }
    String query = "UPDATE "+table_name+" SET "+q1;
    
    return query;
    }
    
    public String ALTER(String table_name,String[] column_details,String alter_type)
    {
        String query = "ALTER TABLE "+table_name;
      if(alter_type.equalsIgnoreCase("ADD"))
      {
          query = query+" ADD COLUMN "+column_details[0]+" "+column_details[1]+"("+column_details[2]+")";
      }
      else if(alter_type.equalsIgnoreCase("DROP") || alter_type.equalsIgnoreCase("DELETE"))
      {
          query = query+" DROP COLUMN "+column_details[0];
      }
        return query;
    }
    public String getData() throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException{
    	String className = "com.scorepredictor.dummy";
    	Class<?> c = Class.forName(className);
    	// Alternatively, you can load the new class with a new Classloader, if you don't want to pollute the current Classloader 
        // Class<?> clazz = new URLClassLoader(new URL[]{getClass().getClassLoader().getResource("").toURI().toURL()}, getClass().getClassLoader()).loadClass(className); 
        Method main = c.getDeclaredMethod("main", String[].class);
        try ( ByteArrayOutputStream out = new ByteArrayOutputStream();
              PrintStream ps = new PrintStream(out)) {
            System.setOut(ps);
            main.invoke(main, new Object[]{new String[0]});
            return out.toString();
        } 
        finally { 
            // Reset to the console 
//            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        } 
    }
    
}
