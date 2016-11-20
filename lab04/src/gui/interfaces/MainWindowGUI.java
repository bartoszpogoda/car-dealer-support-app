package gui.interfaces;

import javax.swing.JDialog;

public interface MainWindowGUI {
	public void switchToFilterView();

	public void switchToAddCarView();

	public void reportError(String message);

	public void update();

	public JDialog createModalDialog();
}
