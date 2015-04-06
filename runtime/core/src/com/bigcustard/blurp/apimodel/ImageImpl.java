package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class ImageImpl extends Image {

    private ApiModelRepository apiModelRepository;

    public ImageImpl(String filename, ApiModelRepository apiModelRepository) {

        super(filename);
        this.apiModelRepository = apiModelRepository;
    }

    @Override
    public void remove() {

        apiModelRepository.removeImage(this);
    }

    @Override
    public boolean equals(Object other) {

        return this == other || (other instanceof ImageImpl && filename.equals(((ImageImpl) other).filename));
    }

    @Override
    public int hashCode() {

        return filename.hashCode();
    }
}
