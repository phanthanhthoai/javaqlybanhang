/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import Util.dialog;
import com.mysql.cj.jdbc.Blob;
import java.awt.Component;
import java.awt.Image;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Admin
 */
public class SanPhamBUS {

    ArrayList<SanPhamDTO> listSp = null;
    public SanPhamDAO spDAO = new SanPhamDAO();
    DefaultTableModel model;    
    private JTable xxx;
    int idSp;

    public boolean themSp(String ten, String mota, InputStream hinhanh) {
        ten = ten.trim();
        SanPhamDTO sp = new SanPhamDTO();
        sp.setName(ten);
        sp.setDescription(mota);
        boolean kq = spDAO.themSp(sp, hinhanh);
        if (kq) {
            new dialog("Cập nhập thành công!", dialog.SUCCESS_DIALOG);
        } else {
            new dialog("Cập nhập thất bại!", dialog.ERROR_DIALOG);
        }
        return kq;
    }

    public boolean suaSp(int id, String ten, String mota, InputStream hinhanh, int checkImage) {
        ten = ten.trim();
        SanPhamDTO sp = new SanPhamDTO();
        sp.setId(id);
        sp.setName(ten);
       
        sp.setDescription(mota);
        boolean kq = spDAO.suaSp(sp, hinhanh, checkImage);
        if (kq) {
            new dialog("Sửa thành công!", dialog.SUCCESS_DIALOG);
        } else {
            new dialog("Sửa thất bại!", dialog.ERROR_DIALOG);
        }
        return kq;
    }

    public SanPhamDTO laySpId(int id) {
        return spDAO.laySpId(id);
    }
    public ArrayList<SanPhamDTO> laydanhsachsp(String search){
        return spDAO.layDsSp(search);
    }
    public void layDsSp(JTable tbl, String search) {
        listSp = spDAO.layDsSp(search);
        model = new DefaultTableModel(
                new String[]{
                    "Mã SP", "Tên SP", "Mô tả", "Hình Ảnh", "Trạng thái"
                },
                0
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 4) {
                    return Boolean.class;
                }
                return String.class;
            }
        };
        xxx = tbl;
        tbl.setModel(model);
        model.setRowCount(0);

        for (SanPhamDTO sp : listSp) {
            Blob blob = sp.getImage();
            byte[] blobBytes = null;
            try {
                blobBytes = blob.getBytes(1, (int) blob.length());
            } catch (SQLException ex) {
                Logger.getLogger(SanPhamBUS.class.getName()).log(Level.SEVERE, null, ex);
            }
            ImageIcon icon = new ImageIcon(blobBytes);
            model.addRow(new Object[]{
                sp.getId(), 
                sp.getName(),
                sp.getDescription(),
                icon,
                sp.getDeleted() == 0
            });
        }

        tbl.setRowHeight(80);
        tbl.getColumn("Hình Ảnh").setCellRenderer(new myTableCellRenderer());
        tbl.getColumn("Trạng thái").setCellRenderer(new CheckboxRenderer());
        tbl.getColumn("Trạng thái").setCellEditor(new CheckboxEditor(xxx, spDAO));
    }

    static class CheckboxEditor extends AbstractCellEditor implements TableCellEditor {

        private final JCheckBox checkBox;
        private int currentRow;
        private JTable table;
        private final SanPhamDAO spDAOx;

        public CheckboxEditor(JTable tbl, SanPhamDAO spDAO) {
            this.spDAOx = spDAO;
            checkBox = new JCheckBox();
            this.table = tbl;
            checkBox.setHorizontalAlignment(SwingConstants.CENTER);

            checkBox.addActionListener(e -> {

                int confirm = JOptionPane.showConfirmDialog(
                        table,
                        "Bạn có muốn thay đổi trạng thái?",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    stopCellEditing();
                    int row = tbl.getSelectedRow();
                    boolean newValue = (boolean) tbl.getValueAt(row, 4);
                    int del = 1;
                    if (newValue == false) {
                        del = 0;
                    }
                    if (tbl != null) {
                        Object idSpValue = tbl.getValueAt(currentRow, 0);
                        if (idSpValue != null) {
                            try {
                                int idSpC = Integer.parseInt(idSpValue.toString());
                                boolean isUpdated = spDAO.xoaSp(idSpC, del);
                                if (isUpdated) {
                                    tbl.setValueAt(newValue, currentRow, 4);
                                    new dialog("Cập nhật trạng thái thành công!", dialog.SUCCESS_DIALOG);
                                } else {
                                    new dialog("Không thể cập nhật trạng thái sản phẩm!", dialog.ERROR_DIALOG);
                                }
                                System.out.println("Checkbox clicked at row: " + currentRow);
                                System.out.println("New value: " + newValue);
                                System.out.println("ID sản phẩm: " + idSpC);

                            } catch (NumberFormatException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
                
            });
        }

        @Override
        public Object getCellEditorValue() {
            return checkBox.isSelected();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.table = table;
            this.currentRow = row;

            if (value instanceof Boolean) {
                checkBox.setSelected((Boolean) value);
            }

            if (isSelected) {
                checkBox.setBackground(table.getSelectionBackground());
            } else {
                checkBox.setBackground(table.getBackground());
            }
            return checkBox;
        }
    }

    static class CheckboxRenderer extends JCheckBox implements TableCellRenderer {

        public CheckboxRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof Boolean) {
                setSelected((Boolean) value);
            }

            if (isSelected) {
                setBackground(table.getSelectionBackground());
            } else {
                setBackground(table.getBackground());
            }

            return this;
        }
    }

    class myTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof ImageIcon) {
                JLabel label = new JLabel();
                label.setHorizontalAlignment(JLabel.CENTER);

                // Lấy Image từ ImageIcon
                ImageIcon icon = (ImageIcon) value;
                Image img = icon.getImage();
                Image scaledImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(scaledImg));

                return label;
            } else if (value instanceof Blob) {
                try {
                    Blob blob = (Blob) value;
                    byte[] blobBytes = blob.getBytes(1, (int) blob.length());
                    ImageIcon icon = resizeImage(blobBytes);
                    JLabel label = new JLabel(icon);
                    table.setRowHeight(row, 80);
                    return label;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }

    public void xoaSP(int id, int del) {
        spDAO.xoaSp(id, del);
    }

    public void layIDsp(int id) {
        this.idSp = id;
    }

    ImageIcon resizeImage(byte[] blobBytes) {
        ImageIcon icon = new ImageIcon(blobBytes);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }
}
