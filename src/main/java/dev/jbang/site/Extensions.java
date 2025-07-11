package dev.jbang.site;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.quarkiverse.qute.web.markdown.runtime.MdConverter;
import io.quarkus.arc.Arc;
import io.quarkus.qute.TemplateExtension;

@TemplateExtension
public class Extensions {

    public static String format(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }


/*
    public static String asciidoc(String rawText) {
        AsciidoctorJConverter converter = Arc.container().beanInstanceSupplier(AsciidoctorJConverter.class).get().get();

        return converter.apply(rawText.replaceAll("\\{[#/]asciidoc}", ""));
    }
*/

    public static String markdownify(String rawText) {
        MdConverter converter = Arc.container().beanInstanceSupplier(MdConverter.class).get().get();
        return converter.html(rawText);
    }
}
