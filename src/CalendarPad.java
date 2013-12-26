import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Timer;


import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Hashtable;

public class CalendarPad extends JFrame implements MouseListener
{
   int year,month,day;
   Hashtable hashtable;             
   File file;                       
   JTextField showDay[];  
   //try
   JTextField source;
   JTextField preSource=null;
   //endTry
   JLabel title[];                   
   Calendar calendar;
   int theWeek; 
   NotePad notepad=null;             
   Month alterMonth;//change the month
   Year  alterYear;//change the year
   String week[]={"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
   JPanel leftPanel,rightPanel;  
  
   public  CalendarPad(int year,int month,int day)
   { 
	 //构造方法。
     leftPanel=new JPanel();
     JPanel leftCenter=new JPanel();
     JPanel leftNorth=new JPanel();
     leftCenter.setLayout(new GridLayout(7,7));   
     rightPanel=new JPanel();
     
     this.year=year;
     this.month=month;
     this.day=day;
     
     alterYear=new Year(this);//创建Year类对象alterYear。
     alterYear.setYear(year); //调用setYear(int year)方法。
     alterMonth=new Month(this);
     alterMonth.setMonth(month);
  
     title=new JLabel[7];                         
     showDay=new JTextField[42];                   
     for(int j=0;j<7;j++)                         
       {
    	 //week set
         title[j]=new JLabel();
         title[j].setText(week[j]);
         //创建一个具有凸出斜面边缘的边框，
         //将组件当前背景色的较亮的色度用于高亮显示，较暗的色度用于阴影。
         title[j].setBorder(BorderFactory.createRaisedBevelBorder());
         leftCenter.add(title[j]);
       } 
     title[0].setForeground(Color.red);//set the foreground color to red.
     title[6].setForeground(Color.blue);

     for(int i=0;i<42;i++)                        
       {
    	 //day set
         showDay[i]=new JTextField();
         showDay[i].addMouseListener(this);
         showDay[i].setEditable(false);//set can't edit.
         showDay[i].setBorder(BorderFactory.createLineBorder(Color.white));
         leftCenter.add(showDay[i]);
         
       }
         
     calendar=Calendar.getInstance();
     Box box=Box.createHorizontalBox();//create a from left to right Display its components         
     box.add(alterYear);
     box.add(alterMonth);
     leftNorth.add(box);
     leftPanel.setLayout(new BorderLayout());//set the leftPanel's layout to "BordarLayout".
     leftPanel.add(leftNorth,BorderLayout.NORTH);
     leftPanel.add(leftCenter,BorderLayout.CENTER);
     leftPanel.add(new JLabel("^-^ 当前日期  ^-^ "),
                  BorderLayout.SOUTH) ;
     leftPanel.validate();
     Container con=getContentPane();
     JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                     leftPanel,rightPanel);
     
     con.add(split,BorderLayout.CENTER);
     con.validate();
    
     hashtable=new Hashtable();
     file=new File("noteText.txt");
      if(!file.exists())
      {
       try{
           FileOutputStream out=new FileOutputStream(file);
           ObjectOutputStream objectOut=new ObjectOutputStream(out);
           objectOut.writeObject(hashtable);
           objectOut.close();
           out.close();
          }
       catch(IOException e)
          {
          }
      } 
    
     notepad=new NotePad(this);                                      
     rightPanel.add(notepad);
     
     setCalendarPad(year,month);
     addWindowListener(new WindowAdapter()
                    { public void windowClosing(WindowEvent e)
                       {
                         System.exit(0);
                       }
                    });
     setVisible(true);
     setBounds(300,200,700,285);//set the window's size and location.
   }
  public void setCalendarPad(int year,int month)
   {
     calendar.set(year,month-1,1);//I don't know.              
     notepad.setArticleInf(year, month,day);
     theWeek=calendar.get(Calendar.DAY_OF_WEEK)-1;
     if(month==1||month==3||month==5||month==7
                        ||month==8||month==10||month==12)
        {
            rankNumber(theWeek,31);         
        }
     else if(month==4||month==6||month==9||month==11)
        {
            rankNumber(theWeek,30);
        }
     else if(month==2)
        {
         if((year%4==0&&year%100!=0)||(year%400==0))  
           {
             rankNumber(theWeek,29);
           }
         else
           {
             rankNumber(theWeek,28);
           }
       }
   } 
  public void rankNumber(int theWeek,int numMonth)
   {
      for(int i=theWeek,n=1;i<theWeek+numMonth;i++)
             {
               showDay[i].setText(""+n);
               if(n==day)
                 {
                   showDay[i].setForeground(Color.green); 
                   showDay[i].setFont(new Font("TimesRoman",Font.BOLD,20));
                   
                 }
               else
                 { 
                  showDay[i].setFont(new Font("TimesRoman",Font.BOLD,12));
                  showDay[i].setForeground(Color.black);
                  
                 }
               if(i%7==6)
                 {
                  showDay[i].setForeground(Color.blue);  
                  
                 }
               if(i%7==0)
                 {
                  showDay[i].setForeground(Color.red);  
                 }
               n++; 
             }
       for(int i=0;i<theWeek;i++)
             {
                showDay[i].setText("");
             }
       for(int i=theWeek+numMonth;i<42;i++)
             {
                showDay[i].setText("");
             }
   }
 public int getYear()
   {
    return year;
   } 
 public void setYear(int y)
   {
     year=y;
     notepad.setYear(year);
   }
 public int getMonth()
   {
    return month;
   }
 public void setMonth(int m)
   {
     month=m;
     notepad.setMonth(month); 
   }
 public int getDay()
   {
    return day;
   }
 public void setDay(int d)
   {
    day=d;
    notepad.setDay(day);
   }
 public Hashtable getHashtable()
   {
     return hashtable;
   }
 public File getFile()
   {
     return file;
   }
 //try
 public boolean booDay()
 {
	 int i;
	 boolean boo;
	 i=Integer.parseInt(source.getText());
	 if(i>=0&&i<=31)
	 {
		 boo=true;
	 }
	 else
		 boo=false;
	 return boo;
 }
 public void paintSelectDayLine()
 {
	 if(booDay()==true)
	 {
	     source.setBorder(BorderFactory.createLineBorder(Color.blue));
	     source.setFont(new Font("TimesRoman",Font.BOLD,20));
	 }
 }
 public void clearLine()
 {
	 if(preSource!=null)
	 {
		 Calendar calend=Calendar.getInstance();
		 preSource.setBorder(BorderFactory.createLineBorder(Color.white));
		 if(calend.get(Calendar.DAY_OF_MONTH)!=Integer.parseInt(preSource.getText()))
			 preSource.setFont(new Font("TimesRoman",Font.BOLD,12));
	 }
 }
  //endTry
 public void mousePressed(MouseEvent e)
   {
	 
	 try{
		 source=(JTextField)e.getSource();
		 
		 //try
		 clearLine();
		 paintSelectDayLine();
		 preSource=source;
		 //entTry
		 
    	 day=Integer.parseInt(source.getText());
    	 notepad.setDay(day);
         notepad.setArticleInf(year,month,day);
         notepad.setTextField(null);
         notepad.getNoteInf(year,month,day);
       
		 
        } 
      catch(Exception ee)
        {
        }
      
      }
 public void mouseClicked(MouseEvent e)
   {
   }
 public void mouseReleased(MouseEvent e)
   {
   }
 public void mouseEntered(MouseEvent e)
   {
   }
 public void mouseExited(MouseEvent e)
   {
   }
  public static void main(String args[])
   {
       Calendar calendar=Calendar.getInstance();    
       int y=calendar.get(Calendar.YEAR);           
       int m=calendar.get(Calendar.MONTH)+1;        
       int d=calendar.get(Calendar.DAY_OF_MONTH);
       new CalendarPad(y,m,d);
   }
}  
