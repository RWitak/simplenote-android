package com.automattic.simplenote.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkdownHeaderSignStripper {
    public static String MD_HEADER_SIGNS_REGEX = "^[#]+[ ]?";

    public static String stripHeaderSigns(String mdText) {
        if (mdText == null || mdText.isEmpty()) {
            return "";
        }

        Pattern p = Pattern.compile(MD_HEADER_SIGNS_REGEX, Pattern.MULTILINE);
        Matcher m = p.matcher(mdText);

        return m.replaceAll("");
    }
}
