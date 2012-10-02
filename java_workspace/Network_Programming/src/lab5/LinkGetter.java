package lab5;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

public class LinkGetter extends HTMLEditorKit.ParserCallback {
	private String baseURL;
	private Monitor urls;
	
	public LinkGetter(Monitor mon) {
		urls = mon;
	}

	public void handleStartTag(HTML.Tag tag, MutableAttributeSet a, int position) {
		if (tag == HTML.Tag.A) {
			String href = (String) a.getAttribute(HTML.Attribute.HREF);
			if (href != null) {
				try {
					String afterMod = new URL(new URL(baseURL), href).toString();
					if (afterMod.startsWith("mailto:")) {
						urls.addMail(afterMod);
					} else {
						urls.addLink(afterMod);
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
		}
		if (tag == HTML.Tag.FRAME) {
			String href = (String) a.getAttribute(HTML.Attribute.SRC);
			System.out.println("Frame: " + href);
		}
	}

	public void handleSimpleTag(HTML.Tag tag,	MutableAttributeSet a, int pos) {
		if (tag == HTML.Tag.BASE) {
			String href = (String) a.getAttribute(HTML.Attribute.HREF);
			baseURL = href;
		}
	}

}
