package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Task;

/**
 *
 * @author Alberto Araújo
 */
public class TaskTableModel extends AbstractTableModel {

  String[] columns = { "Nome", "Descrição", "Prazo", "Tarefa Concluída", "Editar", "Excluir" };
  List<Task> tasks = new ArrayList();

  @Override
  public int getColumnCount() {
    return columns.length;
  }

  @Override
  public int getRowCount() {
    return tasks.size();
  }

  @Override
  public String getColumnName(int columnIndex) {
    return columns[columnIndex];
  };

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    switch (columnIndex) {
      case 0:
        return tasks.get(rowIndex).getName();
      case 1:
        return tasks.get(rowIndex).getDescription();
      case 2:
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(tasks.get(rowIndex).getDeadline());
      case 3:
        return tasks.get(rowIndex).getCompleted();
      case 4:
        return "";
      case 5:
        return "";
      default:
        return "Dados não encotrados";
    }
  }

  public String[] getColumns() {
    return this.columns;
  }

  public List<Task> getTasks() {
    return this.tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

}
