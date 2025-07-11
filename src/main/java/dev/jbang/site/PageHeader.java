package dev.jbang.site;

import io.quarkiverse.roq.frontmatter.runtime.model.Page;
import io.quarkus.qute.TemplateExtension;
import io.vertx.core.json.JsonObject;

@TemplateExtension
public class PageHeader {
    public String overlay_color;
    public String overlay_filter;
    public String overlay_image;
    public String image_description;
    public boolean has_overlay;

    @Override
    public String toString() {
        return "PageHeader{" +
            "overlay_color='" + overlay_color + '\'' +
            ", overlay_filter='" + overlay_filter + '\'' +
            ", overlay_image='" + overlay_image + '\'' +
            ", image_description='" + image_description + '\'' +
            '}';
    }

    public PageHeader(Page page) {
        var data = page.data();

        image_description = page.title();

        if (data.containsKey("header")) {
            JsonObject header = data.getJsonObject("header");
            overlay_color = header.getString("overlay_color", null);
            overlay_filter = header.getString("overlay_filter", null);
            overlay_image = header.getString("overlay_image", null);
            image_description = header.getString("image_description", image_description); // Keep page title default

            if (overlay_filter != null) {
                if (overlay_filter.contains("rgba")) {
                    overlay_filter = "linear-gradient(" + overlay_filter + ", " + overlay_filter + ")";
                } else {
                    var temp = "rgba(0, 0, 0, " + overlay_filter + ")";
                    overlay_filter = "linear-gradient(" + temp +", " + temp +")";
                }
            }
        }

        has_overlay = overlay_image != null || overlay_color != null;
    }

    public static PageHeader headerOf(Page page) {
        return new PageHeader(page);
    }
}
