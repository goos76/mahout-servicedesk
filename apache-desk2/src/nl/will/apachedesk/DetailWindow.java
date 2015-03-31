package nl.will.apachedesk;

import com.vaadin.server.ClassResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class DetailWindow extends Window {

	public DetailWindow(String title, String content) {
		super("<b>" + title + "</b>");
		setCaptionAsHtml(true);
		setWidth(50.0f, Unit.PERCENTAGE);
		VerticalLayout windowLayout = new VerticalLayout();

		final VerticalLayout contentLayout = new VerticalLayout();
		contentLayout.setMargin(true);
		contentLayout.addComponent(new Label(content));
		Panel contentPanel = new Panel();
		contentPanel.setContent(contentLayout);

		windowLayout.addComponent(contentLayout);

		GridLayout buttonLayout = new GridLayout(4, 1);
		buttonLayout.setWidth(100f, Unit.PERCENTAGE);
		buttonLayout.setMargin(true);
		Label questionLabel = new Label("Heeft dit 't probleem opgelost?");
		buttonLayout.addComponent(questionLabel, 0, 0);

		Button yesButton = new Button();
		yesButton.setHeight(60f, Unit.PIXELS);
		yesButton.setIcon(new ClassResource("yes.png"));
		yesButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Notification.show("Ja:", null, Type.TRAY_NOTIFICATION);
				close();

			}
		});
		buttonLayout.addComponent(yesButton, 1, 0);

		Button noButton = new Button();
		noButton.setHeight(60f, Unit.PIXELS);
		noButton.setIcon(new ClassResource("no.png"));
		noButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Notification.show("No:", null, Type.TRAY_NOTIFICATION);
				close();
			}
		});
		buttonLayout.addComponent(noButton, 2, 0);

		Button incidentButton = new Button();
		incidentButton.setCaption("meld incident");
		incidentButton.setHeight(60f, Unit.PIXELS);
		// incidentButton.setIcon(new ClassResource("yes.png"));
		incidentButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Notification.show("Incident:", null, Type.TRAY_NOTIFICATION);
				//close();

			}
		});
		buttonLayout.addComponent(incidentButton, 3, 0);
		windowLayout.addComponent(buttonLayout);

		setContent(windowLayout);
	}

}
