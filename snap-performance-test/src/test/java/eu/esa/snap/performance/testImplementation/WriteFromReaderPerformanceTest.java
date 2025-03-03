package eu.esa.snap.performance.testImplementation;

import eu.esa.snap.performance.actions.*;
import eu.esa.snap.performance.util.Parameters;
import eu.esa.snap.performance.util.TestUtils;

import java.util.List;
import java.util.logging.Logger;

public class WriteFromReaderPerformanceTest extends PerformanceTest {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public WriteFromReaderPerformanceTest(String testName, Parameters parameters) {
        super(testName, parameters);
    }

    @Override
    public void execute() throws Throwable {
        logger.info("Execution of " + getTestName() + " started....");

        String productName = getParameters().getProducts().get(0);
        String testDataDir = getParameters().getTestDir();

        String outputDir = getParameters().getOutputDir();
        String productNameWithoutExtension = TestUtils.cutExtensionFromFileName(productName);
        String fullOutputDir = TestUtils.createOutputDir(outputDir, getTestName(), productNameWithoutExtension, getParameters().getThreading());

        List<String> outputFormats = getParameters().getOutputFormats();
        String format1 = outputFormats.get(0);
        String format2 = outputFormats.get(1);

        TestUtils.setFlavour(getParameters().isUseZip());

        String threading = getParameters().getThreading();
        Action baseAction1 = new WriteAction(null, fullOutputDir, format1, threading);
        Action baseAction2 = new WriteAction(null, fullOutputDir, format2, threading);

        Action measurementActions1 = TestUtils.constructMeasurementActionsPipeline(baseAction1, getParameters());
        Action measurementActions2 = TestUtils.constructMeasurementActionsPipeline(baseAction2, getParameters());

        Action readBeforeExecutionAction1 = new ReadBeforeExecutionAction(measurementActions1, productName, testDataDir);
        Action readBeforeExecutionAction2 = new ReadBeforeExecutionAction(measurementActions2, productName, testDataDir);

        Action multipleExecutions1 = new MultipleExecutionsAction(readBeforeExecutionAction1, getParameters());
        Action multipleExecutions2 = new MultipleExecutionsAction(readBeforeExecutionAction2, getParameters());

        multipleExecutions1.execute();
        multipleExecutions2.execute();

        setResult1(multipleExecutions1.fetchResults());
        setResult2(multipleExecutions2.fetchResults());

        logger.info("Execution of " + getTestName() + " finished :)");
    }

}
