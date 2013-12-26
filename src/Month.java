import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Month extends Box implements ActionListener
{
  int month;                           
  JTextField showMonth=null;           
  JButton nextMonth,preMonth;
  CalendarPad calendar;
  public Month(CalendarPad calendar)
  {  
     super(BoxLayout.X_AXIS);//Place from left to right.        
     this.calendar=calendar;
     showMonth=new JTextField(3);
     month=calendar.getMonth();
     showMonth.setEditable(false);//set can not edit.
     showMonth.setForeground(Color.blue);//Set the foreground color to blue.
     showMonth.setFont(new Font("TimesRomn",Font.BOLD,16));//set the font.
     nextMonth=new JButton("nextMonth");
     preMonth=new JButton("preMonth");
     add(preMonth);
     add(showMonth);
     add(nextMonth);
     preMonth.addActionListener(this);
     nextMonth.addActionListener(this);
     showMonth.setText(""+month);
  }
 public void setMonth(int month)
  {
    if(month<=12&&month>=1)
      {
       this.month=month;
      }
    else
      {
        this.month=1;
      }
    showMonth.setText(""+month);
  }
 public int getMonth()
  {
    return month;
  } 
 public void actionPerformed(ActionEvent e)
  {
	 //try
	calendar.clearLine();
	//endTry
    if(e.getSource()==preMonth)
      {
        if(month>=2)
         {
            month=month-1;
            calendar.setMonth(month);
            calendar.setCalendarPad(calendar.getYear(),month);
         }
        else if(month==1)
         {
            month=12;
            calendar.setMonth(month);
            calendar.setCalendarPad(calendar.getYear(),month);
         }
        showMonth.setText(""+month);
      }
    else if(e.getSource()==nextMonth)
      {
        if(month<12)
          {
            month=month+1;
            calendar.setMonth(month);
            calendar.setCalendarPad(calendar.getYear(),month);
          }
        else if(month==12)
          {
            month=1;
            calendar.setMonth(month);
            calendar.setCalendarPad(calendar.getYear(),month);
          }
         showMonth.setText(""+month);
      }
    
}
}
