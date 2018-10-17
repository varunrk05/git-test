import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

class test1
{
    JRadioButton b[] = new JRadioButton [4];
    JPanel jp,jp1;
    JLabel jl1,jl2;
    ButtonGroup bg;

    test1(int x,int y,String s,String s1[])
    {
        try
        {
            makeQues(x,y,s,s1);
        }
        catch(Exception e)
        {System.out.println(e);}
    }

    void makeQues(int x,int y,String s,String s1[])
    {
        jp1 = new JPanel(new FlowLayout());
        jp= new JPanel(new FlowLayout(0,10,0));

        jl1 = new JLabel(s);
        jl2 = new JLabel("Unattempted Question");

        b[0] = new JRadioButton("A: "+s1[0]);
        b[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jl2.setText("You Selected: A");
            }
        });

        b[1] = new JRadioButton("B: "+s1[1]);
        b[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jl2.setText("You Selected: B");
            }
        });

        b[2] = new JRadioButton("C: "+s1[2]);
        b[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jl2.setText("You Selected: C");
            }
        });

        b[3] = new JRadioButton("D: "+s1[3]);
        b[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jl2.setText("You Selected: D");
            }
        });

        bg = new ButtonGroup();
        bg.add(b[0]);
        bg.add(b[1]);
        bg.add(b[2]);
        bg.add(b[3]);

        jp1.setBounds(x,y,50,10);
        jp.setBounds(x,y+10,100,10);

        jp1.add(jl1);
        jp.add(b[0]);
        jp.add(b[1]);
        jp.add(b[2]);
        jp.add(b[3]);
        jp.add(jl2);
    }

    
}

public class a extends JFrame
{
  	JLabel correct=new JLabel("");
  	JLabel wrong=new JLabel("");
  	JLabel Unattempted=new JLabel("");
  	JLabel k=new JLabel("                                                                                                                    ");
    String finalString;
    String correctString;
    char a1,a2,a3,a4,a5;
    int score=0;
    int U=0;
    test1 ob1,ob2,ob3,ob4,ob5;
    a()
    {
        try {
            makeGUI();
        }
        catch (Exception exc)
        {
            System.out.println("Can't create because of " + exc);
        }
    }


    void makeGUI()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550,500);
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.LEFT,0,10));
        //setLayout(new GridLayout(15,8));

        String s1[] = {"1938","1947","1953","1942"};
        String s2[]= {"1720","1718","1729","1734"};
        String s3[]= {"Bangalore","Delhi","Mumbai","Chennai"};
        String s4[]= {"June","March","April","July"};
        String s5[]= {"Brisbane","Sydney","Melbourne","Canberra"};
        JButton jb = new JButton("Submit answers");
        ob1 = new test1(30,30,"In which year did India achieve independence?",s1);
        ob2 = new test1(30,50,"Which among the following is the Ramanujan-Hardy number?",s2);
        ob3 = new test1(30,50,"Which city is known as the silicon valley of India?",s3);
        ob4 = new test1(30,50,"Which month was brexit announced?",s4);
        ob5 = new test1(30,50,"What is the capital of Australia?",s5);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jb.setEnabled(false);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(ob1.jl2.getText());
                stringBuilder.append("\n");
                stringBuilder.append(ob2.jl2.getText());
                stringBuilder.append("\n");
                stringBuilder.append(ob3.jl2.getText());
                stringBuilder.append("\n");
                stringBuilder.append(ob4.jl2.getText());
                stringBuilder.append("\n");
                stringBuilder.append(ob5.jl2.getText());
                finalString = stringBuilder.toString();
                //System.out.println(finalString);
                save();
                print();

                //perform actions
            }
        });

        add(ob1.jp1);
        add(ob1.jp);
        add(ob2.jp1);
        add(ob2.jp);
        add(ob3.jp1);
        add(ob3.jp);
        add(ob4.jp1);
        add(ob4.jp);
        add(ob5.jp1);
        add(ob5.jp);
        add(jb);
        setVisible(true);
        add(k);
        add(correct);
        add(wrong);
        add(Unattempted);

    }

    public void save()
    {
      SwingWorker<Integer,Void> worker=new SwingWorker<Integer,Void>(){
          protected Integer doInBackground()
          {
            try(FileWriter fout=new FileWriter("Student.txt"))
              {
                fout.write(finalString);
              }catch(Exception e){
                  return -1;
              }

          return 0;
          }
          protected void done()
          {
          }
      };
      worker.execute();
  }

  public void print()
  {
    SwingWorker<Integer,Void> printer=new SwingWorker<Integer,Void>(){
        protected Integer doInBackground()
        {
          try(BufferedReader fin=new BufferedReader(new FileReader("Answers.txt")))
            {
              while((correctString=fin.readLine())!=null)
              {
              //  System.out.println(correctString);
                a1=correctString.charAt(0);
                a2=correctString.charAt(1);
                a3=correctString.charAt(2);
                a4=correctString.charAt(3);
                a5=correctString.charAt(4);
              }
            }catch(Exception e){
                return -1;
            }
          //  System.out.println(a1+"->a1");
          //  System.out.println(a2+"->a2");
          //  System.out.println(a3+"->a3");
          //  System.out.println(a4+"->a4");
          //  System.out.println(a5+"->a5");
            CalculateScore();
        return 0;
        }
        protected void done()
        {
        }
    };
    printer.execute();
}
    public void CalculateScore()
    {
      if(ob1.jl2.getText().charAt(14)==a1)
        score++;
      else if(ob1.jl2.getText().charAt(0)=='U')
        U++;
      if(ob2.jl2.getText().charAt(14)==a2)
        score++;
      else if(ob2.jl2.getText().charAt(0)=='U')
          U++;
      if(ob3.jl2.getText().charAt(14)==a3)
        score++;
      else if(ob3.jl2.getText().charAt(0)=='U')
          U++;
      if(ob4.jl2.getText().charAt(14)==a4)
        score++;
      else if(ob4.jl2.getText().charAt(0)=='U')
          U++;
      if(ob5.jl2.getText().charAt(14)==a5)
        score++;
      else if(ob5.jl2.getText().charAt(0)=='U')
          U++;
      //System.out.println(score);
      correct.setText("Correct Responses: "+score+"  ");
      int a=5-score-U;
      wrong.setText("Wrong Responses: "+a);
      Unattempted.setText("   Unattempted questions: "+U);
    }
    public static void main(String[] args)
    {
        try {
            SwingUtilities.invokeAndWait(
                    new Runnable() {
                        public void run() {
                            new a();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

}
