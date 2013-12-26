import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Year extends Box implements ActionListener
{
	//Box为使用 BoxLayout 对象作为其布局管理器的一个轻量级容器
	int year;                           
	JTextField showYear=null;           
	JButton nextYear,preYear;
	CalendarPad calendar;
  public Year(CalendarPad calendar)
  {  
     super(BoxLayout.X_AXIS);  //设置从左到右水平布置组件。
     showYear=new JTextField(4);//类的实例化。
     showYear.setForeground(Color.blue);//设置前景色的颜色为蓝色。
     showYear.setFont(new Font("TimesRomn",Font.BOLD,14)); //设置字体相关属性。
     this.calendar=calendar;
     year=calendar.getYear();//调用CalendarPad类的getYear()方法。
     nextYear=new JButton("nextYear");//初始化。
     preYear=new JButton("preYear");
     add(preYear);//放置按钮　。
     add(showYear);
     add(nextYear);
     showYear.addActionListener(this);
     preYear.addActionListener(this);//按钮注册。
     nextYear.addActionListener(this);
  }
 public void setYear(int year)
  {
    this.year=year;
    showYear.setText(""+year);//设置showYear的内容。
  }
 public int getYear()
  {
    return year;
  } 
 public void actionPerformed(ActionEvent e)
  {
	 //try
	calendar.clearLine();
	//endTry
    if(e.getSource()==preYear)
      {
    	//处理此按钮的代码。
        year=year-1;
        showYear.setText(""+year);
        calendar.setYear(year);//调用方法。
        calendar.setCalendarPad(year,calendar.getMonth());
        //调用setCalendarPad()方法，重画日历板。
      }
    else if(e.getSource()==nextYear)
      {
        year=year+1;
        showYear.setText(""+year);
        calendar.setYear(year);
        calendar.setCalendarPad(year,calendar.getMonth());
      }
    else if(e.getSource()==showYear)
      {
         try
            {
              year=Integer.parseInt(showYear.getText());
              showYear.setText(""+year);
              calendar.setYear(year);
              calendar.setCalendarPad(year,calendar.getMonth());
            }
         catch(NumberFormatException ee)
            {
              showYear.setText(""+year);
              calendar.setYear(year);
              calendar.setCalendarPad(year,calendar.getMonth());
            }
      }
  }   
}
