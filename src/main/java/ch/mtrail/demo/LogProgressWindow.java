package ch.mtrail.demo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class LogProgressWindow {

	private Text text;

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		final Display display = Display.getDefault();
		final Shell shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(2, false));

		final GridData gd_text = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		text = new Text(shell, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		text.setLayoutData(gd_text);
		

		final Button btnStart = new Button(shell, SWT.NONE);
		btnStart.setText("Start");
		btnStart.addListener(SWT.Selection, (Event e) -> {
			if (e.type == SWT.Selection) {
				System.out.println("Button A pressed");
			}
		});

		final Button btnClearLog = new Button(shell, SWT.NONE);
		btnClearLog.setText("Clear Log");
		btnClearLog.addListener(SWT.Selection, (Event e) -> {
			if (e.type == SWT.Selection) {
				text.setText("");
			}
		});

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public void appendText(String line) {
		Display.getDefault().syncExec(() -> {
			text.setText((text.getText() + "\r\n" + line).trim());
		});
	}
}
