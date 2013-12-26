import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.*;

public class NotePad extends JPanel implements ActionListener
{
  JTextArea text;              
  JButton saveNote,deleteNote;
  Hashtable table;             
  JLabel articleInf;               
  int year,month,day;          
  File file;                   
  CalendarPad calendar;
 
  public  NotePad(CalendarPad calendar)
   {
     this.calendar=calendar;
     year=calendar.getYear();
     month=calendar.getMonth();
     day=calendar.getDay();
     table=calendar.getHashtable();
     file=calendar.getFile();
     articleInf=new JLabel(changeMonth(month)+" "+day+"th  "+year,JLabel.CENTER);
     articleInf.setFont(new Font("TimesRoman",Font.BOLD,16));
     articleInf.setForeground(Color.blue);
     text=new JTextArea(10,10);
     saveNote=new JButton("saveNote") ;
     deleteNote=new JButton("deleteNote") ;
     saveNote.addActionListener(this);
     deleteNote.addActionListener(this);
     setLayout(new BorderLayout());
     JPanel pSouth=new JPanel();       
     add(articleInf,BorderLayout.NORTH);
     pSouth.add(saveNote);
     pSouth.add(deleteNote);
     add(pSouth,BorderLayout.SOUTH);
     add(new JScrollPane(text),BorderLayout.CENTER);
   }
 public void actionPerformed(ActionEvent e)
   {
     if(e.getSource()==saveNote)
      {
        saveNote(year,month,day);
      }
     else if(e.getSource()==deleteNote)
      {
       deleteNote(year,month,day);
      }
   }
  public void setYear(int year)
  {
    this.year=year;
  }
 public int getYear()
  {
    return year;
  }
 public void setMonth(int month)
  {
    this.month=month;
  } 
  public int getMonth()
  {
    return month;
  } 
  public void setDay(int day)
  {
    this.day=day;
  }
  public int getDay()
   {
    return day;
   }
 public void setArticleInf(int year,int month,int day)
   {
    
     articleInf.setText(changeMonth(month)+" "+day+"th"+"  "+year);
   }
  public void setTextField(String s)
   {
     text.setText(s);
   }
  public void getNoteInf(int year,int month,int day)
   {
       String key=""+year+""+month+""+day;
       try
             {
              FileInputStream   inOne=new FileInputStream(file);
              ObjectInputStream inTwo=new ObjectInputStream(inOne);
              table=(Hashtable)inTwo.readObject();         
              inOne.close();
              inTwo.close();
             }
       catch(Exception ee)
             {
             }
       if(table.containsKey(key))
          {
    	    String m=" This day have note! see?";
            int ok=JOptionPane.showConfirmDialog(this,m,"Query",JOptionPane.YES_NO_OPTION,
                                               JOptionPane.QUESTION_MESSAGE);
                if(ok==JOptionPane.YES_OPTION)
                  {
                    text.setText((String)table.get(key));
                  }
                else
                  {
                   text.setText(""); 
                  }
          } 
       else
          {
            text.setText("no note!");
          } 
   }
  public void saveNote(int year,int month,int day)
   {
     String noteContext=text.getText();
        String key=""+year+""+month+""+day;
        
        String m="Save note?";
        int ok=JOptionPane.showConfirmDialog(this,m,"Query",JOptionPane.YES_NO_OPTION,
                                               JOptionPane.QUESTION_MESSAGE);
        if(ok==JOptionPane.YES_OPTION)
          {
           try
             {
              FileInputStream   inOne=new FileInputStream(file);
              ObjectInputStream inTwo=new ObjectInputStream(inOne);
              table=(Hashtable)inTwo.readObject();
              inOne.close();
              inTwo.close();
              table.put(key,noteContext);
              FileOutputStream out=new FileOutputStream(file);
              ObjectOutputStream objectOut=new ObjectOutputStream(out);
              objectOut.writeObject(table);
              objectOut.close();
              out.close();
             }
           catch(Exception ee)
             {
             }
         }
   }
  public void deleteNote(int year,int month,int day)
   {
      String key=""+year+""+month+""+day;
         if(table.containsKey(key))
          {  
        	  String m="Delete this note?";
            int ok=JOptionPane.showConfirmDialog(this,m,"Query",JOptionPane.YES_NO_OPTION,
                                               JOptionPane.QUESTION_MESSAGE);
            if(ok==JOptionPane.YES_OPTION)
              { 
              try
                {
                 FileInputStream   inOne=new FileInputStream(file);
                 ObjectInputStream inTwo=new ObjectInputStream(inOne);
                 table=(Hashtable)inTwo.readObject();
                 inOne.close();
                 inTwo.close();
                 table.remove(key);                                        
                 FileOutputStream out=new FileOutputStream(file);
                 ObjectOutputStream objectOut=new ObjectOutputStream(out);
                 objectOut.writeObject(table);
                 objectOut.close();
                 out.close();
                 text.setText(null);
                }
               catch(Exception ee)
                {
                }
              }
          }
        else
          {
            
        	String m="There is no note!";
            JOptionPane.showMessageDialog(this,m,"Query",JOptionPane.WARNING_MESSAGE);
          }
   }
  public String changeMonth(int month)
  {
     String[] monthStr={"January","February","March","April","May","June","July","August","September","October","November","December"};
     String strMonth="";
     switch(month)
     {
            case 1:
                 strMonth=monthStr[0];
                 break;
            case 2:
                 strMonth=monthStr[1];
                 break;
            case 3:
                 strMonth=monthStr[2];
                 break;
            case 4:
                 strMonth=monthStr[3];
                 break;
            case 5:
                 strMonth=monthStr[4];
                 break;
            case 6:
                 strMonth=monthStr[5];
                 break;
             case 7:
                 strMonth=monthStr[6];
                 break;
             case 8:
                 strMonth=monthStr[7];
                 break;
             case 9:
                 strMonth=monthStr[8];
                 break;
             case 10:
                 strMonth=monthStr[9];
                 break;
            case 11:
                 strMonth=monthStr[10];
                 break;
             case 12:
                 strMonth=monthStr[11];
                 break;
     }
      return strMonth;
  }

}
