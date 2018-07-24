package com.humanMind.test;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CanvasTest extends JFrame implements ActionListener{
private JPanel jp1, jp2;
private JButton btn1, btn2, btn3,btn4;
private JTextField t1;
private Canvas can;
private int x, y, size;
private Color color;

public void Exam_Pic(){
jp1 = new JPanel();
jp2 = new JPanel(new GridLayout(5, 2));

jp2.add(t1 = new JTextField(10));
jp2.add(btn1 = new JButton("사이즈변경"));
jp2.add(btn2 = new JButton("RED"));
jp2.add(btn3 = new JButton("BLUE"));
jp2.add(btn4 = new JButton("YELLOW"));

btn2.setBackground(Color.red);
btn3.setBackground(Color.BLUE);
btn4.setBackground(Color.YELLOW);

btn1.addActionListener(new ActionListener() {

@Override
public void actionPerformed(ActionEvent e) {
Object obj1 = e.getSource();
size = Integer.parseInt(t1.getText());
}
});
btn2.addActionListener(this);
btn3.addActionListener(this);
btn4.addActionListener(this);

add(can = new Canvas(){
@Override
public void paint(Graphics g) {
g.setColor(color);
g.fillOval(x, y, size, size);
}
@Override
public void update(Graphics g) {
paint(g);
}
});
// 마우스의 동작을 감지하는 감지자.
can.addMouseMotionListener(new MouseMotionAdapter() {
@Override
public void mouseDragged(MouseEvent e) {
x = e.getX();
y = e.getY();
// 마우스가 움직일때마다 repaint 를 호출해서 jvm 에게전달하면 update =>paint
can.repaint();
}
});

add(jp1, "West");
add(jp2, BorderLayout.EAST);
setBounds(200, 20, 800, 600);
setVisible(true);
}

public static void main(String[] args) {
}

@Override
public void actionPerformed(ActionEvent e) {
Object obj = e.getSource();
if(obj == btn2){
color = Color.red;
}else if(obj == btn3){
color = Color.BLUE;
}else if(obj == btn4){
color = Color.YELLOW;
}

}
}