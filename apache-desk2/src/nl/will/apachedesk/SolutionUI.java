package nl.will.apachedesk;

import java.util.Date;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

public class SolutionUI extends VerticalLayout{
	
	public SolutionUI(){
		Button button = new Button("www.nu.nl " + new Date());
		button.setStyleName(BaseTheme.BUTTON_LINK);
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Notification.show("Detail panel:", null, Type.TRAY_NOTIFICATION);

				final DetailWindow window = new DetailWindow(button.getCaption(), ApacheDesk2UI.LOREM_IPSUM);

				window.setModal(true);
				window.center();
				UI.getCurrent().addWindow(window);
			}
		});

		this.addComponent(button);

		final VerticalLayout contentLayout = new VerticalLayout();
		contentLayout.setMargin(true);
		contentLayout.addComponent(new Label(ApacheDesk2UI.LOREM_IPSUM, ContentMode.HTML));
		Panel contentPanel = new Panel();
		contentPanel.setContent(contentLayout);

		this.addComponent(contentPanel);
	}

}
