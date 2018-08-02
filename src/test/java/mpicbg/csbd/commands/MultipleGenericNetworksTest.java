
package mpicbg.csbd.commands;

import mpicbg.csbd.CSBDeepTest;
import net.imagej.Dataset;
import net.imagej.axis.Axes;
import net.imagej.axis.AxisType;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.integer.ByteType;
import net.imglib2.type.numeric.integer.UnsignedIntType;
import net.imglib2.type.numeric.real.FloatType;
import org.junit.Test;
import org.scijava.command.CommandModule;
import org.scijava.module.Module;

import java.io.File;
import java.util.List;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MultipleGenericNetworksTest extends CSBDeepTest {

	@Test
	public void testMultipleGenericNetworks() {

		launchImageJ();

		String[] networks = {"/home/random/Development/imagej/project/CSBDeep/tests/generic_test2/denoise2D/model.zip",
				"/home/random/Development/imagej/project/CSBDeep/tests/generic_test2/denoise3D/model.zip"};

		for (int i = 0; i < 3; i++) {

			final Dataset input = createDataset(new FloatType(), new long[] { 10, 10, 10 }, new AxisType[] {
					Axes.X, Axes.Y, Axes.Z });

			for(String networkSrc : networks) {
				final Future<CommandModule> future = ij.command().run(GenericNetwork.class,
						false,
						"input", input,
						"modelFile", new File(networkSrc),
						"overlap", 2);
				assertFalse("Plugin future is null", future == null);
				final Module module = ij.module().waitFor(future);
				List<Dataset> result = (List<Dataset>) module.getOutput("output");
				assertEquals(1, result.size());
				final Dataset output = result.get(0);
				testResultAxesAndSize(input, output);
			}

			System.out.println(i);
		}

	}

}
