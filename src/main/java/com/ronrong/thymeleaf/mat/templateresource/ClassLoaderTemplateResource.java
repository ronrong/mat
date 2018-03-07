package com.ronrong.thymeleaf.mat.templateresource;


import com.ronrong.thymeleaf.mat.util.StringUtils;
import com.ronrong.thymeleaf.mat.util.Validate;

import java.io.*;

/**
 * <p>
 *   {@link IDecorationResource}的实现，表示由一个{@link ClassLoader}（即居于<em>class path</em>）访问的资源。
 * </p>
 */
public final class ClassLoaderTemplateResource extends AbstractTemplateResource {


    private final ClassLoader classLoader;
    private final String path;
    private final String characterEncoding;



    public ClassLoaderTemplateResource(final ClassLoader classLoader, final String path, final String characterEncoding) {

        super();

        Validate.notNull(classLoader, "Class Loader cannot be null");
        Validate.notEmpty(path, "Resource Path cannot be null or empty");
        // Character encoding CAN be null (system default will be used)

        this.classLoader = classLoader;
        final String cleanPath = TemplateResourceUtils.cleanPath(path);
        this.path = (cleanPath.charAt(0) == '/' ? cleanPath.substring(1) : cleanPath);
        this.characterEncoding = characterEncoding;

    }




    public String getDescription() {
        return this.path;
    }




    public String getBaseName() {
        return TemplateResourceUtils.computeBaseName(this.path);
    }



    public Reader reader() throws IOException {

        final InputStream inputStream = this.classLoader.getResourceAsStream(this.path);
        if (inputStream == null) {
            throw new FileNotFoundException(String.format("ClassLoader resource \"%s\" does not exist", this.path));
        }

        if (!StringUtils.isEmptyOrWhitespace(this.characterEncoding)) {
            return new BufferedReader(new InputStreamReader(new BufferedInputStream(inputStream), this.characterEncoding));
        }

        return new BufferedReader(new InputStreamReader(new BufferedInputStream(inputStream)));

    }




    public IDecorationResource relative(final String relativeLocation) {

        Validate.notEmpty(relativeLocation, "Relative Path cannot be null or empty");

        final String fullRelativeLocation = TemplateResourceUtils.computeRelativeLocation(this.path, relativeLocation);
        return new ClassLoaderTemplateResource(this.classLoader, fullRelativeLocation, this.characterEncoding);

    }




    public boolean exists() {
        return (this.classLoader.getResource(this.path) != null);
    }



}
