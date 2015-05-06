package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class ImageImpl extends Image {

    public ImageImpl(String filename) {

        super(filename);
    }

    @Override
    public void remove() {

        BlurpStore.modelRepository.removeImage(this);
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
