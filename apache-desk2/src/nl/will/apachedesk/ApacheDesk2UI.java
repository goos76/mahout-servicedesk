package nl.will.apachedesk;

import java.util.Date;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.ClassResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
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

@SuppressWarnings("serial")
@Theme("apache_desk2")
public class ApacheDesk2UI extends UI {

	private static final String LOREM_IPSUM = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged..";

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = ApacheDesk2UI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setWidth(75f, Unit.PERCENTAGE);
		setContent(layout);

		final Label titleLabel = new Label();
		titleLabel.setIcon(new ClassResource("apache.jpg"));
		titleLabel.setCaption("<p>apache-desk </p>");
		titleLabel.setCaptionAsHtml(true);

		layout.addComponent(titleLabel);

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
		layout.addComponent(textArea);
		Button searchResultsButton = new Button("Zoek");
		layout.addComponent(searchResultsButton);

		VerticalLayout searchResults = new VerticalLayout();
		layout.addComponent(searchResults);
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

				final DetailWindow window = new DetailWindow(button.getCaption(), LOREM_IPSUM);

				window.setModal(true);
				window.center();
				UI.getCurrent().addWindow(window);
			}
		});

		searchResult.addComponent(button);

		final VerticalLayout contentLayout = new VerticalLayout();
		contentLayout.setMargin(true);
		contentLayout.addComponent(new Label(LOREM_IPSUM, ContentMode.HTML));
		Panel contentPanel = new Panel();
		contentPanel.setContent(contentLayout);

		searchResult.addComponent(contentPanel);

		return searchResult;
	}
}