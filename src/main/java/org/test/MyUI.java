package org.test;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        MenuBar menuBar = new MenuBar();
        menuBar.addItem("Import", (item) -> {
            JavaScript.eval("document.getElementById('package-upload-button').elements[1].click();");
        });

        Upload upload = new Upload(null, (file, mime) -> null);
        upload.setImmediate(true);
        upload.setButtonCaption("Upload");
        upload.setDescription("Upload");
        upload.setId("package-upload-button");
        upload.addStyleName("package-upload-button");

        HorizontalLayout div1 = new HorizontalLayout();
        div1.setMargin(true);
        div1.addComponent(menuBar);

        HorizontalLayout div2 = new HorizontalLayout();
        div2.setMargin(true);
        div2.addComponent(upload);

        layout.setMargin(true);
        layout.addComponents(
            new Label("Click the menubar item below to open file selection dialog via Javascript.\n" +
                    "Only works in IE/Edge at the moment :(\n\n" +
                    "Copy&pasting this to the console also works in Chrome:\n" +
                    "document.getElementById('package-upload-button').elements[1].click();", ContentMode.PREFORMATTED),
            div1,
            div2
        );
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
