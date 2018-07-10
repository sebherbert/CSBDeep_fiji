package mpicbg.csbd.tiling.task;

import java.util.List;

import mpicbg.csbd.task.Task;
import net.imagej.axis.AxisType;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.real.FloatType;

import mpicbg.csbd.tiling.AdvancedTiledView;
import mpicbg.csbd.tiling.Tiling;

public interface OutputTiler< T extends RealType< T >> extends Task {

	public List< RandomAccessibleInterval< T > > run(
			List< AdvancedTiledView< T > > input,
			Tiling tiling,
			AxisType[] axisTypes );

}
