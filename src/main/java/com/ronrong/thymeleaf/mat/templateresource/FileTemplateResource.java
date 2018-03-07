package com.ronrong.thymeleaf.mat.templateresource;



import com.ronrong.thymeleaf.mat.util.StringUtils;
import com.ronrong.thymeleaf.mat.util.Validate;

import java.io.*;

/**
 * <p>
 *   表示文件系统中文件的{@link IDecorationResource}的实现。
 * </p>
 */
public final class FileTemplateResource extends AbstractTemplateResource implements Serializable {


    private final String path;
    private final File file;
    private final String characterEncoding;



    public FileTemplateResource(final String path, final String characterEncoding) {

        super();

        Validate.notEmpty(path, "Resource Path cannot be null or empty");
        // Character encoding CAN be null (system default will be used)

        this.path = TemplateResourceUtils.cleanPath(path);
        this.file = new File(path);
        this.characterEncoding = characterEncoding;

    }


    public FileTemplateResource(final File file, final String characterEncoding) {

        super();

        Validate.notNull(file, "Resource File cannot be null");
        // Character encoding CAN be null (system default will be used)

        this.path = TemplateResourceUtils.cleanPath(file.getPath());
        this.file = file;
        this.characterEncoding = characterEncoding;

    }




    public String getDescription() {
        return this.file.getAbsolutePath();
    }




    public String getBaseName() {
        return TemplateResourceUtils.computeBaseName(this.path);
    }


    public Reader reader() throws IOException {

        final InputStream inputStream = new FileInputStream(this.file);

        if (!StringUtils.isEmptyOrWhitespace(this.characterEncoding)) {
            return new BufferedReader(new InputStreamReader(new BufferedInputStream(inputStream), this.characterEncoding));
        }

        return new BufferedReader(new InputStreamReader(new BufferedInputStream(inputStream)));

    }

    public IDecorationResource relative(final String relativeLocation) {

        Validate.notEmpty(relativeLocation, "Relative Path cannot be null or empty");

        final String fullRelativeLocation = TemplateResourceUtils.computeRelativeLocation(this.path, relativeLocation);
        return new FileTemplateResource(fullRelativeLocation, this.characterEncoding);

    }

    public boolean exists() {
        return this.file.exists();
    }



}
