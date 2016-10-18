package biz.hirte.swt.controls;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;

/**
 * DatePicker renders a Textbox in readonly modus and a small Button (18x18) next to the Textbox on the right hand side.
 * The Button's Background is a Image that shows a calendar. A click on the Button will bring up a small Calendar with
 * the current date set. The users can pick a data. The chosen date is then propagated to the internal state 
 * of this Component and directly shown in the Textbox. 
 * 
 * @author hirte
 *
 */
public class DatePicker extends CustomComponent<LocalDate> {

	private Text txtDate;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public DatePicker(Composite parent, int style) {
		super(LocalDate.now(), parent, style);
		setLayout(new FormLayout());

		txtDate = new Text(this, SWT.BORDER);
		Button button = new Button(this, SWT.NONE);

		FormData fd_text = new FormData();
		fd_text.bottom = new FormAttachment(0, 21);
		fd_text.top = new FormAttachment(0);
		fd_text.left = new FormAttachment(0);
		fd_text.right = new FormAttachment(button, -2);
		txtDate.setLayoutData(fd_text);
		txtDate.setEditable(false);

		FormData fd_button = new FormData();
		fd_button.width = 18;
		fd_button.height = 18;
		fd_button.right = new FormAttachment(100);
		fd_button.top = new FormAttachment(0, 2);
		button.setLayoutData(fd_button);
		button.setSelection(true);
		button.setImage(ResourceManager.getPluginImage("biz.hirte.swt.controls", "icon/calendar.png"));
		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				DatePickerDialog dlg = new DatePickerDialog(getShell(), SWT.NONE, getModel());
				dlg.centerAround(e.display.getCursorLocation());

				setModel(dlg.open());

				notifyValueChangeListener(getModel());

				updateText();
			}
		});
	}

	public LocalDate getDate() {
		return getModel();
	}

	public void setDate(LocalDate ld) {
		setModel(ld);
		updateText();
	}

	private void updateText() {

		if (this.model == null) {

			this.txtDate.setText("");

		} else {

			this.txtDate.setText(DateTimeFormatter.ISO_DATE.format(this.model));

		}

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
