import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Year extends Box implements ActionListener
{
	//BoxΪʹ�� BoxLayout ������Ϊ�䲼�ֹ�������һ������������
	int year;                           
	JTextField showYear=null;           
	JButton nextYear,preYear;
	CalendarPad calendar;
  public Year(CalendarPad calendar)
  {  
     super(BoxLayout.X_AXIS);  //���ô�����ˮƽ���������
     showYear=new JTextField(4);//���ʵ������
     showYear.setForeground(Color.blue);//����ǰ��ɫ����ɫΪ��ɫ��
     showYear.setFont(new Font("TimesRomn",Font.BOLD,14)); //��������������ԡ�
     this.calendar=calendar;
     year=calendar.getYear();//����CalendarPad���getYear()������
     nextYear=new JButton("nextYear");//��ʼ����
     preYear=new JButton("preYear");
     add(preYear);//���ð�ť����
     add(showYear);
     add(nextYear);
     showYear.addActionListener(this);
     preYear.addActionListener(this);//��ťע�ᡣ
     nextYear.addActionListener(this);
  }
 public void setYear(int year)
  {
    this.year=year;
    showYear.setText(""+year);//����showYear�����ݡ�
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
    	//����˰�ť�Ĵ��롣
        year=year-1;
        showYear.setText(""+year);
        calendar.setYear(year);//���÷�����
        calendar.setCalendarPad(year,calendar.getMonth());
        //����setCalendarPad()�������ػ������塣
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
