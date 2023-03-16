package util;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;

public class ButtonColumnCellRenderer extends DefaultTableCellRenderer {
  private String buttonType;

  public ButtonColumnCellRenderer(String buttonType) {
    this.buttonType = buttonType;
  }

  public String getButtonType() {
    return this.buttonType;
  }

  public void setButtonType(String buttonType) {
    this.buttonType = buttonType;
  }

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
      int row, int col) {
    JLabel label;
    label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setIcon(new ImageIcon(getClass().getResource("/" + buttonType + ".png")));
    return label;
  }
}
