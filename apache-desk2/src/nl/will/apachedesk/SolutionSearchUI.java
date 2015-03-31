package nl.will.apachedesk;

import java.util.Date;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.ClassResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

public class SolutionSearchUI extends VerticalLayout {

	public SolutionSearchUI() {

		this.setMargin(true);
		this.setWidth(75f, Unit.PERCENTAGE);

		final Label titleLabel = new Label();
		titleLabel.setIcon(new ClassResource("apache.jpg"));
		titleLabel.setCaption("<p>apache-desk </p>");
		titleLabel.setCaptionAsHtml(true);

		this.addComponent(titleLabel);

		TextArea textArea = new TextArea();
		textArea.setCaption("Wat is het probleem?");
		textArea.setCaptionAsHtml(true);

		textArea.setValue("Wat is het probleem?");
		textArea.setRows(5);
		textArea.setImmediate(true);
		textArea.setSizeFull();

		textArea.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(final ValueChangeEvent event) {
				final String valueString = String.valueOf(event.getProperty().getValue());
				Notification.show("Value changed:", valueString, Type.TRAY_NOTIFICATION);
			}
		});
		this.addComponent(textArea);
		Button searchResultsButton = new Button("Zoek");
		this.addComponent(searchResultsButton);

		VerticalLayout searchResults = new VerticalLayout();
		this.addComponent(searchResults);
		final Label searchResultsLabel = new Label();
		searchResultsLabel.setCaption("Zoekresultaten");
		searchResultsLabel.setCaptionAsHtml(true);
		searchResults.addComponent(searchResultsLabel);
		searchResultsButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				searchResults.removeAllComponents();
				for (int i = 0; i < 3; i++) {
					Component searchResult = createSearchResult();
					searchResults.addComponent(searchResult);

				}
			}
		});
	}

	private VerticalLayout createSearchResult() {
		VerticalLayout searchResult = new VerticalLayout();

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

		searchResult.addComponent(button);

		final VerticalLayout contentLayout = new VerticalLayout();
		contentLayout.setMargin(true);
		contentLayout.addComponent(new Label(ApacheDesk2UI.LOREM_IPSUM, ContentMode.HTML));
		Panel contentPanel = new Panel();
		contentPanel.setContent(contentLayout);

		searchResult.addComponent(contentPanel);

		return searchResult;
	}

}
