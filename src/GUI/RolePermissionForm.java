/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Admin
 */
public class RolePermissionForm extends javax.swing.JFrame {

    /**
     * Creates new form RolePermissionForm
     */
//    public RolePermissionForm() {
//        initComponents();
//    }
    private JList<String> roleList;
    private DefaultListModel<String> roleListModel;
    private JCheckBox chkView, chkAdd, chkEdit, chkDelete;
    private JButton btnSave;

    public RolePermissionForm() {
        setTitle("Phân quyền người dùng");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Danh sách vai trò
        roleListModel = new DefaultListModel<>();
        roleListModel.addElement("Nhân viên");
        roleListModel.addElement("Quản lý");
        roleListModel.addElement("Admin");
        roleList = new JList<>(roleListModel);
        roleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(roleList), BorderLayout.WEST);

        // Danh sách quyền
        JPanel permissionPanel = new JPanel(new GridLayout(5, 1));
        chkView = new JCheckBox("Xem danh sách nhân viên");
        chkAdd = new JCheckBox("Thêm nhân viên");
        chkEdit = new JCheckBox("Sửa nhân viên");
        chkDelete = new JCheckBox("Xóa nhân viên");
        permissionPanel.add(chkView);
        permissionPanel.add(chkAdd);
        permissionPanel.add(chkEdit);
        permissionPanel.add(chkDelete);
        add(permissionPanel, BorderLayout.CENTER);

        // Nút lưu
        btnSave = new JButton("Lưu thay đổi");
        add(btnSave, BorderLayout.SOUTH);

        // Xử lý sự kiện lưu quyền
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRole = roleList.getSelectedValue();
                if (selectedRole == null) {
                    JOptionPane.showMessageDialog(null, "Chọn một vai trò trước!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(null, "Đã cập nhật quyền cho vai trò: " + selectedRole);
            }
        });

        setVisible(true);
    }
    public static void main(String[] args) {
        new RolePermissionForm();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(RolePermissionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(RolePermissionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(RolePermissionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(RolePermissionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new RolePermissionForm().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
