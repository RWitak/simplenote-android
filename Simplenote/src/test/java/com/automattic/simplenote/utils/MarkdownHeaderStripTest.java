package com.automattic.simplenote.utils;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.automattic.simplenote.utils.MarkdownHeaderSignStripper.stripHeaderSigns;
import static org.junit.Assert.assertEquals;

public class MarkdownHeaderStripTest {
    @Test
    public void testMarkdownHeaderRegexPattern() {
        String headerMarkdown = "#Title\n\n##Subtitle\n### H3\n####H4\n##### H5";

        Pattern p = Pattern.compile(
                MarkdownHeaderSignStripper.MD_HEADER_SIGNS_REGEX,
                Pattern.MULTILINE);
        Matcher m = p.matcher(headerMarkdown);

        int count = 0;
        while (m.find()) {
            count++;
        }

        assertEquals(5, count);
    }

    @Test
    public void testMarkdownHeaderStripped() {
        String headerMarkdown = "#Title\n\n##Subtitle\n### H3\n####H4\n##### H5";
        String stripped = stripHeaderSigns(headerMarkdown);

        assertEquals("Title\n\nSubtitle\nH3\nH4\nH5", stripped);
    }

    @Test
    public void testOnlyHeaderSignsStripped() {
        String mixedMarkdown =
                "#no sign\n\n## #sign\n### two##signs \nsigns #1 ##2## # spaced #";
        String stripped = stripHeaderSigns(mixedMarkdown);

        assertEquals("no sign\n\n#sign\ntwo##signs \nsigns #1 ##2## # spaced #", stripped);
    }

    @Test
    public void testNullOrEmptyReturnsEmpty() {
        assertEquals("", stripHeaderSigns(null));
        assertEquals("", stripHeaderSigns(""));
    }
}
