package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;
import org.junit.*;
import org.mockito.*;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ImageImplTest {

    @Mock private ApiModelRepository mockModelRepository;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void removeRemovesImageFromModelRepository() {

        ImageImpl testCandidate = new ImageImpl("abc", mockModelRepository);
        testCandidate.remove();
        Mockito.verify(mockModelRepository).removeImage(testCandidate);
    }

    @Test
    public void equalsHashCodeWorkOnFilename() {

        Image image1 = new ImageImpl("abc", mockModelRepository);
        Image image2 = new ImageImpl("abc", mockModelRepository);

        assertThat(image1, equalTo(image1));
        assertThat(image1, equalTo(image2));
        assertThat(image2, equalTo(image1));
        assertThat(image1.hashCode(), is(image2.hashCode()));

        Image image3 = new ImageImpl("def", mockModelRepository);
        assertThat(image1, not(equalTo(image3)));
        assertThat(image1.hashCode(), not(image3.hashCode()));
    }
}
