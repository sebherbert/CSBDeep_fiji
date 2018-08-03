package mpicbg.csbd.network;

import org.junit.Test;

import net.imagej.ImageJ;

public class TensorflowTest {

    @Test
    public void testTensorflowService() {
        ImageJ ij = new ImageJ();
        ij.ui().setHeadless(true);
        ij.command().run(TensorflowCommand.class, false);
    }

}
