package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class ImageImpl extends Image {

    private ModelRepository modelRepository;

    public ImageImpl(String filename, ModelRepository modelRepository) {

        super(filename);
        this.modelRepository = modelRepository;
    }

    @Override
    public void remove() {

        modelRepository.removeImage(this);
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
