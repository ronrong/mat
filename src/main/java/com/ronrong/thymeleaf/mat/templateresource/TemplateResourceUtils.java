package com.ronrong.thymeleaf.mat.templateresource;


import com.ronrong.thymeleaf.mat.util.StringUtils;

public final class TemplateResourceUtils {



    public static String cleanPath(final String path) {

        if (path == null) {
            return null;
        }

        String unixPath = StringUtils.replace(path, "\\", "/");

        if (unixPath.length() == 0 || (unixPath.indexOf("/.") < 0 && unixPath.indexOf("//") < 0)) {
            return unixPath;
        }

        boolean rootBased = (unixPath.charAt(0) == '/');
        unixPath = (rootBased? unixPath : ('/' + unixPath));

        final StringBuilder strBuilder = new StringBuilder(unixPath.length());

        int index = unixPath.lastIndexOf('/');
        int pos = unixPath.length() - 1;
        int topCount = 0;
        while (index >= 0) {

            final int tokenLen = pos - index;

            if (tokenLen > 0) {

                if (tokenLen == 1 && unixPath.charAt(index + 1) == '.') {
                } else if (tokenLen == 2 && unixPath.charAt(index + 1) == '.' && unixPath.charAt(index + 2) == '.') {
                    topCount++;
                } else if (topCount > 0){
                    topCount--;
                } else {
                    strBuilder.insert(0, unixPath, index, (index + tokenLen + 1));
                }

            }

            pos = index - 1;
            index = (pos >= 0? unixPath.lastIndexOf('/', pos) : -1);

        }

        for (int i = 0; i < topCount; i++) {
            strBuilder.insert(0, "/..");
        }

        if (!rootBased) {
            strBuilder.deleteCharAt(0);
        }

        return strBuilder.toString();
    }




    public static String computeRelativeLocation(final String location, final String relativeLocation) {
        final int separatorPos = location.lastIndexOf('/');
        if (separatorPos != -1) {
            final StringBuilder relativeBuilder = new StringBuilder(location.length() + relativeLocation.length());
            relativeBuilder.append(location, 0, separatorPos);
            if (relativeLocation.charAt(0) != '/') {
                relativeBuilder.append('/');
            }
            relativeBuilder.append(relativeLocation);
            return relativeBuilder.toString();
        }
        return relativeLocation;
    }




    public static String computeBaseName(final String path) {

        if (path == null || path.length() == 0) {
            return null;
        }

        final String basePath = (path.charAt(path.length() - 1) == '/'? path.substring(0,path.length() - 1) : path);

        final int slashPos = basePath.lastIndexOf('/');
        if (slashPos != -1) {
            final int dotPos = basePath.lastIndexOf('.');
            if (dotPos != -1 && dotPos > slashPos + 1) {
                return basePath.substring(slashPos + 1, dotPos);
            }
            return basePath.substring(slashPos + 1);
        } else {
            final int dotPos = basePath.lastIndexOf('.');
            if (dotPos != -1) {
                return basePath.substring(0, dotPos);
            }
        }

        return (basePath.length() > 0? basePath : null);

    }





    private TemplateResourceUtils() {
        super();
    }

}
