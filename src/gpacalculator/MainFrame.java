package gpacalculator;

import com.mysql.jdbc.PreparedStatement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

public class MainFrame extends javax.swing.JFrame {

    String url = "jdbc:mysql://localhost:3306/gpaCalculator";
    String username = "root";
    String password = "";
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String result;
    double no_Credit;
    public static JLabel image;
    // public static JComboBox combo;
    public static JComboBox com;

    public MainFrame() {
        initComponents();
        comboCourseName();
        getcombo();
//--------------------------------------------------------------------------
     /*   try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
*/
//--------------------------------------------------------------------------
    }
    //ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> clone = new ArrayList<String>();  // to remember the selectiong courses 
    ArrayList<Double> noOfCredit = new ArrayList<>(); // to get list of credits for selecting courses 
    ArrayList<Double> usersResult = new ArrayList<>(); // to get user earned credit values (from the results they got)

    private void getcombo() {
        comboCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                String outcome = comboCourse.getSelectedItem().toString();
                createLabels(outcome);
            }
        });
    }

    private void createLabels(String s) {

        try {
            con = (Connection) DriverManager.getConnection(url, username, password);
            String query = "SELECT courseName,credits FROM courses WHERE courseID ='" + s + "'";
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                result = rs.getString("courseName");
                no_Credit = rs.getInt("credits");
                System.out.println(result + "  inside while result");
                System.out.println(no_Credit + "  inside while no_Credit");
                noOfCredit.add(no_Credit);
                clone.add(result);
                showGUI(result);
            }
        } catch (Exception e) {
        }

        System.out.println("noOfCredit" + noOfCredit);
    }

    private void showGUI(String result) {

        System.out.println("s" + result);
        image = new JLabel(result);
        image.setFont(new Font("Serif", Font.PLAIN, 15));
        image.setBorder(new EmptyBorder(0, 0, 0, 0));
        jPanel2.add(image);
        jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.Y_AXIS));
        pack();

    }

    JComboBox[] combos2 = new JComboBox[clone.size()];

    private void createCombo() {  //only initiate when click DONE button
        JComboBox[] combos = new JComboBox[clone.size()];
        System.out.println("clone size " + clone.size());
        for (int i = 0; i < clone.size(); i++) {
            combos[i] = new JComboBox() {
                @Override
                public Dimension getMaximumSize() {
                    Dimension max = super.getMaximumSize();
                    max.height = getPreferredSize().height;
                    return max;
                }
            };
            combos[i].addItem(new ComboItem("A+", 4.25));
            combos[i].addItem(new ComboItem("A", 4));
            combos[i].addItem(new ComboItem("A-", 3.75));
            combos[i].addItem(new ComboItem("B+", 3.25));
            combos[i].addItem(new ComboItem("B", 3));
            combos[i].addItem(new ComboItem("B-", 2.75));
            combos[i].addItem(new ComboItem("C+", 2.25));
            combos[i].addItem(new ComboItem("C", 2));
            combos[i].addItem(new ComboItem("C-", 1.75));
            combos[i].addItem(new ComboItem("D+", 1.25));
            combos[i].addItem(new ComboItem("D", 1));
            combos[i].addItem(new ComboItem("D-", 0.75));
            combos[i].addItem(new ComboItem("E", 0.00));

            jPanel3.add(combos[i]);
            jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.Y_AXIS));
            pack();
        }
        combos2 = combos;
    }

    private void giveYourResults() {
        com.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Object item = com.getSelectedItem();
                double value = ((ComboItem) item).getValue();
                usersResult.add(value);
                System.out.println("--------------------------------------" + value);
            }
        });
        System.out.println("userresults ---- " + usersResult);
    }

    class ComboItem {

        private String key;
        private double value;

        public ComboItem(String key, double value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key;
        }

        public String getKey() {
            return key;
        }

        public double getValue() {
            return value;
        }
    }
//---------------------------------------------------------------------------------

    void comboCourseName() {

        try {
            con = (Connection) DriverManager.getConnection(url, username, password);
            String query = "SELECT courseID FROM courses";
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                String combCourseName = rs.getString("courseID");
                this.comboCourse.addItem(combCourseName);
            }
        } catch (Exception e) {
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        comboCourse = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jBtnDone = new javax.swing.JButton();
        jBtnCalcu = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        JLblGPA = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GPA Calculator V.1");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("GPA Calculator"));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Your Result ");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Course Name");

        jButton1.setText("Add Courses");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 157, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );

        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jBtnDone.setText("Done");
        jBtnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDoneActionPerformed(evt);
            }
        });

        jBtnCalcu.setText("Calculate");
        jBtnCalcu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCalcuActionPerformed(evt);
            }
        });

        jLabel3.setText("Select Course :-");

        JLblGPA.setFont(new java.awt.Font("Tempus Sans ITC", 1, 28)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 2, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Your GPA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(151, 151, 151)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(JLblGPA, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBtnCalcu))))
                        .addGap(23, 23, 23))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(jBtnDone)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(comboCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jBtnCalcu)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JLblGPA, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jBtnDone)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        NewCourse nc = new NewCourse();
        nc.setVisible(true);
        nc.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jBtnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDoneActionPerformed
        createCombo();
    }//GEN-LAST:event_jBtnDoneActionPerformed

    private void jBtnCalcuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCalcuActionPerformed
        Calculation cal = new Calculation();
        for (int i = 0; i < clone.size(); i++) {
            Object item = combos2[i].getSelectedItem();
            double value = ((ComboItem) item).getValue();
            usersResult.add(value);                             //userResults should return to calculate the GPA
            System.out.println("--------------------------------------" + value);
            System.out.println("No of credit///// " + noOfCredit);
            System.out.println("userResul///// " + usersResult);

        }
        cal.multiple(noOfCredit, usersResult);
        usersResult.clear();
        JLblGPA.setText(cal.gpavalue);

    }//GEN-LAST:event_jBtnCalcuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
              /* if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName()); 
                    break;
                }*/ UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLblGPA;
    private javax.swing.JComboBox<String> comboCourse;
    private javax.swing.JButton jBtnCalcu;
    private javax.swing.JButton jBtnDone;
    private javax.swing.JButton jButton1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

}
